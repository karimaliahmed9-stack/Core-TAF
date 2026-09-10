package Core.AllureReports;

import TAF.Utils.FileHelp.FileUtil;
import TAF.Utils.LogManager.LogManager;
import TAF.Utils.OperatingSyatem.OSUtil;
import TAF.Utils.Terminal.Terminalutils;

import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

import static TAF.Utils.AllureReports.AllureConstant.*;
import static TAF.Utils.DataReaderManager.PropertyReader.getProperty;
import static TAF.Utils.TimeManager.TimeManager.GetCurrentTime;

public class AllureReportGenerator {
    //Generated allure report
    //--single-file - generate single file report
    public static void generateReports(boolean IssingleFile) {
        Path outputFolder = IssingleFile ? AllureConstant.REPORT_PATH : AllureConstant.FULL_REPORT_PATH;
        //allure generate -o report --single-file  --clean
        List<String> command = new ArrayList<>(List.of(
                AllureBinaryManager.getExcutable().toString(), "generate",
                AllureConstant.RESULTS_FOLDER.toString(), "-o",
                outputFolder.toString(), "--clean"
        ));
        if (IssingleFile) command.add("--single-file");
        Terminalutils.ExcuteTerminalaCommand(command.toArray(new String[0]));
    }


    //Open Allure report in the browser
    public static void openReport(String reportFileName) {
        //لو هرن لوكال افتح الريبورت
        if (!getProperty("ExcutionType").toLowerCase().contains("local")) return;
        Path reportpath = REPORT_PATH.resolve(reportFileName);
        switch (OSUtil.getcurrentos()) {
            case WINDOWS -> Terminalutils.ExcuteTerminalaCommand("cmd.exe", "/c", "start", reportpath.toString());
            case MAC, LINUX -> Terminalutils.ExcuteTerminalaCommand("open", reportpath.toString());
            default -> LogManager.Warn("Opening allure report is not supported on this OS. ");
        }

    }

    //Copy history folder to results folders
    public static void copyhistory() {
        try {
            FileUtil.copyDirectory(HISTORY_FOLDER.toString(), RESULTS_HISTORY_FOLDER.toString());
        } catch (Exception e) {
            LogManager.Error("Error copying history files" + e.getMessage());
        }
    }

    public static String renaming() {
        String newFileName = AllureConstant.REPORT_PREFIX + GetCurrentTime() + AllureConstant.REPORT_EXTENSION;
        FileUtil.RenamingFile(AllureConstant.REPORT_PATH.resolve(AllureConstant.INDEX_HTML).toString(), newFileName);
        return newFileName;
    }

}
