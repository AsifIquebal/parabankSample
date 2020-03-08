package pageObjects.applicationPages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import pageObjects.base.BasePage;
import utility.MyWrapper;

import java.util.ArrayList;
import java.util.List;

public class AccountServices extends BasePage {

    private By billPayLink = By.linkText("Bill Pay");
    private By allAccounts = By.xpath("//tr[@ng-repeat='account in accounts']/td/a");

    public AccountServices(WebDriver driver) {
        super(driver);
    }

    public BillPay navigateToBillPay() {
        MyWrapper.click(driver(), billPayLink);
        return new BillPay(driver());
    }

    public List<String> getAllAccounts() {
        List<WebElement> accountElements = driver().findElements(allAccounts);
        List<String> accounts = new ArrayList<>();
        for (WebElement a : accountElements) {
            accounts.add(a.getText());
        }
        return accounts;
    }

}
