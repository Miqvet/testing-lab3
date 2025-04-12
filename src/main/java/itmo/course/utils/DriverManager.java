package itmo.course.utils;

import io.github.bonigarcia.wdm.WebDriverManager;
import lombok.Getter;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.openqa.selenium.Cookie;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.firefox.FirefoxProfile;

import javax.annotation.processing.Generated;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.time.Duration;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

public class DriverManager {
    @Getter
    private static WebDriver fireFoxWebDriver;
    @Getter
    private static WebDriver chromeWebDriver;

    public static void initDrivers(String mode) {
        switch (mode) {
            case "chrome" -> createChromeDriver();
            case "fox" -> createFirefoxDriver();
            case "both" -> {
                createChromeDriver();
                createFirefoxDriver();
            }
        }
    }

    public static List<WebDriver> getDrivers() {
        var drivers = new ArrayList<WebDriver>();
        if (chromeWebDriver != null) drivers.add(chromeWebDriver);
        if (fireFoxWebDriver != null) drivers.add(fireFoxWebDriver);
        return drivers;
    }

    private static void createChromeDriver() {
        WebDriverManager.chromedriver().setup();
        chromeWebDriver = new ChromeDriver();
        chromeWebDriver.manage().window().maximize();
        chromeWebDriver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(20));
    }

    private static void createFirefoxDriver() {
        WebDriverManager.firefoxdriver().setup();
        fireFoxWebDriver = new FirefoxDriver();
        fireFoxWebDriver.manage().window().maximize();
        fireFoxWebDriver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(20));
    }
}