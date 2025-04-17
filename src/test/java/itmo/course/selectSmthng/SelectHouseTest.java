package itmo.course.selectSmthng;

import itmo.course.exceptions.BlockedException;
import itmo.course.pages.ContentPage;
import itmo.course.utils.DriverManager;
import itmo.course.utils.HumanInteraction;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.openqa.selenium.WebDriver;

import java.util.stream.Stream;

public class SelectHouseTest {
    private static final String FIRST_IMAGE = "/html/body/div[1]/div/div[1]/div[2]/div/div/div[3]/div[1]/div[1]/div[1]/div[1]/div/img";
    private static final String SECOND_IMAGE = "/html/body/div[3]/div/div[2]/div/div/div/div[2]/div[1]/div[2]/div[2]/div[1]/img";
    private static final String NEXT_IMAGE = "/html/body/div[3]/div/div[2]/div/div/div[2]/button[2]";
    private static final String CLOSE_IMAGES_BUTTON = "/html/body/div[3]/div/div[2]/div/div/button";
    private static final String SHOW_ROOM_BUTTON = "/html/body/div[1]/div/div[1]/div[2]/div/div/div[3]/div[2]/div/div[1]/div[1]/div[3]/button";
    private static final String CHOOSE_ROOM_BUTTON = "/html/body/div[1]/div/div[1]/div[2]/div/div/div[3]/div[1]/div[2]/div/div/div/div[2]/div/div[1]/div[2]/div[4]/div[2]/div[2]/button";

    private static final String TARGET_URL_STAGE_1 = "https://www.ozon.ru/travel/hotels/product/otel-molo-1101607012/?checkIn=2025-04-19&checkOut=2025-04-20";
    private static final String[] BROWSERS = {"chrome"};

    static Stream<Arguments> browsersProvider() {
        return Stream.of(BROWSERS)
                .map(Arguments::of);
    }
    private WebDriver getDriver(String browser, DriverManager driverManager) {
        return switch (browser.toLowerCase()) {
            case "chrome" -> driverManager.getChromeWebDriver();
            case "firefox" -> driverManager.getFireFoxWebDriver();
            default -> throw new IllegalArgumentException("Unknown browser");
        };
    }

    @ParameterizedTest
    @MethodSource("browsersProvider")
    void selectTourStage1Test(String browser) {
        var driverManager = new DriverManager();

        WebDriver driver = getDriver(browser, driverManager);
        ContentPage page = new ContentPage(driver);
        page.openUrl(TARGET_URL_STAGE_1);
        if (page.isPageBlocked()) {
            throw new BlockedException("Access blocked");
        }

        page.disableCookie();
        page.waitAndClick(FIRST_IMAGE);
        page.waitAndClick(SECOND_IMAGE);
        page.waitAndClick(NEXT_IMAGE);
        page.waitAndClick(CLOSE_IMAGES_BUTTON);
        page.waitAndClick(SHOW_ROOM_BUTTON);
        page.waitAndClick(CHOOSE_ROOM_BUTTON);
        HumanInteraction.randomDelay(3000, 4000);
        driver.close();
    }
}