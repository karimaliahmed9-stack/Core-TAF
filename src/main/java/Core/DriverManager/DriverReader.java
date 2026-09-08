package Core.DriverManager;

import org.openqa.selenium.WebDriver;

//أي Class يعمل implements DriverReader لازم يوفر طريقة اسمها getDriver() ترجع WebDriver.
public interface DriverReader {
     WebDriver getDriver();
}
