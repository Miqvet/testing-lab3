package itmo.course;

import io.github.bonigarcia.wdm.WebDriverManager;
import itmo.course.pages.ContentPage;
import itmo.course.utils.DriverManager;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;
import org.openqa.selenium.WebDriver;

import java.util.Objects;
import java.util.stream.Stream;

class ContentViewAndScrollTest {
    private static String mode = "chrome";
    @BeforeAll
    static void setupAll() {
        DriverManager.initDrivers(mode); // Инициализация драйверов перед использованием
    }

    static Stream<WebDriver> driversProvider() {
        return DriverManager.getDrivers().stream()
                .filter(Objects::nonNull);
    }

    @ParameterizedTest
    @MethodSource("driversProvider")
    void testTravelSiteInteraction(WebDriver driver) {
        System.out.println(driver.getTitle());
        var contentPage = new ContentPage(driver);
        try {
            // Шаг 1: Открыть главную страницу
            contentPage.openMainPage();

            // Шаг 2: Скролл вниз и вверх
            contentPage.scrollToBottom();
            contentPage.scrollToTop();

            // Шаг 3: Открыть первое предложение
            contentPage.openFirstOffer();

            // Шаг 4: Проверить превью
            Assertions.assertTrue(contentPage.isImagePreviewVisible(),
                    "Preview image should be visible");

            // Шаг 5: Закрыть превью
            contentPage.closeImagePreview();
        } finally {
            driver.quit();
        }
    }
}