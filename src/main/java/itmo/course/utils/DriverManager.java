package itmo.course.utils;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;

import java.time.Duration;

import org.openqa.selenium.chrome.ChromeOptions;
import java.util.Collections;
import java.util.Map;

public class DriverManager {
    private WebDriver fireFoxWebDriver;
    private WebDriver chromeWebDriver;
    private static final String USER_AGENT = "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 " +
            "(KHTML, like Gecko) Chrome/120.0.0.0 Safari/537.36";

    public void initDrivers(String[] mode) {
        for(var browser : mode) {
            switch (browser) {
                case "chrome" -> createChromeDriver();
                case "firefox" -> createFirefoxDriver();
                case "both" -> {
                    createChromeDriver();
                    createFirefoxDriver();
                }
                default -> throw new IllegalArgumentException("Unsupported driver mode: " + mode);
            }
        }
    }

    private void createChromeDriver() {
        try {
            WebDriverManager.chromedriver().clearDriverCache().setup();

            ChromeOptions options = new ChromeOptions();
            options.setExperimentalOption("excludeSwitches",
                    Collections.singletonList("enable-automation"));
            options.addArguments(
                    "--disable-blink-features=AutomationControlled",
                    "--user-agent=" + USER_AGENT,
                    "--start-maximized",
                    "--disable-web-security",
                    "--no-sandbox"
            );

            chromeWebDriver = new ChromeDriver(options);
            stealthChrome(chromeWebDriver);
            configureDriver(chromeWebDriver);
        } catch (Exception e) {
            System.err.println("[Chrome] Initialization error: " + e.getMessage());
        }
    }

    private void createFirefoxDriver() {
        try {
            WebDriverManager.firefoxdriver().clearDriverCache().setup();

            FirefoxOptions options = new FirefoxOptions();
            options.addPreference("general.useragent.override", USER_AGENT);
            options.addPreference("dom.webdriver.enabled", false);

            fireFoxWebDriver = new FirefoxDriver(options);
            configureDriver(fireFoxWebDriver);
        } catch (Exception e) {
            System.err.println("[Firefox] Initialization error: " + e.getMessage());
        }
    }

    private void configureDriver(WebDriver driver) {
        driver.manage().window().setSize(new Dimension(1280, 850));
        driver.manage().timeouts()
                .pageLoadTimeout(Duration.ofSeconds(10))
                .implicitlyWait(Duration.ofSeconds(3));
        driver.manage().deleteAllCookies();
    }

    private void stealthChrome(WebDriver driver) {
        ((ChromeDriver) driver).executeCdpCommand(
                "Page.addScriptToEvaluateOnNewDocument",
                Map.of("source",
                        "Object.defineProperty(navigator, 'webdriver', {get: () => undefined})")
        );
    }

    public WebDriver getChromeWebDriver() {
        if (chromeWebDriver == null) createChromeDriver();
        return chromeWebDriver;
    }

    public WebDriver getFireFoxWebDriver() {
        if (fireFoxWebDriver == null) createFirefoxDriver();
        return fireFoxWebDriver;
    }

    public void resetDrivers() {
        performCleanup(chromeWebDriver);
        performCleanup(fireFoxWebDriver);
    }

    private void performCleanup(WebDriver driver) {
        if (driver != null) {
            driver.manage().deleteAllCookies();
            driver.close();
        }
    }
}