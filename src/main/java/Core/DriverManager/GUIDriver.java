package Core.DriverManager;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ThreadGuard;

public class GUIDriver {
    //هقرا اسم البراوزر من system property (property Reader.getProperty)
    private final String Browser = PropertyReader.getProperty("browserType");
    private ThreadLocal<WebDriver> ThreadLocaldriver = new ThreadLocal<>();

    public GUIDriver() {
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
}
