package Core.DriverManager;

import org.openqa.selenium.PageLoadStrategy;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.safari.SafariDriver;
import org.openqa.selenium.safari.SafariOptions;

public class SafaryFactory extends AbstractDriver {


    public SafariOptions GetOption() {
        SafariOptions Option = new SafariOptions();

        Option.setAcceptInsecureCerts(true);
        Option.setPageLoadStrategy(PageLoadStrategy.NORMAL);
        return Option;
    }

    @Override
    public WebDriver CreatDriver() {
        return new SafariDriver(GetOption());
    }
}
