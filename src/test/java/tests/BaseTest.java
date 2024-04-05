package tests;

import config.DriverConfig;
import io.appium.java_client.AppiumDriver;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.beans.factory.annotation.Autowired;
import org.testng.ITestContext;
import org.testng.annotations.BeforeMethod;
import utils.TestData;

@ContextConfiguration(classes = {DriverConfig.class, TestData.class})
public class BaseTest extends AbstractTestNGSpringContextTests {
    @Autowired
    AppiumDriver driver;

    @Autowired
    TestData testData;

    @BeforeMethod(alwaysRun = true)
    public void createTestContext(ITestContext context) {
        context.setAttribute("driver", driver);
    }
}
