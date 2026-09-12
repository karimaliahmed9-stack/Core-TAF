package Tests;

import ConfigureTest.BaseTest;
import Core.DriverManager.GUIDriver;
import Core.Pages.TestForCore_TAFPage;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class TestCore_TAFTC_001 extends BaseTest {


    //Test
    @Test
    public void TestCore_TAF() {

    }


    //Configurations

    @BeforeMethod
    public void Setup() {
        driver = new GUIDriver();
        new TestForCore_TAFPage(driver).Navigation();

    }

    @AfterMethod
    public void TearDown() {
        driver.quitDriver();
    }
}
