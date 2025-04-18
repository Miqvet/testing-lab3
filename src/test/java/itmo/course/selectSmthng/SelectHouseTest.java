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
    //Тут работает
    private static final String SELECT_TOWN = "/html/body/div[1]/div/div[1]/div[1]/div/div/div/div[7]/div/div[1]/div[1]/div/div/div/div[2]/input ";
    private static final String SELECT_CURR_TOWN = "/html/body/div[1]/div/div[1]/div[1]/div/div/div/div[7]/div[2]/div[1]/div[1]/div/div[1]/div/div/div[2]/div[2]";
    private static final String SELECT_DATE = "/html/body/div[1]/div/div[1]/div[1]/div/div/div/div[7]/div[2]/div[1]/div[2]/div/div[1]/div/div[2]/div/div/div/div[2]/div[1]/table/tbody/tr[4]/td[4]";
    private static final String SELECT_SECOND_DATE = "/html/body/div[1]/div/div[1]/div[1]/div/div/div/div[7]/div[2]/div[1]/div[2]/div/div[1]/div/div[2]/div/div/div/div[2]/div[1]/table/tbody/tr[4]/td[6]";
    private static final String PRESS_BUTTON = "/html/body/div[1]/div/div[1]/div[1]/div/div/div/div[7]/div/div[2]/button";

    private static final String SELECT_FILTER = "/html/body/div[1]/div/div[1]/div/div/div[2]/div/div[1]/div[1]/div[3]/div/div/div/div[2]/div/div";
//    private static final String SELECT_1_OPTION = "/html/body/div[3]/div/div[2]/div/div/div/div/div/div[2]/div/div[1]/div/div[1]/div[2]/div[2]/div/div[4]/div";
//    private static final String SELECT_2_OPTION = "/html/body/div[3]/div/div[2]/div/div/div/div/div/div[2]/div/div[1]/div/div[2]/div[1]/div[2]/div/div[3]/div";
//    private static final String SELECT_3_OPTION = "/html/body/div[3]/div/div[2]/div/div/div/div/div/div[2]/div/div[1]/div/div[3]/div[2]/div[2]/div/div[4]/div";
//    private static final String APPLY_BUTTON = "/html/body/div[3]/div/div[2]/div/div/div/div/div/div[2]/div/div[2]/button[2]/div[2]";

    private static final String SELECT_1_OPTION = "/html/body/div[3]/div/div[2]/div/div/div/div/div/div/div[3]/div/div[1]/div[2]/div[2]/div/div/div[2]/div/div";
    private static final String SELECT_2_OPTION = "/html/body/div[3]/div/div[2]/div/div/div/div/div/div/div[3]/div/div[2]/div[2]/div[2]/div/div/div[1]/div/div";
    private static final String SELECT_3_OPTION = "/html/body/div[3]/div/div[2]/div/div/div/div/div/div/div[3]/div/div[2]/div[5]/div[2]/div/div/div[12]/div/div";
    private static final String APPLY_BUTTON = "/html/body/div[3]/div/div[2]/div/div/div/div/div/div/div[1]/div/div/div/button";

    private static final String FIRST_IMAGE = "/html/body/div[1]/div/div[1]/div[2]/div/div/div[3]/div[1]/div[1]/div[1]/div[1]/div/img";
    private static final String SECOND_IMAGE = "/html/body/div[3]/div/div[2]/div/div/div/div[2]/div[1]/div[2]/div[2]/div[1]/img";
    private static final String NEXT_IMAGE = "/html/body/div[3]/div/div[2]/div/div/div[2]/button[2]";
    private static final String CLOSE_IMAGES_BUTTON = "/html/body/div[3]/div/div[2]/div/div/button";
    private static final String SHOW_ROOM_BUTTON = "/html/body/div[1]/div/div[1]/div[2]/div/div/div[3]/div[2]/div/div[1]/div[1]/div[3]/button";
    private static final String CHOOSE_ROOM_BUTTON = "/html/body/div[1]/div/div[1]/div[2]/div/div/div[3]/div[1]/div[2]/div/div/div/div[2]/div/div[1]/div[2]/div[4]/div[2]/div[2]/button";

    private static final String TARGET_URL_STAGE_3 = "https://www.ozon.ru/travel/hotels/product/otel-molo-1101607012/?checkIn=2025-04-19&checkOut=2025-04-20";
    private static final String TARGET_URL_STAGE_2 = "https://www.ozon.ru/travel/hotels/search-new?Dlts=2&checkIn=2025-04-25&checkOut=2025-04-30&cityOrRegionId=6564841564000";
    private static final String TARGET_URL_STAGE_1 = "https://www.ozon.ru/travel/hotels";
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
    void selectHouseStage3(String browser) {
        var driverManager = new DriverManager();

        WebDriver driver = getDriver(browser, driverManager);
        ContentPage page = new ContentPage(driver);
        page.openUrl(TARGET_URL_STAGE_3);
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
    @ParameterizedTest
    @MethodSource("browsersProvider")
    void selectHouseSage2(String browser) {
        var driverManager = new DriverManager();

        WebDriver driver = getDriver(browser, driverManager);
        ContentPage page = new ContentPage(driver);
        page.openUrl(TARGET_URL_STAGE_2);
        if (page.isPageBlocked()) {
            throw new BlockedException("Access blocked");
        }

        page.disableCookie();
        page.waitAndClick(SELECT_FILTER);
        page.waitAndClick(SELECT_1_OPTION);
        page.waitAndClick(SELECT_2_OPTION);
        page.waitAndClick(SELECT_3_OPTION);
        page.waitAndClick(APPLY_BUTTON);
        HumanInteraction.randomDelay(3000, 4000);
        driver.close();
    }
    @ParameterizedTest
    @MethodSource("browsersProvider")
    void selectHouseSage1(String browser) {
        var driverManager = new DriverManager();

        WebDriver driver = getDriver(browser, driverManager);
        ContentPage page = new ContentPage(driver);
        page.openUrl(TARGET_URL_STAGE_1);
        if (page.isPageBlocked()) {
            throw new BlockedException("Access blocked");
        }

        page.disableCookie();
        page.waitAndClick(SELECT_TOWN);
        page.waitAndClick(SELECT_CURR_TOWN);
        page.waitAndClick(SELECT_DATE);
        page.waitAndClick(SELECT_SECOND_DATE);
        page.waitAndClick(PRESS_BUTTON);
        HumanInteraction.randomDelay(3000, 4000);
        driver.close();
    }
}