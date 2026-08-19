package de.phonebook.tests;

import de.phonebook.core.TestBase;
import de.phonebook.model.User;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import de.phonebook.data.UserData;

public class CreateAccountTest extends TestBase {

    @BeforeMethod
    public void ensurePrecondition() {
        if (!app.getUser().isLoginLinkPresent()) {
            app.getUser().clickOnSignOutButton();
        }
    }

    @Test
    public void registerPositiveTest() {
        app.getUser().clickOnLoginLink();

        User user = new User()
                .setEmail(app.getUser().newEmail())
                .setPassword(UserData.PASSWORD);

        app.getUser().fillLoginRegistrationForm(user);
        app.getUser().clickOnRegistrationButton();

        Assert.assertTrue(
                app.getUser().isSignOutButtonPresent()
        );
    }

    @Test
    public void registerExistingUserNegativeTest() {
        app.getUser().clickOnLoginLink();

        User user = new User()
                .setEmail(UserData.EMAIL)
                .setPassword(UserData.PASSWORD);

        app.getUser().fillLoginRegistrationForm(user);
        app.getUser().clickOnRegistrationButton();

        Assert.assertTrue(
                app.getUser().isAlertPresent()
        );
    }
}