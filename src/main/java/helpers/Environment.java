package helpers;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

import java.io.File;

@Configuration
@PropertySource("classpath:application.properties")
public class Environment {
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

    @Bean("deviceName")
    public String getDeviceName() {
        return deviceName;
    }

    @Bean("platformVersion")
    public String getPlatformVersion() {
        return platformVersion;
    }

    @Bean("platformName")
    public String getPlatformName() {
        return platformName;
    }

    @Bean("bundleId")
    public String getBundleId() {
        return bundleId;
    }

    @Bean("app")
    public String getApp() {
        return new File("src/test/resources/app/" + app).getAbsolutePath();
    }

    @Bean("appActivity")
    public String getAppActivity() {
        return appActivity;
    }

    @Bean("appPackage")
    public String getAppPackage() {
        return appPackage;
    }


    @Bean("appiumLog")
    public String getAppiumLog() {
        return appiumLog;
    }

    @Bean("fullReset")
    public Boolean fullReset() { return fullReset; }
}