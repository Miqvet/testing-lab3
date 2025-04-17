package itmo.course.utils;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.firefox.FirefoxProfile;

import java.io.File;

public class RealProfileFirefoxExample {
    public static void main(String[] args) {
        // Укажи путь к своему настоящему Firefox профилю
        String profilePath = "C:/Users/nik31/AppData/Roaming/Mozilla/Firefox/Profiles/1weqlnl7.default-release";

        // Загружаем профиль
        FirefoxProfile profile = new FirefoxProfile(new File(profilePath));

        System.out.println(243);
        // Опции браузера
        FirefoxOptions options = new FirefoxOptions();
        options.setProfile(profile);

        // Запуск Firefox с реальным профилем
        WebDriver driver = new FirefoxDriver(options);

        // Открываем нужную страницу
        driver.get("https://www.ozon.ru/travel");

        // Дальше можно выполнять любые действия
    }
}
