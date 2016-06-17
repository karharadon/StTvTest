package lastochkin.streamTV.tests;

import com.google.inject.Inject;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterSuite;


public abstract class BaseTest {

    @Inject
    public
    WebDriver driver;

    @AfterSuite
    public void close() {
        if (driver != null) {
            driver.quit();
        }
    }


}
