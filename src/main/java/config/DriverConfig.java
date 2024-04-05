
package config;

import io.appium.java_client.AppiumDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

import java.io.File;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.concurrent.TimeUnit;

@Configuration
@PropertySource("classpath:application.properties")
@ComponentScan(basePackages = {"configuration", "tests", "utils", "pages"})
public class DriverConfig {
    @Value("${deviceName}")
    String deviceName;
    @Value("${platformVersion}")
    String platformVersion;
    @Value("${platformName}")
    String platformName;
    @Value("${bundleId:optional}")
    String bundleId;
    @Value("${app}")
    String app;
    @Value("${appActivity:optional}")
    String appActivity;
    @Value("${appPackage:optional}")
    String appPackage;
    @Value("${appiumLog:optional}")
    String appiumLog;
    @Value("${fullReset:optional}")
    Boolean fullReset;
    @Value("${noReset:optional}")
    Boolean noReset;

    @Bean(destroyMethod = "quit")
    public AppiumDriver androidAppiumDriver() throws MalformedURLException {
        AppiumDriver driver = null;
        DesiredCapabilities capabilities = new DesiredCapabilities();
        capabilities.setCapability("app", new File("src/test/resources/app/" + app).getAbsolutePath());
        capabilities.setCapability("platformName", platformName);
        capabilities.setCapability("deviceName", deviceName);
        capabilities.setCapability("automationName", "UiAutomator2");
        capabilities.setCapability("autoGrantPermissions", "true");
        capabilities.setCapability("platformVersion", platformVersion);
        capabilities.setCapability("noReset", noReset);
        capabilities.setCapability("fullReset", fullReset);
        capabilities.setCapability("newCommandTimeout", 6000);
        try {
            driver = new AppiumDriver(new URL("http://127.0.0.1:4725/wd/hub"), capabilities);
        } catch (MalformedURLException e) {
            throw new RuntimeException("Appium server URL is invalid", e);
        }
        driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
        return driver;
    }
}
