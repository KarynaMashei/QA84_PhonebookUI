package de.phonebook.tests;

import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.time.Duration;

public class RemoveContactTests extends TestBase {

    @BeforeMethod
    public void precondition() {
        clickOnLoginLink();

        fillLoginRegistrationForm(
                "karyna.autotest.20260729@gmail.com",
                "Aa12345!"
        );

        clickOnLoginButton();
        clickOnAddLink();

        fillAddContactForm(
                "Oliver",
                "Kan",
                "1234567890",
                "oliver.kan@gmail.com",
                "Berlin",
                "QA contact"
        );

        clickOnSaveButton();
    }

    @Test
    public void removeContactPositiveTest() {
        int sizeBefore = sizeOfContacts();

        removeContact();

        new WebDriverWait(driver, Duration.ofSeconds(10))
                .until(webDriver ->
                        sizeOfContacts() == sizeBefore - 1
                );

        int sizeAfter = sizeOfContacts();

        Assert.assertEquals(sizeAfter, sizeBefore - 1);
    }
}
