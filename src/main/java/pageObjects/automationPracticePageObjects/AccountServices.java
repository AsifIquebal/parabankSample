package pageObjects.automationPracticePageObjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import pageObjects.base.BasePage;
import utility.JavaScriptUtils;
import utility.MyWrapper;

import java.util.ArrayList;
import java.util.List;

public class AccountServices extends BasePage {

    private By billPayLink = By.linkText("Bill Pay");
    private By allAccounts = By.xpath("//tr[@ng-repeat='account in accounts']/td/a");
    private By accountTable = By.xpath("//table[@id='accountTable']");
    private By totalAmount = By.xpath("//td/*[text()='Total']//../following-sibling::td/*[starts-with(text(),'$')]");

    public AccountServices(WebDriver driver) {
        super(driver);
    }

    public BillPay navigateToBillPay() {
        MyWrapper.click(driver(), billPayLink);
        return new BillPay(driver());
    }

    public List<String> getAllAccounts() {
        JavaScriptUtils.waitForDOMLoad(driver());
        MyWrapper.waitForElementVisibility(driver(), totalAmount);
        /*try{
            System.out.println("in thread sleep .... waiting ");
            Thread.sleep(30000);
        }
        catch(InterruptedException ie){
        }*/
        List<WebElement> accountElements = driver().findElements(allAccounts);
        List<String> accounts = new ArrayList<>();
        for (WebElement a : accountElements) {
            accounts.add(a.getText());
        }
        return accounts;
    }

}
