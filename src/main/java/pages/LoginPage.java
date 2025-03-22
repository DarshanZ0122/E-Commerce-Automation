package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class LoginPage {
    WebDriver driver;

    // Locators
    private final By usernameField = By.id("user-name");
    private final By passwordField = By.id("password");
    private final By loginButton = By.id("login-button");
    private final By errorMessage = By.cssSelector(".error-message-container.error");
    private final By errorButton = By.className("error-button");

    // Constructor
    public LoginPage(WebDriver driver) {
        this.driver = driver;
    }

    // ✅ Enter username
    public void enterUsername(String username) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
        wait.until(ExpectedConditions.visibilityOfElementLocated(usernameField)).sendKeys(username);
    }

    // ✅ Enter password
    public void enterPassword(String password) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
        wait.until(ExpectedConditions.visibilityOfElementLocated(passwordField)).sendKeys(password);
    }

    // ✅ Click login button
    public void clickLogin() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
        wait.until(ExpectedConditions.elementToBeClickable(loginButton)).click();
    }

    // ✅ Combined login method
    public void login(String username, String password) {
        enterUsername(username);
        enterPassword(password);
        clickLogin();
    }

    // ✅ Get login error message
    public String getLoginErrorMessage() {
        try {
            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
            WebElement errorElement = wait.until(ExpectedConditions.visibilityOfElementLocated(errorMessage));
            return errorElement.getText();
        } catch (Exception e) {
            return null;
        }
    }

    // ✅ Check if the error button is displayed
    public boolean isErrorButtonDisplayed() {
        try {
            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
            return wait.until(ExpectedConditions.visibilityOfElementLocated(errorButton)).isDisplayed();
        } catch (Exception e) {
            return false;
        }
    }
}
