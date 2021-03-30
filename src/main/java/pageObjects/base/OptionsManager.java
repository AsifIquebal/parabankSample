package pageObjects.base;

import org.openqa.selenium.Capabilities;
import org.openqa.selenium.PageLoadStrategy;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxDriverLogLevel;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.firefox.FirefoxProfile;


public class OptionsManager {

    public Capabilities capabilities;

    public Capabilities getCapabilities (String browser) {
        if (browser.equalsIgnoreCase("firefox"))
            capabilities = getFirefoxOptions();
        else if(browser.equalsIgnoreCase("chrome"))
            capabilities = getChromeOptions();
        return capabilities;
    }

    public static ChromeOptions getChromeOptions(){
        ChromeOptions options = new ChromeOptions();
        //options.setAcceptInsecureCerts(true);
        //options.setUnhandledPromptBehaviour(UnexpectedAlertBehaviour.ACCEPT);
        //disable automation info bar
        options.addArguments("disable-infobars");
        // Start in Maximized mode
        options.addArguments("--start-maximized");
        //options.addArguments("--no-sandbox"); // Bypass OS security model
        //options.addArguments("--disable-dev-shm-usage"); // overcome limited resource problems
        options.addArguments("allow-running-insecure-content");
        options.addArguments("enable-automation");

        if(System.getProperty("headless").equalsIgnoreCase("yes")){
            options.addArguments("--headless");
        }
        //options.addArguments("--headless");
        //options.addArguments("--window-size=1920,1080");
        options.addArguments("--disable-extensions");
        options.addArguments("--dns-prefetch-disable");
        options.addArguments("--disable-gpu");
        options.addArguments("enable-features=NetworkServiceInProcess");
        options.addArguments("--silent");
        //options.addArguments("disable-features=NetworkService");
        options.setPageLoadStrategy(PageLoadStrategy.NORMAL);
        /*Headless on Windows, Check periodically for any modification*/
        /*If you are using chromedriver in headless mode on Linux platform the argument disable-gpu is crucial and mandatory.*/
        //options.addArguments("--headless","--disable-gpu");
        //Exception exception = new Exception()
        //options.addArguments("perfLoggingPrefs");
        //options.setExperimentalOption("perfLoggingPrefs", chromePrefs);
        return options;
    }

    public static FirefoxOptions getFirefoxOptions () {
        FirefoxOptions options = new FirefoxOptions();
        FirefoxProfile profile = new FirefoxProfile();
        options.setLogLevel(FirefoxDriverLogLevel.ERROR);
        //Accept Untrusted Certificates
        profile.setAcceptUntrustedCertificates(true);
        profile.setAssumeUntrustedCertificateIssuer(false);
        //Use No Proxy Settings
        profile.setPreference("network.proxy.type", 0);
        //Set Firefox profile to capabilities
        options.setCapability(FirefoxDriver.PROFILE, profile);
        return options;
    }

}