package Core.AttachementsManager;

import Core.LogManager.LogManager;
import Core.TimeManager.TimeManager;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;

import java.io.File;

public class ScreenShootsManager {

    public static final String screenshoot_path = "Test-Output/ScreenShoots/";


    //Take full page screen shoot
    public static void TakeFullScreenShots(WebDriver driver, String screenshootName, String name) {
        try {
            File screenshootSrc = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
            File screenshootFile = new File(screenshoot_path + screenshootName + name + "-" + TimeManager.GetCurrentTime() + ".png");
            FileUtils.copyFile(screenshootSrc, screenshootFile);

            // attch the screenshot to allure if needed
            //AllureAttachementManager.Attahmentscreenshot(screenshootName, screenshootFile.getAbsolutePath());


            LogManager.Info("Capture screenshot succeeded");
        } catch (Exception e) {
            LogManager.Error("Failed to capture ScreenShots :", e.getMessage());
        }
    }
}
