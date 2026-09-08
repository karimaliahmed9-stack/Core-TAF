package Core.FileUtilsManager;

import Core.DataReaderManager.PropertyReader;
import Core.LogManager.LogManager;
import org.apache.commons.io.FileUtils;

import java.io.File;

public class FileUtil {
    private static final String UserDir = PropertyReader.getProperty("user.dir") + File.separator;

    public FileUtil() {
        super();
    }
    //Cleaning Directory
    public static void cleaningDirectory(File directory) {

        try {

            if (!directory.exists()) {
                directory.mkdirs();

                LogManager.Info("Directory created: " + directory.getAbsolutePath());

                return;
            }

            FileUtils.cleanDirectory(directory);

            LogManager.Info("Directory cleaned successfully: " + directory.getAbsolutePath());

        } catch (Exception e) {

            LogManager.Error("Failed to clean directory: " + directory.getAbsolutePath(), e.getMessage()
            );
        }
    }


    public static void CleaningWithForce(File file) {
        try {
            FileUtils.forceDelete(file);
            LogManager.Info("File deleted successfully with force: " + file.getAbsolutePath());

        } catch (Exception e) {
            LogManager.Error("Failed to clean directory :" + e.getMessage());
        }
    }

    //Creating Directory
    public static void CreatingDirectory(String path) {
        try {

            File file = new File(UserDir + path);
            if (!file.exists()) {
                file.mkdirs();
                LogManager.Info("Directory created:", path);
            }
        } catch (Exception e) {
            LogManager.Error("Failed to create Directory:" + e.getMessage());
        }
    }
}
