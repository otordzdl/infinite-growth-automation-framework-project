package tests;

import io.github.otordzdl.infinitegrowth.core.base.BaseWebTest;
import com.aventstack.extentreports.Status;
import org.testng.Assert;
import org.testng.annotations.*;
import pages.LoginPage;

public class LoginWebTest extends BaseWebTest {
    @Test(description="Prueba de Login Exitoso")
    @Parameters({"url","user","password"})
    public void loginExitoso(String url,String user,String password){


        wrapper.navigateTo(url);

        LoginPage loginPage= new LoginPage(wrapper);

        loginPage.enterCredentials(user,password);
        Boolean successLogin= loginPage.validateLogin();
        Assert.assertTrue(successLogin,"Revisar Login Exitoso");

        test.log(Status.PASS, "La prueba fue exitosa.");

    }

    @Test
    public void pruebaFallido(){
        test = extent.createTest("Prueba de inicio de sesión Fallido");

        wrapper.navigateTo("https://www.saucedemo.com/");

        LoginPage loginPage= new LoginPage(wrapper);

        loginPage.enterCredentials("standard_user","secret_sauec");
        Boolean successLogin= loginPage.validateLogin();
        Assert.assertTrue(successLogin,"Revisar Login Exitoso");

        test.log(Status.PASS, "La prueba fue exitosa.");

    }
}



