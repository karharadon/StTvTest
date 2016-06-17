package lastochkin.streamTV.tests;

import com.google.inject.Inject;
import lastochkin.streamTV.helpers.ConfigProperties;
import lastochkin.streamTV.helpers.GuiceTestModule;
import lastochkin.streamTV.helpers.ScreenShot;
import lastochkin.streamTV.helpers.TestListener;
import lastochkin.streamTV.pages.LoginPage;
import lastochkin.streamTV.wrestlers.Wrestler;
import lastochkin.streamTV.wrestlers.WrestlerService;
import org.openqa.selenium.WebDriver;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.annotations.*;
import ru.yandex.qatools.allure.annotations.Description;
import ru.yandex.qatools.allure.annotations.Severity;
import ru.yandex.qatools.allure.model.SeverityLevel;

import static org.hamcrest.MatcherAssert.*;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

@Listeners(TestListener.class)
@Guice(modules = GuiceTestModule.class)
public class UItests extends BaseTest {

    @Inject
    ScreenShot scr;

    @Inject
    LoginPage loginPage;

    @Inject
    WrestlerService wrestlerService;

    @Description("Test create wrestler and delete")
    @Severity(SeverityLevel.BLOCKER)
    @Test(dataProviderClass = Wrestler.class, dataProvider = "wrestlers", priority = 1)
    public void createAndDelete(Wrestler wrestler1) {

    }

    @Description("Test create wrestler and verify data")
    @Severity(SeverityLevel.CRITICAL)
    @Test(dataProviderClass = Wrestler.class, dataProvider = "wrestlers", priority = 2)
    public void createAndVerify(Wrestler wrestler1) {

        driver.get("http://streamtv.net.ua/base/");
        scr.captureScreen(this.getClass().getSimpleName());
                assertThat("different wrestlers", wrestler1.equals("wrestler"));

    }

    @Description("Test updates wrestler and verify data")
    @Severity(SeverityLevel.CRITICAL)
    @Test(dataProviderClass = Wrestler.class, dataProvider = "wrestlers", priority = 3)
    public void updateAndVerify(Wrestler wrestler1, Wrestler wrestler2) {
        driver.get("http://vk.com/");
        System.out.println(wrestler1.hashCode());
        System.out.println(wrestler2.hashCode());
    }

    @Description("Test creates six wrestlers verify that filters works correct")
    @Severity(SeverityLevel.BLOCKER)
    @Test(dataProviderClass = Wrestler.class, dataProvider = "wrestlers", priority = 4)
    public void checkFilters(Wrestler wrestler1, Wrestler wrestler2, Wrestler wrestler3, Wrestler wrestler4,
                             Wrestler wrestler5, Wrestler wrestler6) {
        driver.get("http://google.com/");
        System.out.println(wrestler1.hashCode());
        System.out.println(wrestler2.hashCode());
        System.out.println(wrestler3.toString());
        System.out.println(wrestler4.toString());
        System.out.println(wrestler5.toString());
        System.out.println(wrestler6.toString());
    }

    @Description("Test verify that correct photo upload to the wrestlers profile")
    @Severity(SeverityLevel.CRITICAL)
    @Test(dataProviderClass = Wrestler.class, dataProvider = "wrestlers", priority = 5)
    public void uploadImage(Wrestler wrestler1) throws IOException {
        System.out.println(wrestler1.toString());
    }

    @Description("Test verify that correct file upload to the wrestlers profile")
    @Severity(SeverityLevel.CRITICAL)
    @Test(dataProviderClass = Wrestler.class, dataProvider = "wrestlers", priority = 6)
    public void uploadAndDeleteAttachment(Wrestler wrestler1) throws IOException {
        loginPage.run();
        wrestlerService.wrestlerServiceRun();
        System.out.println(wrestler1);
    }
}

