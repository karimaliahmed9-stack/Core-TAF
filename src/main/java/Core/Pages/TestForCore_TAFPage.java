package Core.Pages;

import Core.DataReaderManager.PropertyReader;
import Core.DriverManager.GUIDriver;
import io.qameta.allure.Step;

public class TestForCore_TAFPage {
    private final GUIDriver driver;
    public TestForCore_TAFPage(GUIDriver driver) {
        this.driver = driver;
    }

    //Locator



    //Action on Locator

    @Step("Navigate to Base URL")
    public TestForCore_TAFPage Navigation()
    {
        driver.browsersActions().Navigation(PropertyReader.getProperty("BaseUrl"));
        return this;
    }

    //Validations



}
