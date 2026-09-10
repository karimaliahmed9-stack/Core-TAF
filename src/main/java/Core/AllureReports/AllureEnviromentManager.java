package Core.AllureReports;

import Core.DataReaderManager.PropertyReader;
import Core.LogManager.LogManager;
import com.google.common.collect.ImmutableMap;

import java.io.File;

import static com.github.automatedowl.tools.AllureEnvironmentWriter.allureEnvironmentWriter;

public class AllureEnviromentManager {
    public static void setAllureEnvironment() {
        allureEnvironmentWriter(
                ImmutableMap.<String, String>builder()
                        .put("OS", PropertyReader.getProperty("os.name"))
                        .put("java Version", PropertyReader.getProperty("java.runtime.version"))
                        .put("Browser", PropertyReader.getProperty("browserType"))
                        .put("Execution Type", PropertyReader.getProperty("ExecutionType"))
                        .put("URL", PropertyReader.getProperty("BaseUrl"))
                        .build(), (AllureConstants_Paths.RESULTS_FOLDER) + File.separator);
        LogManager.Info("Allure environment variable set .");
        try {
            AllureBinaryManager.DownloadAndExtract();
        } catch (Exception e) {
            LogManager.Error("Error to downloaded and extracted." + e.getMessage());
        }
    }
}








