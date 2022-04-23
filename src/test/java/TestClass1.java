import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import pageObjects.AccountServices;
import pageObjects.LoginPage;
import base.BaseTest;
import utility.MyUtils;

import java.util.ArrayList;
import java.util.List;

public class TestClass1 extends BaseTest {

    LoginPage loginPage;
    AccountServices accountServices;

    @BeforeClass
    public void cleanUpUsersByResettingDB(){
        clearDataBase();
    }

    @BeforeMethod
    public void setUp() {
        loginPage = launchApplication();
    }

    @Test
    public void test01_checkAndCreateUser() {
        //
        //accountServices = loginPage.enterUserName("aut556").enterPassword("aut556").clickOnSignInButton();
        // using generic type is useful
        accountServices = loginPage.login("aut556","aut556", AccountServices.class);
        String pageTitle = accountServices.getPageTitle();
        List<String> accounts = new ArrayList<>();
        if (pageTitle.equalsIgnoreCase("ParaBank | Accounts Overview")) {
            logger.info("User login successful");
            accounts = accountServices.getAllAccounts();
        } else if (pageTitle.equalsIgnoreCase("ParaBank | Error")) {
            loginPage.clickOnRegisterLink().registerUser();
            loginPage.clickOnLogOut();
            launchApplication();
            accounts = loginPage
                    .enterUserName(MyUtils.getPropertiesFile().getProperty("username"))
                    .enterPassword(MyUtils.getPropertiesFile().getProperty("password"))
                    .clickOnSignInButton().getAllAccounts();
        }
        logger.info("Accounts listed: " + accounts);
        Assert.assertTrue(accounts.size() > 0, "No Accounts found");
    }

    @Test
    public void test02_loginTest() {
        loginPage
                .enterUserName(MyUtils.getPropertiesFile().getProperty("username"))
                .enterPassword(MyUtils.getPropertiesFile().getProperty("password"));
        accountServices = loginPage.clickOnSignInButton();
        Assert.assertEquals(accountServices.getPageTitle(), "ParaBank | Accounts Overview");
    }

    @Test
    public void testABCase_GenericReturnType() {
        loginPage = loginPage.login("incorrect", "0101", LoginPage.class);
        System.out.println(loginPage.getInvalidLoginErrorMessage());
        loginPage.clickOnLogOut();
        accountServices = loginPage.login("aut556", "aut556", AccountServices.class);

    }

    @AfterMethod
    public void cleanUp() {
        tearDown();
    }

}
