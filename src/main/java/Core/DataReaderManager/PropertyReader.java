package Core.DataReaderManager;

import Core.LogManager.LogManager;
import org.apache.commons.io.FileUtils;

import java.io.File;
import java.util.Collection;
import java.util.Properties;

public class PropertyReader {
    public static Properties loadproperties() {
        try {
            Properties properties = new Properties();
            Collection<File> propertiesFile;
            propertiesFile = FileUtils.listFiles(new File("src/main/resources/"), new String[]{"properties"}, true);
            propertiesFile.forEach(file -> {
                try {
                    properties.load(FileUtils.openInputStream(file));
                } catch (Exception e) {
                    LogManager.Error("Error loading properties from file: ", file.getName(), e.getMessage());
                }
                properties.putAll(System.getProperties());
                System.getProperties().putAll(properties);
            });
            return properties;
        } catch (Exception e) {
            LogManager.Error("Error loading properties: ", e.getMessage());
        }
        return null;
    }
    public static String getProperty(String key) {
        try {
            return System.getProperty(key);
        } catch (Exception e) {
            LogManager.Error("Error retrieving property: ", key, e.getMessage());
            return "";
        }
    }
}
