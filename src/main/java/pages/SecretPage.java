package pages;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.pagefactory.AndroidFindBy;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebElement;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.testng.Assert;

import java.util.List;

@Component
public class SecretPage extends BasePage {
    private static final Logger log = LogManager.getLogger(SecretPage.class);

    @AndroidFindBy(uiAutomator = "text(\"SKIP\")")
    private WebElement skipButton;
    @AndroidFindBy(uiAutomator = "text(\"Back up secret phrase\")")
    private WebElement backUpSecretPhraseTitle;
    @AndroidFindBy(uiAutomator = "text(\"Back up manually\")")
    private WebElement backUpManuallyButton;
    @AndroidFindBy(uiAutomator = "text(\"Back up to Google Drive\")")
    private WebElement backUpToGoogleDriveButton;

    @AndroidFindBy(className = "android.widget.TextView")
    private List<WebElement> textElements;
    @AndroidFindBy(uiAutomator = "text(\"This secret phrase is the master key to your wallet\")")
    private WebElement pageTitle;
    @AndroidFindBy(uiAutomator = "text(\"Continue\")")
    private WebElement continueButtonElement;

    @AndroidFindBy(uiAutomator = "text(\"Trust Wallet does not keep a copy of your secret phrase.\")")
    private WebElement notCopySecretCheckBox;

    @AndroidFindBy(uiAutomator = "text(\"Saving this digitally in plain text is NOT recommended. Examples include screenshots, text files, or emailing yourself\")")
    private WebElement notRecommendedCheckBox;

    @AndroidFindBy(uiAutomator = "text(\"Write down your secret phrase, and store it in a secure offline location!\")")
    private WebElement storeOfflineCheckBox;

    @Autowired
    public SecretPage(AppiumDriver driver) {
        super(driver);
    }

    @Override
    public boolean isLoaded() {
        return wait.until((driver) -> skipButton.isDisplayed());
    }

    public boolean isSkipButtonDisplayed() {
        return skipButton.isDisplayed();
    }

    public boolean isBackupManuallyButtonDisplayed() {
        return backUpManuallyButton.isDisplayed();
    }

    public boolean isBackUpSecretPhraseTitleDisplayed() {
        return backUpSecretPhraseTitle.isDisplayed();
    }

    public boolean isBackupToGoogleDriverButtonDisplayed() {
        return backUpToGoogleDriveButton.isDisplayed();
    }

    public SecretPage verifyUI() {
        log.info("Verify UI of Secret Page");
        Assert.assertTrue(isSkipButtonDisplayed());
        Assert.assertTrue(isBackupManuallyButtonDisplayed());
        Assert.assertTrue(isBackUpSecretPhraseTitleDisplayed());
        Assert.assertTrue(isBackupToGoogleDriverButtonDisplayed());
        return this;
    }

    public SecretPage tapOnBackupManuallyButton() {
        log.info("Tap on Backup Manually Button");
        click(driver, backUpManuallyButton);
        return this;
    }

    public SecretPage checkAllCheckbox() {
        log.info("Check All checkbox");
        click(driver, notCopySecretCheckBox);
        click(driver, storeOfflineCheckBox);
        click(driver, notRecommendedCheckBox);
        return this;
    }

    public SecretPage tapContinueButton() {
        log.info("tap Continue Button");
        click(driver, continueButtonElement);
        return this;
    }

}
