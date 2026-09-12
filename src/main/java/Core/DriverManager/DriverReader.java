package Core.DriverManager;

import org.openqa.selenium.WebDriver;

//أي Class يعمل implements DriverReader لازم يوفر طريقة اسمها getDriver() ترجع WebDriver.
// To call get driver from listeners and Base Test class
public interface DriverReader {
     WebDriver getDriver();
}
