package Core.Actions;

import Core.LogManager.LogManager;
import Core.WaitManager.WaitManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class FrameActions {

    private final WebDriver driver;
    private final WaitManager waitManager;

    public FrameActions(WebDriver driver) {
        this.driver = driver;
        this.waitManager = new WaitManager(driver);
    }

    //Switch to a Frame by index

    public void switchToFrameByIndex(int index) {
        waitManager.getFluentWait().until(driver1 -> {
            try {
                driver1.switchTo().frame(index);
                return true;
            } catch (Exception e) {
                LogManager.Error("Failed to switch to frame by index: " + e.getMessage());
                return false;
            }
        });
    }

    //Switch to a Frame by Name
    public void switchToFrameByName(String name) {
        waitManager.getFluentWait().until(driver1 -> {
            try {
                driver1.switchTo().frame(name);
                return true;
            } catch (Exception e) {
                LogManager.Error("Failed to switch to frame by name: " + e.getMessage());
                return false;
            }
        });
    }

    //Switch to a Frame by WebElement

    public void switchToFrameByWebElement(By frameElement) {
        waitManager.getFluentWait().until(driver1 -> {
            try {
                driver1.switchTo().frame(driver.findElement(frameElement));
                return true;
            } catch (Exception e) {
                LogManager.Error("Failed to switch to frame by WebElement: " + e.getMessage());
                return false;
            }
        });
    }

    //Switch to a frame by Default Content
    public void switchToDefaultContent() {
        waitManager.getFluentWait().until(driver1 -> {
            try {
                driver1.switchTo().defaultContent();
                return true;
            } catch (Exception e) {
                LogManager.Error("Failed to switch to default content: " + e.getMessage());
                return false;
            }
        });

    }
}
