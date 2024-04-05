package tests;

import org.springframework.beans.factory.annotation.Autowired;
import org.testng.annotations.Test;
import pages.*;

import java.util.Map;

public class CreateNewWalletTest extends BaseTest {

    @Autowired
    WelcomePage welcomePage;

    @Autowired
    HomePage homePage;
    @Autowired
    SecretPage secretPage;

    @Autowired
    SecretPhrasePage secretPhrasePage;

    @Autowired
    SecretPhraseConfirmPage secretPhraseConfirmPage;

    @Autowired
    PassCodePage passCodePage;

    @Test
    public void CreateNewWalletTest() {
        welcomePage.isLoaded();
        welcomePage.tapDeviceSecurityCompromisedClosePopupButton()
                .verifyUI()
                .tapGettingStartedButton();
        homePage.isLoaded();
        homePage.verifyUI()
                .verifyAllButtonsAtActionToolBarIsDisplayed()
                .tapCreateNewWalletButton();
        secretPage.isLoaded();
        secretPage
                .verifyUI()
                .tapOnBackupManuallyButton()
                .checkAllCheckbox()
                .tapContinueButton();
        secretPhrasePage.isLoaded();
        secretPhrasePage.verifyUI();
        Map<Integer, String> secretsMapWithNumberAsKey = secretPhrasePage.getSecretPhrases();
        secretPhrasePage.tapContinueButton();
        secretPhraseConfirmPage.isLoaded();
        secretPhraseConfirmPage.confirmSecretPhrases(secretsMapWithNumberAsKey)
                .tapContinueButton();
        passCodePage.isLoaded();
        passCodePage.verifyCreatePageCodePageIsDisplayed()
                .setPassCode(testData.getPasscode())
                .verifyConfirmPageCodePageIsDisplayed()
                .confirmPassCode(testData.getPasscode());
        homePage.verifyWelcomeAboardPopupIsDisplayed()
                .verifyAllButtonsAtActionToolBarIsDisplayed();

    }

}