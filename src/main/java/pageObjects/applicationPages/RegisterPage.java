package pageObjects.applicationPages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import pageObjects.base.BasePage;
import utility.MyWrapper;

public class RegisterPage extends BasePage {

    public RegisterPage(WebDriver driver) {
        super(driver);
    }

    By firstName = By.id("customer.firstName");
    By lastName = By.id("customer.lastName");
    By addressField = By.id("customer.address.street");
    By cityField = By.id("customer.address.city");
    By stateField = By.id("customer.address.state");
    By zipField = By.id("customer.address.zipCode");
    By phoneField = By.id("customer.phoneNumber");
    By ssnField = By.id("customer.ssn");
    By userNameField = By.id("customer.username");
    By passwordField = By.id("customer.password");
    By confirmField = By.id("repeatedPassword");
    By registerButton = By.xpath("//input[@value='Register']");

    public void registerUser(){
        MyWrapper.sendKeys(driver(),firstName,"FirstName");
        MyWrapper.sendKeys(driver(),lastName,"LastName");
        MyWrapper.sendKeys(driver(),addressField,"TestAddress");
        MyWrapper.sendKeys(driver(),cityField,"TestCity");
        MyWrapper.sendKeys(driver(),stateField,"TestState");
        MyWrapper.sendKeys(driver(),zipField,"12345");
        MyWrapper.sendKeys(driver(),phoneField,"1234567890");
        MyWrapper.sendKeys(driver(),ssnField,"1234567890");
        MyWrapper.sendKeys(driver(),userNameField,"aut556");
        MyWrapper.sendKeys(driver(),passwordField,"aut556");
        MyWrapper.sendKeys(driver(),confirmField,"aut556");
        MyWrapper.click(driver(),registerButton);
        if(getPageTitle().equalsIgnoreCase("ParaBank | Customer Created")){
            logger.info("User Registered...");
        }else{
            logger.info("User Registration fails...");
        }
    }



}
