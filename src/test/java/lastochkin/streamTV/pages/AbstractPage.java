package lastochkin.streamTV.pages;

import com.google.inject.Inject;
import lastochkin.streamTV.helpers.ScreenShot;
import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import static org.openqa.selenium.support.ui.ExpectedConditions.presenceOfElementLocated;

public abstract class AbstractPage {

    @Inject
    public AbstractPage(WebDriver driver) {
        this.driver = driver;
    }

    protected String className = this.getClass().getSimpleName();

    WebDriver driver;

    public void clearAndSendKeys(WebElement webElement, String text) {
        webElement.clear();
        webElement.sendKeys(text);
    }

    public void clearAndSendKeys(WebElement webElement, int text) {
        webElement.clear();
        webElement.sendKeys(Integer.toString(text));
    }

    public void waitWhenClickable(WebElement element, int timeSec) {
        (new WebDriverWait(driver, timeSec)).until(ExpectedConditions.elementToBeClickable(element));
    }

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

    public void captureScreen(String fileName) {
        ScreenShot screenShot = new ScreenShot(driver);
        screenShot.captureScreen(fileName);
    }

    protected WebElement waitForElementPresence(By xpath, int timeoutInSeconds) {
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
