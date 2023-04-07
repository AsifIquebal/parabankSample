import base.Constants;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.Test;

import java.net.URL;
import java.time.Duration;

public class RandD_Docker {
    /* Standalone:
        docker run -d -p 4444:4444 --shm-size 2g selenium/standalone-chrome:91.0
        Connect to browser based VNC through localhost:7900
        docker run -d -p 4444:4444 -p 7900:7900 --shm-size 2g selenium/standalone-chrome:91.0

    */
    WebDriver driver;

    @Test
    public void test01() {
        try {
            ChromeOptions options = new ChromeOptions();
            URL remoteURL = new URL("http://localhost:4444/wd/hub");
            driver = new RemoteWebDriver(remoteURL, options);
            driver.get("https://www.google.com");
            System.out.println(driver.getTitle());
            driver.findElement(By.name("q")).sendKeys("Selenium Docker", Keys.ENTER);
            By firstResult = By.cssSelector("h3");
            new WebDriverWait(driver, Duration.ofSeconds(Constants.WAIT_TIME)).until(ExpectedConditions.visibilityOfElementLocated(firstResult));
            //Thread.sleep(10000);
            System.out.println(driver.findElement(firstResult).getText());
            System.out.println("Running " + options.getBrowserName() + ", version: " + options.getBrowserVersion()+ ",  on " + options.getPlatformName());
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            driver.quit();
        }
    }

    @Test
    public void test02_kubernetes() {
        try {
            ChromeOptions options = new ChromeOptions();
            URL remote_ChromeURL = new URL("http://my-selenium-grid.com/wd/hub");
            driver = new RemoteWebDriver(remote_ChromeURL, options);
            driver.get("https://www.google.com");
            System.out.println(driver.getTitle());
            driver.findElement(By.name("q")).sendKeys("Selenium Docker", Keys.ENTER);
            By firstResult = By.cssSelector("h3");
            new WebDriverWait(driver, Duration.ofSeconds(Constants.WAIT_TIME)).until(ExpectedConditions.visibilityOfElementLocated(firstResult));
            System.out.println(driver.findElement(firstResult).getText());
            System.out.println("Running " + options.getBrowserName() + ", version: " + options.getBrowserVersion() + ",  on " + options.getPlatformName());
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            driver.quit();
        }
    }

}
