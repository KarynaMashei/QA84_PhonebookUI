package de.phonebook.fw;

import de.phonebook.core.BaseHelper;
import de.phonebook.model.Contact;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;

public class ContactHelper extends BaseHelper {

    public ContactHelper(WebDriver driver) {
        super(driver);
    }

    public void clickOnSaveButton() {
        click(By.cssSelector(".add_form__2rsm2 button"));
    }

    public void fillAddContactForm(Contact contact) {
        type(By.xpath("//input[1]"), contact.getName());
        type(By.xpath("//input[2]"), contact.getLastName());
        type(By.xpath("//input[3]"), contact.getPhone());
        type(By.xpath("//input[4]"), contact.getEmail());
        type(By.xpath("//input[5]"), contact.getAddress());
        type(By.xpath("//input[6]"), contact.getDescription());
    }

    public void clickOnAddLink() {
        click(By.cssSelector("[href='/add']"));
    }

    public boolean verifyByName(String text) {
        return waitUntilAnyElementContainsText(By.cssSelector("h2"), text);
    }

    public boolean verifyByPhone(String phone) {
        return waitUntilAnyElementContainsText(
                By.cssSelector(".contact-item_card__2SOIM"),
                phone
        );
    }

    private boolean waitUntilAnyElementContainsText(By locator, String text) {
        return new WebDriverWait(driver, Duration.ofSeconds(10)).until(
                currentDriver -> currentDriver.findElements(locator).stream()
                        .map(WebElement::getText)
                        .anyMatch(value -> value.contains(text))
        );
    }

    public void removeContact() {
        click(By.cssSelector(".contact-item_card__2SOIM"));
        click(By.xpath("//button[.='Remove']"));
    }

    public void waitUntilContactsCountIsLessThan(int previousCount) {
        new WebDriverWait(driver, Duration.ofSeconds(10)).until(
                ExpectedConditions.numberOfElementsToBeLessThan(
                        By.cssSelector(".contact-item_card__2SOIM"), previousCount
                )
        );
    }
    public int sizeOfContacts() {
        if (isElementPresent(By.cssSelector(".contact-item_card__2SOIM"))) {
            return driver.findElements(
                    By.cssSelector(".contact-item_card__2SOIM")
            ).size();
        }

        return 0;
    }
}
