package itmo.course.pages;

import org.openqa.selenium.*;

public class ChooseHousePage extends BasePage {
    private static final String FIRST_IMAGE = "//div[@data-widget='hotelsGallery']/child::div[1]";
    private static final String SECOND_IMAGE = "//span[text()='Отель Molo']/parent::div/parent::div/child::div[2]/child::div[1]/child::div[1]";
    private static final String NEXT_IMAGE = "(//button[.//*[local-name()='svg'][@width='24'][@height='24'] and .//div[contains(@class, 'ag94-a')]])[3]";
    private static final String CLOSE_IMAGES_BUTTON = "//button[\n" +
            "    contains(@style, 'background: rgba(204, 214, 228, 0.4)') \n" +
            "    and contains(@style, 'color: rgba(0, 26, 52, 0.6)') \n" +
            "    and .//*[local-name()='svg'][@width='16']//*[local-name()='path'][contains(@d, 'M2.533')]\n" +
            "]";
    private static final String SHOW_ROOM_BUTTON = "/html/body/div[1]/div/div[1]/div[2]/div/div/div[3]/div[2]/div/div[1]/div[1]/div[3]/button";
    private static final String CHOOSE_ROOM_BUTTON = "(//div[text()='Показать 4 номера']/../..)[2]";

    private static final String TARGET_URL_STAGE_1 = "https://www.ozon.ru/travel/hotels/product/otel-molo-1101607012/?checkIn=2025-04-29&checkOut=2025-04-30";

    private static final String COOKIE_BUTTON = "//button[.//*[contains(text(), 'ОК')]]";
    private static final String BLOCKED_HEADER_XPATH = "//h1[contains(normalize-space(), 'Доступ ограничен')]";

    public ChooseHousePage(WebDriver driver) {
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
        this.waitAndClick(FIRST_IMAGE);
        this.waitAndClick(SECOND_IMAGE);
        this.waitAndClick(NEXT_IMAGE);
        this.waitAndClick(CLOSE_IMAGES_BUTTON);
        this.waitAndClick(SHOW_ROOM_BUTTON);
        this.waitAndClick(CHOOSE_ROOM_BUTTON);
    }
    public void close(){
        driver.close();
    }
}
