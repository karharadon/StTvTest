package lastochkin.streamTV.helpers;

import com.google.inject.Inject;
import com.google.inject.name.Named;
import lastochkin.streamTV.pages.AbstractPage;
import lastochkin.streamTV.pages.LoginPage;
import lastochkin.streamTV.tests.BaseTest;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.ITestResult;
import org.testng.TestListenerAdapter;
import org.testng.annotations.Guice;
import ru.yandex.qatools.allure.annotations.Description;

import java.io.File;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;


@Guice(modules = GuiceTestModule.class)
public class TestListener extends TestListenerAdapter {

    private Logger LOG = LoggerFactory.getLogger(this.getClass());

    WebDriver driver;

    @Override
    public void onTestSuccess(ITestResult result) {
        LOG.info("+++++++++++++++++Test:" + result.getName() + " - passed succesfull  ++++++++++++++++ ");
    }

    @Inject
    @Override
    @Description("Method makes screenshot if test will fail")
    public void onTestFailure(ITestResult result) {
        this.driver = ((BaseTest) result.getInstance()).driver;
        new ScreenShot(driver).captureScreen(result.getName());

        LOG.info("..................... Test:" + result.getName() + " - failed ...........................");
        LOG.info("..................... LOOK SCREENSHOT IN THE ROOT OF PROJECT! ...........................");
    }

    @Override
    public void onTestStart(ITestResult result) {
        LOG.info("..................... Test:" + result.getName() + " - start ...........................");
    }

}
