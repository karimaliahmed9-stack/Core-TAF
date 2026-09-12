package ConfigureTest;

import Core.DriverManager.DriverReader;
import Core.DriverManager.GUIDriver;
import org.openqa.selenium.WebDriver;

public class BaseTest implements DriverReader {
    protected GUIDriver driver;
    @Override
    public WebDriver getDriver() {
        return driver.getDriver();
    }
}
