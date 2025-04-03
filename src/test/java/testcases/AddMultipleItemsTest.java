package testcases;

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
