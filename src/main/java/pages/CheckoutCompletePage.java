package pages;

import io.github.otordzdl.infinitegrowth.core.selenium.Wrapper;
import org.openqa.selenium.By;
import org.testng.Assert;

public class CheckoutCompletePage {
    private Wrapper wrapper;
    private By checkoutCompleteLabel = By.xpath("//span[@class='title' and text()='Checkout: Complete!']");
    private By backHomeButton = By.id("back-to-products");

    private By messageLabel = By.xpath("//h2[@class='complete-header' and text()='Thank you for your order!']");

    public CheckoutCompletePage(Wrapper wrapper) {
        this.wrapper = wrapper;
    }


    public void validateCheckoutComplete() {
        Assert.assertTrue(
                wrapper.reviewIfElementIsDisplayed(checkoutCompleteLabel) &&
                        wrapper.reviewIfElementIsDisplayed(backHomeButton) &&
                        wrapper.reviewIfElementIsDisplayed(messageLabel),
                "Validar pantalla Checkout Complete"
        );
    }
}
