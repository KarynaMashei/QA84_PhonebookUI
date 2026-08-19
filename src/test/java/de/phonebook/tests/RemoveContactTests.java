package de.phonebook.tests;

import de.phonebook.core.TestBase;
import de.phonebook.model.Contact;
import de.phonebook.model.User;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import de.phonebook.data.UserData;

public class RemoveContactTests extends TestBase {

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
    }

    @Test
    public void removeContactPositiveTest() {
        int sizeBefore = app.getContact().sizeOfContacts();


        app.getContact().removeContact();
        app.getContact().waitUntilContactsCountIsLessThan(sizeBefore);

        int sizeAfter = app.getContact().sizeOfContacts();

        Assert.assertEquals(sizeAfter, sizeBefore - 1);
    }
}