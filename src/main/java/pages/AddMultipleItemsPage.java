package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;

public class AddMultipleItemsPage {
    WebDriver driver;
    WebDriverWait wait;

    // Locators for add-to-cart buttons
    private final By addToCartButtons = By.cssSelector("button.btn_primary");
    private final By cartBadge = By.cssSelector(".shopping_cart_badge");

    public AddMultipleItemsPage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(1000));
    }

    public void addMultipleItemsToCart(int count) {
        List<WebElement> buttons = wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(addToCartButtons));

        if (buttons.size() >= count) {
            for (int i = 0; i < count; i++) {
                buttons.get(i).click();
            }
        } else {
            throw new RuntimeException("Not enough products available to add to cart!");
        }
    }

    public String getCartItemCount() {
        WebElement cartIcon = wait.until(ExpectedConditions.visibilityOfElementLocated(cartBadge));
        return cartIcon.getText();
    }
}
