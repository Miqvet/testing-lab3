package itmo.course.selectSmthng;

import itmo.course.exceptions.BlockedException;
import itmo.course.pages.ChooseHousePage;
import itmo.course.pages.ContentPage;
import itmo.course.pages.FilterHousePage;
import itmo.course.pages.SelectHouseSettings;
import itmo.course.utils.DriverManager;
import itmo.course.utils.HumanInteraction;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.openqa.selenium.WebDriver;

import java.util.logging.Filter;
import java.util.stream.Stream;

@SuppressWarnings("LanguageDetectionInspection")
public class SelectHouseTest {
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
        ChooseHousePage page = new ChooseHousePage(driver);
        page.openHotelPage();
        if (page.isPageBlocked()) {
            throw new BlockedException("Access blocked");
        }
        page.disableCookie();
        page.selectHouseSelection();
        HumanInteraction.randomDelay(3000, 4000);
        driver.close();
    }
    @ParameterizedTest
    @MethodSource("browsersProvider")
    void selectHouseSage2(String browser) {
        var driverManager = new DriverManager();

        WebDriver driver = getDriver(browser, driverManager);
        FilterHousePage page = new FilterHousePage(driver);
        page.openHotelPage();
        if (page.isPageBlocked()) {
            throw new BlockedException("Access blocked");
        }

        page.disableCookie();
        page.selectHouseSelection();
        HumanInteraction.randomDelay(3000, 4000);
        driver.close();
    }
    @ParameterizedTest
    @MethodSource("browsersProvider")
    void selectHouseSage1(String browser) {
        var driverManager = new DriverManager();

        WebDriver driver = getDriver(browser, driverManager);
        SelectHouseSettings page = new SelectHouseSettings(driver);
        page.openHotelPage();
        if (page.isPageBlocked()) {
            throw new BlockedException("Access blocked");
        }

        page.disableCookie();
        page.selectHouseSelection();
        HumanInteraction.randomDelay(3000, 4000);
        driver.close();
    }
}