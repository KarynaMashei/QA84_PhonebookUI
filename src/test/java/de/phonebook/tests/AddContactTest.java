package de.phonebook.tests;

import de.phonebook.core.TestBase;
import de.phonebook.model.Contact;
import de.phonebook.model.User;
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
                .setEmail("karyna.autotest.20260729@gmail.com")
                .setPassword("Aa12345!");

        app.getUser().fillLoginRegistrationForm(user);
        app.getUser().clickOnLoginButton();
    }

    @Test
    public void addContactPositiveTest() {
        app.getContact().clickOnAddLink();

        Contact contact = new Contact()
                .setName("Oliver")
                .setLastName("Kan")
                .setPhone("1234567890")
                .setEmail("oliver.kan@gmail.com")
                .setAddress("Berlin")
                .setDescription("QA contact");

        app.getContact().fillAddContactForm(contact);
        app.getContact().clickOnSaveButton();

        Assert.assertTrue(
                app.getContact().verifyByName("Oliver")
        );
    }

    @AfterMethod
    public void postcondition() {
        if (app.getContact().sizeOfContacts() > 0) {
            app.getContact().removeContact();
        }
    }
}