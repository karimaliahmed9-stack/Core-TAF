package Core.Actions;

import Core.LogManager.LogManager;
import Core.WaitManager.WaitManager;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class ElementActions {
    private final WaitManager waitManager;
    private final WebDriver driver;

    //Contractor
    public ElementActions(WebDriver driver) {
        this.driver = driver;
        this.waitManager = new WaitManager(driver);
    }

    //Find Elements
    public WebElement FindElement(By Locator) {
        return driver.findElement(Locator);
    }

    //Clicking
    public void Click(By Locator) {
        waitManager.getFluentWait().until(driver1 -> {
            try {
                WebElement e = driver1.findElement(Locator);
                ScrollToElementJS(Locator);
                e.isDisplayed();
                e.isEnabled();
                LogManager.Info("Scrolling to element" + Locator);
                e.click();
                LogManager.Info("Clicking on element: " + Locator);
                return true;
            } catch (Exception e) {
                LogManager.Error("Clicking is False" + e);
                return false;
            }
        });
    }


    public void JavaScriptClick(By locator) {
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("arguments[0].click();", FindElement(locator));
    }


    public void ScrollToTop() {
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("window.scrollTo(0, 0);");
    }


    public void ScrollToBottom() {
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("window.scrollTo(0, document.body.scrollHeight);");
    }


    //SendKey
    public void SendKey(By locator, String key) {
        waitManager.getFluentWait().until(d -> {
            try {
                WebElement e = d.findElement(locator);
                ScrollToElementJS(locator);
                e.isDisplayed();
                e.isEnabled();
                e.clear();
                e.sendKeys(key);
                LogManager.Info("Sending key: " + key + " to element: " + locator);
                return true;
            } catch (Exception e) {
                LogManager.Error("Sending key is False" + e);
                return false;
            }
        });
    }

    //For Send Phone Number
    //String phone = TimeManager.GetUniquePhoneNumber("011", 8);
    //011 _12345678
    public String GetUniquePhoneNumber(By Locator, String prefix, int numberOfDigits) {

        String time = String.valueOf(System.currentTimeMillis());

        if (numberOfDigits > time.length()) {
            LogManager.Error("Number of digits requested exceeds the length of the current time in milliseconds."
            );
        }

        String Phone = prefix + time.substring(time.length() - numberOfDigits);
        //SendKey_Without_Data(Locator,Phone);
        SendKey(Locator, Phone);
        return Phone;
    }

    //SendKey Without The Clearing Data
    public void SendKey_Without_Data(By locator, String key) {
        waitManager.getFluentWait().until(d -> {
            try {
                WebElement e = d.findElement(locator);
                ScrollToElementJS(locator);
                e.isDisplayed();
                e.isEnabled();
                e.sendKeys(key);
                LogManager.Info("Sending key: " + key + " to element: " + locator);
                return true;
            } catch (Exception e) {
                LogManager.Error("Sending key is False" + e);
                return false;
            }
        });
    }

    //Clear Data Only
    public void ClearData(By locator) {
        waitManager.getFluentWait().until(d -> {
            try {
                WebElement e = d.findElement(locator);
                ScrollToElementJS(locator);
                e.isDisplayed();
                e.isEnabled();
                e.clear();
                LogManager.Info("Clearing data from element: " + locator);
                return true;
            } catch (Exception e) {
                LogManager.Error("Clearing data is False" + e);
                return false;
            }
        });
    }


    //GetText
    public String GetText(By locator) {
        return waitManager.getFluentWait().until(d -> {
            try {
                WebElement e = d.findElement(locator);
                ScrollToElementJS(locator);
                String message = e.getText();
                LogManager.Info("Text is: " + message);
                return !message.isEmpty() ? message : null;


            } catch (Exception e) {
                LogManager.Error("Text Alert is False" + e);
                return null;
            }
        });

    }


    //Scroll with JS
    public void ScrollToElementJS(By locator) {

        ((JavascriptExecutor) driver).executeScript("""
                arguments[0].scrollIntoView({behaviour:"auto", block:"center", inline:"center"});""", FindElement(locator));
    }

    //Select From Drop down
    public void SelectFromDropDown(By locator, String value) {
        waitManager.getFluentWait().until(driver1 ->
        {
            try {
                WebElement element = driver1.findElement(locator);
                ScrollToElementJS(locator);
                Select select = new Select(element);
                select.selectByVisibleText(value);
                LogManager.Info("Selecting from dropdown: " + value + " from element: " + locator);
                return true;
            } catch (Exception e) {
                LogManager.Error("Selecting from dropdown is False" + e);
                return false;
            }
        });
    }

    //Wait for Spinner Disappear
    public void waitForSpinnerToDisappear(By spinner) {
        waitManager.getFluentWait().until(driver1 -> {
            try {
                WebElement spinnerElement = driver1.findElement(spinner);

            } catch (Exception e) {
                LogManager.Info("Spinner is not displayed");
            }
            return null;
        });
    }

    //Upload Files
    public void UploadFile(By locator, String filePath) {
        // Convert String file path to Path object
        Path path = Paths.get(filePath);
        //is path not completely
        if (!path.isAbsolute()) {
            //Confige from relative path to absolute path
            path = Paths.get(System.getProperty("user.dir"), filePath);
        }
        //بيضمن ان الباث سليم ونضيف
        path = path.toAbsolutePath().normalize();
        //هل الملف موجود عندنا عجهاز
        if (!Files.exists(path)) {
            LogManager.Error("File not found: " + path);
        }

        Path finalPath = path;
        waitManager.getFluentWait().until(driver -> {
            try {
                WebElement element = driver.findElement(locator);

                ScrollToElementJS(locator);

                element.sendKeys(finalPath.toString());

                return true;

            } catch (Exception e) {
                return false;
            }
        });
    }


    //Drag & Drop
    public void DragAndDrop(By sourceLocator, By targetLocator) {
        waitManager.getFluentWait().until(driver -> {
            try {
                WebElement sourceElement = driver.findElement(sourceLocator);
                WebElement targetElement = driver.findElement(targetLocator);
                ScrollToElementJS(sourceLocator);
                ScrollToElementJS(targetLocator);
                org.openqa.selenium.interactions.Actions actions = new org.openqa.selenium.interactions.Actions(driver);
                actions.dragAndDrop(sourceElement, targetElement).perform();
                LogManager.Info("Dragging element: " + sourceLocator + " to element: " + targetLocator);
                return true;
            } catch (Exception e) {
                LogManager.Error("Dragging and dropping is False" + e);
                return false;
            }
        });
    }

    //Frame
    public void SwitchToFrame(By locator) {
        waitManager.getFluentWait().until(driver -> {
            try {
                WebElement frameElement = driver.findElement(locator);
                ScrollToElementJS(locator);
                driver.switchTo().frame(frameElement);
                LogManager.Info("Switching to frame: " + locator);
                return true;
            } catch (Exception e) {
                LogManager.Error("Switching to frame is False" + e);
                return false;
            }
        });
    }

    //Alert
    public void AcceptAlert() {
        waitManager.getFluentWait().until(driver -> {
            try {
                driver.switchTo().alert().accept();
                LogManager.Info("Accepting alert");
                return true;
            } catch (Exception e) {
                LogManager.Error("Accepting alert is False" + e);
                return false;
            }
        });
    }

    public void DismissAlert() {
        waitManager.getFluentWait().until(driver -> {
            try {
                driver.switchTo().alert().dismiss();
                LogManager.Info("Dismissing alert");
                return true;
            } catch (Exception e) {
                LogManager.Error("Dismissing alert is False" + e);
                return false;
            }
        });
    }

    public String GetAlertText() {
        return waitManager.getFluentWait().until(driver -> {
            try {
                String alertText = driver.switchTo().alert().getText();
                LogManager.Info("Getting alert text: " + alertText);
                return alertText;
            } catch (Exception e) {
                LogManager.Error("Getting alert text is False" + e);
                return null;
            }
        });
    }


    //For Radio Checked_box
    public void Checked_Box(By locator) {
        waitManager.getFluentWait().until(driver -> {
            try {
                WebElement element = driver.findElement(locator);
                ScrollToElementJS(locator);
                if (!element.isSelected()) {
                    element.click();
                    LogManager.Info("Checked the checkbox: " + locator);
                }
                return true;
            } catch (Exception e) {
                LogManager.Error("Checking the checkbox is False" + e);
                return false;
            }
        });
    }

    //For Unchecked_BOx
    public void Unchecked_Box(By locator) {
        waitManager.getFluentWait().until(driver -> {
            try {
                WebElement element = driver.findElement(locator);
                ScrollToElementJS(locator);
                if (element.isSelected()) {
                    element.click();
                    LogManager.Info("Unchecked the checkbox: " + locator);
                }
                return true;
            } catch (Exception e) {
                LogManager.Error("Unchecking the checkbox is False" + e);
                return false;
            }
        });

    }

    //Keyboard Actions -> PressEscape
    public void PressEscape(By locator) {
        waitManager.getFluentWait().until(driver -> {
            try {
                WebElement element = driver.findElement(locator);
                ScrollToElementJS(locator);
                element.sendKeys(org.openqa.selenium.Keys.ESCAPE);
                LogManager.Info("Pressing Escape key on element: " + locator);
                return true;
            } catch (Exception e) {
                LogManager.Error("Pressing Escape key is False" + e);
                return false;
            }
        });
    }

    //Keyboard Actions-> PressEnter
    public void PressEnter(By locator) {
        waitManager.getFluentWait().until(driver -> {
            try {
                WebElement element = driver.findElement(locator);
                ScrollToElementJS(locator);
                element.sendKeys(org.openqa.selenium.Keys.ENTER);
                LogManager.Info("Pressing Enter on element: " + locator);
                return true;
            } catch (Exception e) {
                LogManager.Error("Pressing Enter is False" + e);
                return false;
            }
        });
    }

    //Keyboard Actions
    public void PressTab(By locator) {
        waitManager.getFluentWait().until(driver -> {
            try {
                WebElement element = driver.findElement(locator);
                ScrollToElementJS(locator);
                element.sendKeys(org.openqa.selenium.Keys.TAB);
                LogManager.Info("Pressing Tab on element: " + locator);
                return true;
            } catch (Exception e) {
                LogManager.Error("Pressing Tab is False" + e);
                return false;
            }
        });
    }

    //Keyboard Actions -> PressBackspace
    public void PressBackspace(By locator) {
        waitManager.getFluentWait().until(driver -> {
            try {
                WebElement element = driver.findElement(locator);
                ScrollToElementJS(locator);
                element.sendKeys(org.openqa.selenium.Keys.BACK_SPACE);
                LogManager.Info("Pressing Backspace on element: " + locator);
                return true;
            } catch (Exception e) {
                LogManager.Error("Pressing Backspace is False" + e);
                return false;
            }
        });
    }

    //Mouse Actions -> HoverOverElement
    public void HoverOverElement(By locator) {
        waitManager.getFluentWait().until(driver -> {
            try {
                WebElement element = driver.findElement(locator);
                ScrollToElementJS(locator);
                org.openqa.selenium.interactions.Actions actions = new org.openqa.selenium.interactions.Actions(driver);
                actions.moveToElement(element).perform();
                LogManager.Info("Hovering over element: " + locator);
                return true;
            } catch (Exception e) {
                LogManager.Error("Hovering over element is False" + e);
                return false;
            }
        });
    }

    //Mouse Actions -> ScrollByAction
    public void ScrollByAction(int x, int y) {
        waitManager.getFluentWait().until(driver -> {
            try {
                org.openqa.selenium.interactions.Actions actions = new org.openqa.selenium.interactions.Actions(driver);
                actions.moveByOffset(x, y).perform();
                LogManager.Info("Scrolling by offset: (" + x + ", " + y + ")");
                return true;
            } catch (Exception e) {
                LogManager.Error("Scrolling by offset is False" + e);
                return false;
            }
        });
    }

    //Mouse Actions -> DubleClickElement
    public void Duble_Click_Element(By locator) {
        waitManager.getFluentWait().until(driver -> {
            try {
                WebElement element = driver.findElement(locator);
                ScrollToElementJS(locator);
                org.openqa.selenium.interactions.Actions actions = new org.openqa.selenium.interactions.Actions(driver);
                actions.doubleClick(element).perform();
                LogManager.Info("Duble Clicking : " + locator);
                return true;
            } catch (Exception e) {
                LogManager.Error("Duble Clicking is false :" + e);
                return false;
            }
        });
    }

    //Mouse Actions -> RightClickElement
    public void RightClickElement(By locator) {
        waitManager.getFluentWait().until(driver -> {
            try {
                WebElement element = driver.findElement(locator);
                ScrollToElementJS(locator);
                org.openqa.selenium.interactions.Actions actions = new org.openqa.selenium.interactions.Actions(driver);
                actions.contextClick(element).perform();
                LogManager.Info("Hovering over element: " + locator);
                return true;
            } catch (Exception e) {
                LogManager.Error("Hovering over element is False" + e);
                return false;
            }
        });
    }

    //Mouse Actions -> Release
    public void Release(By locator) {
        waitManager.getFluentWait().until(driver -> {
            try {
                WebElement element = driver.findElement(locator);
                ScrollToElementJS(locator);
                org.openqa.selenium.interactions.Actions actions = new org.openqa.selenium.interactions.Actions(driver);
                actions.release(element).perform();
                LogManager.Info("Releasing element: " + locator);
                return true;
            } catch (Exception e) {
                LogManager.Error("Releasing element is False" + e);
                return false;
            }
        });
    }

    //Mouse Actions -> ClickAndHold
    public void ClickAndHold(By locator) {
        waitManager.getFluentWait().until(driver -> {
            try {
                WebElement element = driver.findElement(locator);
                ScrollToElementJS(locator);
                org.openqa.selenium.interactions.Actions actions = new org.openqa.selenium.interactions.Actions(driver);
                actions.clickAndHold(element).perform();
                LogManager.Info("Clicking and holding element: " + locator);
                return true;
            } catch (Exception e) {
                LogManager.Error("Clicking and holding element is False" + e);
                return false;
            }
        });
    }
}
