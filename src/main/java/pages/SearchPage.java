package pages;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class SearchPage {
    WebDriver driver;

    private final By searchBox = By.id("search"); // Change locator as per website
    private final By searchButton = By.id("search-button");

    public SearchPage(WebDriver driver) {
        this.driver = driver;
    }

    public void searchProduct(String productName) {
        driver.findElement(searchBox).sendKeys(productName);
        driver.findElement(searchButton).click();
    }
}
