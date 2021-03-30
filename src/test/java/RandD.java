import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.annotations.Test;

public class RandD {

    @Test
    public void test_01() throws Throwable {
        System.setProperty("webdriver.chrome.driver", "src/main/resources/drivers/chromedriver.exe");
        WebDriver driver = new ChromeDriver();
        driver.get("http://www.google.com");
        driver.quit();
        System.out.println("Is driver null: " + driver.equals(null));
        System.out.println(((RemoteWebDriver)driver).getSessionId());
        System.out.println(driver.toString());
    }


    @Test
    public void test123(){
        System.out.println(System.getProperty("headless"));
        if(System.getProperty("headless").equalsIgnoreCase("yes")){
            System.out.println("Headless YES");
        }else {
            System.out.println("Headless NO");
        }
    }

}
