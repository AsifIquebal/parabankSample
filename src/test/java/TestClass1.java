import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import pageObjects.applicationPages.Accounts;
import pageObjects.applicationPages.LoginPage;
import pageObjects.applicationPages.RegisterPage;
import pageObjects.base.BaseTest;
import utility.MyUtils;

public class TestClass1 extends BaseTest {
    LoginPage loginPage;
    Accounts accounts;

    @BeforeMethod
    public void setUp() {
        loginPage = LaunchApplication();

    }

    @Test
    public void loginTest() {
        //loginPage = LaunchApplication();
        loginPage
                .enterUserName(MyUtils.getPropertiesFile().getProperty("username"))
                .enterPassword(MyUtils.getPropertiesFile().getProperty("password"));
        accounts = loginPage.clickOnSignInButton();
        Assert.assertEquals(accounts.getPageTitle(), "ParaBank | Accounts Overview");
    }

    @Test
    public void checkAndCreateUser() {
        String pageTitle = loginPage.enterUserName("aut556").enterPassword("aut556").clickOnSignInButton().getPageTitle();
        if(pageTitle.equalsIgnoreCase("ParaBank | Accounts Overview")){
            logger.info("User login successful");
        }else if (pageTitle.equalsIgnoreCase("ParaBank | Error")){
            logger.info("Registering user...");
            loginPage.clickOnRegisterLink().registerUser();
        }
    }

    @Test
    public void test1() {
        loginPage = loginPage.login("123", "123", LoginPage.class);
        System.out.println(loginPage.getInvalidLoginErrorMessage());
        accounts = loginPage.login("aut556", "aut556", Accounts.class);
    }

    @Test
    public void test2(){
        LoginPage loginPage = new LoginPage(driver());
        RegisterPage registerPage = loginPage.clickOnRegisterLink();
        registerPage.registerUser();
    }

    /*@AfterTest
    public void tearDown(){

    }*/

}


