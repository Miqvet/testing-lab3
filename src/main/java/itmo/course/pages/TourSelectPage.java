package itmo.course.pages;

import org.openqa.selenium.*;

public class TourSelectPage extends BasePage {
    private static final String DEPARTURE_TOWN = "//*[@id=\"layoutPage\"]/div[1]/div[1]/div/div[1]/div/form/div[1]/div/label/div/div/input";
    private static final String TARGET_PLACE = "/html/body/div[1]/div/div[1]/div[1]/div/div[1]/div/form/div[2]/div/label/div/div/input";
    private static final String SELECT_TOWN = "/html/body/div[3]/div/div/div/div[2]";
    private static final String SELECT_TARGET_TOWN = "/html/body/div[3]/div/div/div/div[2]";
    private static final String TARGET_TIME = "/html/body/div[1]/div/div[1]/div[1]/div/div[1]/div/form/div[3]/div/label/div/div";
    private static final String SELECT_TARGET_TIME = "/html/body/div[3]/div/div/div/div[1]/div[2]/div[1]/table/tbody/tr[4]/td[7]";
    private static final String STAGE_1_FINISH_BUTTON = "/html/body/div[1]/div/div[1]/div[1]/div/div[1]/div/form/button";

    private static final String TARGET_URL_STAGE_2 = "https://www.ozon.ru/travel/tours/";

    private static final String COOKIE_BUTTON = "/html/body/div[1]/div/div[2]/div/div/div/div/button";
    private static final String BLOCKED_HEADER_XPATH = "//h1[contains(normalize-space(), 'Доступ ограничен')]";

    public TourSelectPage(WebDriver driver) {
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
    public void selectTourSelection(){
        this.disableCookie();
        this.waitAndClick(DEPARTURE_TOWN);
        this.waitAndClick(SELECT_TOWN);
        this.waitAndClick(TARGET_PLACE);
        this.waitAndClick(SELECT_TARGET_TOWN);
        this.waitAndClick(TARGET_TIME);
        this.waitAndClick(SELECT_TARGET_TIME);
        this.waitAndClick(STAGE_1_FINISH_BUTTON);
    }
    public void close(){
        driver.close();
    }
}
