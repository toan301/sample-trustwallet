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
import java.util.Map;
import java.util.stream.Collectors;

@Component
public class SecretPhrasePage extends BasePage {
    private static final Logger log = LogManager.getLogger(SecretPhrasePage.class);

    @AndroidFindBy(xpath = "//android.widget.TextView[contains(@text, '1.')]/parent::android.view.View//android.widget.TextView")
    private List<WebElement> secretPhraseTexts;
    @AndroidFindBy(uiAutomator = "text(\"Secret phrase\")")
    private WebElement secretPhraseTitle;
    @AndroidFindBy(uiAutomator = "text(\"Continue\")")
    private WebElement continueButton;
    @AndroidFindBy(uiAutomator = "text(\"Copy to Clipboard\")")
    private WebElement copyToClipBoardButton;

    @Autowired
    public SecretPhrasePage(AppiumDriver driver) {
        super(driver);
    }

    @Override
    public boolean isLoaded() {
        return wait.until((driver) -> secretPhraseTitle.isDisplayed());
    }

    public boolean isSecretPhraseTitleDisplayed() {
        return secretPhraseTitle.isDisplayed();
    }

    public boolean isContinueButtonDisplayed() {
        return continueButton.isDisplayed();
    }

    public boolean isCopyToClipBoardButtonDisplayed() {
        return copyToClipBoardButton.isDisplayed();
    }

    public SecretPhrasePage verifyUI() {
        log.info("Verify UI of Secret Page");
        Assert.assertTrue(isSecretPhraseTitleDisplayed());
        Assert.assertTrue(isContinueButtonDisplayed());
        Assert.assertTrue(isCopyToClipBoardButtonDisplayed());
        return this;
    }

    public Map<Integer, String> getSecretPhrases() {
        log.info("Get Secret Phrase");
        return secretPhraseTexts
                .stream()
                .map(WebElement::getText)
                .map(s -> s.split("\\. ", 2))
                .collect(Collectors.toMap(arr -> Integer.parseInt(arr[0]), arr -> arr[1], (a, b) -> b));
    }

    public SecretPhrasePage tapContinueButton() {
        log.info("Tap Continue Button");
        click(driver, continueButton);
        return this;
    }
}
