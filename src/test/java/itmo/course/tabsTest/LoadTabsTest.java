package itmo.course.tabsTest;

import itmo.course.exceptions.BlockedException;
import itmo.course.pages.ContentPage;
import itmo.course.utils.DriverManager;
import itmo.course.utils.HumanInteraction;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.openqa.selenium.WebDriver;

import java.util.stream.Stream;

class LoadTabsTest {
    private static final String TARGET_URL = "https://www.ozon.ru/travel/";
    private static final String[] BROWSERS = {"chrome"};

    static Stream<Arguments> browsersProvider() {
        return Stream.of(BROWSERS)
                .map(Arguments::of);
    }

    @ParameterizedTest
    @MethodSource("browsersProvider")
    void testFlightTabs(String browser) {
        var driverManager = new DriverManager();

        WebDriver driver = getDriver(browser, driverManager);
        ContentPage page = new ContentPage(driver);
        page.openUrl(TARGET_URL);
        if (page.isPageBlocked()) {
            throw new BlockedException("Access blocked");
        }
        page.disableCookie();
        page.scrollToBottom();
        HumanInteraction.randomDelay(1000,2000);
        page.scrollToTop();

        driver.close();
    }

    @ParameterizedTest
    @MethodSource("browsersProvider")
    void testHotelTabs(String browser) {
        var driverManager = new DriverManager();

        WebDriver driver = getDriver(browser, driverManager);
        ContentPage page = new ContentPage(driver);
        page.openUrl(TARGET_URL);
        if (page.isPageBlocked()) {
            throw new BlockedException("Access blocked");
        }
        page.disableCookie();
        page.clickHotelsTab();
        page.scrollToBottom();
        HumanInteraction.randomDelay(1000,2000);
        page.scrollToTop();

        driver.close();
    }

    @ParameterizedTest
    @MethodSource("browsersProvider")
    void testTrainTabs(String browser) {
        var driverManager = new DriverManager();

        WebDriver driver = getDriver(browser, driverManager);
        ContentPage page = new ContentPage(driver);
        page.openUrl(TARGET_URL);
        if (page.isPageBlocked()) {
            throw new BlockedException("Access blocked");
        }
        page.disableCookie();
        page.clickTrainTab();

        driver.close();
    }

    @ParameterizedTest
    @MethodSource("browsersProvider")
    void testTourTabs(String browser) {
        var driverManager = new DriverManager();

        WebDriver driver = getDriver(browser, driverManager);
        ContentPage page = new ContentPage(driver);
        page.openUrl(TARGET_URL);
        if (page.isPageBlocked()) {
            throw new BlockedException("Access blocked");
        }
        page.disableCookie();
        page.clickToursTab();

        driver.close();
    }

    private WebDriver getDriver(String browser, DriverManager driverManager) {
        return switch (browser.toLowerCase()) {
            case "chrome" -> driverManager.getChromeWebDriver();
            case "firefox" -> driverManager.getFireFoxWebDriver();
            default -> throw new IllegalArgumentException("Unknown browser");
        };
    }
}

