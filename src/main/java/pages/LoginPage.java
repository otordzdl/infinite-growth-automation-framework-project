package pages;

import io.github.otordzdl.infinitegrowth.core.selenium.Wrapper;
import org.openqa.selenium.By;
import org.testng.Assert;

public class LoginPage {
    private Wrapper wrapper;
    private By usernameField=By.id("user-name");
    private By passwordField=By.id("password");
    private By loginButton=By.id("login-button");

    private By errorMessage=By.xpath("//*[@data-test='error']");

    public LoginPage(Wrapper wrapper){
        this.wrapper=wrapper;
    }

    public void enterCredentials(String user, String password){
        wrapper.fillFieldWithText(usernameField,user);
        wrapper.fillFieldWithText(passwordField,password);
        wrapper.clickInElement(loginButton);
    }

    public void validateErrorLogin(){
        Assert.assertTrue(wrapper.reviewIfElementIsDisplayed(errorMessage));
    }

    public Boolean validateLogin(){
        return wrapper.reviewIfElementIsDisplayed(By.id("react-burger-menu-btn"));
    }



}
