package de.phonebook.tests;

import de.phonebook.core.TestBase;
import de.phonebook.data.UserData;
import de.phonebook.model.Contact;
import de.phonebook.model.User;
import de.phonebook.utils.MyDataProviders;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class AddContactTest extends TestBase {

    @BeforeMethod
    public void precondition() {
        if (!app.getUser().isLoginLinkPresent()) {
            app.getUser().clickOnSignOutButton();
        }

        app.getUser().clickOnLoginLink();

        User user = new User()
                .setEmail(UserData.EMAIL)
                .setPassword(UserData.PASSWORD);

        app.getUser().fillLoginRegistrationForm(user);
        app.getUser().clickOnLoginButton();
    }

    @Test(
            dataProvider = "addNewContactFromCsv",
            dataProviderClass = MyDataProviders.class
    )
    public void addContactPositiveTest(Contact contact) {
        app.getContact().clickOnAddLink();
        app.getContact().fillAddContactForm(contact);
        app.getContact().clickOnSaveButton();

        Assert.assertTrue(
                app.getContact().verifyByName(contact.getName())
        );
    }

    @AfterMethod
    public void postcondition() {
        if (app.getContact().sizeOfContacts() > 0) {
            app.getContact().removeContact();
        }
    }
}