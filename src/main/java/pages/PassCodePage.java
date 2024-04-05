package pages;

import io.appium.java_client.AppiumBy;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.pagefactory.AndroidFindBy;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebElement;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.testng.Assert;

import java.time.Duration;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Component
public class PassCodePage extends BasePage {
    private static final Logger log = LogManager.getLogger(PassCodePage.class);

    @AndroidFindBy(uiAutomator = "text(\"Create passcode\")")
    private WebElement createPassCodeTitle;
    @AndroidFindBy(uiAutomator = "text(\"Confirm passcode\")")
    private WebElement confirmPassCodeTitle;

    @Autowired
    public PassCodePage(AppiumDriver driver) {
        super(driver);
    }

    @Override
    public boolean isLoaded() {
        return wait.until((driver) -> createPassCodeTitle.isDisplayed());
    }

    public Boolean isConfirmPassCodeTitleDisplayed() {
        return confirmPassCodeTitle.isDisplayed();
    }

    public Boolean isCreatePassCodeDisplayed() {
        return createPassCodeTitle.isDisplayed();
    }

    public PassCodePage verifyCreatePageCodePageIsDisplayed() {
        log.info("Verify visibility of Passcode Page");
        Assert.assertTrue(isCreatePassCodeDisplayed());
        return this;
    }

    public PassCodePage verifyConfirmPageCodePageIsDisplayed() {
        log.info("Verify visibility of Confirm Passcode Page");
        Assert.assertTrue(isConfirmPassCodeTitleDisplayed());
        return this;
    }

    public PassCodePage setPassCode(String passcode) {
        log.info("Set Passcode");
        for (char ch : passcode.toCharArray()) {
            WebElement element = driver.findElement(AppiumBy.androidUIAutomator("text(\"" + String.valueOf(ch) + "\")"));
            waitForElement(driver, element, Duration.ofSeconds(shortWaitTime));
            element.click();
        }
        return this;
    }

    public PassCodePage confirmPassCode(String passcode) {
        log.info("Confirm Passcode");
        for (char ch : passcode.toCharArray()) {
            WebElement element = driver.findElement(AppiumBy.androidUIAutomator("text(\"" + String.valueOf(ch) + "\")"));
            waitForElement(driver, element, Duration.ofSeconds(shortWaitTime));
            element.click();
        }
        return this;
    }
}
