package itmo.course.selectSmthng;

import itmo.course.exceptions.BlockedException;
import itmo.course.pages.TourSelectPage;
import itmo.course.utils.DriverManager;
import itmo.course.utils.HumanInteraction;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.openqa.selenium.WebDriver;

import java.util.stream.Stream;

public class SelectTourAndStartPayTest {
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
        TourSelectPage page = new TourSelectPage(getDriver(browser, driverManager));
        page.openHotelPage();
        if (page.isPageBlocked()) {
            throw new BlockedException("Access blocked");
        }
        page.selectTourSelection();
        HumanInteraction.randomDelay(3000, 4000);
        page.close();
    }
}