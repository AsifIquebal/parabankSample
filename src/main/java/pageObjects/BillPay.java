package pageObjects;

import base.BasePage;
import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class BillPay extends BasePage {

    public BillPay(WebDriver driver) {
        super(driver);
    }

    private final By payeeName = By.xpath("//input[@name='payee.name']");
    private final By payeeAddress = By.xpath("//input[@name='payee.address.street']");
    private final By payeeCity = By.xpath("//input[@name='payee.address.city']");
    private final By payeeState = By.xpath("//input[@name='payee.address.state']");
    private final By payeeZipCode = By.xpath("//input[@name='payee.address.zipCode']");
    private final By payeePhoneNumber = By.xpath("//input[@name='payee.phoneNumber']");
    private final By payeeAccountNumber = By.xpath("//input[@name='payee.accountNumber']");
    private final By payeeVerifyAccountNumber = By.xpath("//input[@name='verifyAccount']");
    private final By amount = By.xpath("//input[@name='amount']");
    private final By sendPaymentButton = By.xpath("//input[@value='Send Payment']");

    private final By resultBanner = By.xpath("//div[@ng-show='showResult']//p");

    public BillPay enterPayeeName(String nameOfPayee) {
        driver.findElement(payeeName).sendKeys(nameOfPayee);
        return this;
    }

    public BillPay enterPayeeAddress(String addressOfPayee) {
        driver.findElement(payeeAddress).sendKeys(addressOfPayee);
        return this;
    }

    public BillPay enterPayeeCity(String cityOfPayee) {
        driver.findElement(payeeCity).sendKeys(cityOfPayee);
        return this;
    }

    public BillPay enterPayeeState(String state) {
        driver.findElement(payeeState).sendKeys(state);
        return this;
    }

    public BillPay enterPayeeZip(String zipCode) {
        driver.findElement(payeeZipCode).sendKeys(zipCode);
        return this;
    }

    public BillPay enterPayeePhone(String phoneNumber) {
        driver.findElement(payeePhoneNumber).sendKeys(phoneNumber);
        return this;
    }

    public BillPay enterPayeeAccountNumber(String accountNumber) {
        driver.findElement(payeeAccountNumber).sendKeys(accountNumber);
        return this;
    }

    public BillPay enterPayeeVerifyAccountNumber(String verifyAccountNumber) {
        driver.findElement(payeeVerifyAccountNumber).sendKeys(verifyAccountNumber);
        return this;
    }

    @Step("Payment Amount: {paymentAmount}")
    public BillPay enterAmount(String paymentAmount) {
        driver.findElement(amount).sendKeys(paymentAmount);
        return this;
    }

    public void clickSendPayment() {
        myWrapper.click(driver, sendPaymentButton);
        //Wait for page title
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(15));
        wait.until(ExpectedConditions.titleContains("Bill Payment Complete"));
        logger.info(getResultText());
        //driver.findElement(sendPaymentButton).click();
    }

    public String getResultText() {
        return driver.findElement(resultBanner).getText();
    }

    //Select name = fromAccountId

}
