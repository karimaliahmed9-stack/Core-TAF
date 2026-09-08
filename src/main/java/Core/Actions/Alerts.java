package Core.Actions;

import Core.LogManager.LogManager;
import Core.WaitManager.WaitManager;
import org.openqa.selenium.WebDriver;

public class Alerts {
    private final WebDriver driver;
    private final WaitManager waitManager;

    public Alerts(WebDriver driver) {
        this.driver = driver;
        this.waitManager = new WaitManager(driver);
    }

    //Alert Accept
    public void acceptAlert() {
        waitManager.getFluentWait().until(driver1 -> {
            try {
                LogManager.Info("Accepting alert");
                driver.switchTo().alert().accept();
                return true;
            } catch (Exception e) {
                LogManager.Error("Accepting alert is False" + e);
                return false;
            }
        });

    }

    //Alert Dismiss
    public void dismissAlert() {
        waitManager.getFluentWait().until(driver1 -> {
            try {
                LogManager.Info("Dismissing alert");
                driver.switchTo().alert().dismiss();
                return true;
            } catch (Exception e) {
                LogManager.Error("Dismissing alert is False" + e);
                return false;
            }
        });

    }

    //Alert Send Key
    public void sendKeysToAlert(String text) {
        waitManager.getFluentWait().until(driver1 -> {
            try {
                LogManager.Info("Sending keys to alert: " + text);
                driver.switchTo().alert().sendKeys(text);
                return true;
            } catch (Exception e) {
                LogManager.Error("Sending keys to alert is False" + e);
                return false;
            }
        });

    }

    //Alert Get Text
    public String getAlertText() {
        return waitManager.getFluentWait().until(driver1 -> {
            try {
                String text = driver.switchTo().alert().getText();
                LogManager.Info("Getting text from alert" + text);
                return !text.isEmpty() ? text : null;
            } catch (Exception e) {
                LogManager.Error("Getting text from alert is False" + e);
                return null;
            }
        });

    }
}
