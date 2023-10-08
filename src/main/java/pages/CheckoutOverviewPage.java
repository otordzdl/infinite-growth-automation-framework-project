package pages;

import io.github.otordzdl.infinitegrowth.core.selenium.Wrapper;
import org.openqa.selenium.By;
import org.testng.Assert;

public class CheckoutOverviewPage {
    private Wrapper wrapper;
    private By cancelButton = By.id("cancel");
    private By finishButton = By.id("finish");
    private String productLink = "//div[@class='inventory_item_name' and text()='%s']";
    private String priceProduct = "//div[@class='inventory_item_name' and text()='%s']//ancestor::div[@class='cart_item_label']//div[@class='inventory_item_price']";
    private By subtotalLabel = By.className("summary_subtotal_label");
    private By taxesLabel = By.className("summary_tax_label");
    private By totalLabel = By.className("summary_total_label");

    public CheckoutOverviewPage(Wrapper wrapper) {
        this.wrapper = wrapper;
    }

    public void validateCheckoutOverview(){
        Assert.assertTrue(
                wrapper.reviewIfElementIsDisplayed(cancelButton) &&
                        wrapper.reviewIfElementIsDisplayed(finishButton) &&
                        wrapper.reviewIfElementIsDisplayed(subtotalLabel) &&
                        wrapper.reviewIfElementIsDisplayed(taxesLabel) &&
                        wrapper.reviewIfElementIsDisplayed(totalLabel),
                "Validar pagina Checkout Overview"

        );
    }

    public void validateProductInSummary(String product){
        Assert.assertTrue(wrapper.reviewIfElementIsDisplayed( By.xpath(String.format(productLink,product))),
                "Validar si existe el producto "+product + " en la pagina CheckoutSummary");
    }

    public void finishPurchase(){
        wrapper.clickInElement(finishButton);
    }
}
