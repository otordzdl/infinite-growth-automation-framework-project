package pages;

import io.github.otordzdl.infinitegrowth.core.selenium.Wrapper;
import org.openqa.selenium.By;
import org.testng.Assert;

public class HomePage {
    private Wrapper wrapper;
    private By burguerMenu = By.id("react-burger-menu-btn");
    private By shoppingcartLink = By.className("shopping_cart_link");
    private By lblLogo = By.className("app_logo");

    private By shoppingCartBadge = By.className("shopping_cart_badge");
    private String productAddButton = "//div[@class='inventory_item_name' and text()='%s']//ancestor::div[@class='inventory_item_description']//button[text()='Add to cart']";
    private String productRemoveButton = "//div[@class='inventory_item_name' and text()='%s']//ancestor::div[@class='inventory_item_description']//button[text()='Remove']";
    private String productPrice = "//div[@class='inventory_item_name' and text()='%s']//ancestor::div[@class='inventory_item_description']//div[@class='inventory_item_price']";

    public HomePage(Wrapper wrapper) {
        this.wrapper = wrapper;
    }

    public void validateHome() {
        wrapper.reviewIfElementIsDisplayed(burguerMenu);
        wrapper.reviewIfElementIsDisplayed(shoppingcartLink);
        wrapper.reviewIfElementIsDisplayed(lblLogo);
    }

    public void addProductToCart(String product) {
        wrapper.clickInElement(By.xpath(String.format(productAddButton, product)));
    }

    public void removeProductToCart(String product) {
        wrapper.clickInElement(By.xpath(String.format(productRemoveButton, product)));
    }

    public void validateShoppingCartBadge(int productsAdded) {
        String actualProductsAddedString = wrapper.getTextFromElement(shoppingCartBadge);
        int actualProductsAdded;
        try {
            actualProductsAdded = Integer.parseInt(actualProductsAddedString);
        } catch (Exception e) {
            actualProductsAdded = 0;
        }

        Assert.assertEquals(actualProductsAdded,productsAdded,"Validar Productos en Carrito");

    }

    public void goToShoppingCart(){
        wrapper.clickInElement(shoppingcartLink);
    }


}
