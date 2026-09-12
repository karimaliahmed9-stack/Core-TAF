package ConfigureTest;

import Core.DataReaderManager.JsonReader;
import Core.DriverManager.DriverReader;
import Core.DriverManager.GUIDriver;
import org.openqa.selenium.WebDriver;

public class BaseTest implements DriverReader {
    protected GUIDriver driver;
    protected JsonReader jsonReader;
    @Override
    public WebDriver getDriver() {
        return driver.getDriver();
    }



    public void BeforeClass()
    {
        jsonReader = new JsonReader("DataForTestCore_TAF");
    }



}
