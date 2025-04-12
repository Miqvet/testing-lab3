package itmo.course.pages;

import itmo.course.utils.HumanInteraction;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;

import java.util.NoSuchElementException;

import org.openqa.selenium.*;

public class ContentPage extends BasePage {
    private static final String OFFER_CARD = "//div[contains(@class, 'tile-root')][1]";
    private static final String IMAGE_PREVIEW = "//div[contains(@class, 'preview-modal')]";
    private static final String CLOSE_BUTTON = "//button[contains(@class, 'close-button')]";
    private static final String BLOCKED_PAGE_INDICATOR = "//h1[contains(text(), 'Доступ ограничен')]";
    private static final String PAGE_CONTENT = "//div[contains(@class, 'main-content')]";

    public ContentPage(WebDriver driver) {
        super(driver);
    }

    public void openFirstOffer() {
        clickWithRetry(OFFER_CARD, 3);
    }

    public boolean isImagePreviewVisible() {
        try {
            return wait.until(d -> driver.findElement(By.xpath(IMAGE_PREVIEW)).isDisplayed());
        } catch (TimeoutException e) {
            return false;
        }
    }

    public void closeImagePreview() {
        clickWithRetry(CLOSE_BUTTON, 2);
    }

    public boolean isAccessBlocked() {
        try {
            return driver.findElement(By.xpath(BLOCKED_PAGE_INDICATOR)).isDisplayed();
        } catch (NoSuchElementException e) {
            return false;
        }
    }

    private void clickWithRetry(String xpath, int maxAttempts) {
        for (int attempt = 1; attempt <= maxAttempts; attempt++) {
            try {
                WebElement element = wait.until(ExpectedConditions.elementToBeClickable(By.xpath(xpath)));
                scrollToElement(element);
                element.click();
                return;
            } catch (StaleElementReferenceException e) {
                if (attempt == maxAttempts) throw e;
                HumanInteraction.randomDelay(500, 1000);
            }
        }
    }

    private void scrollToElement(WebElement element) {
        js.executeScript("arguments[0].scrollIntoView({behavior: 'smooth', block: 'center'})", element);
        wait.until(ExpectedConditions.visibilityOf(element));
    }

    private void waitForPageLoad() {
        wait.until(d -> js.executeScript("return document.readyState").equals("complete"));
        wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath(PAGE_CONTENT)));
    }
}