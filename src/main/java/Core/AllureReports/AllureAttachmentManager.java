package Core.AllureReports;

import Core.AttachementsManager.ScreenRecordManager;
import Core.DataReaderManager.PropertyReader;
import Core.LogManager.LogManager;
import io.qameta.allure.Allure;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Path;


public class AllureAttachmentManager {
    //attachment -> 1- screenshot , 2-  screen record , 3- logs
    public static void AttachmentScreenshot(String name, String path) {
        try {
            Path screenshot = Path.of(path);
            if (Files.exists(screenshot))
                Allure.addAttachment(name, "image/png", Files.newInputStream(screenshot), ".png");
            else {
                LogManager.Info("Screenshot not found.", path);
            }
        } catch (Exception e) {
            LogManager.Error("Error Attaching screenshot" + e.getMessage());
        }
    }

    public static void AttachmentScreenRecord(String testmethodName) {
        if (PropertyReader.getProperty("RecordTests").equalsIgnoreCase("true")) {
            try {
                File recordDer = new File(ScreenRecordManager.RECORDINGS_PATHE + testmethodName);
                if (recordDer != null && recordDer.getName().endsWith(".mp4")) {
                    Allure.addAttachment(testmethodName, "video/mp4", Files.newInputStream(recordDer.toPath()),
                            ".mp4");
                }
            } catch (Exception e) {
                LogManager.Error("Error Attaching screen record" + e.getMessage());
            }
        }
    }

    //
    public static void AttachmentLogs() {
        try {

            File logfile = new File(
                    LogManager.LOG_PATH + File.separator + "Logs.log"
            );

            if (logfile.exists()) {
                Allure.addAttachment(
                        "Logs.log",
                        "text/plain",
                        Files.newInputStream(logfile.toPath()),
                        ".log"
                );
            }
        } catch (Exception e) {
            LogManager.Error("Error Attaching logs" + e.getMessage());
        }
    }


}
