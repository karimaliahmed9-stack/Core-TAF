package Core.DriverManager;

import Core.Actions.Alerts;
import Core.Actions.BrowsersActions;
import Core.Actions.ElementActions;
import Core.Assertions.HardAssert;
import Core.Assertions.SoftAssert;
import Core.DataReaderManager.PropertyReader;
import Core.LogManager.LogManager;
import Core.WaitManager.WaitManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ThreadGuard;

public class GUIDriver {
    //هقرا اسم البراوزر من system property (property Reader.getProperty)
    private final String Browser = PropertyReader.getProperty("browserType");
    private ThreadLocal<WebDriver> ThreadLocaldriver = new ThreadLocal<>();

    public GUIDriver() {
        LogManager.Info("Browser Type is :" + Browser);
        //هنا من كلاس الاينم هختار اي بروزر وهحولو ل uppercase
        //كدا شغلنا المصنع
        Browsers browserType = Browsers.valueOf(Browser.toUpperCase());
        //هنا بقي بنده علي المصنع نفسو من الاينم بقي

        AbstractDriver abstractDriver = browserType.GetDriverFactory();
        //عشان لو استخدمتو في اكثر من thread يعرفني

        WebDriver driver = ThreadGuard.protect(abstractDriver.CreatDriver());
        ThreadLocaldriver.set(driver);

    }

    public WebDriver getDriver() {
        return ThreadLocaldriver.get();
    }

    public void quitDriver() {

        ThreadLocaldriver.get().quit();
    }


    public ElementActions elementActions() {
        return new ElementActions(getDriver());
    }

    public BrowsersActions browsersActions() {
        return new BrowsersActions(getDriver());
    }

    public Alerts alerts() {
        return new Alerts(getDriver());
    }

    public HardAssert hardAssert() {
        return new HardAssert(getDriver());
    }

    public SoftAssert softAssert() {
        return new SoftAssert(getDriver());
    }

    public WaitManager waitManager() {
        return new WaitManager(getDriver());
    }
}
