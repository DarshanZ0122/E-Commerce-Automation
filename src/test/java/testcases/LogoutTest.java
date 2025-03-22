package testcases;

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
}
