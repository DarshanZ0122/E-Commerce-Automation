/*package testcases;

import base.BaseTest;
import org.testng.Assert;
import org.testng.Reporter;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import pages.LoginPage;
import pages.LogoutPage;
import utilities.ExcelReader;
import org.openqa.selenium.By;

public class LogoutTest extends BaseTest {

    @DataProvider(name = "loginData")
    public Object[][] getLoginData() {
        ExcelReader.loadExcel("src/main/resources/TestData.xlsx");
        return new Object[][] {
                { ExcelReader.getCellData("Sheet1", 1, 1), ExcelReader.getCellData("Sheet1", 1, 2) } // Standard User
        };
    }

    @Test(dataProvider = "loginData", timeOut = 60000) // Timeout set to 60 seconds
    public void testLogout(String username, String password) {
        LoginPage loginPage = new LoginPage(driver);
        LogoutPage logoutPage = new LogoutPage(driver);

        // ✅ Step 1: Login with provided credentials
        loginPage.login(username, password);

        // ✅ Step 2: Verify login was successful
        Assert.assertTrue(driver.getCurrentUrl().contains("inventory"),
                "❌ Login failed - Logout test cannot proceed!");

        Reporter.log("✅ Login successful for logout test");

        // ✅ Step 3: Perform logout action
        logoutPage.logout();

        // ✅ Step 4: Verify logout was successful by checking login button visibility using XPath
        boolean isLoginButtonVisible = driver.findElement(By.xpath("//*[@id=\"login-button\"]")).isDisplayed();
        Assert.assertTrue(isLoginButtonVisible, "❌ Logout failed - Login button is not visible!");

        Reporter.log("✅ Logout successful");
        System.out.println("✅ Test Passed: Logout successful!");
    }

    @AfterMethod
    public void tearDown() {
        driver.quit();
    }
}*/

package testcases;

import base.BaseTest;
import com.aventstack.extentreports.*;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.ITestResult;
import org.testng.annotations.*;
import pages.LoginPage;
import pages.LogoutPage;
import utilities.ExcelReader;

public class LogoutTest extends BaseTest {

    private static ExtentReports extent;
    private static ExtentTest extentTest;

    // ✅ Initialize Extent Report before suite
    @BeforeSuite
    public void setupExtentReport() {
        ExtentSparkReporter spark = new ExtentSparkReporter("test-output/ExtentReport_Logout.html");
        spark.config().setDocumentTitle("Logout Test Report");
        spark.config().setReportName("Logout Functionality Test Results");
        spark.config().setTheme(com.aventstack.extentreports.reporter.configuration.Theme.DARK);

        extent = new ExtentReports();
        extent.attachReporter(spark);
        extent.setSystemInfo("Tester", "Darshan Zore");
        extent.setSystemInfo("Environment", "QA");
        extent.setSystemInfo("Project", "SauceDemo Logout Test");
    }

    @DataProvider(name = "loginData")
    public Object[][] getLoginData() {
        ExcelReader.loadExcel("src/main/resources/TestData.xlsx");
        return new Object[][] {
                { ExcelReader.getCellData("Sheet1", 1, 1), ExcelReader.getCellData("Sheet1", 1, 2) } // Standard User
        };
    }

    @Test(dataProvider = "loginData", timeOut = 60000)
    public void testLogout(String username, String password) {
        extentTest = extent.createTest("Logout Test for user: " + username);

        LoginPage loginPage = new LoginPage(driver);
        LogoutPage logoutPage = new LogoutPage(driver);

        try {
            // ✅ Step 1: Login
            loginPage.login(username, password);
            extentTest.info("Entered username and password for: " + username);

            // ✅ Step 2: Verify login successful
            Assert.assertTrue(driver.getCurrentUrl().contains("inventory"), "❌ Login failed - Logout test cannot proceed!");
            extentTest.pass("✅ Login successful for logout test");

            // ✅ Step 3: Perform logout
            logoutPage.logout();
            extentTest.info("Clicked on logout button");

            // ✅ Step 4: Verify logout success
            boolean isLoginButtonVisible = driver.findElement(By.xpath("//*[@id='login-button']")).isDisplayed();
            Assert.assertTrue(isLoginButtonVisible, "❌ Logout failed - Login button not visible!");
            extentTest.pass("✅ Logout successful - Login button visible");

            System.out.println("✅ Test Passed: Logout successful!");

        } catch (AssertionError ae) {
            extentTest.fail("❌ Assertion Failed: " + ae.getMessage());
            throw ae;
        } catch (Exception e) {
            extentTest.fail("❌ Exception during test: " + e.getMessage());
            throw e;
        }
    }

    // ✅ Capture test results and log them
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

        // ✅ Quit driver after each test
        driver.quit();
    }

    // ✅ Flush Extent Report at the end
    @AfterSuite
    public void tearDownExtent() {
        if (extent != null) {
            extent.flush();
        }
    }
}

