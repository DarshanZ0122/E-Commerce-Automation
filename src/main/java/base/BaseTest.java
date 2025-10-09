package base;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import utilities.ExcelReader;

import java.time.Duration;

public class BaseTest {

    public WebDriver driver;

    @DataProvider(name = "userData")
    public Object[][] getUserData() {
        ExcelReader.loadExcel("src/main/resources/TestData.xlsx");
        return new Object[][]{
                {ExcelReader.getCellData("Sheet1", 1, 0), ExcelReader.getCellData("Sheet1", 1, 1)}, // Standard User
                {ExcelReader.getCellData("Sheet1", 2, 0), ExcelReader.getCellData("Sheet1", 2, 1)}, // Locked User
                {ExcelReader.getCellData("Sheet1", 3, 0), ExcelReader.getCellData("Sheet1", 3, 1)}, // Performance Glitch User
                {ExcelReader.getCellData("Sheet1", 4, 0), ExcelReader.getCellData("Sheet1", 4, 1)}  // Problem User
        };
    }

    @BeforeMethod
    public void setup() {
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        driver.get("https://www.saucedemo.com/");
    }

    @AfterMethod
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }
}
/* Cross Browser Testing;
package base;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.annotations.*;
import utilities.ExcelReader;

import java.net.MalformedURLException;
import java.net.URL;
import java.time.Duration;

public class BaseTest {

    protected WebDriver driver;

    @DataProvider(name = "userData")
    public Object[][] getUserData() {
        ExcelReader.loadExcel("src/main/resources/TestData.xlsx");
        return new Object[][]{
                {ExcelReader.getCellData("Sheet1", 1, 0), ExcelReader.getCellData("Sheet1", 1, 1)}, // Standard User
                {ExcelReader.getCellData("Sheet1", 2, 0), ExcelReader.getCellData("Sheet1", 2, 1)}, // Locked User
                {ExcelReader.getCellData("Sheet1", 3, 0), ExcelReader.getCellData("Sheet1", 3, 1)}, // Performance Glitch User
                {ExcelReader.getCellData("Sheet1", 4, 0), ExcelReader.getCellData("Sheet1", 4, 1)}  // Problem User
        };
    }

    // ✅ Accept browser name from TestNG XML or default to Chrome
    @Parameters({"browser", "useGrid"})
    @BeforeMethod
    public void setup(@Optional("chrome") String browser, @Optional("false") String useGrid) throws MalformedURLException {

        System.out.println("=======================================");
        System.out.println("Starting test on browser: " + browser + " | Grid mode: " + useGrid);
        System.out.println("=======================================");

        // ✅ If useGrid = true → use RemoteWebDriver
        if (useGrid.equalsIgnoreCase("true")) {
            DesiredCapabilities capabilities = new DesiredCapabilities();

            switch (browser.toLowerCase()) {
                case "firefox":
                    capabilities.setBrowserName("firefox");
                    break;
                case "edge":
                    capabilities.setBrowserName("MicrosoftEdge");
                    break;
                case "chrome":
                default:
                    capabilities.setBrowserName("chrome");
                    break;
            }

            driver = new RemoteWebDriver(new URL("http://localhost:4444/wd/hub"), capabilities);
        } else {
            // ✅ Run locally if Grid not used
            switch (browser.toLowerCase()) {
                case "firefox":
                    WebDriverManager.firefoxdriver().setup();
                    driver = new FirefoxDriver();
                    break;
                case "edge":
                    WebDriverManager.edgedriver().setup();
                    driver = new EdgeDriver();
                    break;
                case "chrome":
                default:
                    WebDriverManager.chromedriver().setup();
                    driver = new ChromeDriver();
                    break;
            }
        }

        // ✅ Common setup
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        driver.get("https://www.saucedemo.com/");
    }

    @AfterMethod
    public void tearDown() {
        if (driver != null) {
            driver.quit();
            System.out.println("✅ Browser closed successfully.");
        }
    }
}

 */