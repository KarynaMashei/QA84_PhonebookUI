package de.phonebook.core;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
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
        return driver.findElements(locator).size() > 0;
    }

    public void type(By locator, String text) {
        click(locator);
        driver.findElement(locator).clear();
        driver.findElement(locator).sendKeys(text);
    }

    public void click(By locator) {
        driver.findElement(locator).click();
    }

    public boolean isAlertPresent() {
        Alert alert = new WebDriverWait(driver, Duration.ofSeconds(20))
                .until(ExpectedConditions.alertIsPresent());

        if (alert == null) {
            return false;
        } else {
            alert.accept();
            return true;
        }
    }

    public void pause(int millis) {
        try {
            Thread.sleep(millis);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            throw new RuntimeException(e);
        }
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