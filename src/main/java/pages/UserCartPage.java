package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class UserCartPage {

    WebDriver driver;
    WebDriverWait wait;

    // Locators
    private final By addToCartButton = By.cssSelector("button.btn_primary");
    private final By cartBadge = By.cssSelector(".shopping_cart_badge");

    // Constructor
    public UserCartPage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }

    public void addItemToCart() {
        WebElement button = wait.until(ExpectedConditions.elementToBeClickable(addToCartButton));
        button.click();
    }

    public String getCartBadgeValue() {
        WebElement badge = wait.until(ExpectedConditions.visibilityOfElementLocated(cartBadge));
        return badge.getText();
    }

    public boolean isCartBadgeDisplayed() {
        try {
            return wait.until(ExpectedConditions.visibilityOfElementLocated(cartBadge)).isDisplayed();
        } catch (Exception e) {
            return false;
        }
    }
}
