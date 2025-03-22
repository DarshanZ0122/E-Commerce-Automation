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
