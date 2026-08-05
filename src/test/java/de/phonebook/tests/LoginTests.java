package de.phonebook.tests;

import de.phonebook.core.TestBase;
import de.phonebook.model.User;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class LoginTests extends TestBase {

    @BeforeMethod
    public void ensurePrecondition() {
        if (!app.getUser().isLoginLinkPresent()) {
            app.getUser().clickOnSignOutButton();
        }
    }

    @Test
    public void loginRegisteredUserPositiveTest() {
        app.getUser().clickOnLoginLink();

        User user = new User()
                .setEmail("karyna.autotest.20260729@gmail.com")
                .setPassword("Aa12345!");

        app.getUser().fillLoginRegistrationForm(user);
        app.getUser().clickOnLoginButton();

        Assert.assertTrue(
                app.getUser().isSignOutButtonPresent()
        );
    }

    @Test
    public void loginWithoutEmailNegativeTest() {
        app.getUser().clickOnLoginLink();

        User user = new User()
                .setEmail("")
                .setPassword("Aa12345!");

        app.getUser().fillLoginRegistrationForm(user);
        app.getUser().clickOnLoginButton();

        Assert.assertTrue(
                app.getUser().isAlertPresent()
        );
    }
}