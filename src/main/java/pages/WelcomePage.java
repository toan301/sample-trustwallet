package pages;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.pagefactory.AndroidFindBy;
import org.openqa.selenium.WebElement;
import org.springframework.stereotype.Component;
import org.springframework.beans.factory.annotation.Autowired;
import org.testng.Assert;
import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.LogManager;

@Component
public class WelcomePage extends BasePage {
    private static final Logger log = LogManager.getLogger(WelcomePage.class);

    @AndroidFindBy(uiAutomator = "text(\"Get Started\")")
    private WebElement getStartedButton;

    @AndroidFindBy(xpath = "//android.widget.TextView[@text=\"By tapping “Get Started” you agree and consent to our \n" +
            "Terms of Service and Privacy Policy\"]")
    private WebElement termConditionFooter;

    @AndroidFindBy(xpath = "//android.widget.TextView[@text=\"DEVICE SECURITY COMPROMISED\"]")
    private WebElement deviceSecurityCompromisedPopup;

    @AndroidFindBy(xpath = "//android.widget.Button")
    private WebElement deviceSecurityCompromisedClosePopupButton;

    @Autowired
    public WelcomePage(AppiumDriver driver) {
        super(driver);
    }

    public boolean isDeviceSecurityCompromisedPopupDisplayed() {
        return deviceSecurityCompromisedPopup.isDisplayed();
    }

    @Override
    public boolean isLoaded() {
        return wait.until((driver) -> deviceSecurityCompromisedPopup.isDisplayed());
    }

    public WelcomePage tapDeviceSecurityCompromisedClosePopupButton() {
        log.info("Close Device Security Compromised Popup");
        if (isDeviceSecurityCompromisedPopupDisplayed()) {
            click(driver, deviceSecurityCompromisedClosePopupButton);
        }
        return this;
    }

    public boolean isGetStartedButtonDisplayed() {
        return getStartedButton.isDisplayed();
    }

    public boolean isTermsConditionsDisplayed() {
        return termConditionFooter.isDisplayed();
    }

    public WelcomePage verifyUI() {
        log.info("Verify UI of Welcome Page");
        Assert.assertTrue(isGetStartedButtonDisplayed(),
                "Get Started Button not displayed in the Welcome Screen");
        Assert.assertTrue(isTermsConditionsDisplayed(),
                "Terms and condition not displayed in the Welcome Screen");
        return this;
    }

    public WelcomePage tapGettingStartedButton() {
        log.info("Tap Get Started Button");
        click(driver, getStartedButton);
        return this;
    }
}
