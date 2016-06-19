package lastochkin.streamTV.pages;

import com.google.inject.Inject;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import static lastochkin.streamTV.helpers.ConfigProperties.getProperty;

public class LoginPage extends AbstractPage {

    @Inject
    public LoginPage(WebDriver driver) {
        super(driver);
        PageFactory.initElements(driver, this);
    }

    public MainPage login(WebDriver driver) {
        open(driver);
        clearAndSendKeys(fieldLogin, getProperty("username"));
        clearAndSendKeys(fieldPassword, getProperty("password"));
        buttonLogin.click();
        System.out.println("Site was opened.");
        return PageFactory.initElements(driver, MainPage.class);
    }

    private LoginPage open(WebDriver driver) {
        driver.get(getProperty("url"));
        waitForPageLoad(driver);
        return PageFactory.initElements(driver, LoginPage.class);
    }

    @FindBy(xpath = "//div/input [1]")
    public WebElement fieldLogin;

    @FindBy(xpath = "//div/input [@placeholder = 'Password']")
    public WebElement fieldPassword;

    @FindBy(xpath = "//button")
    public WebElement buttonLogin;
}
