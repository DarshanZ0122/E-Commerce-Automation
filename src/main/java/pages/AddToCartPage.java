package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class AddToCartPage {
    WebDriver driver;
    WebDriverWait wait;

    // Define selector properly
    private final By addToCartButton = By.id("add-to-cart-sauce-labs-backpack");

    public AddToCartPage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10)); // Add explicit wait
    }

    public void addItemToCart() {
        // Wait until element is visible and clickable
        WebElement element = wait.until(ExpectedConditions.elementToBeClickable(addToCartButton));
        element.click();
    }
}
