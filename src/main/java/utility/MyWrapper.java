package utility;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import base.Constants;

import java.time.Duration;

public class MyWrapper {

    private final static Logger logger = LogManager.getLogger();
    public MyWrapper sendKeys(WebDriver driver, By by, String valueToType) {
        try {
            new WebDriverWait(driver, Duration.ofSeconds(Constants.WAIT_TIME)).until(ExpectedConditions.visibilityOfElementLocated(by));
            //wait.until(ExpectedConditions.refreshed(ExpectedConditions.stalenessOf("table")));
            JavaScriptUtils.scrollIntoView(driver.findElement(by), driver);
            driver.findElement(by).clear();
            driver.findElement(by).sendKeys(valueToType);
            //return this;
        } catch (NoSuchElementException ex) {
            // when(ex is NoSuchElementException || ex is WebDriverTimeoutException)
            //ExtentTest test = ScenarioContext.Current.Get < ExtentTest > ();
            //test.Error("Could not perform SendKeys on element identified by " + by.ToString() + " after " + Constants.DefaultTimeout.ToString() + " seconds", MediaEntityBuilder.CreateScreenCaptureFromPath(ReportingMethods.CreateScreenshot(driver)).Build());
            logger.error("Could not perform SendKeys on element identified by " + by.toString());
            Assert.fail();
        } catch (Exception ex) {
            //when(ex is StaleElementReferenceException) find element again and retry
            new WebDriverWait(driver, Duration.ofSeconds(Constants.WAIT_TIME)).until(ExpectedConditions.visibilityOfElementLocated(by));
            JavaScriptUtils.scrollIntoView(driver.findElement(by), driver);
            driver.findElement(by).clear();
            driver.findElement(by).sendKeys(valueToType);
            //return this;
            //ex.printStackTrace();
        }
        return this;
    }

    public static String getText(WebDriver driver, By by) {
        String text = null;
        try {
            new WebDriverWait(driver, Duration.ofSeconds(Constants.WAIT_TIME)).until(ExpectedConditions.visibilityOfElementLocated(by));
            JavaScriptUtils.scrollIntoView(driver.findElement(by), driver);
            text = driver.findElement(by).getText();
        } catch (NoSuchElementException nsee){
            logger.error("Could not perform SendKeys on element identified by " + by.toString());
            Assert.fail();
        } catch (Exception e){
            new WebDriverWait(driver, Duration.ofSeconds(Constants.WAIT_TIME)).until(ExpectedConditions.visibilityOfElementLocated(by));
            JavaScriptUtils.scrollIntoView(driver.findElement(by), driver);
            text = driver.findElement(by).getText();
        }
        return text;
    }

    public MyWrapper click(WebDriver driver, By by) {
        //System.out.println(driver != null);
        //System.out.println(by != null);
        try {
            new WebDriverWait(driver, Duration.ofSeconds(Constants.WAIT_TIME)).until(ExpectedConditions.visibilityOfElementLocated(by));
            //JavaScriptUtils.scrollIntoView(driver.findElement(by), driver);
            driver.findElement(by).click();
            JavaScriptUtils.waitForDOMLoad(driver);
        } catch (NoSuchElementException ex) {
            assert by != null;
            logger.error("Could not perform SendKeys on element identified by " + by);
            Assert.fail();
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
            new WebDriverWait(driver, Duration.ofSeconds(Constants.WAIT_TIME)).until(ExpectedConditions.visibilityOfElementLocated(by));
            JavaScriptUtils.scrollIntoView(driver.findElement(by), driver);
            driver.findElement(by).click();
            JavaScriptUtils.waitForDOMLoad(driver);
        }
        return this;
    }

    public static WebElement waitUntilElementExists(WebDriver driver, final By by) {
        FluentWait<WebDriver> my_wait = new FluentWait<>(driver)
                .withTimeout(Duration.ofSeconds(30))
                .pollingEvery(Duration.ofMillis(500))
                .ignoring(NoSuchElementException.class);

        WebElement element = my_wait.until((driver1) -> driver.findElement(by));
        return element;
    }

    public static WebDriverWait waitExplicit(WebDriver driver, By by) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.presenceOfElementLocated(by));
        return wait;
    }

    public static WebDriverWait waitExplicit(WebDriver driver, WebElement element) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.visibilityOf(element));
        return wait;
    }

    public void moveMouse(WebDriver driver, By by) throws InterruptedException {
        Actions actions = new Actions(driver);
        actions.moveToElement(driver.findElement(by)).build().perform();
        Thread.sleep(2000);
    }

    public static void waitForElementVisibility(WebDriver driver, By by){
        new WebDriverWait(driver, Duration.ofSeconds(45)).until(ExpectedConditions.visibilityOfElementLocated(by));
    }

}
