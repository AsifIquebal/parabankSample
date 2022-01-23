package pageObjects.automationPracticePageObjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import pageObjects.base.BasePage;

public class AdminPage extends BasePage {

    private final By cleanButton = By.xpath("//button[normalize-space()='Clean']");

    public AdminPage(WebDriver driver) {
        super(driver);
    }

    public void clickOnCleanButton() {
        myWrapper.click(driver(), cleanButton);
    }

}
