package de.phonebook.core;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;

import java.lang.reflect.Method;
import java.util.Arrays;

public class TestBase {

    protected static ApplicationManager app =
            new ApplicationManager(
                    System.getProperty("browser", "chrome")
            );

    private static final Logger logger =
            LoggerFactory.getLogger(TestBase.class);

    @BeforeSuite
    public void setUp() {
        app.init();
    }

    @AfterSuite
    public void tearDown() {
        app.stop();
    }

    @BeforeMethod
    public void startTest(Method method, Object[] parameters) {
        logger.info(
                "Start test {} with parameters {}",
                method.getName(),
                Arrays.toString(parameters)
        );
    }

    @AfterMethod
    public void stopTest(ITestResult result) {
        String testName = result.getMethod().getMethodName();

        if (result.isSuccess()) {
            logger.info("Test PASSED: {}", testName);
        } else {
            try {
                String screenshotPath =
                        app.getUser().takeScreenshot();

                logger.error(
                        "Test FAILED: {}. Screenshot: {}",
                        testName,
                        screenshotPath,
                        result.getThrowable()
                );
            } catch (RuntimeException screenshotError) {
                logger.error(
                        "Test FAILED: {}. Screenshot could not be saved",
                        testName,
                        result.getThrowable()
                );
            }
        }
    }
}