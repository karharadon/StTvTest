package testsUI;

import helpers.Driver;
import helpers.ScreenShot;
import pages.LoginPage;

import wrestlers.Wrestler;
import wrestlers.WrestlerService;
import org.openqa.selenium.support.PageFactory;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;

public abstract class BaseTest extends Driver {


    LoginPage loginPage = PageFactory.initElements(getWebDriver(), LoginPage.class);
    WrestlerService wrestlerService = new WrestlerService();

    @AfterSuite(enabled = false)
    public void close() {
        if (getWebDriver() != null) {
            getWebDriver().quit();
        }
    }

    //TODO: Check wrestlers before suite don't exist/delete
    @BeforeSuite
    public void beforeSuite() {

    }

    @AfterMethod(enabled = true)
    public void deleteWrestlersIfTestFailed(ITestResult tr) {
        System.out.println("...............................AFTER METHOD......................");

        //make screenshot if failed
        if (ITestResult.FAILURE == tr.getStatus()) {
            // this.driver = ((BaseTest) tr.getInstance()).driver;
            new ScreenShot(getWebDriver()).captureScreen(tr.getName());
            System.out.println("..................... LOOK SCREENSHOT IN THE ROOT OF PROJECT! .......................");
        }


        //delete wrestlers if test failed
        if (ITestResult.FAILURE == tr.getStatus() && UItests.wrestlersExist > 0) {
            Wrestler wrestler1 = (Wrestler) tr.getParameters()[0];

            loginPage.login(getWebDriver());
            if (UItests.wrestlersExist == 1) {
                wrestlerService.deleteWrestlerIfExist(wrestler1);
            } else {
                Wrestler wrestler2 = (Wrestler) tr.getParameters()[1];
                wrestlerService.deleteWrestlerIfExist(wrestler1);
                wrestlerService.deleteWrestlerIfExist(wrestler2);
            }
        }

        System.out.println("Wrestlers created for tests don't exist.");
        UItests.wrestlersExist = 0;
        System.out.println(".....................................................");
    }

}


