package lastochkin.streamTV.helpers;

import com.google.inject.Inject;
import lastochkin.streamTV.tests.BaseTest;
import org.openqa.selenium.WebDriver;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.ITestResult;
import org.testng.TestListenerAdapter;
import org.testng.annotations.Guice;

import java.io.File;


@Guice(modules = GuiceTestModule.class)
public class TestListener extends TestListenerAdapter {

    private Logger LOG = LoggerFactory.getLogger(this.getClass());

    WebDriver driver;

    @Inject
    ScreenShot scr;

    @Override
    public void onTestSuccess(ITestResult result) {

        LOG.info("+++++++++++++++++Test:" + result.getName() + " - passed succesfull  ++++++++++++++++ ");

    }

    @Override
    public void onTestFailure(ITestResult result) {

       // this.driver = ((BaseTest)result.getInstance()).driver;
        //scr.captureScreen(result.getClass().getSimpleName());
        LOG.info("-----------------Test:" + result.getName() + " - failed ----------------------------");


    }

    @Override
    public void onTestStart(ITestResult result) {
        LOG.info("..................... Test:" + result.getName() + " - start ...........................");
    }

}
