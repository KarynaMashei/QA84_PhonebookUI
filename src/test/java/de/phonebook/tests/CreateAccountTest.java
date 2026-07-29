package de.phonebook.tests;

import org.testng.Assert;
import org.testng.annotations.Test;

public class CreateAccountTest extends TestBase {

    @Test
    public void registerPositiveTest() {
        clickOnLoginLink();

        fillLoginRegistrationForm(
                newEmail(),
                "Aa12345!"
        );

        clickOnRegistrationButton();

        Assert.assertTrue(isSignOutButtonPresent());
    }

    @Test
    public void registerExistingUserNegativeTest() {
        clickOnLoginLink();

        fillLoginRegistrationForm(
                "karyna.autotest.20260729@gmail.com",
                "Aa12345!"
        );

        clickOnRegistrationButton();

        Assert.assertTrue(isAlertPresent());
    }
}