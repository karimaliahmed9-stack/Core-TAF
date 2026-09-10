package Core.AllureReports;

import Core.LogManager.LogManager;
import io.qameta.allure.Allure;
import org.apache.logging.log4j.core.LoggerContext;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Path;


public class AllureAttachmentManager {
    //attachment -> 1- screenshot , 2-  screen record , 3- logs
    public static void AttachmentScreenshot(String name, String path) {
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

//    public static void AttachmentScreenRecord(String testmethodName) {
//        if (PropertyReader.getProperty("RecordTests").equalsIgnoreCase("true")) {
//            try {
//                File recordDer = new File(ScreenRecordManager.RECORDINGS_PATHE + testmethodName);
//                if (recordDer != null &&recordDer.getName().endsWith(".mp4")) {
//                    Allure.addAttachment(testmethodName, "video/mp4", Files.newInputStream(recordDer.toPath()),
//                            ".mp4");
//                }
//            } catch (Exception e) {
//                LogManager.Error("Error Attaching screen record" + e.getMessage());
//            }
//        }
//    }

    public static void AttachmentLogs() {
        try {

            org.apache.logging.log4j.LogManager.shutdown();
            File logfile = new File(LogManager.LOG_PATH + File.separator + "Logs.log");
            ((LoggerContext) org.apache.logging.log4j.LogManager.getContext(false)).reconfigure();
            if (logfile.exists())
                Allure.addAttachment("Logs.log", Files.readString(logfile.toPath()));
        } catch (Exception e) {
            LogManager.Error("Error Attaching logs file" + e.getMessage());
        }
    }


}
