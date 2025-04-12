package itmo.course.pages;

import itmo.course.utils.HumanInteraction;
import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

abstract class BasePage {
    protected WebDriver driver;
    protected WebDriverWait wait;
    protected JavascriptExecutor js;

    public BasePage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(15));
        this.js = (JavascriptExecutor) driver;
    }

    public void setWindowSize(int width, int height) {
        driver.manage().window().setSize(new org.openqa.selenium.Dimension(width, height));
    }

    protected WebElement waitAndClick(String xpath) {
        WebElement element = wait.until(ExpectedConditions.elementToBeClickable(By.xpath(xpath)));
        scrollToElement(element);
        element.click();
        return element;
    }

    protected WebElement clickWithJS(String xpath) {
        WebElement element = wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath(xpath)));
        scrollToElement(element);
        js.executeScript("arguments[0].click();", element);
        return element;
    }

    private void scrollToElement(WebElement element) {
        js.executeScript("arguments[0].scrollIntoView({behavior: 'smooth', block: 'center'});", element);
        wait.until(ExpectedConditions.visibilityOf(element));
    }

    public void openUrl(String url) {
        driver.get(url);
    }

    public void scrollToBottom() {
        js.executeScript("window.scrollTo(0, document.body.scrollHeight)");
        wait.until(ExpectedConditions.jsReturnsValue("return document.readyState === 'complete';"));
    }

    public void scrollToTop() {
        js.executeScript("window.scrollTo(0, 0)");
        wait.until(ExpectedConditions.jsReturnsValue("return document.readyState === 'complete';"));
    }

    public void performHumanScroll() {
        Actions actions = new Actions(driver);
        for (int i = 0; i < 3; i++) {
            actions.scrollByAmount(0, 300).perform();
            HumanInteraction.randomDelay(400, 800);
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
}