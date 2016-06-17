package lastochkin.streamTV.pages;

import com.google.inject.Inject;
import org.openqa.selenium.WebDriver;

/**
 * Created by karhamint on 16.06.16.
 */
public class MainPage extends AbstractPage{

    @Inject
    public MainPage(WebDriver driver){
        super(driver);
    }

    public void run(){
        System.out.println("run");
    }
}
