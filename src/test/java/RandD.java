import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import utility.JavaScriptUtils;

import java.util.Set;

public class RandD {

    WebDriver driver;

    @BeforeClass
    public void setUp() {
        //WebDriverManager.chromedriver().setup();
        WebDriverManager.chromedriver().cachePath(System.getProperty("user.dir") + "/src/test/resources/drivers").setup();
        ChromeOptions options = new ChromeOptions();
        //options.addArguments("--headless");
        driver = new ChromeDriver(options);
    }

    @AfterClass
    public void tearDown(){
        driver.quit();
    }

    @Test
    public void test_01() throws Throwable {
        System.setProperty("webdriver.chrome.driver", "src/main/resources/drivers/chromedriver.exe");
        WebDriver driver = new ChromeDriver();
        driver.get("http://www.google.com");
        driver.quit();
        System.out.println("Is driver null: " + driver.equals(null));
        System.out.println(((RemoteWebDriver) driver).getSessionId());
        System.out.println(driver.toString());
    }


    @Test
    public void test123() {
        System.out.println(System.getProperty("headless"));
        if (System.getProperty("headless").equalsIgnoreCase("yes")) {
            System.out.println("Headless YES");
        } else {
            System.out.println("Headless NO");
        }
    }

    @Test
    public void test01() {
        String current = System.getProperty("os.name");
        String host = System.getenv("HOSTNAME");  // Most OSs
        if (host == null) {
            System.out.println("host is null on System.getenv(\"HOSTNAME\")");
            host = System.getenv("COMPUTERNAME");  // Windows
        }
        System.out.println(current + ":" + host);
    }

    // its not good to wait for javascript documentReadyState
   /* The WebDriver get method states
   'Load a new web page in the current browser window.
   This is done using an HTTP GET operation,
   and the method will block until the load is complete.'
   */
    @Test
    public void donotUseInGet() {
        driver.get("http://automationpractice.com/index.php");
        int counter = 0;
        while (counter < 50) {
            System.out.println(JavaScriptUtils.getDocumentReadyState(driver));
            counter++;
        }
    }

    @Test
    public void searchForProduct() {
        driver.get("https://www.amazon.in/");
        driver.findElement(By.id("twotabsearchtextbox")).sendKeys("boat rock");
        new WebDriverWait(driver, 5)
                .until(ExpectedConditions.presenceOfElementLocated(By.xpath("//div[@data-keyword='boat rockerz 255 pro+ plus']"))).click();
        String parentWindowHandle = driver.getWindowHandle();
        new WebDriverWait(driver, 5)
                .until(ExpectedConditions.elementToBeClickable(By.xpath("//a[contains(@href,'/boAt-Rockerz-255-Pro-Earphones/dp/B08TTXNZ4Y')]//span[contains(@class,'a-text-normal')]"))).click();
        Set<String> windowHandles = driver.getWindowHandles();
        System.out.println("Windows count: " + windowHandles.size());
        for (String handle:windowHandles
             ) {
            System.out.println(handle);
            if(handle!=parentWindowHandle){
                driver.switchTo().window(handle);
            }
        }
        new WebDriverWait(driver, 5)
                .until(ExpectedConditions.elementToBeClickable(By.id("add-to-cart-button"))).click();
        new WebDriverWait(driver,5)
                .until(ExpectedConditions.elementToBeClickable(By.id("hlb-ptc-btn-native"))).click();
    }

}
