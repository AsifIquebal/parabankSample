package base;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import utility.MyWrapper;

public abstract class BasePage {

    public BasePage(WebDriver driver) {
        this.driver = driver;
    }

    public final static Logger logger = LogManager.getLogger();

    public WebDriver driver;

    public MyWrapper myWrapper = new MyWrapper();

    public String getPageTitle() {
        return driver.getTitle();
    }

    public String getURL() {
        return driver.getCurrentUrl();
    }

    public By logOutLink = By.linkText("Log Out");

}
