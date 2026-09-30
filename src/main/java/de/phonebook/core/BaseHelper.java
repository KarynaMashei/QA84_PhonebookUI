package de.phonebook.core;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.StandardCopyOption;
import java.time.Duration;

public class BaseHelper {

    protected WebDriver driver;

    public BaseHelper(WebDriver driver) {
        this.driver = driver;
    }

    public boolean isElementPresent(By locator) {
        return !driver.findElements(locator).isEmpty();
    }

    public void type(By locator, String text) {
        WebElement element = waitForVisibility(locator);
        element.clear();
        element.sendKeys(text);
    }

    public void click(By locator) {
        new WebDriverWait(driver, Duration.ofSeconds(10))
                .until(ExpectedConditions.elementToBeClickable(locator))
                .click();
    }

    public boolean isAlertPresent() {
        try {
            Alert alert = new WebDriverWait(driver, Duration.ofSeconds(10))
                    .until(ExpectedConditions.alertIsPresent());
            alert.accept();
            return true;
        } catch (TimeoutException e) {
            return false;
        }
    }

    protected WebElement waitForVisibility(By locator) {
        return new WebDriverWait(driver, Duration.ofSeconds(10))
                .until(ExpectedConditions.visibilityOfElementLocated(locator));
    }

    public String takeScreenshot() {
        File source = ((TakesScreenshot) driver)
                .getScreenshotAs(OutputType.FILE);

        File screenshotsFolder = new File("screenshots");

        if (!screenshotsFolder.exists() && !screenshotsFolder.mkdirs()) {
            throw new RuntimeException("Cannot create screenshots folder");
        }

        File screenshot = new File(
                screenshotsFolder,
                "screen-" + System.currentTimeMillis() + ".png"
        );

        try {
            Files.copy(
                    source.toPath(),
                    screenshot.toPath(),
                    StandardCopyOption.REPLACE_EXISTING
            );

            return screenshot.getAbsolutePath();
        } catch (IOException e) {
            throw new RuntimeException("Cannot save screenshot", e);
        }
    }
}
