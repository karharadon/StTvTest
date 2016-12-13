package lastochkin.streamTV.wrestlers;

import com.google.inject.Inject;
import lastochkin.streamTV.helpers.Driver;
import lastochkin.streamTV.pages.MainPage;
import lastochkin.streamTV.pages.ProfilePage;
import lastochkin.streamTV.testsUI.BaseTest;
import lastochkin.streamTV.testsUI.UItests;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.testng.annotations.Guice;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.awt.image.DataBuffer;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;


public class WrestlerService extends Driver {


    MainPage mainPage = PageFactory.initElements(getWebDriver(), MainPage.class);
    ProfilePage profilePage = PageFactory.initElements(getWebDriver(), ProfilePage.class);



    private final DateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
    private final Date today = Calendar.getInstance().getTime();
    private final String sysDate = dateFormat.format(today);
    private final String photoUploaded = "No";

    File downloadedImage = null;
    String expectedImage = System.getProperty("user.dir") + "/src/test/resources/expectedImage.png";
    String imageForUploading = System.getProperty("user.dir") + "/src/test/resources/imageForUploading.jpg";
    String attachmentForUploading = System.getProperty("user.dir") + "/src/test/resources/attachmentForTest.txt";
    String attachmentName = "attachmentForTest.txt";

    public ArrayList<String> errorsAfterCreating = new ArrayList<>();
    public ArrayList<String> errorsAfterUpdating = new ArrayList<>();

    public void createWrestler(Wrestler wrestler1) {
        mainPage.clickButtonToCreateNewWrestler();
        profilePage.fillAllFields(wrestler1);
        profilePage.buttonSave.click();
        profilePage.closeProfilePage();
        System.out.println("Wrestler was created.");
        UItests.wrestlersExist++;
    }

    public void findWrestler(String wrestlerFullName) {
        mainPage.clearAndSendKeys(mainPage.waitWhenClickable(mainPage.fieldSearchFor), wrestlerFullName);
        mainPage.waitWhenClickableAndClick(mainPage.buttonSearchFor);
    }

    public MainPage deleteWrestler() {
        mainPage.waitWhenClickableAndClick(mainPage.buttonSearchFor);
        mainPage.goToWrestlerProfile();
        profilePage.deleteWrestler.click();
        profilePage.confirmWrestlerDeletation();
        System.out.println("Wrestler was deleted.");
        UItests.wrestlersExist--;
        return PageFactory.initElements(getWebDriver(), MainPage.class);
    }

    public void checkDeletion(String wrestlerFullName) {
        if (isExist(wrestlerFullName)) {
            throw new RuntimeException("Wrestler wasn't deleted or exist wrestlers with a same name!");
        }
    }

    private boolean isExist(String wrestlerFullName) {
        mainPage.clearAndSendKeys(mainPage.fieldSearchFor, wrestlerFullName);
        mainPage.waitWhenClickableAndClick(mainPage.buttonSearchFor);
        return mainPage.fio.size() != 0;
    }

    public void verifySearchResultWithCode(Wrestler wrestler1, String fullName, ArrayList<String> errors) {
        assertSearchResulstWithCode(mainPage.wrestlerFIO, fullName, errors);
        assertSearchResulstWithCode(mainPage.wrestlerRegion, wrestler1.regionFirst, errors);
        assertSearchResulstWithCode(mainPage.wrestlerFST, wrestler1.fstFirst, errors);
        assertSearchResulstWithCode(mainPage.wrestlerLicense, wrestler1.year, errors);
        assertSearchResulstWithCode(mainPage.wrestlerPhoto, photoUploaded, errors);
        assertSearchResulstWithCode(mainPage.wrestlerStyle, wrestler1.style, errors);
        assertSearchResulstWithCode(mainPage.wrestlerChanged, sysDate, errors);
        System.out.println("Search results were verified with code.");
    }

    public void verifyProfileDataWithCode(Wrestler wrestler1, ArrayList<String> errors) {
        mainPage.wrestlerFIO.click();
        assertProfileDataWithCode2(profilePage.fieldLastName, wrestler1.lastName, errors);
        assertProfileDataWithCode2(profilePage.fieldFirstName, wrestler1.firstName, errors);
        assertProfileDataWithCode2(profilePage.fieldDateOfBirth, wrestler1.dateOfBirth, errors);
        assertProfileDataWithCode2(profilePage.fieldMiddleName, wrestler1.middleName, errors);
        assertProfileDataWithCode(profilePage.fieldRegion1, wrestler1.regionFirst, errors);
        assertProfileDataWithCode(profilePage.fieldRegion2, wrestler1.regionSecond, errors);
        assertProfileDataWithCode(profilePage.fieldFST1, wrestler1.fstFirst, errors);
        assertProfileDataWithCode(profilePage.fieldFST2, wrestler1.fstSecond, errors);
        assertProfileDataWithCode2(profilePage.fieldTrainer1, wrestler1.trainerFirst, errors);
        assertProfileDataWithCode2(profilePage.fieldTrainer2, wrestler1.trainerSecond, errors);
        assertProfileDataWithCode(profilePage.fieldStyle, wrestler1.style, errors);
        assertProfileDataWithCode(profilePage.fieldAge, wrestler1.age, errors);
        assertProfileDataWithCode(profilePage.fieldYear, wrestler1.year, errors);
        assertProfileDataWithCode(profilePage.fieldCard, wrestler1.card, errors);
        profilePage.closeProfilePage();
        System.out.println("Profile data were verified with code.");
    }

    protected void assertSearchResulstWithCode(WebElement fact, String expected, ArrayList<String> err) {
        try {
            assert (fact.getText().equals(expected));
            System.out.println("Assertation search results with code data");
        } catch (AssertionError e) {
            err.add("After search result the field " + " expected contains " + expected
                    + " but was: " + fact.getText());
        }
    }

    //method used for drop fields
    protected void assertProfileDataWithCode(WebElement fact, String expected, ArrayList<String> err) {
        try {
            assert (mainPage.getComboboxElement(fact).equals(expected));
            System.out.println("Assertation profile data with code data");
        } catch (AssertionError e) {
            err.add("On the profile page the field " + " expected contains " + expected
                    + " but was: " + mainPage.getComboboxElement(fact));
        }
    }

    //method used for simple fields
    protected void assertProfileDataWithCode2(WebElement fact, String expected, ArrayList<String> errors) {
        try {
            assert (fact.getAttribute("value").equals(expected));
            System.out.println("Assertation profile data with code data");
        } catch (AssertionError e) {
            errors.add("On the profile page the field" + " expected contains " + expected
                    + " but was: " + fact.getAttribute("value"));
        }
    }


    public void checkExeptions(ArrayList<String> errors) {
        if (errors.size() > 0) {
            errors.forEach(System.out::println);
            throw new RuntimeException("Some fields contain wrong data!");
        }
    }

    public void updateWrestler(Wrestler wrestler1, Wrestler wrestler2) {
        findWrestler(wrestler1.getFullName());
        mainPage.goToWrestlerProfile();
        profilePage.fillAllFields(wrestler2);
        profilePage.buttonSave.click();
        profilePage.closeProfilePage();
        System.out.println("Wrestler was updated");
    }

    public void createFewWrestlersForTestingFilters(Wrestler wrestler2, Wrestler wrestler3, Wrestler wrestler4,
                                                    Wrestler wrestler5, Wrestler wrestler6) {
        createWrestler(wrestler2);
        createWrestler(wrestler3);
        createWrestler(wrestler4);
        createWrestler(wrestler5);
        createWrestler(wrestler6);
        System.out.println("Wrestlers for filters were made");
    }

    public void useAndCheckDifferentFilters(Wrestler wrestler2) {
        String filterPerPage = "50";
        String exceptionText = "Amount of wrestlers after filter != expected";

        mainPage.waitWhenClickable(mainPage.buttonSearchFor).click();
        mainPage.selectFromDD(mainPage.filterPages, filterPerPage);
        assertThat("Amount of wrestlers on the page more than filter \"perPage\"!",
                mainPage.number.size() <= Integer.parseInt(filterPerPage));

        mainPage.clearAndSendKeys(mainPage.fieldSearchFor, wrestler2.getFullName());
        mainPage.waitWhenClickableAndClick(mainPage.buttonSearchFor);
        if (mainPage.fio.size() != 5) throw new RuntimeException(exceptionText);
        checkFilter(mainPage.fio, wrestler2.getFullName());

        mainPage.selectFromDD(mainPage.filterRegion, wrestler2.regionFirst);
        if (mainPage.fio.size() != 4) throw new RuntimeException(exceptionText);
        checkFilter(mainPage.region, wrestler2.regionFirst);

        mainPage.selectFromDD(mainPage.filterFST, wrestler2.fstFirst);
        if (mainPage.fio.size() != 3) throw new RuntimeException(exceptionText);
        checkFilter(mainPage.fst, wrestler2.fstFirst);

        mainPage.selectFromDD(mainPage.filterLicense, wrestler2.year);
        if (mainPage.fio.size() != 2) throw new RuntimeException(exceptionText);
        checkFilter(mainPage.license, wrestler2.year);

        mainPage.selectFromDD(mainPage.filterPhoto, photoUploaded);
        checkFilter(mainPage.photo, photoUploaded);

        mainPage.selectFromDD(mainPage.filterStyle, wrestler2.style);
        if (mainPage.fio.size() != 1) throw new RuntimeException(exceptionText);
        checkFilter(mainPage.style, wrestler2.style);

        mainPage.waitWhenClickable(mainPage.resetFilters).click();
        if (mainPage.fio.size() != 5) throw new RuntimeException(exceptionText);
    }

    public void checkFilter(List<WebElement> list, String filter) {
        for (WebElement aList : list) {
            assertThat("The field " + filter + " expected " + filter + "but was: " + aList.getText(),
                    aList.getText().equals(filter));
            System.out.println("Filter was checked.");
        }
    }

    public void deleteWrestlersCreatedForTestingFilters(Wrestler wrestler2) {
        for (int i = 0; i < 5; i++) {
            findWrestler(wrestler2.getFullName());
            deleteWrestler();
        }
        System.out.println("All wrestlers created for filters were deleted");
    }

    public void uploadAttachment() {

        mainPage.goToWrestlerProfile();
        profilePage.formForAttachment.sendKeys(attachmentForUploading);
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public void checkThatCorrectAttachmentWasUploaded() {
        assertThat("Uploaded file has unexpected name!", attachmentName.equals(profilePage.uploadedAttachment.getText()));
        System.out.println("Correct file was uploaded.");
        profilePage.closeProfilePage();
    }

    public void checkAttachmentDeletation(Wrestler wrestler1) {
        findWrestler(wrestler1.getFullName());
        mainPage.goToWrestlerProfile();
        profilePage.waitWhenClickable(profilePage.deleteAttachment).click();
        profilePage.closeProfilePage();
        findWrestler(wrestler1.getFullName());
        mainPage.goToWrestlerProfile();
        assertThat("Attachment file was not deleted!", profilePage.NumberOfAttachments() == 0);
        System.out.println("Attachment file was deleted successfully");
        profilePage.closeProfilePage();
    }

    //TODO Change Thread.sleep
    public void uploadImage() {
        mainPage.goToWrestlerProfile();
        profilePage.formForPhoto.sendKeys(imageForUploading);
        profilePage.waitWhenClickable(profilePage.image);
        profilePage.closeProfilePage();
    }

    public void checkThatCorrectImageWasUploaded(Wrestler wrestler1) throws IOException {
        boolean arePhotosEqual = imageIsEqual(expectedImage, downloadPhoto(wrestler1));
        assertThat("Downloaded image is not as expected", arePhotosEqual, is(true));
        System.out.println("Correct image was uploaded");
        profilePage.closeProfilePage();
        downloadedImage.deleteOnExit();
    }

    public boolean imageIsEqual(String expectedFile, String actualFile) throws IOException {

        BufferedImage bufImExpected = ImageIO.read(new File(expectedFile));
        DataBuffer datBufExpected = bufImExpected.getData().getDataBuffer();
        int sizefileExp = datBufExpected.getSize();

        BufferedImage bufImActual = ImageIO.read(new File(actualFile));
        DataBuffer datBufActual = bufImActual.getData().getDataBuffer();
        int sizeFileActual = datBufActual.getSize();

        Boolean isEqual = true;
        if (sizefileExp == sizeFileActual) {
            for (int j = 0; j < sizefileExp; j++) {
                if (datBufExpected.getElem(j) != datBufActual.getElem(j)) {
                    isEqual = false;
                    break;
                }
            }
        } else {
            isEqual = false;
        }
        return isEqual;
    }

    public String downloadPhoto(Wrestler wrestler1) throws IOException {
        findWrestler(wrestler1.getFullName());
        mainPage.goToWrestlerProfile();
        String location = profilePage.waitWhenClickable(profilePage.image).getAttribute("src");
        URL url = new URL(location);
        BufferedImage bufImgOne = ImageIO.read(url);
        downloadedImage = File.createTempFile("downloadedPhoto", ".png");
        ImageIO.write(bufImgOne, "png", downloadedImage);
        return downloadedImage.getAbsolutePath();
    }

    //TODO check this method
    public void deleteWrestlerIfExist(Wrestler wrestler) {
        if (isExist(wrestler.getFullName())) {
            for (int i = 0; i < mainPage.fio.size(); i++) {
                deleteWrestler();
            }
        }
    }
}
