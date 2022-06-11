package base;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.Optional;
import pageObjects.AccountServices;
import pageObjects.AdminPage;
import pageObjects.BillPay;
import pageObjects.LoginPage;

import java.io.IOException;

public abstract class BaseTest {
    // below are the specific references which the test classes are using
    public LoginPage loginPage;
    public AccountServices accountServices;
    public BillPay billPayPage;

    ThreadLocal<WebDriver> driverThreadLocal = new ThreadLocal<>();
    public final static Logger logger = LogManager.getLogger();
    // Sign In Link
    By signInLink = By.xpath("//a[normalize-space()='Sign in']");
    private WebDriver driver;
    // Sign Out link
    private final By signOut = By.xpath("//div/a[normalize-space()='Sign out']");
    private final By logOutlink = By.xpath("//a[text()='Log Out']");
    //@BeforeClass
    //@Parameters("browser")
    public void launchBrowser(@Optional("chrome") String browser) throws IOException, InterruptedException {
        String OS = System.getProperty("os.name").toLowerCase();
        logger.info("Running on Platform: " + OS);
        if (browser.equalsIgnoreCase("chrome")) {
            if (OS.equals("linux")) {
                logger.info("On Linux, getting Chromedriver file");
                ProcessBuilder builder = new ProcessBuilder();
                String pathToChromeDriver = Constants.CHROME_DRIVER_PATH_LINUX;
                logger.info("driver path: " + pathToChromeDriver);
                builder.command("sh", "-c", "chmod +x '" + pathToChromeDriver + "'");
                Process process = builder.start();
                int exitCode = process.waitFor();
                assert exitCode == 0;
                System.setProperty("webdriver.chrome.driver", pathToChromeDriver);
                System.setProperty("webdriver.chrome.silentOutput", "true");
            } else {
                logger.info("On Windows, getting Chromedriver file");
                System.setProperty("webdriver.chrome.driver", Constants.CHROME_DRIVER_PATH_WINDOWS);
            }
            driver = new ChromeDriver(OptionsManager.getChromeOptions());
        } else if (browser.equals("firefox")) {
            if (OS.equals("linux")) {
                logger.info("On Linux, getting GeckoDriver file");
                ProcessBuilder builder = new ProcessBuilder();
                String pathToGeckoDriver = Constants.GECKO_DRIVER_PATH_LINUX;
                logger.info("driver path: " + pathToGeckoDriver);
                builder.command("sh", "-c", "chmod +x '" + pathToGeckoDriver + "'");
                Process process = builder.start();
                /*StreamGobbler streamGobbler = new StreamGobbler(process.getInputStream(), System.out::println);
                Executors.newSingleThreadExecutor().submit(streamGobbler);
                int exitCode = process.waitFor();
                assert exitCode == 0;*/
                System.setProperty("webdriver.gecko.driver", pathToGeckoDriver);
                System.setProperty(FirefoxDriver.SystemProperty.BROWSER_LOGFILE, "false");
            } else {
                System.setProperty("webdriver.gecko.driver", Constants.GECKO_DRIVER_PATH_WINDOWS);
            }
            driver = new FirefoxDriver(OptionsManager.getFirefoxOptions());
        } else if (browser.equalsIgnoreCase("opera")) {
            if (OS.equals("linux")) {
                logger.info("On Linux, getting Opera file");
                ProcessBuilder builder = new ProcessBuilder();
                String pathToOperaDriver = Constants.OPERA_DRIVER_PATH_LINUX;
                logger.info("driver path: " + pathToOperaDriver);
                builder.command("sh", "-c", "chmod +x '" + pathToOperaDriver + "'");
                Process process = builder.start();
                int exitCode = process.waitFor();
                assert exitCode == 0;
                System.setProperty("webdriver.opera.driver", pathToOperaDriver);
            } else {
                System.setProperty("webdriver.opera.driver", Constants.GECKO_DRIVER_PATH_WINDOWS);
            }
        } else {
            new RuntimeException("Didn't found Driver...");
        }
    }

    protected WebDriver getDriver() {
        if (driver == null) {
            WebDriverManager.chromedriver().cachePath(System.getProperty("user.dir") + "/src/test/resources/drivers").setup();
            ChromeOptions options = new ChromeOptions();
            driver = new ChromeDriver(options);
            driverThreadLocal.set(driver);
            //driver.manage().window().maximize();
            logger.info("setting up Chromedriver");
        } else {
            //logger.info("DRIVER is NOT null");
        }
        return driverThreadLocal.get();
    }

    public LoginPage launchApplication() {
        getDriver().get(Constants.APP_URL);
        return new LoginPage(driver);
    }

    public void logOut(){
        getDriver().findElement(logOutlink).click();
    }

    @AfterClass
    public void tearDown() {
        getDriver().quit();
        driverThreadLocal.remove();
    }

    public void clearDataBase() {
        launchApplication().clickOnAdminPageLink(AdminPage.class).clickOnCleanButton();
    }

}
