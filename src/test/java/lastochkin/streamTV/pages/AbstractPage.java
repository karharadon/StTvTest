package lastochkin.streamTV.pages;

import com.google.inject.Inject;
import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import static lastochkin.streamTV.helpers.ConfigProperties.getProperty;
import static org.openqa.selenium.support.ui.ExpectedConditions.presenceOfElementLocated;

public abstract class AbstractPage {

    @Inject
    AbstractPage(WebDriver driver) {
        this.driver = driver;
    }

    private final int waitWebElem = Integer.parseInt(getProperty("waitWebElem"));

    WebDriver driver;

    public void selectFromDD(WebElement element, String text) {
        Select select = new Select(element);
        select.selectByVisibleText(text);
    }

    void waitForPageLoad(WebDriver driver) {
        ExpectedCondition<Boolean> pageLoadCondition = new
                ExpectedCondition<Boolean>() {
                    public Boolean apply(WebDriver driver) {
                        return ((JavascriptExecutor) driver).executeScript("return document.readyState").equals("complete");
                    }
                };
        WebDriverWait wait = new WebDriverWait(driver, 30);
        wait.until(pageLoadCondition);
    }

    public String getComboboxElement(WebElement webElement) {
        Select comboBox = new Select(webElement);
        return comboBox.getFirstSelectedOption().getText();
    }

    public void clearAndSendKeys(WebElement webElement, String text) {
        waitWhenClickable(webElement).clear();
        webElement.sendKeys(text);
    }

    public void waitWhenClickableAndClick(WebElement element) {
        (new WebDriverWait(driver, waitWebElem)).until(ExpectedConditions.elementToBeClickable(element));
        try {
            element.click();
        } catch (WebDriverException e) {
            javaScriptHelpsClickOnButton(element);
            e.getMessage();
            e.getAdditionalInformation();
            e.getBuildInformation();
            e.getSystemInformation();
            System.out.println("JavaScript helped click button");
        }
    }

    private void javaScriptHelpsClickOnButton(WebElement webElement) {
        try {
            ((JavascriptExecutor) driver).executeScript("var evt = document.createEvent('MouseEvents');"
                    + "evt.initMouseEvent('click',true, " + "true, " + "window, 0, 0, 0, 0, 0, false, false, false," +
                    " false, 0,null);" + "arguments[0].dispatchEvent(evt);", webElement);
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("Can not click on button. Java Script didn't help.");
        }
    }

    public WebElement waitWhenClickable(WebElement element) {
        try {
            new WebDriverWait(driver, waitWebElem).until(ExpectedConditions.elementToBeClickable(element));
        } catch (Exception e) {
            //TODO
        }
        return element;
    }

    public WebElement waitForElementPresence(By xpath, int timeoutInSeconds) {
        new WebDriverWait(driver, timeoutInSeconds * 1000).until(presenceOfElementLocated(xpath));
        return driver.findElement(xpath);
    }

    protected void waitForAbsenceOfElement(By xpath, int timeoutInSeconds) {
        for (int i = 0; i < timeoutInSeconds * 2; i++) {
            new WebDriverWait(driver, 500);
            if (driver.findElements(xpath).isEmpty()) {
                break;
            }
        }
    }
}
