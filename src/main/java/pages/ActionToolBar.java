package pages;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.pagefactory.AndroidFindBy;
import org.openqa.selenium.WebElement;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ActionToolBar extends BasePage {

    @AndroidFindBy(uiAutomator = "text(\"Home\")")
    private WebElement home;
    @AndroidFindBy(uiAutomator = "text(\"Swap\")")
    private WebElement swap;
    @AndroidFindBy(uiAutomator = "text(\"Earn\")")
    private WebElement earn;
    @AndroidFindBy(uiAutomator = "text(\"Discover\")")
    private WebElement discover;
    @AndroidFindBy(uiAutomator = "text(\"Browser\")")
    private WebElement browser;

    @Autowired
    public ActionToolBar(AppiumDriver driver) {
        super(driver);
    }

    @Override
    public boolean isLoaded() {
        return wait.until((driver) -> home.isDisplayed());
    }

    public boolean isHomeTabDisplayed() {
        return home.isDisplayed();
    }

    public boolean isSwapTabDisplayed() {
        return swap.isDisplayed();
    }

    public boolean isEarnTabDisplayed() {
        return earn.isDisplayed();
    }

    public boolean isDiscoverTabDisplayed() {
        return discover.isDisplayed();
    }

    public boolean isBrowserTabDisplayed() {
        return browser.isDisplayed();
    }
}
