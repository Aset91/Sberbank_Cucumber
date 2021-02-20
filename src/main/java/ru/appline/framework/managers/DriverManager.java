package ru.appline.framework.managers;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;

import java.net.MalformedURLException;
import java.net.URI;

import static ru.appline.framework.utils.PropConst.*;


public class DriverManager {


    /**
     * Переменная для хранения объекта веб-драйвера
     */
    public static WebDriver driver;

    /**
     * Переменная для хранения объекта DriverManager
     */
    private static DriverManager INSTANCE = null;

    /**
     * Manager properties
     */
    private static TestPropManager props = TestPropManager.getTestPropManager();

    /**
     * конструктор синглтона
     */

    private DriverManager() {
        initDriver();
    }

    /**
     * Метод ленивой инициализации веб драйвера
     *
     * @return WebDriver - возвращает веб драйвер
     */

    public static WebDriver getDriver() {
        if (INSTANCE == null) {
            INSTANCE = new DriverManager();
        }
        return driver;
    }

    private void initDriver() {
        switch (props.getProperty(TYPE_BROWSER)) {
            case "firefox":
                System.setProperty("webdriver.gecko.driver", props.getProperty(PATH_GECKO_DRIVER));
                driver = new FirefoxDriver();
                break;
            case "chrome":
                System.setProperty("webdriver.chrome.driver", props.getProperty(PATH_CHROME_DRIVER));
                driver = new ChromeDriver();
                break;
            case "remote":
                DesiredCapabilities capabilities = new DesiredCapabilities();
                capabilities.setBrowserName("chrome");
                capabilities.setVersion("73.0");
                capabilities.setCapability("enableVNC", true);
                capabilities.setCapability("enableVideo", false);
                try {
                RemoteWebDriver driver = new RemoteWebDriver(
                        URI.create("http://selenoid.appline.ru:4445/wd/hub").toURL(),
                        capabilities);
                } catch (MalformedURLException e) {
                    e.printStackTrace();
                }
        }
    }

    /**
     * Метод для закрытия сессии драйвера и браузера
     *
     * @see WebDriver#quit()
     */
    public static void quitDriver() {
        if (driver != null) {
            driver.quit();
            driver = null;
            INSTANCE = null;
        }
    }
}






