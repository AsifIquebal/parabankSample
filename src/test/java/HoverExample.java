import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.util.function.Consumer;

// VinsGuru Sir

public class HoverExample {
    private WebDriver driver;
    private Actions action;

    Consumer<By> hover = (By by) -> {
        action.moveToElement(driver.findElement(by)).perform();
    };

    @Test
    public void hoverTest() {
        driver.get("https://jquery.com/");
        action = new Actions(driver);
        hover.accept(By.linkText("Support"));
        hover.accept(By.linkText("Forums"));
    }

    @BeforeClass
    public void setUp() {
        WebDriverManager.chromedriver().cachePath(System.getProperty("user.dir") + "/src/test/resources/drivers").setup();
        driver = new ChromeDriver();
    }

    @AfterClass
    public void tearDown() throws InterruptedException {
        Thread.sleep(5000);
        driver.quit();
    }

}
