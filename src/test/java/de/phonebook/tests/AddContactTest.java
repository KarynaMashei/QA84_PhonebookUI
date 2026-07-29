package de.phonebook.tests;

import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class AddContactTest extends TestBase {

    @BeforeMethod
    public void precondition() {
        clickOnLoginLink();
        fillLoginRegistrationForm(
                "karyna.autotest.20260729@gmail.com",
                "Aa12345!"
        );
        clickOnLoginButton();
    }

    @Test
    public void addContactPositiveTest() {
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

        Assert.assertTrue(verifyContactByName("Oliver"));
    }

    @AfterMethod
    public void postcondition() {
        if (isElementPresent(By.cssSelector(".contact-item_card__2SOIM"))) {
            removeContact();
        }
    }
}