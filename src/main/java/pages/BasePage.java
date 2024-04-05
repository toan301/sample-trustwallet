package pages;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.pagefactory.AndroidFindBy;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.springframework.stereotype.Component;
import helpers.CommonActions;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;

import java.time.Duration;

@Component
public abstract class BasePage extends CommonActions {

    protected final AppiumDriver driver;
    @AndroidFindBy(xpath = "//android.widget.Button")
    private WebElement closePopupButton;

    public BasePage(AppiumDriver driver) {
        this.driver = driver;
        PageFactory.initElements(new AppiumFieldDecorator(driver), this);
        wait = new WebDriverWait(driver, Duration.ofSeconds(longWaitTime));
    }

    public void tapClosePopupButton() {
        click(driver, closePopupButton);
    }

    public abstract boolean isLoaded();

}