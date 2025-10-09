/*package testcases;

import base.BaseTest;
import org.testng.Assert;
import org.testng.annotations.Test;
import pages.AddMultipleItemsPage;
import pages.LoginPage;

public class AddMultipleItemsTest extends BaseTest {

    @Test
    public void testAddFourItemsToCart() {
        LoginPage loginPage = new LoginPage(driver);
        loginPage.login("standard_user", "secret_sauce");

        AddMultipleItemsPage addMultipleItemsPage = new AddMultipleItemsPage(driver);
        addMultipleItemsPage.addMultipleItemsToCart(4);

        // Validate cart count
        Assert.assertEquals(addMultipleItemsPage.getCartItemCount(), "4", "Cart badge count mismatch!");

        System.out.println("✅ Successfully added 4 items to the cart!");
    }
}
*/
package testcases;

import base.BaseTest;
import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import org.testng.Assert;
import org.testng.annotations.*;
import pages.AddMultipleItemsPage;
import pages.LoginPage;

public class AddMultipleItemsTest extends BaseTest {

    static ExtentReports extent;
    ExtentTest test;

    @BeforeSuite
    public void setupReport() {
        ExtentSparkReporter spark = new ExtentSparkReporter("ExtentReports/AddMultipleItemsReport.html");
        extent = new ExtentReports();
        extent.attachReporter(spark);
        extent.setSystemInfo("Tester", "Darshan Zore");
        extent.setSystemInfo("Project", "SauceDemo Automation");
        extent.setSystemInfo("Browser", "Chrome");
    }

    @BeforeMethod
    public void createTest() {
        test = extent.createTest("Add Multiple Items to Cart Test");
    }

    @Test
    public void testAddFourItemsToCart() {
        try {
            test.log(Status.INFO, "Starting Add Multiple Items Test...");

            // ✅ Step 1: Login
            LoginPage loginPage = new LoginPage(driver);
            loginPage.login("standard_user", "secret_sauce");
            test.log(Status.PASS, "Successfully logged in with valid credentials");

            // ✅ Step 2: Add multiple items
            AddMultipleItemsPage addMultipleItemsPage = new AddMultipleItemsPage(driver);
            addMultipleItemsPage.addMultipleItemsToCart(4);
            test.log(Status.PASS, "Added 4 items to cart");

            // ✅ Step 3: Validate cart count
            String actualCount = addMultipleItemsPage.getCartItemCount();
            Assert.assertEquals(actualCount, "4", "Cart badge count mismatch!");
            test.log(Status.PASS, "Cart count validated successfully: " + actualCount);

            System.out.println("✅ Successfully added 4 items to the cart!");
            test.log(Status.INFO, "✅ Successfully added 4 items to the cart!");

        } catch (Exception e) {
            test.log(Status.FAIL, "❌ Test failed due to: " + e.getMessage());
            Assert.fail("Test failed: " + e.getMessage());
        }
    }

    @AfterMethod
    public void flushReport() {
        extent.flush();
    }

    @AfterSuite
    public void tearDownReport() {
        extent.flush();
    }
}
