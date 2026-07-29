package de.phonebook.tests;

import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.annotations.Test;

public class CreateAccountTest extends TestBase {

    @Test
    public void registerPositiveTest() {
        click(By.cssSelector("[href='/login']"));

        String email = "karyna" + System.currentTimeMillis() + "@gmail.com";

        type(By.name("email"), email);
        type(By.name("password"), "Aa12345!");
        click(By.name("registration"));

        Assert.assertTrue(
                isElementPresent(By.xpath("//*[.='Sign Out']"))
        );
    }

    @Test
    public void registerExistingUserNegativeTest() {
        click(By.cssSelector("[href='/login']"));

        type(By.name("email"), "karyna.autotest.20260729@gmail.com");
        type(By.name("password"), "Aa12345!");
        click(By.name("registration"));

        Assert.assertTrue(isAlertPresent());
    }
}