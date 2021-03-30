import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.Test;

public class RandD {

    @Test
    public void test_01(){
        System.setProperty("webdriver.chrome.driver", "src/main/resources/drivers/chromedriver.exe");
        WebDriver driver = new ChromeDriver();
        driver.get("http://www.google.com");
        //((JavascriptExecutor) driver).executeScript("window.open('http://gmail.com/','_blank');");
        driver.close();
        driver.getTitle();
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
