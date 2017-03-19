package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindAll;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;

import java.util.List;

public class MainPage extends AbstractPage {

    public MainPage(WebDriver driver) {
        super(driver);
    }

    public void clickButtonToCreateNewWrestler() {
        buttonCreateNewWrestler.click();
        PageFactory.initElements(driver, ProfilePage.class);
    }

    public void goToWrestlerProfile() {
        waitWhenClickable(wrestlerFIO).click();
        PageFactory.initElements(driver, ProfilePage.class);
    }


    @FindBy(xpath = "//input[@ng-model=\"searchFor\"]")
    public WebElement fieldSearchFor;

    @FindBy(xpath = "//button[@type=\"submit\"]")
    public WebElement buttonSearchFor;

    @FindBy(xpath = "//tbody/tr[1]/td[2]")
    public WebElement wrestlerFIO;

    @FindBy(xpath = "//tbody/tr[1]/td[3]")
    public
    WebElement wrestlerRegion;

    @FindBy(xpath = "//tbody/tr[1]/td[4]")
    public
    WebElement wrestlerFST;

    @FindBy(xpath = "//tbody/tr[1]/td[5]")
    public
    WebElement wrestlerLicense;

    @FindBy(xpath = "//tbody/tr[1]/td[6]")
    public
    WebElement wrestlerPhoto;

    @FindBy(xpath = "//tbody/tr[1]/td[7]")
    public
    WebElement wrestlerStyle;

    @FindBy(xpath = "//tbody/tr[1]/td[8]")
    public
    WebElement wrestlerChanged;

    @FindBy(xpath = "//button[2]")
    public WebElement buttonCreateNewWrestler;

    @FindBy(xpath = "//select[@ng-model=\"filters.fregion\"]")
    public
    WebElement filterRegion;

    @FindBy(xpath = "//select[@ng-model=\"filters.ffst\"]")
    public
    WebElement filterFST;

    @FindBy(xpath = "//select[@ng-model=\"filters.fyear\"]")
    public
    WebElement filterLicense;

    @FindBy(xpath = "//select[@ng-model=\"filters.fphoto\"]")
    public
    WebElement filterPhoto;

    @FindBy(xpath = "//select[@ng-model=\"filters.fstyle\"]")
    public
    WebElement filterStyle;

    @FindBy(xpath = "//button[@ng-click=\"resetFilters()\"]")
    public
    WebElement resetFilters;

    @FindBy(xpath = "//select[@ng-model=\"perPage\"]")
    public
    WebElement filterPages;

    @FindAll(@FindBy(how = How.XPATH, using = "//tr/td[1]"))
    public
    List<WebElement> number;

    @FindAll(@FindBy(how = How.XPATH, using = "//tr/td[2]"))
    public
    List<WebElement> fio;

    @FindAll(@FindBy(how = How.XPATH, using = "//tr/td[3]"))
    public
    List<WebElement> region;

    @FindAll(@FindBy(how = How.XPATH, using = "//tr/td[4]"))
    public
    List<WebElement> fst;

    @FindAll(@FindBy(how = How.XPATH, using = "//tr/td[5]"))
    public
    List<WebElement> license;

    @FindAll(@FindBy(how = How.XPATH, using = "//tr/td[6]"))
    public
    List<WebElement> photo;

    @FindAll(@FindBy(how = How.XPATH, using = "//tr/td[7]"))
    public
    List<WebElement> style;
}