package Core.AllureReports;

import java.io.File;
import java.nio.file.Path;
import java.nio.file.Paths;

import static Core.DataReaderManager.PropertyReader.getProperty;

public class AllureConstants_Paths {
    //Path > static > final عشان محدش يقدر يوصلها ولا يتعامل معاها


    public static final Path USER_DIR = Paths.get(getProperty("user.dir"), File.separator);
    public static final Path USER_Home = Paths.get(getProperty("user.home"), File.separator);


    public static final Path RESULTS_FOLDER = Paths.get(String.valueOf(USER_DIR), "Test-Output", "Allure_Results", File.separator);
    public static final Path REPORT_PATH = Paths.get(String.valueOf(USER_DIR), "Test-Output", "Report", File.separator);
    public static final Path FULL_REPORT_PATH = Paths.get(String.valueOf(USER_DIR), "Test-Output", "Full_Report", File.separator);


    public static final Path HISTORY_FOLDER = Paths.get(FULL_REPORT_PATH.toString(), "history", File.separator);
    public static final Path RESULTS_HISTORY_FOLDER = Paths.get(RESULTS_FOLDER.toString(), "history", File.separator);


    public static final String INDEX_HTML = "index.html";
    public static final String REPORT_PREFIX = "AllureReport_";
    public static final String REPORT_EXTENSION = ".html";


    public static final String ALLURE_ZIP_BASE_URL = "https://repo.maven.apache.org/maven2/io/qameta/allure/allure-commandline/";
    public static final Path EXTRACTION_DIR = Paths.get(String.valueOf(USER_Home), ".m2/repository/allure", File.separator);


}
