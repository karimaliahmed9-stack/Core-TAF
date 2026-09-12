package Core.AllureReports;

import Core.FileUtilsManager.FileUtil;
import Core.LogManager.LogManager;
import Core.OSManager.OSUtil;
import Core.Terminal.TerminalUtils;
import org.apache.commons.io.FileUtils;

import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

import static Core.AllureReports.AllureConstants_Paths.*;
import static Core.DataReaderManager.PropertyReader.getProperty;
import static Core.TimeManager.TimeManager.GetCurrentTime;

public class AllureReportGenerator {
    //Generated allure report
    //--single-file - generate single file report
    public static void generateReports(boolean IssingleFile) {
        Path outputFolder = IssingleFile ? REPORT_PATH : AllureConstants_Paths.FULL_REPORT_PATH;
        //allure generate -o report --single-file  --clean
        List<String> command = new ArrayList<>(List.of(
                AllureBinaryManager.getExcutable().toString(), "generate",
                AllureConstants_Paths.RESULTS_FOLDER.toString(), "-o",
                outputFolder.toString(), "--clean"
        ));
        if (IssingleFile) command.add("--single-file");
        TerminalUtils.ExecuteTerminalCommand(command.toArray(new String[0]));
    }


    //Open Allure report in the browser
    public static void openReport(String reportFileName) {
        //لو هرن لوكال افتح الريبورت
        if (!getProperty("ExecutionType").toLowerCase().contains("local")) return;
        Path reportpath = REPORT_PATH.resolve(reportFileName);
        switch (OSUtil.getcurrentos()) {
            case WINDOWS -> TerminalUtils.ExecuteTerminalCommand("cmd.exe", "/c", "start", reportpath.toString());
            case MAC, LINUX -> TerminalUtils.ExecuteTerminalCommand("open", reportpath.toString());
            default -> LogManager.Warn("Opening allure report is not supported on this OS. ");
        }

    }

    //Copy history folder to results folders
    public static void Copy_history() {
        try {
            FileUtils.copyDirectory(HISTORY_FOLDER.toFile(), RESULTS_HISTORY_FOLDER.toFile());
        } catch (Exception e) {
            LogManager.Error("Error copying history files" + e.getMessage());
        }
    }

    public static String renaming() {
        // AllureReport2026109_123456.html
        String newFileName = AllureConstants_Paths.REPORT_PREFIX + GetCurrentTime() +
                AllureConstants_Paths.REPORT_EXTENSION;
        FileUtil.RenamingFile(REPORT_PATH.resolve(AllureConstants_Paths.INDEX_HTML).toString(),
                REPORT_PATH.resolve(newFileName).toString());
        return newFileName;
    }


}
