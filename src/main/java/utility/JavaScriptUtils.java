package utility;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.WebDriverWait;

public class JavaScriptUtils {

    public static void flash(WebElement element, WebDriver driver) {
        JavascriptExecutor js = ((JavascriptExecutor) driver);
        String bgcolor = element.getCssValue("backgroundColor");
        for (int i = 0; i < 10; i++) {
            changeColor("rgb(0,200,0)", element, driver);//1
            changeColor(bgcolor, element, driver);//2
        }
    }

    public static void changeColor(String color, WebElement element, WebDriver driver) {
        JavascriptExecutor js = ((JavascriptExecutor) driver);
        js.executeScript("arguments[0].style.backgroundColor = '" + color + "'", element);

        try {
            Thread.sleep(20);
        } catch (InterruptedException e) {
        }
    }

    public static void drawBorder(WebElement element, WebDriver driver) {
        JavascriptExecutor js = ((JavascriptExecutor) driver);
        js.executeScript("arguments[0].style.border='3px solid red'", element);
    }

    public static void generateAlert(WebDriver driver, String message) {
        JavascriptExecutor js = ((JavascriptExecutor) driver);
        js.executeScript("alert('" + message + "')");

    }

    public static void clickElementByJS(WebElement element, WebDriver driver) {
        JavascriptExecutor js = ((JavascriptExecutor) driver);
        js.executeScript("arguments[0].click();", element);

    }

    public static void refreshBrowserByJS(WebDriver driver) {
        JavascriptExecutor js = ((JavascriptExecutor) driver);
        js.executeScript("history.go(0)");
    }

    public static String getTitleByJS(WebDriver driver) {
        JavascriptExecutor js = ((JavascriptExecutor) driver);
        String title = js.executeScript("return document.title;").toString();
        return title;
    }

    public static String getWindowName(WebDriver driver) {
        JavascriptExecutor js = ((JavascriptExecutor) driver);
        String windowName = js.executeScript("return window.name").toString();
        return windowName;
    }

    public static String getPageInnerText(WebDriver driver) {
        JavascriptExecutor js = ((JavascriptExecutor) driver);
        String pageText = js.executeScript("return document.documentElement.innerText;").toString();
        return pageText;
    }

    public static void scrollPageDown(WebDriver driver) {
        JavascriptExecutor js = ((JavascriptExecutor) driver);
        js.executeScript("window.scrollTo(0,document.body.scrollHeight)");
    }

    public static void scrollIntoView(WebElement element, WebDriver driver) {
        JavascriptExecutor js = ((JavascriptExecutor) driver);
        js.executeScript("arguments[0].scrollIntoView(true);", element);
    }

    public static void enterText(WebElement element, String value, WebDriver driver) {
        JavascriptExecutor js = ((JavascriptExecutor) driver);
        js.executeScript("arguments[0].value = '" + value + "';", element);
    }

    public static void waitForDOMLoad(WebDriver driver) {
        new WebDriverWait(driver, 100).until(wd ->((JavascriptExecutor) wd).executeScript("return document.readyState").equals("complete"));
    }

    public static String getDocumentReadyState(WebDriver driver){
        JavascriptExecutor js = ((JavascriptExecutor) driver);
        return js.executeScript("return document.readyState").toString();
    }
    // enter keys without using sendKeys
    // jse.executeScript("document.getElementById('email').value = 'sunilrathore77@gmail.com';");

    /*// Refresh Browser Window
		System.out.println("Sending Refresh by Java Script");
		js.executeScript("history.go(0)");
    // Get InnerText
    String sText =  js.executeScript("return document.documentElement.innerText;").toString();
		System.out.println("Inner Text: "+sText);*/

    // get the domain name
    /*String domain_name=(String) js.executeScript("return document.domain");
		System.out.println("By Java Script:"+domain_name);*/
}
