package pageObjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import base.BasePage;
import utility.JavaScriptUtils;
import utility.MyWrapper;

import java.util.ArrayList;
import java.util.List;

public class AccountServices extends BasePage {

    private final By billPayLink = By.linkText("Bill Pay");
    private final By allAccounts = By.xpath("//tr[@ng-repeat='account in accounts']/td/a");
    private final By accountTable = By.xpath("//table[@id='accountTable']");
    private final By totalAmount = By.xpath("//td/*[text()='Total']//../following-sibling::td/*[starts-with(text(),'$')]");
    private final By welcomeBanner = By.xpath("//*[@class='smallText']");

    public String getWelComeText(){
        return driver.findElement(welcomeBanner).getText();
    }

    public AccountServices(WebDriver driver) {
        super(driver);
        myWrapper = new MyWrapper();
    }

    public BillPay navigateToBillPay() {
        myWrapper.click(driver, billPayLink);
        return new BillPay(driver);
    }

    public List<String> getAllAccounts() {
        JavaScriptUtils.waitForDOMLoad(driver);
        MyWrapper.waitForElementVisibility(driver, totalAmount);
        /*try{
            System.out.println("in thread sleep .... waiting ");
            Thread.sleep(30000);
        }
        catch(InterruptedException ie){
        }*/
        List<WebElement> accountElements = driver.findElements(allAccounts);
        List<String> accounts = new ArrayList<>();
        for (WebElement a : accountElements) {
            accounts.add(a.getText());
        }
        return accounts;
    }

}
