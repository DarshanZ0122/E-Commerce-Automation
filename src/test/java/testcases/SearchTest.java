package testcases;
import base.BaseTest;
import org.testng.annotations.Test;
import pages.SearchPage;

public class SearchTest extends BaseTest {
    @Test
    public void testSearchProduct() {
        SearchPage searchPage = new SearchPage(driver);
        searchPage.searchProduct("Laptop");
    }
}
