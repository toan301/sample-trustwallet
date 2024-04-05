package helpers;

import org.openqa.selenium.*;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.*;
import org.springframework.stereotype.Component;

import java.time.Duration;
import java.util.*;
import java.util.concurrent.TimeUnit;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Component
public class CommonActions {
    //    protected WebElement element;
    public static final long longWaitTime = 30;
    public static final int shortWaitTime = 10;
    public WebDriverWait wait;

    public boolean isAlertPresent(WebDriver driver) {
        try {
            driver.switchTo().alert();
            return true;
        } // try
        catch (NoAlertPresentException e) {
            return false;
        }
    }

    //Get The Current Day
    public static String getCurrentDay() {
        //Create a Calendar Object
        Calendar calendar = Calendar.getInstance(TimeZone.getDefault());
        //Get Current Day as a number
        int todayInt = calendar.get(Calendar.DAY_OF_MONTH);
        //Integer to String Conversion
        String todayStr = Integer.toString(todayInt);
        System.out.println("Today Str: " + todayStr + "\n");
        return todayStr;
    }

    public void selectOptionFromDropDownByText(WebDriver driver, WebElement element, String value) {
        Select dropdown = new Select(element);
        dropdown.selectByVisibleText(value);
    }

    public void selectOptionFromDropDownByIndex(WebDriver driver, WebElement element, int index) {
        Select dropdown = new Select(element);
        dropdown.selectByIndex(index);
    }

    public void waitForAjax(WebDriver driver) {
        new WebDriverWait(driver, Duration.ofSeconds(180)).until(new ExpectedCondition<Boolean>() {
            public Boolean apply(WebDriver driver) {
                JavascriptExecutor js = (JavascriptExecutor) driver;
                return (Boolean) js.executeScript("return jQuery.active == 0");
            }
        });
    }


    public boolean isElementDisplayed(WebDriver driver, WebElement locator) {
        try {
            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(shortWaitTime));
            wait.until(ExpectedConditions.visibilityOf(locator));
            return locator.isDisplayed();
        } catch (org.openqa.selenium.NoSuchElementException
                 | org.openqa.selenium.StaleElementReferenceException
                 | org.openqa.selenium.TimeoutException e) {
            return false;
        }
    }

    public void click(WebDriver driver, WebElement locator) {
        waitForElement(driver, locator, Duration.ofSeconds(shortWaitTime));
        waitForElementClickAble(driver, locator, Duration.ofSeconds(shortWaitTime));
        locator.click();
    }

    public void type(WebDriver driver, WebElement locator, String value) {
        waitForElement(driver, locator, Duration.ofSeconds(shortWaitTime));
        locator.clear();
        locator.sendKeys(value);
    }

    public void waitForElement(WebDriver driver, WebElement locator, Duration timeoutInSeconds) {
        try {
            WebDriverWait wait = new WebDriverWait(driver, timeoutInSeconds);
            wait.ignoring(StaleElementReferenceException.class)
                    .ignoring(NoSuchElementException.class)
                    .until(ExpectedConditions.visibilityOf(locator));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void waitForElementClickAble(WebDriver driver, WebElement locator, Duration timeoutInSeconds) {
        try {
            WebDriverWait wait = new WebDriverWait(driver, timeoutInSeconds);
            wait.ignoring(StaleElementReferenceException.class)
                    .ignoring(NoSuchElementException.class)
                    .until(ExpectedConditions.elementToBeClickable(locator));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void waitForElementInvisible(WebDriver driver, WebElement locator, Duration timeoutInSeconds) {
        try {
            WebDriverWait wait = new WebDriverWait(driver, timeoutInSeconds);
            wait.ignoring(StaleElementReferenceException.class)
                    .ignoring(NoSuchElementException.class)
                    .until(ExpectedConditions.invisibilityOf(locator));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void waitForElementToBeGone(WebDriver driver, WebElement locator, Duration timeout) {
        if (isElementDisplayed(driver, locator)) {
            new WebDriverWait(driver, timeout).until(ExpectedConditions.not(ExpectedConditions.visibilityOf(locator)));
        }
    }

    public void selectItemComboBoxByText(WebDriver driver, WebElement locator, String item) {
        waitForElement(driver, locator, Duration.ofSeconds(longWaitTime));
        Select select = new Select(locator);
        select.selectByVisibleText(item);
    }

    public void selectItemComboBoxByValue(WebDriver driver, WebElement locator, String value) {
        String optionInDropDown = "md-option[value='%s']";
        waitForElement(driver, locator, Duration.ofSeconds(longWaitTime));
        this.click(driver, locator);
        WebElement statusOption = driver.findElement(new By.ByCssSelector(String.format(optionInDropDown, value)));
        this.click(driver, statusOption);
    }

    public void selectItemComboBoxIncludeText(WebDriver driver, WebElement locator, String text) {
        String optionInDropDown = "//div[@class='md-text' and contains(.,'%s')]/..";//div[@class='md-text' and contains(.,'T2')]/..
        waitForElement(driver, locator, Duration.ofSeconds(longWaitTime));
        this.click(driver, locator);
        WebElement statusOption = driver.findElement(new By.ByXPath(String.format(optionInDropDown, text)));
        waitForElement(driver, statusOption, Duration.ofSeconds(longWaitTime));
        this.click(driver, statusOption);
    }

    public void selectItemComboBoxByIndex(WebDriver driver, WebElement locator, int index) {
        waitForElement(driver, locator, Duration.ofSeconds(longWaitTime));
        Select select = new Select(locator);
        select.selectByIndex(index);
    }

    public void refresh(WebDriver driver) {
        driver.navigate().refresh();
        sleep(2);
    }

    public void back(WebDriver driver) {
        driver.navigate().back();
    }

    public void forward(WebDriver driver) {
        driver.navigate().forward();
    }

    public void openURL(WebDriver driver, String url) {
        driver.get(url);
        driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);
        driver.manage().window().maximize();
    }

    public String getPageTitle(WebDriver driver) {
        return driver.getTitle();
    }

    public void waitForAlert(WebDriver driver) {
        new WebDriverWait(driver, Duration.ofSeconds(60)).ignoring(NoAlertPresentException.class)
                .until(ExpectedConditions.alertIsPresent());
    }

    public void acceptJavascriptAlert(WebDriver driver) {
        Alert alert = driver.switchTo().alert();
        alert.accept();
    }

    public void dismissJavascriptAlert(WebDriver driver) {
        Alert alert = driver.switchTo().alert();
        alert.dismiss();
    }

    public String getTextJavascriptAlert(WebDriver driver) {
        String message;
        try {
            Alert alert = driver.switchTo().alert();
            message = alert.getText();
            alert.accept();
        } catch (final WebDriverException e) {
            message = null;
        }
        return message;
    }

    public void acceptJavascriptPrompt(WebDriver driver, String value) {
        Alert alert = driver.switchTo().alert();
        driver.switchTo().alert().sendKeys(value);
        alert.accept();
    }

    public void waitForAjaxDone(WebDriver driver) {
        while (true) {
            Boolean ajaxIsComplete = (Boolean) executeJavaScript(driver, "return jQuery.active == 0");
            if (ajaxIsComplete) {
                break;
            }
            sleep(5);
        }
    }

    public void waitUntilAjaxRequestCompletes(WebDriver driver) {
        new FluentWait<WebDriver>(driver).withTimeout(Duration.ofMinutes(45)).pollingEvery(Duration.ofSeconds(15))
                .until(new ExpectedCondition<Boolean>() {
                    public Boolean apply(WebDriver d) {
                        JavascriptExecutor jsExec = (JavascriptExecutor) d;
                        return (Boolean) jsExec.executeScript("return jQuery.active == 0");
                    }
                });
    }


    public String getAttributeValue(WebDriver driver, WebElement locator, String attribute) {
        waitForElement(driver, locator, Duration.ofSeconds(longWaitTime));
        return locator.getAttribute(attribute);
    }

    public void doubleClick(WebDriver driver, WebElement locator) {
        waitForElement(driver, locator, Duration.ofSeconds(longWaitTime));
        Actions action = new Actions(driver);
        action.doubleClick(locator).perform();
    }


    public void moveMouseToElement(WebDriver driver, WebElement locator) {
        waitForElement(driver, locator, Duration.ofSeconds(longWaitTime));
        Actions action = new Actions(driver);
        action.moveToElement(locator, 156, 20);
        action.build().perform();
    }


    public Object executeJavaScript(WebDriver driver, String javascript) {
        try {
            JavascriptExecutor js = (JavascriptExecutor) driver;
            return js.executeScript(javascript);
        } catch (Exception e) {
            e.getMessage();
            return null;
        }
    }

    public Object executeJavaScript(WebDriver driver, String javascript, WebElement locator) {
        try {
            JavascriptExecutor js = (JavascriptExecutor) driver;
            return js.executeScript(javascript, locator);
        } catch (Exception e) {
            e.getMessage();
            return null;
        }
    }

    public String getCurrentUrl(WebDriver driver) {
        return driver.getCurrentUrl();
    }

    public void scrollToBottomPage(WebDriver driver) {
        executeJavaScript(driver,
                "window.scrollTo(0,Math.max(document.documentElement.scrollHeight,document.body.scrollHeight,document.documentElement.clientHeight));");
    }

    public void scrollPageToElement(WebDriver driver, WebElement locator) {
        waitForElement(driver, locator, Duration.ofSeconds(longWaitTime));
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView();", locator);
        sleep(2);
    }

    public String randomString() {
        Random rand = new Random();
        int number = rand.nextInt(9000) + 1;
        String numberString = Integer.toString(number);
        return numberString;
    }

    public void uncheckTheCheckbox(WebDriver driver, WebElement locator) {
        waitForElement(driver, locator, Duration.ofSeconds(longWaitTime));
        if (locator.isSelected()) {
            click(driver, locator);
        }
    }

    public boolean isCheckboxChecked(WebDriver driver, WebElement locator) {
        waitForElement(driver, locator, Duration.ofSeconds(longWaitTime));
        return locator.isSelected();
    }

    public void dragAndDrop(WebDriver driver, By sourceDriver, By targetDriver) {
        WebElement source = driver.findElement(sourceDriver);
        WebElement target = driver.findElement(targetDriver);
        Actions action = new Actions(driver);
        action.dragAndDrop(source, target);
        action.perform();
    }


    public String getText(WebDriver driver, WebElement locator) {
        try {
            waitForElement(driver, locator, Duration.ofSeconds(longWaitTime));
            return locator.getText();
        } catch (Exception e) {
            return null;
        }
    }

    public void sleep(long timeout) {
        try {
            Thread.sleep(timeout * 1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public float convertLocatorToFloat(WebDriver driver, By locator) {
        float price = 0;
        Pattern p = Pattern.compile("\\d+((.|,)\\d+)?");
        Matcher m = p.matcher(driver.findElement(locator).getText());
        if (m.find()) {
//            System.out.println(m.group());
            price = Float.parseFloat(m.group());
        }
        return price;
    }

    public float convertTextToFloat(String text) {
        float price = 0;
        Pattern p = Pattern.compile("\\d+((.|,)\\d+)?");
        Matcher m = p.matcher(text);
        if (m.find()) {
            price = Float.parseFloat(m.group());
        }
        return price;
    }

    public void closeBrowser(WebDriver driver) {
        try {
            driver.quit();
            System.gc();
            if (driver.toString().toLowerCase().contains("chrome")) {
                String cmd = "taskkill /IM chromedriver.exe /F";
                Process process = Runtime.getRuntime().exec(cmd);
                process.waitFor();
            }
            if (driver.toString().toLowerCase().contains("internetexplorer")) {
                String cmd = "taskkill /IM IEDriverServer.exe /F";
                Process process = Runtime.getRuntime().exec(cmd);
                process.waitFor();
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    public WebDriver switchOtherWindow(WebDriver driver) {
        WebDriver tmp = driver;
        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        for (String winHandle : driver.getWindowHandles()) {
            tmp = driver.switchTo().window(winHandle);
        }
        return tmp;
    }

    public String getCurrentDriver(WebDriver driver) {
        return driver.getWindowHandle();
    }

    public WebDriver switchCurrentDriver(WebDriver driver, String currentHandle) {
        try {
            driver.close();
            return driver.switchTo().window(currentHandle);
        } catch (Exception ex) {
            System.out.println(ex);
        }
        return driver;
    }

    public void switchToPopUp(WebDriver driver) {
        String subWindowHandler = null;
        Set<String> handles = driver.getWindowHandles(); // get all window
        // handles
        Iterator<String> iterator = handles.iterator();
        while (iterator.hasNext()) {
            subWindowHandler = iterator.next();
        }
        driver.switchTo().window(subWindowHandler);
    }

    public void closeOtherWindows(WebDriver driver, String parentPage) {
        Set<String> set = driver.getWindowHandles();
        set.remove(parentPage);
        assert set.size() == 1;
        driver.switchTo().window((String) set.toArray()[0]);
        driver.close();
        driver.switchTo().window(parentPage);
    }
}
