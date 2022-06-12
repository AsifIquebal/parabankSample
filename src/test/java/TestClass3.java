import base.BaseTest;
import io.qameta.allure.Feature;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import pageObjects.AccountServices;
import pageObjects.LoginPage;

@Feature("Bill Payment Test Cases")
public class TestClass3 extends BaseTest {

    @BeforeMethod
    public void setUp() {
        getDriver().get("https://parabank.parasoft.com");
        loginPage = new LoginPage(getDriver());
    }

    @Test
    @Severity(SeverityLevel.CRITICAL)
    public void verifyBillPaymentSuccessCase() {
        //loginPage = launchApplication();
        accountServices = loginPage.login("john", "demo", AccountServices.class);
        billPayPage = accountServices.navigateToBillPay();
        billPayPage.enterPayeeName("Simon Black")
                .enterPayeeAddress("9569 Sugar St.")
                .enterPayeeCity("San Jose")
                .enterPayeeState("CA")
                .enterPayeeZip("95123")
                .enterPayeePhone("876459876")
                .enterPayeeAccountNumber("43214")
                .enterPayeeVerifyAccountNumber("43214")
                .enterAmount("105")
                .clickSendPayment();
        Assert.assertTrue(billPayPage.getResultText().contains("successful"));
    }

    @Test
    @Severity(SeverityLevel.CRITICAL)
    public void verifyBillPaymentFailCase() {
        //loginPage = launchApplication();
        accountServices = loginPage.login("john", "demo", AccountServices.class);
        billPayPage = accountServices.navigateToBillPay();
        billPayPage.enterPayeeName("Simon Black")
                .enterPayeeAddress("9569 Sugar St.")
                .enterPayeeCity("San Jose")
                .enterPayeeState("CA")
                .enterPayeeZip("95123")
                .enterPayeePhone("876459876")
                .enterPayeeAccountNumber("43214")
                .enterPayeeVerifyAccountNumber("43214")
                .enterAmount("-501")
                .clickSendPayment();
        Assert.assertTrue(billPayPage.getResultText().contains("not successful"));
    }

}
