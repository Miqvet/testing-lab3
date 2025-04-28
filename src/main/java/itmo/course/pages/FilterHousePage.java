package itmo.course.pages;

import org.openqa.selenium.*;

public class FilterHousePage extends BasePage {
    private static final String SELECT_FILTER = "//span[contains(text(), 'Фильтры')]";
    private static final String SELECT_1_OPTION = "//span[contains(text(), 'Двухразовое питание')]";
    private static final String SELECT_2_OPTION = "//span[contains(text(), 'Виллы')]";
    private static final String SELECT_3_OPTION = "//span[contains(text(), '5*')]";
    private static final String SELECT_4_OPTION = "//span[contains(text(), 'Мини-бар')]";
    private static final String APPLY_BUTTON = "//button[.//*[contains(text(), 'Применить фильтры')]]";

    private static final String TARGET_URL_STAGE_2 = "https://www.ozon.ru/travel/hotels/search-new?Dlts=2&checkIn=2025-04-29&checkOut=2025-04-30&cityOrRegionId=6564841564000";


    private static final String COOKIE_BUTTON = "//button[.//*[contains(text(), 'ОК')]]";
    private static final String BLOCKED_HEADER_XPATH = "//h1[contains(normalize-space(), 'Доступ ограничен')]";

    public FilterHousePage(WebDriver driver) {
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
        driver.get(TARGET_URL_STAGE_2);
    }
    public void selectHouseSelection(){
        this.waitAndClick(SELECT_FILTER);
        this.waitAndClick(SELECT_1_OPTION);
        this.waitAndClick(SELECT_2_OPTION);
        this.waitAndClick(SELECT_3_OPTION);
        this.waitAndClick(SELECT_4_OPTION);
        this.waitAndClick(APPLY_BUTTON);
    }
    public void close(){
        driver.close();
    }
}
