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

public class SelectTourAndStartPayTest {
    private static final String DEPARTURE_TOWN = "//*[@id=\"layoutPage\"]/div[1]/div[1]/div/div[1]/div/form/div[1]/div/label/div/div/input";
    private static final String TARGET_PLACE = "/html/body/div[1]/div/div[1]/div[1]/div/div[1]/div/form/div[2]/div/label/div/div/input";
    private static final String SELECT_TOWN = "/html/body/div[3]/div/div/div/div[2]";
    private static final String SELECT_TARGET_TOWN = "/html/body/div[3]/div/div/div/div[2]";
    private static final String TARGET_TIME = "/html/body/div[1]/div/div[1]/div[1]/div/div[1]/div/form/div[3]/div/label/div/div";
    private static final String SELECT_TARGET_TIME = "/html/body/div[3]/div/div/div/div[1]/div[2]/div[1]/table/tbody/tr[4]/td[7]";
    private static final String STAGE_1_FINISH_BUTTON = "/html/body/div[1]/div/div[1]/div[1]/div/div[1]/div/form/button";

    private static final String TARGET_URL_STAGE_2 = "https://www.ozon.ru/travel/tours/";
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
        page.openUrl(TARGET_URL_STAGE_2);
        if (page.isPageBlocked()) {
            throw new BlockedException("Access blocked");
        }

        page.disableCookie();
        page.waitAndClick(DEPARTURE_TOWN);
        page.waitAndClick(SELECT_TOWN);
        page.waitAndClick(TARGET_PLACE);
        page.waitAndClick(SELECT_TARGET_TOWN);
        page.waitAndClick(TARGET_TIME);
        page.waitAndClick(SELECT_TARGET_TIME);
        page.waitAndClick(STAGE_1_FINISH_BUTTON);

        HumanInteraction.randomDelay(3000, 4000);
    }
}