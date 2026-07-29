package de.phonebook.tests;

import org.testng.Assert;
import org.testng.annotations.Test;

public class LoginTests extends TestBase {

    @Test
    public void loginRegisteredUserPositiveTest() {
        clickOnLoginLink();

        fillLoginRegistrationForm(
                "karyna.autotest.20260729@gmail.com",
                "Aa12345!"
        );

        clickOnLoginButton();

        Assert.assertTrue(isSignOutButtonPresent());
    }
}
