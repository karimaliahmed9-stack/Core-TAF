package Core.AllureReports;

import TAF.Utils.DataReaderManager.PropertyReader;
import TAF.Utils.LogManager.LogManager;
import TAF.Utils.ScreenShots.ScreenRecordManager;
import io.qameta.allure.Allure;
import org.apache.logging.log4j.core.LoggerContext;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Path;


public class AllureAttachementManager {
    //attachment -> 1- screenshot , 2-  screen record , 3- logs
    public static void Attahmentscreenshot(String name, String path) {
        try {
            Path screenshot = Path.of(path);
            if (Files.exists(screenshot))
                Allure.addAttachment(name, Files.newInputStream(screenshot));
            else {
                LogManager.Info("Screenshot not found.", path);
            }
        } catch (Exception e) {
            LogManager.Error("Error Attaching screenshot" + e.getMessage());
        }
    }

    public static void Attahmentscreenrecord(String testmethodName, String path) {
        if (PropertyReader.getProperty("RecordTests").equalsIgnoreCase("true")) {

            try {
                File recordDer = new File(ScreenRecordManager.RECORDINGS_PATHE + testmethodName);
                if (recordDer != null &&recordDer.getName().endsWith(".mp4")) {
                    Allure.addAttachment(testmethodName, "video/mp4", Files.newInputStream(recordDer.toPath()),
                            ".mp4");
                }
            } catch (Exception e) {
                LogManager.Error("Error Attaching screen record" + e.getMessage());
            }
        }
    }

    public static void Attahmentlogs() {
        try {

            org.apache.logging.log4j.LogManager.shutdown();
            File logfile = new File(LogManager.LOGS_PATH + File.separator + "Logs.logKarim");
            ((LoggerContext) org.apache.logging.log4j.LogManager.getContext(false)).reconfigure();
            if (logfile.exists())
                Allure.addAttachment("Logs.logKarim", Files.readString(logfile.toPath()));
        } catch (Exception e) {
            LogManager.Error("Error Attaching logs file" + e.getMessage());
        }
    }


}
