package pageObjects.applicationPages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import pageObjects.base.BasePage;
import utility.MyWrapper;

import java.lang.reflect.InvocationTargetException;

public class LoginPage extends BasePage {

    By userNameField = By.name("username");
    By passwordField = By.name("password");
    By signInButton = By.xpath("//input[@value='Log In']");
    By invalidLogInErrorMessage = By.xpath("//div[@id='rightPanel']/p");
    By registerLink = By.linkText("Register");

    public LoginPage(WebDriver driver) {
        super(driver);
    }

    public LoginPage enterUserName(String userName) {
        logger.info("Entering username");
        MyWrapper.sendKeys(driver(), userNameField, userName);
        return this;
    }

    public LoginPage enterPassword(String passWord) {
        logger.info("Entering password...");
        MyWrapper.sendKeys(driver(), passwordField, passWord);
        return this;
    }

    public RegisterPage clickOnRegisterLink() {
        MyWrapper.click(driver(), registerLink);
        return new RegisterPage(driver());
    }

    public AccountServices clickOnSignInButton() {
        logger.info("Clicking on Sign In Button");
        MyWrapper.click(driver(), signInButton);
        return new AccountServices(driver());
    }

    public String getInvalidLoginErrorMessage() {
        return MyWrapper.getText(driver(), invalidLogInErrorMessage);
    }

    public <T extends BasePage> T login(String username, String password, Class<T> type) {
        enterUserName(username).enterPassword(password).clickOnSignInButton();
        logger.info("Page Title: " + getPageTitle());
        //type.getClass().Constructor.
        T t = null;
        try {
            t = type.getDeclaredConstructor(WebDriver.class).newInstance(driver());
        } catch (InstantiationException | IllegalAccessException | InvocationTargetException | NoSuchMethodException e) {
            logger.error(e.getMessage());
        } finally {
            return t;
        }

    }


}
