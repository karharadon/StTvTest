package lastochkin.streamTV.pages;

import com.google.inject.Inject;
import lastochkin.streamTV.wrestlers.Wrestler;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

/**
 * Created by karhamint on 16.06.16.
 */
public class ProfilePage extends AbstractPage {

    @Inject
    public ProfilePage(WebDriver driver){
        super(driver);
        PageFactory.initElements(driver, this);
    }

    public void fillAllFields(Wrestler wrestler) {
        clearAndSendKeys(fieldLastName, wrestler.lastName);
        clearAndSendKeys(fieldFirstName, wrestler.firstName);
        clearAndSendKeys(fieldDateOfBirth, wrestler.dateOfBirth);
        clearAndSendKeys(fieldMiddleName, wrestler.middleName);
        selectFromDD(fieldRegion1, wrestler.regionFirst);
        selectFromDD(fieldRegion2, wrestler.regionSecond);
        selectFromDD(fieldFST1, wrestler.fstFirst);
        selectFromDD(fieldFST2, wrestler.fstSecond);
        clearAndSendKeys(fieldTrainer1, wrestler.trainerFirst);
        clearAndSendKeys(fieldTrainer2, wrestler.trainerSecond);
        selectFromDD(fieldStyle, wrestler.style);
        selectFromDD(fieldAge, wrestler.age);
        selectFromDD(fieldYear, wrestler.year);
        selectFromDD(fieldCard, wrestler.card);
        System.out.println("All fields was filled.");
    }



    @FindBy(xpath = "//div/input[@placeholder='Last name']")
    public
    WebElement fieldLastName;

    @FindBy(xpath = "//div/input[@placeholder='First name']")
    public
    WebElement fieldFirstName;

    @FindBy(xpath = "//div/input[@placeholder='Date of Birth']")
    public
    WebElement fieldDateOfBirth;

    @FindBy(xpath = "//div/input[@placeholder='Middle name']")
    public
    WebElement fieldMiddleName;

    @FindBy(xpath = "//fg-select [@value=\"wr.region1\"]//select")
    public
    WebElement fieldRegion1;

    @FindBy(xpath = "//fg-select [@value=\"wr.region2\"]//select")
    public
    WebElement fieldRegion2;

    @FindBy(xpath = "//fg-select [@value=\"wr.fst1\"]//select")
    public
    WebElement fieldFST1;

    @FindBy(xpath = "//fg-select [@value=\"wr.fst2\"]//select")
    public
    WebElement fieldFST2;

    @FindBy(xpath = "//fg-typeahead[@value= \"wr.trainer1\"]//input")
    public
    WebElement fieldTrainer1;

    @FindBy(xpath = "//fg-typeahead [@value=\"wr.trainer2\"]//input")
    public
    WebElement fieldTrainer2;

    @FindBy(xpath = "//fg-select [@value=\"wr.style\"]//select")
    public
    WebElement fieldStyle;

    @FindBy(xpath = "//fg-select [@value=\"wr.lictype\"]//select")
    public
    WebElement fieldAge;

    @FindBy(xpath = "//fg-select [@value=\"wr.expires\"]//select")
    public
    WebElement fieldYear;

    @FindBy(xpath = "//f-select [@value=\"wr.card_state\"]//select")
    public
    WebElement fieldCard;

    @FindBy(xpath = "//div[@class=\"button\"][1]/button")
    public
    WebElement buttonSave;

    @FindBy(xpath = "//div/button[@ng-click=\"delete()\"]")
    public
    WebElement deleteWrestler;

    @FindBy(xpath = "//button[@ng-click=\"ok()\"]")
    public
    WebElement deleteConfirm;

    @FindBy(xpath = "//li[2]//div//span")
    public
    WebElement closePage;


    @FindBy(xpath = "//input[@uploader=\"photoUploader\"]")
    public
    WebElement formForPhoto;

    @FindBy(xpath = "//input[@uploader=\"attachUploader\"]")
    public
    WebElement formForAttachment;

    @FindBy(xpath = "//img[@class=\"center-block\"]")
    public
    WebElement image;

    @FindBy(xpath = "//td/a")
    public
    WebElement uploadedAttachment;

    @FindBy(xpath = "//ico[@ng-click=\"deleteAttach($index)\"]")
    public
    WebElement deleteAttachment;

    public void closeProfilePage() {
        waitWhenClickable(closePage).click();
        PageFactory.initElements(driver, MainPage.class);
    }

    public void confirmWrestlerDeletation() {
        deleteConfirm.click();
        PageFactory.initElements(driver, MainPage.class);
    }


        public int NumberOfAttachments() {
            return driver.findElements(By.xpath("//div[@class='file-drop']/table/tbody/tr")).size();
        }

}
