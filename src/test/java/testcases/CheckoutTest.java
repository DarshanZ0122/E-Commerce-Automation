/*package testcases;

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
}*/
package testcases;

import com.aventstack.extentreports.*;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.ITestResult;
import org.testng.annotations.*;
import utilities.DriverSetup;

public class CheckoutTest {

    WebDriver driver;
    private static ExtentReports extent;
    private static ExtentTest extentTest;

    // ✅ Initialize Extent Report before suite
    @BeforeSuite
    public void setupExtentReport() {
        ExtentSparkReporter spark = new ExtentSparkReporter("test-output/ExtentReport_Checkout.html");
        spark.config().setDocumentTitle("Checkout Test Report");
        spark.config().setReportName("SauceDemo Checkout Flow");
        spark.config().setTheme(com.aventstack.extentreports.reporter.configuration.Theme.DARK);

        extent = new ExtentReports();
        extent.attachReporter(spark);
        extent.setSystemInfo("Tester", "Darshan Zore");
        extent.setSystemInfo("Environment", "QA");
        extent.setSystemInfo("Project", "SauceDemo Checkout Test");
    }

    @BeforeMethod
    public void setUp() {
        driver = DriverSetup.getDriver();
        driver.get("https://www.saucedemo.com/");
    }

    @Test
    public void testCheckoutWithHardcodedCredentials() {
        extentTest = extent.createTest("Checkout Flow Test - Standard User");

        try {
            // ✅ Hardcoded credentials
            String username = "standard_user";
            String password = "secret_sauce";
            extentTest.info("🔐 Using credentials: " + username + " / " + password);

            // ✅ Login
            driver.findElement(By.id("user-name")).sendKeys(username);
            driver.findElement(By.id("password")).sendKeys(password);
            driver.findElement(By.id("login-button")).click();
            extentTest.info("✅ Login submitted");

            // ✅ Verify login success
            Assert.assertTrue(driver.findElement(By.cssSelector(".inventory_list")).isDisplayed(), "Login Failed!");
            extentTest.pass("✅ Login successful - Inventory page loaded");

            // ✅ Add product to cart
            driver.findElement(By.cssSelector(".inventory_item:first-child button")).click();
            driver.findElement(By.className("shopping_cart_link")).click();
            extentTest.pass("🛒 Product added to cart successfully");

            // ✅ Proceed to checkout
            driver.findElement(By.id("checkout")).click();
            extentTest.info("➡️ Proceeding to checkout page");

            // ✅ Enter checkout details
            driver.findElement(By.id("first-name")).sendKeys("John");
            driver.findElement(By.id("last-name")).sendKeys("Doe");
            driver.findElement(By.id("postal-code")).sendKeys("12345");
            driver.findElement(By.id("continue")).click();
            extentTest.info("📦 Entered checkout details and clicked continue");

            // ✅ Finish checkout
            driver.findElement(By.id("finish")).click();
            extentTest.info("✅ Clicked finish to complete the order");

            // ✅ Verify order success
            WebElement confirmationMessage = driver.findElement(By.className("complete-header"));
            Assert.assertEquals(confirmationMessage.getText(), "Thank you for your order!", "Order Confirmation Failed!");
            extentTest.pass("🎉 Checkout completed successfully. Confirmation message verified.");

            System.out.println("✅ Test Passed: Checkout Successful!");

        } catch (AssertionError ae) {
            extentTest.fail("❌ Assertion failed: " + ae.getMessage());
            throw ae;
        } catch (Exception e) {
            extentTest.fail("❌ Exception occurred: " + e.getMessage());
            throw e;
        }
    }

    // ✅ Capture test results after each test
    @AfterMethod
    public void captureTestResult(ITestResult result) {
        if (result.getStatus() == ITestResult.FAILURE) {
            extentTest.log(Status.FAIL, "❌ TEST FAILED: " + result.getName());
            extentTest.log(Status.FAIL, "Reason: " + result.getThrowable());
        } else if (result.getStatus() == ITestResult.SUCCESS) {
            extentTest.log(Status.PASS, "✅ TEST PASSED: " + result.getName());
        } else if (result.getStatus() == ITestResult.SKIP) {
            extentTest.log(Status.SKIP, "⚠️ TEST SKIPPED: " + result.getName());
        }

        DriverSetup.closeDriver(); // ✅ Close browser
    }

    // ✅ Flush the Extent Report after all tests
    @AfterSuite
    public void tearDownExtent() {
        if (extent != null) {
            extent.flush();
        }
    }
}

