package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class LogoutPage {

    WebDriver driver;

    // Locators
    private final By menuButton = By.id("react-burger-menu-btn");
    private final By logoutButton = By.id("logout_sidebar_link");

    // Constructor
    public LogoutPage(WebDriver driver) {
        this.driver = driver;
    }

    // ✅ Perform Logout
    public void logout() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
        wait.until(ExpectedConditions.elementToBeClickable(menuButton)).click(); // Open menu
        wait.until(ExpectedConditions.elementToBeClickable(logoutButton)).click(); // Click logout
    }
}
