import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import pageObjects.automationPracticePageObjects.AccountServices;
import pageObjects.automationPracticePageObjects.LoginPage;
import pageObjects.automationPracticePageObjects.RegisterPage;
import pageObjects.base.BaseTest;
import utility.MyUtils;

import java.util.ArrayList;
import java.util.List;

public class TestClass1 extends BaseTest {

    LoginPage loginPage;
    AccountServices accountServices;

    @BeforeMethod
    public void setUp() {
        loginPage = LaunchApplication();
    }

    @Test
    public void loginTest() {
        loginPage
                .enterUserName(MyUtils.getPropertiesFile().getProperty("username"))
                .enterPassword(MyUtils.getPropertiesFile().getProperty("password"));
        accountServices = loginPage.clickOnSignInButton();
        Assert.assertEquals(accountServices.getPageTitle(), "ParaBank | Accounts Overview");
    }

    @Test
    public void checkAndCreateUser() {
        accountServices = loginPage.enterUserName("aut556").enterPassword("aut556").clickOnSignInButton();
        String pageTitle = accountServices.getPageTitle();
        List<String> accounts = new ArrayList<>();
        if (pageTitle.equalsIgnoreCase("ParaBank | Accounts Overview")) {
            logger.info("User login successful");
            accounts = accountServices.getAllAccounts();
        } else if (pageTitle.equalsIgnoreCase("ParaBank | Error")) {
            loginPage.clickOnRegisterLink().registerUser();
            LaunchApplication();
            accounts = loginPage
                    .enterUserName(MyUtils.getPropertiesFile().getProperty("username"))
                    .enterPassword(MyUtils.getPropertiesFile().getProperty("password"))
                    .clickOnSignInButton().getAllAccounts();
        }
        logger.info("Accounts listed: " + accounts);
        Assert.assertTrue(accounts.size() > 0, "No Accounts found");
    }

    @Test
    public void testABCase() {
        loginPage = loginPage.login("123", "123", LoginPage.class);
        System.out.println(loginPage.getInvalidLoginErrorMessage());
        accountServices = loginPage.login("aut556", "aut556", AccountServices.class);
    }

    @Test
    public void test2() {
        LoginPage loginPage = new LoginPage(driver());
        RegisterPage registerPage = loginPage.clickOnRegisterLink();
        registerPage.registerUser();
    }


    /*@AfterTest
    public void tearDown(){

    }*/

}