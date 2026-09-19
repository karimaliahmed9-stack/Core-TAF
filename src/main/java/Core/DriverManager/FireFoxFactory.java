package Core.DriverManager;

import org.openqa.selenium.PageLoadStrategy;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;

public class FireFoxFactory extends AbstractDriver {

    public FirefoxOptions GetOption() {
        FirefoxOptions Option = new FirefoxOptions();
        Option.addArguments("--start-maximized");
        Option.addArguments("--disable-notifications");
        Option.addArguments("--disable-popup-blocking");
        Option.addArguments("--disable-infobars");
        Option.addArguments("--disable-extensions");
        Option.addArguments("--disable-gpu");
        Option.addArguments("--remote-allow-origins=*");
        Option.addArguments("--disable-dev-shm-usage");
        Option.addArguments("--ignore-certificate-errors");
        Option.addArguments("--allow-insecure-localhost");
        Option.setAcceptInsecureCerts(true);
        Option.setPageLoadStrategy(PageLoadStrategy.NORMAL);
        return Option;
    }

    @Override
    public WebDriver CreatDriver() {
        return new FirefoxDriver(GetOption());
    }
}
