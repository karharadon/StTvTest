package lastochkin.streamTV.tests;

import com.google.inject.Inject;
import lastochkin.streamTV.helpers.GuiceTestModule;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.Guice;

@Guice(modules= GuiceTestModule.class)
public abstract class BaseTest {

    @Inject
    public
    WebDriver driver;

    @AfterSuite(enabled = false)
    public void close() {
        if (driver != null) {
            driver.quit();
        }
    }
}
