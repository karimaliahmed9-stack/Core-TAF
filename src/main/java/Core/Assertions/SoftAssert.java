package Core.Assertions;

import Core.LogManager.LogManager;
import org.openqa.selenium.WebDriver;

public class SoftAssert extends BaseAssert {
    private static boolean used = false;
    private static org.testng.asserts.SoftAssert softAssert = new org.testng.asserts.SoftAssert();

    public SoftAssert() {
        super();
    }

    public SoftAssert(WebDriver driver) {
        super(driver);
    }

    @Override
    protected void assertTrue(boolean condition, String message) {
        used = true;
        softAssert.assertTrue(condition, message);
        LogManager.Error("Assertion failed: " + message);
    }

    @Override
    protected void assertFalse(boolean condition, String message) {
        used = true;
        softAssert.assertFalse(condition, message);
        LogManager.Error("Assertion failed: " + message);
    }

    @Override
    protected void assertEquals(String expected, String actual, String message) {
        used = true;
        softAssert.assertEquals(expected, actual, message);
        LogManager.Error("Assertion failed: " + message);
    }


    public static void AssertAll() {
        if (!used) return;
        try {
            softAssert.assertAll();
        } catch (AssertionError e) {
            LogManager.Error("Assertion failed" + e.getMessage());
            throw e;
        } finally {
            softAssert = new org.testng.asserts.SoftAssert(); // Reset the soft assert instance
        }
    }
}
