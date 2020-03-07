package pageObjects.base;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.logging.LogEntry;
import org.openqa.selenium.logging.LogType;
import org.openqa.selenium.logging.LoggingPreferences;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.annotations.Test;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;

public class TestLogging {

    @Test
    public void test01() throws InterruptedException {

        /*logPrefs.enable(LogType.BROWSER, Level.ALL);
        logPrefs.enable(LogType.PERFORMANCE, Level.INFO);

        //DesiredCapabilities capabilities = DesiredCapabilities.chrome();
        Map<String, Object> perfLogPrefs = new HashMap<>();

        LoggingPreferences logPrefs = new LoggingPreferences();
        logPrefs.enable(LogType.PERFORMANCE, Level.ALL);

        perfLogPrefs.put("traceCategories", "browser,devtools.timeline,devtools");
        perfLogPrefs.put("enableNetwork", true);
        ChromeOptions options = new ChromeOptions();
        options.setExperimentalOption("perfLoggingPrefs", perfLogPrefs);
        //capabilities.setCapability(ChromeOptions.CAPABILITY, options);

        System.setProperty("webdriver.chrome.driver", Constants.CHROME_DRIVER_PATH_LINUX);
        options.addArguments("--headless","--disable-gpu");
        WebDriver driver = new ChromeDriver(options);*/

        WebDriver driver = new ChromeDriver(getPerformanceLoggingCapabilities());
        driver.get("http://www.google.com");
        Thread.sleep(5000);
        //System.out.println(perfLogPrefs);
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

    @Test
    public void test2() throws InterruptedException {
        DesiredCapabilities caps = DesiredCapabilities.chrome();
        // Enable performance logging
        LoggingPreferences logPrefs = new LoggingPreferences();
        logPrefs.enable(LogType.PERFORMANCE, Level.ALL);
        caps.setCapability(CapabilityType.LOGGING_PREFS, logPrefs);
        // Enable timeline tracing
        Map<String, Object> chromeOptions = new HashMap<>();
        Map<String, Object> perfLoggingPrefs = new HashMap<>();
        // Tracing categories, please note NO SPACE NEEDED after the commas
        //perfLoggingPrefs.put("traceCategories", "blink.console,disabled-by-default-devtools.timeline");
        perfLoggingPrefs.put("enableNetwork", true);
        chromeOptions.put("perfLoggingPrefs", perfLoggingPrefs);
        //chromeOptions.put("debuggerAddress", "127.0.0.1:10134");
        caps.setCapability(ChromeOptions.CAPABILITY, chromeOptions);
        System.setProperty("webdriver.chrome.driver", Constants.CHROME_DRIVER_PATH_LINUX);
        WebDriver driver = new ChromeDriver(caps);
        driver.get("http://www.google.com");
        Thread.sleep(5000);

        //List<LogEntry> entries = driver.manage().logs().get(LogType.PERFORMANCE).getAll();
        List<LogEntry> entries = driver.manage().logs().get(LogType.PROFILER).getAll();

        System.out.println(entries.size() + " " + LogType.PERFORMANCE + " log entries found");
        for (LogEntry entry : entries) {
            System.out.println(new Date(entry.getTimestamp()) + " " + entry.getLevel() + " " + entry.getMessage());
        }


        driver.close();
        driver.quit();
    }

}
