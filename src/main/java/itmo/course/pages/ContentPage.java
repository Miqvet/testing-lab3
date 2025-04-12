package itmo.course.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;

import java.util.NoSuchElementException;

public class ContentPage extends BasePage {
    // Общие элементы
    private static final String JUSTIFY_END_ELEMENT = "//div[contains(@class, 'justify-end')]";
    private static final String FIRST_IMAGE = "(//img[contains(@class, 'size-full')])[1]";
    private static final String SECOND_IMAGE = "(//img[contains(@class, 'size-full')])[2]";

    private static final String OFFER_CARD = "(//div[contains(@class, 'tile-root')])[1]";
    private static final String IMAGE_PREVIEW = "//div[contains(@class, 'preview-modal')]";
    private static final String CLOSE_PREVIEW_BUTTON = "//button[contains(@class, 'close-button')]";

    public ContentPage(WebDriver driver) {
        super(driver);
    }

    public void openMainPage() {
        driver.get("https://www.ozon.ru/travel/");
        driver.manage().window().maximize();
    }

    public void openFirstOffer() {
        waitAndClick(OFFER_CARD);
    }

    public boolean isImagePreviewVisible() {
        try {
            return driver.findElement(By.xpath(IMAGE_PREVIEW)).isDisplayed();
        } catch (NoSuchElementException e) {
            return false;
        }
    }

    public void closeImagePreview() {
        waitAndClick(CLOSE_PREVIEW_BUTTON);
    }

    public void openPostPage(String postId) {
        driver.get("https://mix.com/!" + postId);
        setWindowSize(1200, 900);
        waitForPageLoad();
    }

    private void waitForPageLoad() {
        wait.until(d -> js.executeScript("return document.readyState").equals("complete"));
    }

    public void clickJustifyEnd() {
        waitAndClick(JUSTIFY_END_ELEMENT);
    }

    public void clickFirstImage() {
        clickWithJS(FIRST_IMAGE);
    }

    public void clickSecondImage() {
        clickWithJS(SECOND_IMAGE);
    }


    public void resetMousePosition() {
        new Actions(driver).moveToElement(driver.findElement(By.tagName("body")), 0, 0).perform();
    }
}