package lastochkin.streamTV.helpers;

import com.google.inject.Inject;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.Guice;

import java.io.File;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

@Guice(modules=GuiceTestModule.class)
public class ScreenShot {

    @Inject
    WebDriver driver;

    @Inject
    public ScreenShot(WebDriver driver) {
        this.driver = driver;
    }

    private DateFormat dateFormat = new SimpleDateFormat("_MM.dd.yyyy_HH.mm.ss");
    private Date today = Calendar.getInstance().getTime();
    private String sysDate = dateFormat.format(today);

    public void captureScreen(String fileName) {
        File scr = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
        try {
            FileUtils.copyFile(scr, new File("ScreenShot\\" + fileName + sysDate + ".png"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
