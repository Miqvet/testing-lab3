package itmo.course;

import itmo.course.exceptions.BlockedException;
import itmo.course.pages.ContentPage;
import itmo.course.utils.DriverManager;
import itmo.course.utils.HumanInteraction;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.openqa.selenium.WebDriver;

import org.junit.jupiter.api.*;
import org.junit.jupiter.params.provider.ValueSource;

import java.util.stream.Stream;

class ContentViewAndScrollTest {
    private static final String TARGET_URL = "https://www.ozon.ru/travel/";
    private static final int MAX_RETRIES = 2;
    private static final String[] BROWSERS = {"firefox", "chrome"};

    static Stream<Arguments> browsersProvider() {
        return Stream.of(BROWSERS)
                .map(Arguments::of);
    }

    @BeforeAll
    static void setup() {
        DriverManager.initDrivers(BROWSERS);
    }

    @ParameterizedTest
    @MethodSource("browsersProvider")
    void testSiteInteraction(String browser) {
        WebDriver driver = getDriver(browser);
        ContentPage page = new ContentPage(driver);

        try {
            for (int attempt = 1; attempt <= MAX_RETRIES; attempt++) {
                try {
                    attemptInteraction(page);
                    return; // Успешное выполнение
                } catch (BlockedException e) {
                    handleBlockade(driver, attempt);
                }
            }
            Assertions.fail("Failed after " + MAX_RETRIES + " attempts");
        } finally {
            HumanInteraction.randomDelay(2000, 4000);
            DriverManager.resetDrivers();
        }
    }

    private WebDriver getDriver(String browser) {
        return switch (browser.toLowerCase()) {
            case "chrome" -> DriverManager.getChromeWebDriver();
            case "firefox" -> DriverManager.getFireFoxWebDriver();
            default -> throw new IllegalArgumentException("Unknown browser");
        };
    }

    private void attemptInteraction(ContentPage page) {
        page.openUrl(TARGET_URL);
        System.out.println("123123");
        HumanInteraction.randomDelay(1500, 3000);

//        if (page.isAccessBlocked()) {
//            throw new BlockedException("Access blocked");
//        }
        System.out.println("ASDASDASD");
        page.scrollToBottom();
        HumanInteraction.randomDelay(1500, 3000);
        System.out.println("qweqweqwe");
        page.scrollToTop();
    }

    private void handleBlockade(WebDriver driver, int attempt) {
        System.out.println("Blockade detected. Attempt: " + attempt);
        driver.navigate().refresh();
        HumanInteraction.randomDelay(5000 + attempt*2000, 8000 + attempt*3000);
    }
}

