package pages;

import io.github.otordzdl.infinitegrowth.core.selenium.Wrapper;
import org.openqa.selenium.By;
import org.testng.Assert;

public class CheckoutPage {
    private Wrapper wrapper;
    private By checkoutTitle = By.xpath("//span[@class='title' and text()='Checkout: Your Information']");
    private By cancelButton = By.id("cancel");
    private By continueButton = By.id("continue");
    private By firstNameInput = By.id("first-name");
    private By lastNameInput = By.id("last-name");
    private By zipInput = By.id("postal-code");

    public CheckoutPage(Wrapper wrapper) {
        this.wrapper = wrapper;
    }

    public void validateCheckoutPage(){
        Assert.assertTrue(
                wrapper.reviewIfElementIsDisplayed(checkoutTitle) &&
                        wrapper.reviewIfElementIsDisplayed(cancelButton) &&
                        wrapper.reviewIfElementIsDisplayed(continueButton) &&
                        wrapper.reviewIfElementIsDisplayed(firstNameInput) &&
                        wrapper.reviewIfElementIsDisplayed(lastNameInput) &&
                        wrapper.reviewIfElementIsDisplayed(zipInput),
                "Validar pantalla de Checkout"
        );
    }

    public void fillCheckoutForm(String name,String lastName, String zip){
        wrapper.fillFieldWithText(firstNameInput,name);
        wrapper.fillFieldWithText(lastNameInput,lastName);
        wrapper.fillFieldWithText(zipInput,zip);
        wrapper.clickInElement(continueButton);
    }


}
