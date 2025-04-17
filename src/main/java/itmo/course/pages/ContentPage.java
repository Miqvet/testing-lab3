package itmo.course.pages;

import itmo.course.utils.HumanInteraction;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;

import org.openqa.selenium.NoSuchElementException;

import org.openqa.selenium.*;

public class ContentPage extends BasePage {
    private static final String COOKIE_BUTTON = "/html/body/div[1]/div/div[2]/div/div/div/div/button";

    private static final String MAIN_PAGE_HEADER = "/html/body/div[1]/div/div[1]/div/div/div[1]/div[2]/div[5]/div/div[1]/span";

    private static final String PAGE_CONTENT = "//div[contains(@class, 'main-content')]";
    private static final String PAGE_LOAD_INDICATOR = "//div[contains(@class,'page-loaded')]";

    private static final String FLIGHTS_FIND_BUTTOn = "/html/body/div[1]/div/div[1]/div/div/div[1]/div[2]/div[8]/div[1]/div[2]/button";

    private static final String HOTELS_BUTTON = "/html/body/div[1]/div/div[1]/div/div/div[1]/div[2]/div[6]/div/a[2]";
    private static final String TRAIN_BUTTON = "/html/body/div[1]/div/div[1]/div/div/div[1]/div[2]/div[6]/div/a[3]";
    private static final String TOURS_BUTTON = "/html/body/div[1]/div/div[1]/div/div/div[1]/div[2]/div[6]/div/a[4]";
    private static final String FLIGHTS_BUTTON = "/html/body/div[1]/div/div[1]/div/div/div[1]/div[2]/div[6]/div/a[1]";

    private static final String HOUSE_ICON = "/html/body/div[1]/div/div[1]/div/div/div[5]/div/div/div[2]/div[2]";
    private static final String IMAGE_HOUSE = "/html/body/div[1]/div/div[1]/div[2]/div/div/div[3]/div[1]";

    private static final String BLOCKED_HEADER_XPATH = "//h1[contains(normalize-space(), 'Доступ ограничен')]";

    // форма полета
    private static final String FROM_INPUT = "/html//div[1]/div[1]/div[1]/div/div[1]/div[2]/div[8]//input[1]";
    private static final String SELECT_FROM_INPUT = "/html/body/div[1]/div/div[1]/div/div/div[1]/div[2]/div[8]/div[2]/div[1]/div[1]/div/div[1]/div/div";
    private static final String TO_INPUT = "/html//div[1]/div[1]/div[1]/div/div[1]/div[2]/div[8]//input[2]";
    private static final String SELECT_TO_INPUT = "/html/body/div[1]/div/div[1]/div/div/div[1]/div[2]/div[8]/div[2]/div[1]/div[2]/div/div[1]/div/div/div[2]/div[2]";
    private static final String DEPARTURE_DATE_INPUT = "/html//div[1]/div[1]/div[1]/div/div[1]/div[2]/div[8]//input[3]";
    private static final String SELECT_DEPARTURE_DATE = "/html/body/div[1]/div/div[1]/div/div/div[1]/div[2]/div[8]/div[2]/div[1]/div[3]/div/div[1]/div/div[2]/div[1]/div/div/div/div[2]/table/tbody/tr[3]/td[3]";
    private static final String RETURN_DATE_INPUT = "/html//div[1]/div[1]/div[1]/div/div[1]/div[2]/div[8]//input[4]";
    private static final String SELECT_TO_DATE_INPUT = "/html/body/div[1]/div/div[1]/div/div/div[1]/div[2]/div[8]/div[2]/div[1]/div[3]/div/div[1]/div/div[2]/div[1]/div/div/div/div[2]/table/tbody/tr[3]/td[3]";
    private static final String PASSENGERS_COUNT_INPUT = "/html//div[1]/div[1]/div[1]/div/div[1]/div[2]/div[8]//input[5]";

    public ContentPage(WebDriver driver) {
        super(driver);
    }

    public void openFirstHotel() {
        waitAndClick(HOUSE_ICON);
    }

    public void openHotelPhoto() {
        waitAndClick(IMAGE_HOUSE);
    }

    public boolean isPageBlocked() {
        try {
            WebElement blockedHeader = driver.findElement(By.xpath(BLOCKED_HEADER_XPATH));
            return blockedHeader.isDisplayed();
        } catch (NoSuchElementException | StaleElementReferenceException e) {
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

    private void waitForPageLoad1() {
        wait.until(d -> js.executeScript("return document.readyState").equals("complete"));
        wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath(PAGE_CONTENT)));
    }

    // Переход по иконкам
    public void clickHotelsTab() {
        waitAndClick(HOTELS_BUTTON);
    }

    public void clickTrainTab() {
        waitAndClick(TRAIN_BUTTON);
    }

    public void clickToursTab() {
        waitAndClick(TOURS_BUTTON);
    }
    public void clickFlightsTab() {
        waitAndClick(FLIGHTS_BUTTON);
    }
    //выбор билета
    public void clickFindButton() {
        waitAndClick(FROM_INPUT);
        waitAndClick(SELECT_FROM_INPUT);
        waitAndClick(SELECT_TO_INPUT);
        waitAndClick(SELECT_DEPARTURE_DATE);
        waitAndClick(SELECT_TO_DATE_INPUT);
        waitAndClick(FLIGHTS_FIND_BUTTOn);
    }

    public void disableCookie(){
        waitAndClick(COOKIE_BUTTON);
    }


    // Локаторы
    private final By surnameField = By.xpath("/html/body/div[1]/div/div[1]/div[2]/div/div/div[3]/div[1]/div[8]/div/div/div[3]/div/div[2]/div[3]/div/div/label/div/div/input");
    private final By nameField = By.xpath("//input[@name='name']");
    private final By middleNameField = By.xpath("//input[@name='middlename']");
    private final By birthDateField = By.xpath("//input[@name='birthday']");
    private final By passportField = By.xpath("//input[@name='seriesAndNumber']");
    private final String menXpath = "/html/body/div[1]/div/div[1]/div[2]/div/div/div[3]/div[1]/div[8]/div/div/div[3]/div/div[2]/div[9]/div/label/div/label[1]/input";

    public void fillSurname(String surname) {
        WebElement field = wait.until(ExpectedConditions.elementToBeClickable(surnameField));
        field.sendKeys(surname);
    }

    public void fillName(String name) {
        fillField(nameField, name);
    }

    public void fillMiddleName(String middleName) {
        fillField(middleNameField, middleName);
    }
    public void fillBirthDate(String birthDate) {
        fillField(birthDateField, birthDate);
    }

    public void fillPassport(String passport) {
        fillField(passportField, passport);
    }

    public void fillMen() {
        waitAndClick(menXpath);
    }

    private void fillField(By locator, String value) {
        WebElement field = wait.until(ExpectedConditions.elementToBeClickable(locator));
        field.click();
        field.clear();
        field.sendKeys(value);
    }

}