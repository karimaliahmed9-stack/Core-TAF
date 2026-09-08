package Core.DriverManager;

import org.openqa.selenium.PageLoadStrategy;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

public class ChromeFactory extends AbstractDriver {

    public ChromeOptions GetOption() {
        ChromeOptions Option = new ChromeOptions();
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
        return new ChromeDriver(GetOption());
    }
}
