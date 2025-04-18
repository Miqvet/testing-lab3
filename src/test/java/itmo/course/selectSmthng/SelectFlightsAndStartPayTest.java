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

public class SelectFlightsAndStartPayTest {
    private static final String TYPE_OF_FLIGHT = "/html/body/div[1]/div/div[1]/div[2]/div[1]/div/div/div[3]/div[1]/div/div[1]/label/div[1]";
    private static final String AEROFLOT_ONLY = "/html/body/div[1]/div/div[1]/div[2]/div[1]/div/div/div[3]/div[1]/div/div[4]/div/div[2]/label[1]/div[1]";
    private static final String ONE_BAGGAGE = "/html/body/div[1]/div/div[1]/div[2]/div[1]/div/div/div[3]/div[2]/section/article[1]/div[2]/div[2]/div[1]";
    private static final String SELECT_FLY = "/html/body/div[1]/div/div[1]/div[2]/div[1]/div/div/div[3]/div[2]/section/article[1]/div[3]/div[2]/button";

    private static final String TARGET_URL_STAGE_3 = "https://www.ozon.ru/travel/flight/book?Dlts=1&classCodes=N%3BN&dates=d2025-05-14d2025-05-14&fareBasis=%232%3B%232&flightNumbers=SU6564%3BSU2834&prices=33256&routes=aerledledaer&searchId=2f703442-d49d-478f-9aca-3812f80aa0c3&serviceClass=ECONOMY&vi=00";
    private static final String TARGET_URL_STAGE_2 = "https://www.ozon.ru/travel/flight/search?Children=0&Dlts=1&Infants=0&ServiceClass=ECONOMY&dates=d2025-05-14d2025-05-14&route=aerledledaer";
    private static final String TARGET_URL_STAGE_1 = "https://www.ozon.ru/travel";
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
    void selectFlyStage2Test(String browser) {
        var driverManager = new DriverManager();

        WebDriver driver = getDriver(browser, driverManager);
        ContentPage page = new ContentPage(driver);
        page.openUrl(TARGET_URL_STAGE_2);
        if (page.isPageBlocked()) {
            throw new BlockedException("Access blocked");
        }

        HumanInteraction.randomDelay(3000, 4000);
        page.disableCookie();
        page.waitAndClick("/html/body/div[3]/div/div[1]/div/div/button");
        page.waitAndClick(TYPE_OF_FLIGHT);
        HumanInteraction.randomDelay(1500, 3000);
        page.waitAndClick(AEROFLOT_ONLY);
        HumanInteraction.randomDelay(1500, 3000);
//        page.waitAndClick(ONE_BAGGAGE);

        driver.close();
    }

    @ParameterizedTest
    @MethodSource("browsersProvider")
    void selectFlyStage1Test(String browser) {
        var driverManager = new DriverManager();

        WebDriver driver = getDriver(browser, driverManager);
        ContentPage page = new ContentPage(driver);
        page.openUrl(TARGET_URL_STAGE_1);
        if (page.isPageBlocked()) {
            throw new BlockedException("Access blocked");
        }

        HumanInteraction.randomDelay(3000, 4000);
        page.disableCookie();
        page.clickFindButton();
        driver.close();
    }

    @ParameterizedTest
    @MethodSource("browsersProvider")
    void selectFlyStage3Test(String browser) {
        var driverManager = new DriverManager();

        WebDriver driver = getDriver(browser, driverManager);
        ContentPage page = new ContentPage(driver);
        page.openUrl(TARGET_URL_STAGE_3);
        if (page.isPageBlocked()) {
            throw new BlockedException("Access blocked");
        }

        HumanInteraction.randomDelay(3000, 4000);
        page.disableCookie();
        page.scrollToBottom();
        page.fillName("Никита");
        page.fillPassport("1231239321");
        page.fillMiddleName("Никитьевич");
        page.fillSurname("Иванов");
        page.fillBirthDate("12.12.2000");

        driver.close();
    }
}