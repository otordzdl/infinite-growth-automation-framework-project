package tests;

import com.aventstack.extentreports.Status;
import io.github.otordzdl.infinitegrowth.core.base.BaseWebTest;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import pages.*;

public class CarritoTest extends BaseWebTest {
    @Test(description = "Agregar un producto y que aumente el numero de producto en carrito")
    @Parameters({"url", "user", "password","products"})
    public void agregarProductosValidarProductosCarrito(String url, String user, String password, String products) {

        LoginPage loginPage = new LoginPage(wrapper);
        HomePage homePage = new HomePage(wrapper);

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

        test.log(Status.PASS, "La prueba fue exitosa.");

    }

    @Test(description = "Validar que permita remover productos agregados y se actualice el numero de productos en carrito")
    @Parameters({"url", "user", "password","products"})
    public void agregarRemoverProductosValidarProductosCarrito(String url, String user, String password, String products) {

        LoginPage loginPage = new LoginPage(wrapper);
        HomePage homePage = new HomePage(wrapper);
        ShoppingCartPage shoppingCartPage = new ShoppingCartPage(wrapper);
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

        homePage.removeProductToCart(productList[0]);
        productcInBadge--;
        homePage.validateShoppingCartBadge(productcInBadge);


        test.log(Status.PASS, "La prueba fue exitosa.");

    }

}
