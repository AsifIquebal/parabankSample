import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

public class BrokenLinksUsingStreams {
    private WebDriver driver;
    private int invalidLinksCount;

    @BeforeClass
    public void setUp() {
        System.setProperty("webdriver.chrome.driver", "src/main/resources/drivers/chromedriver.exe");
        driver = new ChromeDriver();
        driver.get("http://amazon.com");
    }

    @Test
    public void validateInvalidLinks() {
        invalidLinksCount = 0;
        List<WebElement> anchorTagsList = driver.findElements(By.tagName("a"));
        System.out.println("Total no. of links are " + anchorTagsList.size());
        List<String> urls = new ArrayList<>();
        for (WebElement anchorTagElement : anchorTagsList) {
            if (anchorTagElement != null) {
                urls.add(anchorTagElement.getAttribute("href"));
            }
        }
        long startTime = System.currentTimeMillis();
        urls.parallelStream().forEach(e -> verifyURLStatus(e));
        // urls.parallelStream().forEach(this::verifyURLStatus);
        long endTime = System.currentTimeMillis();
        System.out.println("Total time : " + (endTime - startTime));
        System.out.println("Total no. of invalid links are " + invalidLinksCount);
    }

    @AfterClass
    public void tearDown() {
        if (driver != null)
            driver.quit();
    }

    public void verifyURLStatus(String linkUrl) {
        try {
            URL url = new URL(linkUrl);
            HttpURLConnection httpURLConnect = (HttpURLConnection) url.openConnection();
            httpURLConnect.setConnectTimeout(3000);
            httpURLConnect.connect();
            System.out.println(linkUrl + "->" + httpURLConnect.getResponseMessage());
            if (httpURLConnect.getResponseCode() != 200) {
                invalidLinksCount++;
            }
        } catch (Exception ignored) {
        }
    }
}
