package itmo.course.pages;

import org.openqa.selenium.*;

public class SelectHouseSettings extends BasePage {
    private static final String SELECT_TOWN = "//div[contains(text(), 'Город или отель')]/following-sibling::div[1]";
    private static final String SELECT_CURR_TOWN = "//*[@id=\"layoutPage\"]/div[1]/div[1]/div/div/div[2]/div[7]/div[2]/div[1]/div[1]/div/div[1]/div/div/div[2]/div[2]/div[2]/div";
    private static final String SELECT_DATE = "//td[.//*[contains(text(), '29')]]";
    private static final String SELECT_SECOND_DATE = "//td[.//*[contains(text(), '30')]]";
    private static final String PRESS_BUTTON = "//button[.//*[contains(text(), 'Найти отель')]]";

    private static final String TARGET_URL_STAGE_1 = "https://www.ozon.ru/travel/hotels";

    private static final String COOKIE_BUTTON = "//button[.//*[contains(text(), 'ОК')]]";
    private static final String BLOCKED_HEADER_XPATH = "//h1[contains(normalize-space(), 'Доступ ограничен')]";

    public SelectHouseSettings(WebDriver driver) {
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

    public void disableCookie() {
        waitAndClick(COOKIE_BUTTON);
    }

    public void openHotelPage() {
        driver.get(TARGET_URL_STAGE_1);
    }
    public void selectHouseSelection(){
        this.waitAndClick(SELECT_TOWN);
        this.waitAndClick(SELECT_CURR_TOWN);
        this.waitAndClick(SELECT_DATE);
        this.waitAndClick(SELECT_SECOND_DATE);
        this.waitAndClick(PRESS_BUTTON);
    }
    public void close(){
        driver.close();
    }
}
