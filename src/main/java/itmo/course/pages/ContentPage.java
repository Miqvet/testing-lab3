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

    private static final String FLIGHTS_FIND_BUTTOn = "/html/body/div[1]/div/div[1]/div/div/div[1]/div[3]/div[8]/div[1]/div[2]/button";

    private static final String HOTELS_BUTTON = "/html/body/div[1]/div/div[1]/div/div/div[1]/div[3]/div[6]/div/a[2]/div[1]";
    private static final String TRAIN_BUTTON = "/html/body/div[1]/div/div[1]/div/div/div[1]/div[3]/div[6]/div/a[3]/div[1]";
    private static final String TOURS_BUTTON = "/html/body/div[1]/div/div[1]/div/div/div[1]/div[3]/div[6]/div/a[4]/div[1]";
    private static final String FLIGHTS_BUTTON = "/html/body/div[1]/div/div[1]/div/div/div[1]/div[2]/div[6]/div/a[1]";

    private static final String BLOCKED_HEADER_XPATH = "//h1[contains(normalize-space(), 'Доступ ограничен')]";

//    private static final String FROM_INPUT = "/html//div[1]/div[1]/div[1]/div/div[1]/div[2]/div[8]//input[1]";
//    private static final String SELECT_FROM_INPUT = "/html/body/div[1]/div/div[1]/div/div/div[1]/div[2]/div[8]/div[2]/div[1]/div[1]/div/div[1]/div/div";
//    private static final String SELECT_TO_INPUT = "/html/body/div[1]/div/div[1]/div/div/div[1]/div[2]/div[8]/div[2]/div[1]/div[2]/div/div[1]/div/div/div[2]/div[2]";
//    private static final String SELECT_DEPARTURE_DATE = "/html/body/div[1]/div/div[1]/div/div/div[1]/div[2]/div[8]/div[2]/div[1]/div[3]/div/div[1]/div/div[2]/div[1]/div/div/div/div[2]/table/tbody/tr[3]/td[3]";
//    private static final String SELECT_TO_DATE_INPUT = "/html/body/div[1]/div/div[1]/div/div/div[1]/div[2]/div[8]/div[2]/div[1]/div[3]/div/div[1]/div/div[2]/div[1]/div/div/div/div[2]/table/tbody/tr[3]/td[3]";

    private static final String FROM_INPUT = "/html/body/div[1]/div/div[1]/div/div/div[1]/div[3]/div[8]/div[1]/div[1]/div[1]/div/div/div/div[2]/input";
    private static final String SELECT_FROM_INPUT = "/html/body/div[1]/div/div[1]/div/div/div[1]/div[3]/div[8]/div[2]/div[1]/div[1]/div/div[1]/div/div/div[2]/div[2]/div[2]";
    private static final String SELECT_TO_INPUT = "/html/body/div[1]/div/div[1]/div/div/div[1]/div[3]/div[8]/div[2]/div[1]/div[2]/div/div[1]/div/div/div[2]/div[2]/div[2]/div";
    private static final String SELECT_DEPARTURE_DATE = "/html/body/div[1]/div/div[1]/div/div/div[1]/div[3]/div[8]/div[2]/div[1]/div[3]/div/div[1]/div/div[2]/div[1]/div/div/div/div[2]/table/tbody/tr[4]/td[1]";
    private static final String SELECT_TO_DATE_INPUT = "/html/body/div[1]/div/div[1]/div/div/div[1]/div[3]/div[8]/div[2]/div[1]/div[3]/div/div[1]/div/div[2]/div[1]/div/div/div/div[2]/table/tbody/tr[4]/td[1]";


    public ContentPage(WebDriver driver) {
        super(driver);
    }

    public boolean isPageBlocked() {
        try {
            WebElement blockedHeader = driver.findElement(By.xpath(BLOCKED_HEADER_XPATH));
            return blockedHeader.isDisplayed();
        } catch (NoSuchElementException | StaleElementReferenceException e) {
            return false;
        }
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

    //выбор билета
    public void clickFindButton() {
        waitAndClick(FROM_INPUT);
        waitAndClick(SELECT_FROM_INPUT);
        waitAndClick(SELECT_TO_INPUT);
        waitAndClick(SELECT_DEPARTURE_DATE);
        waitAndClick(SELECT_TO_DATE_INPUT);
        waitAndClick(FLIGHTS_FIND_BUTTOn);
    }

    public void disableCookie() {
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

    public void fillField(By locator, String value) {
        WebElement field = wait.until(ExpectedConditions.elementToBeClickable(locator));
        field.click();
        field.clear();
        field.sendKeys(value);
    }
}