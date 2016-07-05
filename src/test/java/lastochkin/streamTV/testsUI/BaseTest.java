package lastochkin.streamTV.testsUI;

import com.google.inject.Inject;
import lastochkin.streamTV.helpers.GuiceTestModule;
import lastochkin.streamTV.helpers.ScreenShot;
import lastochkin.streamTV.pages.LoginPage;
import lastochkin.streamTV.wrestlers.Wrestler;
import lastochkin.streamTV.wrestlers.WrestlerService;
import org.openqa.selenium.WebDriver;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Guice;

@Guice(modules = GuiceTestModule.class)
public abstract class BaseTest {

    @Inject
    WrestlerService wrestlerService;

    @Inject
    LoginPage loginPage;

    @Inject
    public
    WebDriver driver;

    @AfterSuite(enabled = false)
    public void close() {
        if (driver != null) {
            driver.quit();
        }
    }

    //TODO: Check wrestlers before suite dont exist/delete
    @BeforeSuite
    public void beforeSuite() {

    }

    @AfterMethod(enabled = true)
    public void deleteWrestlersIfTestFailed(ITestResult tr) {
        System.out.println("...................AFTER METHOD......................");

        //make screenshot if failed
        if(ITestResult.FAILURE == tr.getStatus()){
            this.driver = ((BaseTest) tr.getInstance()).driver;
            new ScreenShot(driver).captureScreen(tr.getName());
            System.out.println("..................... LOOK SCREENSHOT IN THE ROOT OF PROJECT! .......................");
        }


        //delete wrestlers if test failed
        if (ITestResult.FAILURE == tr.getStatus() && UItests.wrestlersExist > 0) {
            Wrestler wrestler1 = (Wrestler) tr.getParameters()[0];

            loginPage.login(driver);
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


