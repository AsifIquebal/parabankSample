import base.BaseTest;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import pageObjects.AccountServices;

public class TestClass2 extends BaseTest {

    @BeforeMethod
    public void setUp() {
        loginPage = launchApplication();
    }

    @Test(priority = 1)
    public void verifyUserRegistration(){
        loginPage.clickOnRegisterLink().registerUser();
    }

    @Test(priority = 2)
    public void verifyWelComeMessage(){
        accountServices = loginPage.login("aut556", "aut556", AccountServices.class);
        Assert.assertTrue(accountServices.getWelComeText().equalsIgnoreCase("Welcome John Doe"));
    }

}
