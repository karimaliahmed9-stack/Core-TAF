package Core.Assertions;

import Core.Actions.BrowsersActions;
import Core.Actions.ElementActions;
import Core.WaitManager.WaitManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public abstract class BaseAssert {
    protected WebDriver driver;
    protected WaitManager waitManager;
    protected ElementActions elementAction;
    protected BrowsersActions browsersActions;

    protected BaseAssert() {
    }

    protected BaseAssert(WebDriver driver) {
        this.driver = driver;
        this.waitManager = new WaitManager(driver);
        this.elementAction = new ElementActions(driver);
        this.browsersActions = new BrowsersActions(driver);
    }


    protected abstract void assertTrue(boolean condition, String message);

    protected abstract void assertFalse(boolean condition, String message);

    protected abstract void assertEquals(String actual, String expected, String message);


    public void Equals(String actual, String Expected, String message) {
        assertEquals(actual, Expected, message);
    }

    public void isElementVisible(By locator) {
        boolean flag = waitManager.getFluentWait().until(driver ->
        {
            try {
                assertTrue(driver.findElement(locator).isDisplayed(), "Element is not visible " + locator);
                return true;
            } catch (Exception e) {
                return false;
            }
        });
    }

    public void assertPageUrl(String expectedUrl) {
        String actualUrl = browsersActions.GetUrl();
        assertEquals(actualUrl, expectedUrl, "URL does not match. Expected: " + expectedUrl + ", Actual: " + actualUrl);
    }

    public void assertPageTitle(String expectedTitle) {
        String actualTitle = browsersActions.GetTitle();
        assertEquals(expectedTitle, actualTitle, "Title does not match. Expected: " + expectedTitle + ", Actual: " + actualTitle);
    }

    //validate the element not visible on the page
    public void isElementNotVisible(By locator) {
        boolean flag = waitManager.getFluentWait().until(driver ->
        {
            try {
                assertFalse(driver.findElement(locator).isDisplayed(), "Element is visible " + locator);
                return true;
            } catch (Exception e) {
                return true;
            }
        });
    }

}







