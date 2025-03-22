package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class CheckoutPage {

    WebDriver driver;
    WebDriverWait wait;

    // ✅ Locators
    private final By cartButton = By.className("shopping_cart_container");
    private final By checkoutButton = By.className("checkout_button");
    private final By firstNameField = By.id("first-name");
    private final By lastNameField = By.id("last-name");
    private final By zipCodeField = By.id("postal-code");
    private final By continueButton = By.id("continue");
    private final By finishButton = By.id("finish");
    private final By successMessage = By.cssSelector("#checkout_complete_container h2");

    // ✅ Constructor
    public CheckoutPage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(20));
    }

    // ✅ Proceed to checkout method with dynamic data
    public void proceedToCheckout(String firstName, String lastName, String zipCode) {
        // Step 1: Click on Cart
        wait.until(ExpectedConditions.elementToBeClickable(cartButton)).click();

        // Step 2: Click on Checkout Button
        wait.until(ExpectedConditions.elementToBeClickable(checkoutButton)).click();

        // Step 3: Fill Form
        wait.until(ExpectedConditions.visibilityOfElementLocated(firstNameField)).sendKeys(firstName);
        wait.until(ExpectedConditions.visibilityOfElementLocated(lastNameField)).sendKeys(lastName);
        wait.until(ExpectedConditions.visibilityOfElementLocated(zipCodeField)).sendKeys(zipCode);

        // Step 4: Continue and Finish
        wait.until(ExpectedConditions.elementToBeClickable(continueButton)).click();
        wait.until(ExpectedConditions.elementToBeClickable(finishButton)).click();
    }

    // ✅ Validation - Success Message
    public boolean isOrderSuccessful() {
        WebElement successMsg = wait.until(ExpectedConditions.visibilityOfElementLocated(successMessage));
        return successMsg.getText().equals("Thank you for your order!");
    }
}
