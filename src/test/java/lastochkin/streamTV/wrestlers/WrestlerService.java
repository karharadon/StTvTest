package lastochkin.streamTV.wrestlers;

import com.google.inject.Inject;
import lastochkin.streamTV.helpers.GuiceTestModule;
import lastochkin.streamTV.pages.MainPage;
import lastochkin.streamTV.pages.ProfilePage;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.Guice;

/**
 * Created by karhamint on 16.06.16.
 */

@Guice(modules = GuiceTestModule.class)
public class WrestlerService {

    @Inject
    MainPage mainPage;

    @Inject
    ProfilePage profilePage;

    @Inject
    WebDriver driver;


    public  void wrestlerServiceRun(){
        mainPage.run();
        profilePage.run();
    }
}
