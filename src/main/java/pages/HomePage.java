package pages;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.pagefactory.AndroidFindBy;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebElement;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.testng.Assert;

@Component
public class HomePage extends BasePage {
    private static final Logger log = LogManager.getLogger(HomePage.class);

    @Autowired
    ActionToolBar actionToolBar;
    @AndroidFindBy(uiAutomator = "text(\"Create new wallet\")")
    private WebElement createNewWallet;

    @AndroidFindBy(uiAutomator = "text(\"Add existing wallet\")")
    private WebElement addExistingWallet;

    @AndroidFindBy(uiAutomator = "text(\"Welcome aboard\")")
    private WebElement welcomeAboardPopup;

    @AndroidFindBy(uiAutomator = "text(\"Your wallet is now ready to use. Dive in and start your crypto journey. Stay secure!\")")
    private WebElement startUsingTrustWalletButton;

    @AndroidFindBy(xpath = "//android.widget.Button")
    private WebElement closePopupButton;

    @Autowired
    public HomePage(AppiumDriver driver) {
        super(driver);
    }

    @Override
    public boolean isLoaded() {
        return wait.until((driver) -> createNewWallet.isDisplayed());
    }

    public void tapAddExistingWalletButton() {
        addExistingWallet.click();
    }

    public void tapOnStartUsingTrustWalletButton() {
        startUsingTrustWalletButton.click();
    }

    public Boolean isWelcomeAboardPopupDisplayed() {
        return welcomeAboardPopup.isDisplayed();
    }

    public Boolean isStartUsingTrustWalletButtonDisplayed() {
        return startUsingTrustWalletButton.isDisplayed();
    }

    public Boolean isCreateNewWalletButtonDisplayed() {
        return createNewWallet.isDisplayed();
    }

    public Boolean isAddExistingWalletButtonDisplayed() {
        return addExistingWallet.isDisplayed();
    }

    public void tapCreateNewWalletButton() {
        log.info("Tap Create New Wallet button");
        click(driver, createNewWallet);
    }

    public HomePage verifyUI() {
        log.info("Verify UI of Home Page");
        Assert.assertTrue(isCreateNewWalletButtonDisplayed());
        Assert.assertTrue(isAddExistingWalletButtonDisplayed());
        return this;
    }

    public HomePage verifyAllButtonsAtActionToolBarIsDisplayed() {
        log.info("Verify Action Tool bar buttons are displayed");
        Assert.assertTrue(actionToolBar.isHomeTabDisplayed(),
                "Home Button is not displayed");
        Assert.assertTrue(actionToolBar.isEarnTabDisplayed(),
                "Earn Button is not displayed");
        Assert.assertTrue(actionToolBar.isDiscoverTabDisplayed(),
                "Discover Button is not displayed");
        Assert.assertTrue(actionToolBar.isSwapTabDisplayed(),
                "Swap Button is not displayed");
        Assert.assertTrue(actionToolBar.isBrowserTabDisplayed(),
                "Browser Button is not displayed");
        return this;
    }

    public HomePage verifyWelcomeAboardPopupIsDisplayed() {
        log.info("Verify Welcome Aboard popup is displayed");
        Assert.assertTrue(isWelcomeAboardPopupDisplayed(),
                "Welcome Aboard popup is not displayed");
        Assert.assertTrue(isStartUsingTrustWalletButtonDisplayed(),
                "Welcome Aboard popup is not displayed");
        click(driver, closePopupButton);
        return this;
    }
}
