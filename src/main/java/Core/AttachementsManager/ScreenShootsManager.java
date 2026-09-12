package Core.AttachementsManager;

import Core.AllureReports.AllureAttachmentManager;
import Core.LogManager.LogManager;
import Core.TimeManager.TimeManager;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;

import java.io.File;

public class ScreenShootsManager {

    public static final String screenshoot_path = "Test-Output/ScreenShoots/";


    //Take full page screen shoot
    public static void TakeFullScreenShots(WebDriver driver, String screenshootName) {
        try {
            File screenshootSrc = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
            File screenshootFile = new File(screenshoot_path + screenshootName + "-" + TimeManager.GetCurrentTime() + ".png");
            FileUtils.copyFile(screenshootSrc, screenshootFile);

            // attachment the screenshot to allure if needed

            AllureAttachmentManager.AttachmentScreenshot(screenshootName, screenshootFile.getAbsolutePath());


            LogManager.Info("Capture screenshot succeeded");
        } catch (Exception e) {
            LogManager.Error("Failed to capture ScreenShots :", e.getMessage());
        }
    }


    //Take ScreenShoot for specific Element
    public static void TakeElementScreenshots(WebDriver driver, By elemetName) {
        try {
            String ariName = driver.findElement(elemetName).getAccessibleName();
            //Capture screenshot using Takescreenshoot from selenium
            File screenshootSrc = driver.findElement(elemetName).getScreenshotAs(OutputType.FILE);
            // Save screenshot to file
            File screenshootFile = new File(screenshoot_path + ariName + "-" + TimeManager.GetCurrentTime() + ".png");
            FileUtils.copyFile(screenshootSrc, screenshootFile);


            AllureAttachmentManager.AttachmentScreenshot(ariName, screenshootFile.getAbsolutePath());

            LogManager.Info("Capture screenshot succeeded");
        } catch (Exception e) {
            LogManager.Error("Failed to capture Element ScreenShots :", e.getMessage());
        }
    }
}
