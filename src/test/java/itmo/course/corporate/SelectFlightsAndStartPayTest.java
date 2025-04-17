package itmo.course.corporate;

import itmo.course.exceptions.BlockedException;
import itmo.course.pages.ContentPage;
import itmo.course.utils.DriverManager;
import itmo.course.utils.HumanInteraction;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.openqa.selenium.WebDriver;

import java.util.stream.Stream;

public class SelectFlightsAndStartPayTest {
    private static final String START_REGISTRATION = "/html/body/div[1]/div/div[1]/div[2]/div/div/section[1]/section/button";
    private static final String THIRD_OPTION = "/html/body/div[1]/div/div[1]/div[3]/div[1]/div/div/div[1]/div[3]";
    private static final String APPLY_BUTTON = "/html/body/div[1]/div/div[1]/div[3]/div[1]/div/div/button";

    private static final String SELECT_PAY_MYSELF = "/html/body/div[1]/div/div[1]/div[2]/div/div/div[3]/div[2]/div[3]/button[2]";
    private static final String SELECT_PAY_DELAY = "/html/body/div[1]/div/div[1]/div[2]/div/div/div[1]/div[2]/div[3]";

    private static final String TARGET_URL_2 = "https://www.ozon.ru/travel/corporate/";
    private static final String TARGET_URL_1 = "https://www.ozon.ru/travel/hotels/partnership";
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
    void selectBusinessOptionTest(String browser) {
        var driverManager = new DriverManager();

        WebDriver driver = getDriver(browser, driverManager);
        ContentPage page = new ContentPage(driver);
        page.openUrl(TARGET_URL_1);
        if (page.isPageBlocked()) {
            throw new BlockedException("Access blocked");
        }

        HumanInteraction.randomDelay(3000, 4000);
        page.disableCookie();
        page.waitAndClick(START_REGISTRATION);
        page.waitAndClick(THIRD_OPTION);
        driver.close();
    }

    @ParameterizedTest
    @MethodSource("browsersProvider")
    void selectBusinessPayTest(String browser) {
        var driverManager = new DriverManager();

        WebDriver driver = getDriver(browser, driverManager);
        ContentPage page = new ContentPage(driver);
        page.openUrl(TARGET_URL_2);
        if (page.isPageBlocked()) {
            throw new BlockedException("Access blocked");
        }

        HumanInteraction.randomDelay(3000, 4000);
        page.disableCookie();
        page.waitAndClick(SELECT_PAY_MYSELF);
        page.waitAndClick(SELECT_PAY_DELAY);
        driver.close();
    }

}