package pageObjects.base;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.logging.LogType;
import org.openqa.selenium.logging.LoggingPreferences;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.annotations.Optional;
import pageObjects.automationPracticePageObjects.LoginPage;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.Executors;
import java.util.function.Consumer;
import java.util.logging.Level;

public abstract class BaseTest {
    final Map<String, Object> chromePrefs = new HashMap<>();
    public final static Logger logger = LogManager.getLogger();
    // Sign In Link
    By signInLink = By.xpath("//a[normalize-space()='Sign in']");
    private WebDriver driver;
    // Sign Out link
    private By signOut = By.xpath("//div/a[normalize-space()='Sign out']");

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
                StreamGobbler streamGobbler = new StreamGobbler(process.getInputStream(), System.out::println);
                Executors.newSingleThreadExecutor().submit(streamGobbler);
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
                StreamGobbler streamGobbler = new StreamGobbler(process.getInputStream(), System.out::println);
                Executors.newSingleThreadExecutor().submit(streamGobbler);
                int exitCode = process.waitFor();
                assert exitCode == 0;
                System.setProperty("webdriver.opera.driver", pathToOperaDriver);
            } else {
                System.setProperty("webdriver.opera.driver", Constants.GECKO_DRIVER_PATH_WINDOWS);
            }
            /*ChromeOptions options = new ChromeOptions();
            options.setBinary(pathToOperaDriver);

            DesiredCapabilities capabilities = new DesiredCapabilities();
            capabilities.setCapability(ChromeOptions.CAPABILITY, options);
            OperaDriver browser = new OperaDriver(capabilities);

            WebDriver driver =browser;
            driver = new FirefoxDriver(OptionsManager.getFirefoxOptions());*/
        } else {
            new RuntimeException("Didn't found Driver...");
        }
    }


    private static DesiredCapabilities getPerformanceLoggingCapabilities() {
        DesiredCapabilities caps = DesiredCapabilities.chrome();

        // Enable performance logging
        LoggingPreferences logPrefs = new LoggingPreferences();
        logPrefs.enable(LogType.PERFORMANCE, Level.ALL);
        caps.setCapability(CapabilityType.LOGGING_PREFS, logPrefs);

        // Enable timeline tracing
        Map<String, Object> chromeOptions = new HashMap<String, Object>();
        Map<String, String> perfLoggingPrefs = new HashMap<String, String>();
        // Tracing categories, please note NO SPACE NEEDED after the commas
        perfLoggingPrefs.put("traceCategories", "blink.console,disabled-by-default-devtools.timeline");
        chromeOptions.put("perfLoggingPrefs", perfLoggingPrefs);
        //chromeOptions.put("debuggerAddress", "127.0.0.1:10134");
        caps.setCapability(ChromeOptions.CAPABILITY, chromeOptions);

        return caps;
    }
    // all the classes which extends this class will be able to use this method
    /*protected WebDriver getDriver() {
        return driver;
    }*/

    public WebDriver getDriver() {
        if (driver == null) {
            //System.out.println("DRIVER is null");
            System.setProperty("webdriver.chrome.driver", Constants.CHROME_DRIVER_PATH_WINDOWS);
            logger.info("setting up Chromedriver");
            driver = new ChromeDriver(OptionsManager.getChromeOptions());
        } else {
            //System.out.println("DRIVER is NOT null");
        }

        return driver;
    }

    public LoginPage LaunchApplication() {
        getDriver().get(Constants.APP_URL);
        return new LoginPage(driver);
    }

    /*@AfterClass
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }*/

    public void tearDown() {
        if (driver != null) {
            try {
                driver.quit();
                // well instead of setting driver driver to null like below its better to check for the session id
                // ((RemoteWebDriver)driver).getSessionId() will be null after driver quits
                driver = null;
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
        }
    }

    public static class StreamGobbler implements Runnable {
        private InputStream inputStream;
        private Consumer<String> consumer;

        public StreamGobbler(InputStream inputStream, Consumer<String> consumer) {
            this.inputStream = inputStream;
            this.consumer = consumer;
        }

        @Override
        public void run() {
            new BufferedReader(new InputStreamReader(inputStream)).lines().forEach(consumer);
        }
    }

}
