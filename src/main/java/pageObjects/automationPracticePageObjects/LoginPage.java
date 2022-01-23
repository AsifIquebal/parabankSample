package pageObjects.automationPracticePageObjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import pageObjects.base.BasePage;
import utility.JavaScriptUtils;
import utility.MyWrapper;

import java.lang.reflect.InvocationTargetException;

public class LoginPage extends BasePage {

    By userNameField = By.name("username");
    By passwordField = By.name("password");
    By signInButton = By.xpath("//input[@value='Log In']");
    By invalidLogInErrorMessage = By.xpath("//div[@id='rightPanel']/p");
    By registerLink = By.linkText("Register");
    By logOutButton = By.xpath("//a[text()='Log Out']");

    MyWrapper myWrapper = new MyWrapper();

    public LoginPage(WebDriver driver) {
        super(driver);
    }

    public LoginPage enterUserName(String userName) {
        logger.info("Entering username");
        myWrapper.sendKeys(driver(), userNameField, userName);
        return this;
    }

    public LoginPage enterPassword(String passWord) {
        logger.info("Entering password...");
        myWrapper.sendKeys(driver(), passwordField, passWord);
        return this;
    }

    public RegisterPage clickOnRegisterLink() {
        myWrapper.click(driver(), registerLink);
        return new RegisterPage(driver());
    }

    public AccountServices clickOnSignInButton() {
        logger.info("Clicking on Sign In Button");
        myWrapper.click(driver(), signInButton);
        JavaScriptUtils.waitForDOMLoad(driver());
        return new AccountServices(driver());
    }

    public void clickOnLogOut() {
        myWrapper.click(driver(), logOutButton);
    }

    public String getInvalidLoginErrorMessage() {
        return MyWrapper.getText(driver(), invalidLogInErrorMessage);
    }

    // use of Generic return type
    public <T extends BasePage> T login(String username, String password, Class<T> type) {
        enterUserName(username).enterPassword(password).clickOnSignInButton();
        logger.info("Page Title: " + getPageTitle());
        //type.getClass().Constructor.
        T t = null;
        try {
            t = type.getDeclaredConstructor(WebDriver.class).newInstance(driver());
        } catch (InstantiationException | IllegalAccessException | InvocationTargetException | NoSuchMethodException e) {
            logger.error(e.getMessage());
        } /*finally {
            return t;
        }*/
        return t;
    }


}
