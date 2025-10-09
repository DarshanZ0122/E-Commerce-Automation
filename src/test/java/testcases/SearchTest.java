/*package testcases;
import base.BaseTest;
import org.testng.annotations.Test;
import pages.SearchPage;

public class SearchTest extends BaseTest {
    @Test
    public void testSearchProduct() {
        SearchPage searchPage = new SearchPage(driver);
        searchPage.searchProduct("Laptop");
    }
}*/

package testcases;

import base.BaseTest;
import com.aventstack.extentreports.*;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import org.testng.ITestResult;
import org.testng.annotations.*;
import pages.SearchPage;

public class SearchTest extends BaseTest {

    private static ExtentReports extent;
    private static ExtentTest extentTest;

    // ✅ Setup Extent Report before running tests
    @BeforeSuite
    public void setupExtentReport() {
        ExtentSparkReporter spark = new ExtentSparkReporter("test-output/ExtentReport_Search.html");
        spark.config().setDocumentTitle("Search Test Report");
        spark.config().setReportName("Product Search Test Results");
        spark.config().setTheme(com.aventstack.extentreports.reporter.configuration.Theme.DARK);

        extent = new ExtentReports();
        extent.attachReporter(spark);
        extent.setSystemInfo("Tester", "Darshan Zore");
        extent.setSystemInfo("Environment", "QA");
        extent.setSystemInfo("Project", "E-Commerce Search Functionality");
    }

    @Test
    public void testSearchProduct() {
        extentTest = extent.createTest("Search Product Test");

        SearchPage searchPage = new SearchPage(driver);

        try {
            extentTest.info("🔍 Starting product search for: Laptop");
            searchPage.searchProduct("Laptop");
            extentTest.pass("✅ Product search executed successfully for keyword: Laptop");

            // Optional: Add a small validation (like verifying search results)
            // Example:
            // Assert.assertTrue(searchPage.isSearchResultVisible(), "❌ No results displayed!");
            // extentTest.pass("✅ Search results displayed successfully.");

        } catch (AssertionError ae) {
            extentTest.fail("❌ Assertion failed: " + ae.getMessage());
            throw ae;
        } catch (Exception e) {
            extentTest.fail("❌ Exception occurred: " + e.getMessage());
            throw e;
        }
    }

    // ✅ Log result to report
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

        driver.quit();
    }

    // ✅ Flush Extent Report
    @AfterSuite
    public void tearDownExtent() {
        if (extent != null) {
            extent.flush();
        }
    }
}

