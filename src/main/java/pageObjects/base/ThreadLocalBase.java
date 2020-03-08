package pageObjects.base;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;

public class ThreadLocalBase {

    WebDriver driver;

    ThreadLocal<WebDriver> driverThreadLocal = new ThreadLocal<>();

    //@BeforeClass
    @BeforeMethod
    @Parameters("browser")
    public void setDriverThreadLocal(@Optional("Chrome") String browser) {
        String OS = System.getProperty("os.name").toLowerCase();
        if (browser.equalsIgnoreCase("Chrome")) {
            if (OS == "linux") {
                System.setProperty("webdriver.chrome.driver", "src/main/resources/drivers/chromedriver");
            } else {
                System.setProperty("webdriver.chrome.driver", "src/main/resources/drivers/chromedriver.exe");
            }
            ChromeOptions options = new ChromeOptions();
            options.addArguments("disable-infobars");
            //options.addArguments("--start-maximized");
            driver = new ChromeDriver(options);
        } else if (browser.equals("firefox")) {
            if (OS == "linux") {
                System.setProperty("webdriver.gecko.driver", "src/main/resources/drivers/geckodriver");
            } else {
                System.setProperty("webdriver.gecko.driver", "src/main/resources/drivers/geckodriver.exe");
            }
            driver = new FirefoxDriver();
        }
        driverThreadLocal.set(driver);
    }

    public WebDriver getDriver() {
        return driverThreadLocal.get();
    }

    //@AfterClass
    @AfterMethod
    public void tearDown() {
        getDriver().quit();
        driverThreadLocal.remove();
    }

}
