import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.annotations.Test;
import pageObjects.base.BaseTest;

public class UseExpectedCondition extends BaseTest {

    @Test
    public void checkPresenceOfElement() {
        driver().get("https://www.google.com");
        ExpectedConditions.presenceOfElementLocated(By.name("qk")).apply(driver());
    }

    @Test
    public void checkPresenceOfTextBoxThenType() throws InterruptedException {
        driver().get("https://www.google.com");
        ExpectedConditions.presenceOfElementLocated(By.name("q")).apply(driver()).sendKeys("Test");
        Thread.sleep(5000);
    }

    @Test
    public void assertTitle() {
        driver().get("https://www.google.com");
        ExpectedConditions.titleIs("Google").apply(driver());
    }

    @Test
    public void verifyPresenceOfAlertThenAccept() {
        driver().get("https://www.w3schools.com/jsref/tryit.asp?filename=tryjsref_alert");
        // Switch and accept
        ExpectedConditions.frameToBeAvailableAndSwitchToIt(By.id("iframeResult")).apply(driver()).findElement(By.xpath("//button[text()='Try it']")).click();
        ExpectedConditions.alertIsPresent().apply(driver()).accept();
        driver().switchTo().defaultContent();
        // Switch , get alert text then accept
        ExpectedConditions.frameToBeAvailableAndSwitchToIt(By.id("iframeResult")).apply(driver()).findElement(By.xpath("//button[text()='Try it']")).click();
        String alertText = ExpectedConditions.alertIsPresent().apply(driver()).getText();
        System.out.println("Alert text is : " + alertText);
        ExpectedConditions.alertIsPresent().apply(driver()).accept();
    }

}
