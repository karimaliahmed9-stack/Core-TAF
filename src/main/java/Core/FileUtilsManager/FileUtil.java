package Core.FileUtilsManager;

import Core.DataReaderManager.PropertyReader;
import Core.LogManager.LogManager;
import org.apache.commons.io.FileUtils;

import java.io.File;

import static org.apache.commons.io.FileUtils.copyFile;

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

    //Renaming
    public static void RenamingFile(String oldname, String newName) {

        try {
            File oldfile = new File(UserDir + oldname);
            File newfile = new File(UserDir + newName);
            if (oldfile.renameTo(newfile)) {
                LogManager.Info(("File Renamed from: " + oldname + ", File was renamed to  " + newName + "."));
            } else {
                LogManager.Info(("Failed To Rename File From" + oldname + ", already has the desired name " + newName + "."));
            }
        } catch (Exception e) {
            LogManager.Error("Error rename file :" + e.getMessage());
        }
    }

    //Copy Directories
    public static void copyDirectory(String source, String destination) {

        try {

            File sourceDir = new File(source);
            File destinationDir = new File(destination);

            if (!sourceDir.exists()) {

                LogManager.Info("Source directory does not exist: " + sourceDir.getAbsolutePath());

                return;
            }

            FileUtils.copyDirectory(sourceDir, destinationDir);

            LogManager.Info("Directory copied successfully from: " + sourceDir.getAbsolutePath() + " to: "
                    + destinationDir.getAbsolutePath());

        } catch (Exception e) {

            LogManager.Error("Failed to copy directory: " + e.getMessage());
        }
    }
}
