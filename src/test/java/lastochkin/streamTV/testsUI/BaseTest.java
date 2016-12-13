package lastochkin.streamTV.testsUI;

import lastochkin.streamTV.helpers.ConfigProperties;
import lastochkin.streamTV.helpers.Driver;
import lastochkin.streamTV.helpers.ScreenShot;
import lastochkin.streamTV.pages.LoginPage;
import lastochkin.streamTV.pages.MainPage;
import lastochkin.streamTV.wrestlers.Wrestler;
import lastochkin.streamTV.wrestlers.WrestlerService;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.support.PageFactory;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;

import java.util.concurrent.TimeUnit;


public abstract class BaseTest extends Driver {


    LoginPage loginPage = PageFactory.initElements(getWebDriver(), LoginPage.class);
    WrestlerService wrestlerService = new WrestlerService();

    @AfterSuite(enabled = false)
    public void close() {
        if (getWebDriver() != null) {
            getWebDriver().quit();
        }
    }

    //TODO: Check wrestlers before suite dont exist/delete
    @BeforeSuite
    public void beforeSuite() {

    }

    @AfterMethod(enabled = true)
    public void deleteWrestlersIfTestFailed(ITestResult tr) {
        System.out.println("...............................AFTER METHOD......................");

        //make screenshot if failed
        if(ITestResult.FAILURE == tr.getStatus()){
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


