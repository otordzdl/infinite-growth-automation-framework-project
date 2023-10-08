package tests;

import com.aventstack.extentreports.Status;
import io.github.otordzdl.infinitegrowth.core.base.BaseWebTest;
import org.testng.Assert;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import pages.*;


public class E2ETest extends BaseWebTest {

    @Test(description = "Prueba E2E Exitosa")
    @Parameters({"url", "user", "password","products"})
    public void compraProductosExitosa(String url, String user, String password, String products) {

        LoginPage loginPage = new LoginPage(wrapper);
        HomePage homePage = new HomePage(wrapper);
        ShoppingCartPage shoppingCartPage = new ShoppingCartPage(wrapper);
        CheckoutPage checkoutPage = new CheckoutPage(wrapper);
        CheckoutOverviewPage checkoutOverviewPage = new CheckoutOverviewPage(wrapper);
        CheckoutCompletePage checkoutCompletePage = new CheckoutCompletePage(wrapper);
        String[] productList = products.split("\\|");

        wrapper.navigateTo(url);
        loginPage.enterCredentials(user, password);
        homePage.validateHome();

        int productcInBadge = 0;

        for (String product : productList) {
            homePage.addProductToCart(product);
            productcInBadge++;
        }

        homePage.validateShoppingCartBadge(productcInBadge);
        homePage.goToShoppingCart();

        shoppingCartPage.validateShopingCartPage();

        for (String product : productList) {
            shoppingCartPage.reviewProductInShoppingCart(product);
        }

        shoppingCartPage.continueCheckout();

        checkoutPage.validateCheckoutPage();
        checkoutPage.fillCheckoutForm("juan", "ramos", "123");

        checkoutOverviewPage.validateCheckoutOverview();

        for (String product : productList) {
            checkoutOverviewPage.validateProductInSummary(product);
        }

        checkoutOverviewPage.finishPurchase();

        checkoutCompletePage.validateCheckoutComplete();


        test.log(Status.PASS, "La prueba fue exitosa.");

    }
}
