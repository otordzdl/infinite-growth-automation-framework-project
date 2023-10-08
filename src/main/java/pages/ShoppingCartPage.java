package pages;

import io.github.otordzdl.infinitegrowth.core.selenium.Wrapper;
import org.openqa.selenium.By;
import org.testng.Assert;

public class ShoppingCartPage {
    private Wrapper wrapper;
    private By continueShoppingButton = By.id("continue-shopping");
    private By checkoutButton = By.id("checkout");
    private By cartTitle = By.xpath("//*[@class='title' and text()='Your Cart']");

    private String removeProduct = "//div[@class='inventory_item_name' and text()='%s']//ancestor::div[@class='cart_item_label']//button[text()='Remove']";
    private String priceProduct = "//div[@class='inventory_item_name' and text()='%s']//ancestor::div[@class='cart_item_label']//div[@class='inventory_item_price']";
    private String productLink="//div[@class='inventory_item_name' and text()='%s']";
    public ShoppingCartPage(Wrapper wrapper) {
        this.wrapper = wrapper;
    }

    public void validateShopingCartPage(){
        Assert.assertTrue(wrapper.reviewIfElementIsDisplayed(continueShoppingButton) &&
                wrapper.reviewIfElementIsDisplayed(checkoutButton) &&
                wrapper.reviewIfElementIsDisplayed(cartTitle),"Validar que la pagina ShoppingCart se muestre correctamente");
    }

    public void removeProduct(String product){
        wrapper.clickInElement(By.xpath(String.format(removeProduct,product)));
    }

    public void reviewProductInShoppingCart(String product) {
        Assert.assertTrue(wrapper.reviewIfElementIsDisplayed(By.xpath(String.format(productLink, product))),
                "Validar que se muestra el producto "+ product);
    }
    public void reviewPriceByProduct(String product,String price){
        String actualPrice=wrapper.getTextFromElement(By.xpath(String.format(priceProduct,product)));
        Assert.assertEquals(actualPrice,price,"Validar que el precio del producto en el Carrito sea el mismo al del catalogo");
    }

    public void continueCheckout(){
        wrapper.clickInElement(checkoutButton);
    }

    public void backToProducts(){
        wrapper.clickInElement(continueShoppingButton);
    }

}
