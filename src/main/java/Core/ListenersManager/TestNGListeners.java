package Core.ListenersManager;

import Core.AttachementsManager.ScreenShootsManager;
import Core.DataReaderManager.PropertyReader;
import Core.FileUtilsManager.FileUtil;
import Core.LogManager.LogManager;
import org.testng.*;

import java.io.File;

public class TestNGListeners implements IExecutionListener, IInvokedMethodListener, ITestListener {
    public TestNGListeners() {
        LogManager.Info("TestNGListeners Initialized : .....");
    }

    public void onExecutionStart() {
        LogManager.Info("Test Execution Started");
        CleaningTestOutputDirectory();
        LogManager.Info("Cleaning directors: ");
        CreatingDirectory();
        LogManager.Info("Creating directors: ...");
        //كدا نا بنادي علي الفايلات من الريسورسز
        PropertyReader.loadproperties();
        LogManager.Info("Properties loaded");
    }

    public void onExecutionFinish() {
        LogManager.Info("Starting Test Execution Finished: ... ");
        LogManager.Info("Test Execution Finished: ... ");
    }

    public void beforeInvocation(IInvokedMethod method, ITestResult testResult) {

    }

    public void afterInvocation(IInvokedMethod method, ITestResult testResult) {

    }

    public void onTestSuccess(ITestResult result) {
        LogManager.Info("Test Case Passed: " + result.getName() + "Passed");
    }

    public void onTestFailure(ITestResult result) {
        LogManager.Info("Test Case Failed: " + result.getName() + "Failed");
    }

    public void onTestSkipped(ITestResult result) {
        LogManager.Info("Test Case Skipped: " + result.getName() + "Skipped");
    }


    public static void CleaningTestOutputDirectory() {
        FileUtil.cleaningDirectory(new File(ScreenShootsManager.screenshoot_path));
        FileUtil.cleaningDirectory(new File(LogManager.LOG_PATH));
    }

    public void CreatingDirectory() {
        FileUtil.CreatingDirectory(ScreenShootsManager.screenshoot_path);
    }
}
