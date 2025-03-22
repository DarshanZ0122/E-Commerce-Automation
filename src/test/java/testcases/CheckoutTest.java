package testcases;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import utilities.DriverSetup;

public class CheckoutTest {

    WebDriver driver;

    @BeforeMethod
    public void setUp() {
        driver = DriverSetup.getDriver();
        driver.get("https://www.saucedemo.com/");
    }

    @Test
    public void testCheckoutWithHardcodedCredentials() {
        // ✅ Hardcoded username and password
        String username = "standard_user";
        String password = "secret_sauce";

        // ✅ Login
        driver.findElement(By.id("user-name")).sendKeys(username);
        driver.findElement(By.id("password")).sendKeys(password);
        driver.findElement(By.id("login-button")).click();

        // ✅ Verify login success
        Assert.assertTrue(driver.findElement(By.cssSelector(".inventory_list")).isDisplayed(), "Login Failed!");

        // ✅ Add product to cart
        driver.findElement(By.cssSelector(".inventory_item:first-child button")).click();
        driver.findElement(By.className("shopping_cart_link")).click();

        // ✅ Proceed to checkout
        driver.findElement(By.id("checkout")).click();

        // ✅ Enter checkout details
        driver.findElement(By.id("first-name")).sendKeys("John");
        driver.findElement(By.id("last-name")).sendKeys("Doe");
        driver.findElement(By.id("postal-code")).sendKeys("12345");
        driver.findElement(By.id("continue")).click();

        // ✅ Finish checkout
        driver.findElement(By.id("finish")).click();

        // ✅ Verify order success
        WebElement confirmationMessage = driver.findElement(By.className("complete-header"));
        Assert.assertEquals(confirmationMessage.getText(), "Thank you for your order!", "Order Confirmation Failed!");

        System.out.println("✅ Test Passed: Checkout Successful!");
    }

    @AfterMethod
    public void tearDown() {
        DriverSetup.closeDriver(); // ✅ Close the browser after test
    }
}
