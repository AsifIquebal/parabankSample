import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.annotations.Test;
import pageObjects.base.BaseTest;

public class UseExpectedCondition extends BaseTest {

    @Test
    public void checkPresenceOfElement() {
        getDriver().get("https://www.google.com");
        ExpectedConditions.presenceOfElementLocated(By.name("qk")).apply(getDriver());
    }

    @Test
    public void checkPresenceOfTextBoxThenType() throws InterruptedException {
        getDriver().get("https://www.google.com");
        ExpectedConditions.presenceOfElementLocated(By.name("q")).apply(getDriver()).sendKeys("Test");
        Thread.sleep(5000);
    }

    @Test
    public void assertTitle() {
        getDriver().get("https://www.google.com");
        ExpectedConditions.titleIs("Google").apply(getDriver());
    }

    @Test
    public void verifyPresenceOfAlertThenAccept() {
        getDriver().get("https://www.w3schools.com/jsref/tryit.asp?filename=tryjsref_alert");
        // Switch and accept
        ExpectedConditions.frameToBeAvailableAndSwitchToIt(By.id("iframeResult")).apply(getDriver()).findElement(By.xpath("//button[text()='Try it']")).click();
        ExpectedConditions.alertIsPresent().apply(getDriver()).accept();
        getDriver().switchTo().defaultContent();
        // Switch , get alert text then accept
        ExpectedConditions.frameToBeAvailableAndSwitchToIt(By.id("iframeResult")).apply(getDriver()).findElement(By.xpath("//button[text()='Try it']")).click();
        String alertText = ExpectedConditions.alertIsPresent().apply(getDriver()).getText();
        System.out.println("Alert text is : " + alertText);
        ExpectedConditions.alertIsPresent().apply(getDriver()).accept();
    }

}
