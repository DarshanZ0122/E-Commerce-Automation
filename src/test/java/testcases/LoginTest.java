package testcases;

import base.BaseTest;
import org.testng.Assert;
import org.testng.ITestResult;
import org.testng.Reporter;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import pages.LoginPage;
import utilities.ExcelReader;

public class LoginTest extends BaseTest {

    @DataProvider(name = "loginData")
    public Object[][] getLoginData() {
        ExcelReader.loadExcel("src/main/resources/TestData.xlsx");
        return new Object[][] {
                { ExcelReader.getCellData("Sheet1", 1, 1), ExcelReader.getCellData("Sheet1", 1, 2) }, // TC01 - Standard User
                { ExcelReader.getCellData("Sheet1", 2, 1), ExcelReader.getCellData("Sheet1", 2, 2) }, // TC02 - Locked User
                { ExcelReader.getCellData("Sheet1", 3, 1), ExcelReader.getCellData("Sheet1", 3, 2) }, // TC03 - Performance Glitch User
                { ExcelReader.getCellData("Sheet1", 4, 1), ExcelReader.getCellData("Sheet1", 4, 2) }, // TC04 - Problem User
                { ExcelReader.getCellData("Sheet1", 5, 1), ExcelReader.getCellData("Sheet1", 5, 2) }, // TC05 - Error User
                { ExcelReader.getCellData("Sheet1", 6, 1), ExcelReader.getCellData("Sheet1", 6, 2) }  // TC06 - Visual User
        };
    }

    @Test(dataProvider = "loginData", timeOut = 60000) // Timeout set to 60,000 milliseconds (1 minute)
    public void testValidLogin(String username, String password) {
        LoginPage loginPage = new LoginPage(driver);
        loginPage.enterUsername(username);
        loginPage.enterPassword(password);
        loginPage.clickLogin();

        try {
            // ✅ If login is successful, URL should contain 'inventory'
            if (driver.getCurrentUrl().contains("inventory")) {
                System.out.println("✅ Login successful for user: " + username);
                Reporter.log("✅ Login successful for user: " + username);
            } else {
                // ✅ Check if the error button is displayed for locked user case
                if (loginPage.isErrorButtonDisplayed()) {
                    String errorText = loginPage.getLoginErrorMessage();

                    if (errorText != null) {
                        if (errorText.contains("locked")) {
                            System.out.println("✅ User is locked as expected: " + username);
                            Reporter.log("✅ User is locked as expected: " + username);
                            // Test should PASS because system is behaving as expected
                        } else if (errorText.contains("performance")) {
                            System.out.println("⚠️ Performance glitch encountered for user: " + username);
                            Reporter.log("⚠️ Performance glitch encountered for user: " + username + " - Error: " + errorText);
                            Assert.fail("Performance glitch encountered for user: " + username + " - Error: " + errorText);
                        } else {
                            System.out.println("❌ Login failed for user: " + username + ". Error: " + errorText);
                            Reporter.log("❌ Login failed for user: " + username + ". Error: " + errorText);
                            Assert.fail("Login failed for user: " + username + ". Error: " + errorText);
                        }
                    } else {
                        System.out.println("❌ Error button displayed but no message for user: " + username);
                        Reporter.log("❌ Error button displayed but no message for user: " + username);
                        Assert.fail("Error button displayed but no message for user: " + username);
                    }
                } else {
                    // ✅ Handle unknown login failures
                    System.out.println("❌ Unknown error - Login failed for user: " + username);
                    Reporter.log("❌ Unknown error - Login failed for user: " + username);
                    Assert.fail("Unknown error - Login failed for user: " + username);
                }
            }
        } catch (Exception e) {
            System.out.println("❌ Exception for user: " + username + " - " + e.getMessage());
            Reporter.log("❌ Exception for user: " + username + " - " + e.getMessage());
            Assert.fail("Exception for user: " + username + " - " + e.getMessage());
        }
    }

    // ✅ Capture TestNG report after each test
    @AfterMethod
    public void captureTestResult(ITestResult result) {
        if (result.getStatus() == ITestResult.FAILURE) {
            Reporter.log("❌ TEST FAILED: " + result.getName());
            Reporter.log("❌ REASON: " + result.getThrowable());
            System.out.println("❌ TEST FAILED: " + result.getName());
            System.out.println("❌ REASON: " + result.getThrowable());
        } else if (result.getStatus() == ITestResult.SUCCESS) {
            Reporter.log("✅ TEST PASSED: " + result.getName());
            System.out.println("✅ TEST PASSED: " + result.getName());
        } else if (result.getStatus() == ITestResult.SKIP) {
            Reporter.log("⚠️ TEST SKIPPED: " + result.getName());
            System.out.println("⚠️ TEST SKIPPED: " + result.getName());
        }
    }
}
