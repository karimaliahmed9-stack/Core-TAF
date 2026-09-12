package Core.Actions;

import Core.LogManager.LogManager;
import Core.WaitManager.WaitManager;
import org.openqa.selenium.WebDriver;

public class BrowsersActions {
    WebDriver driver;
    private final WaitManager waitManager;

    public BrowsersActions(WebDriver driver) {
        this.driver = driver;
        this.waitManager = new WaitManager(driver);
    }

    //Navigation
    public void Navigation(String url) {
        waitManager.getFluentWait().until(driver1 -> {
            try {
                driver1.navigate().to(url);
                LogManager.Info("Navigating to: " + url);
                return true;
            } catch (Exception e) {
                LogManager.Error("Navigation is False" + e);
                return false;
            }
        });

    }

    //Get Url
    public String GetUrl() {
        waitManager.getFluentWait().until(driver1 -> {
            try {
                String url = driver1.getCurrentUrl();
                LogManager.Info("Getting Current URL: " + url);
                return true;
            } catch (Exception e) {
                LogManager.Error("Getting URL is False" + e);
                return false;
            }
        });
        LogManager.Info("Getting Current URL: " + driver.getCurrentUrl());
        return driver.getCurrentUrl();
    }

    //Get Title
    public String GetTitle() {
        waitManager.getFluentWait().until(driver1 -> {
            try {
                String title = driver1.getTitle();
                LogManager.Info("Getting Current Title: " + title);
                return true;
            } catch (Exception e) {
                LogManager.Error("Getting Title is False" + e);
                return false;
            }
        });
        LogManager.Info("Getting Current Title: " + driver.getTitle());
        return driver.getTitle();
    }

    //Get Window Handel
    public String GetWindowHandle() {
        LogManager.Info("Getting Current Window Handle: " + driver.getWindowHandle());
        return driver.getWindowHandle();
    }

    //Maximize Browser
    public void MaximizeBrowser() {
        LogManager.Info("Maximizing Browser Window");
        driver.manage().window().maximize();
    }

    //Close Tap
    public void CloseBrowser() {
        LogManager.Info("Closing Tap");
        driver.close();
    }

    //Quit Browser
    public void QuitBrowser() {
        LogManager.Info("Closing Browser");
        driver.quit();
    }

    //Forward
    public void Forward() {
        LogManager.Info("Navigating Forward");
        driver.navigate().forward();
    }

    //Backward
    public void Backward() {
        LogManager.Info("Navigating Backward");
        driver.navigate().back();
    }

    //Refresh
    public void Refresh() {
        LogManager.Info("Refreshing Browser");
        driver.navigate().refresh();
    }

    //Switch
    public void SwitchToWindow(String windowHandle) {
        LogManager.Info("Switching to Window: " + windowHandle);
        driver.switchTo().window(windowHandle);
    }


}
