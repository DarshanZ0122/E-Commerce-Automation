/*package testcases;

import base.BaseTest;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.time.Duration;

public class AddToCartTest extends BaseTest {

    @Test
    public void testAddToCart() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(200));

        try {
            // Login to the application
            driver.findElement(By.id("user-name")).sendKeys("standard_user");
            driver.findElement(By.id("password")).sendKeys("secret_sauce");
            driver.findElement(By.id("login-button")).click();

            // Add a small wait after login to allow page load
            Thread.sleep(2000);

            // Wait for the product button to be clickable using CSS Selector
            WebElement addToCartButton = wait.until(ExpectedConditions.elementToBeClickable(
                    By.cssSelector("button.btn_primary")
            ));
            addToCartButton.click();

            // Capture screenshot after clicking the button
            takeScreenshot("After_AddToCart");

            // Validation - Check if item is added to cart
            WebElement cartBadge = wait.until(ExpectedConditions.visibilityOfElementLocated(
                    By.cssSelector(".shopping_cart_badge")
            ));
            Assert.assertEquals(cartBadge.getText(), "1", "Cart badge count mismatch");

            // ✅ Success Message
            System.out.println("✅ Item successfully added to the cart!");

        } catch (Exception e) {
            // ❌ Error Message
            System.err.println("❌ Test failed due to: " + e.getMessage());
            takeScreenshot("Test_Failure");
            Assert.fail("Test failed: " + e.getMessage());
        }
    }

    // Method to capture screenshot
    public void takeScreenshot(String fileName) {
        TakesScreenshot screenshot = ((TakesScreenshot) driver);
        File srcFile = screenshot.getScreenshotAs(OutputType.FILE);
        String filePath = "screenshots/" + fileName + ".png";
        try {
            Files.createDirectories(Paths.get("screenshots"));
            Files.copy(srcFile.toPath(), Paths.get(filePath));
            System.out.println("📸 Screenshot saved: " + filePath);
        } catch (IOException e) {
            System.err.println("❌ Failed to save screenshot: " + e.getMessage());
        }
    }
}*/
package testcases;

import base.BaseTest;
import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.*;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.time.Duration;

public class AddToCartTest extends BaseTest {

    ExtentReports extent;
    ExtentTest test;

    @BeforeSuite
    public void setUpReport() {
        ExtentSparkReporter spark = new ExtentSparkReporter("ExtentReports/AddToCartReport.html");
        extent = new ExtentReports();
        extent.attachReporter(spark);
        extent.setSystemInfo("Tester", "Darshan Zore");
        extent.setSystemInfo("Project", "SauceDemo Automation");
        extent.setSystemInfo("Browser", "Chrome");
    }

    @BeforeMethod
    public void startTest() {
        test = extent.createTest("Add to Cart Test - SauceDemo");
    }

    @Test
    public void testAddToCart() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));

        try {
            test.log(Status.INFO, "Navigating to SauceDemo login page...");
            driver.get("https://www.saucedemo.com/");

            // ✅ Login to the application
            test.log(Status.INFO, "Logging in with valid credentials");
            driver.findElement(By.id("user-name")).sendKeys("standard_user");
            driver.findElement(By.id("password")).sendKeys("secret_sauce");
            driver.findElement(By.id("login-button")).click();

            // Add wait after login
            Thread.sleep(2000);

            // ✅ Wait for product button and click
            WebElement addToCartButton = wait.until(ExpectedConditions.elementToBeClickable(
                    By.cssSelector("button.btn_primary")
            ));
            addToCartButton.click();
            test.log(Status.PASS, "Clicked on 'Add to Cart' button");

            // ✅ Capture screenshot after adding
            String screenshotPath = takeScreenshot("After_AddToCart");
            test.addScreenCaptureFromPath(screenshotPath);

            // ✅ Verify item added
            WebElement cartBadge = wait.until(ExpectedConditions.visibilityOfElementLocated(
                    By.cssSelector(".shopping_cart_badge")
            ));
            Assert.assertEquals(cartBadge.getText(), "1", "Cart badge count mismatch");
            test.log(Status.PASS, "Item successfully added to cart");

            System.out.println("✅ Item successfully added to the cart!");

        } catch (Exception e) {
            String errorScreenshot = takeScreenshot("Test_Failure");
            test.log(Status.FAIL, "Test failed due to exception: " + e.getMessage());
            test.addScreenCaptureFromPath(errorScreenshot);
            Assert.fail("❌ Test failed: " + e.getMessage());
        }
    }

    // ✅ Screenshot Method (with return path)
    public String takeScreenshot(String fileName) {
        TakesScreenshot screenshot = ((TakesScreenshot) driver);
        File srcFile = screenshot.getScreenshotAs(OutputType.FILE);
        String filePath = "screenshots/" + fileName + ".png";
        try {
            Files.createDirectories(Paths.get("screenshots"));
            Files.copy(srcFile.toPath(), Paths.get(filePath));
            System.out.println("📸 Screenshot saved: " + filePath);
            return filePath;
        } catch (IOException e) {
            System.err.println("❌ Failed to save screenshot: " + e.getMessage());
            return null;
        }
    }

    @AfterMethod
    public void tearDownTest() {
        extent.flush(); // ✅ Save report after each test
        System.out.println("📄 Extent Report generated successfully.");
    }

    @AfterSuite
    public void closeReport() {
        extent.flush();
    }
}

