package base;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import utility.MyWrapper;

public abstract class BasePage {

    public final static Logger logger = LogManager.getLogger();

    private final WebDriver driver;

    public MyWrapper myWrapper = new MyWrapper();

    public BasePage(WebDriver driver) {
        this.driver = driver;
    }

    protected WebDriver driver() {
        return driver;
    }

    // Get Page Title
    public String getPageTitle() {
        return driver.getTitle();
    }

    // Get Page URL
    public String getURL() {
        return driver().getCurrentUrl();
    }

    // wait for DOM Load
    public void waitForDomLoad(WebDriver driver){

    }

    public By logOutLink = By.linkText("Log Out");



}
