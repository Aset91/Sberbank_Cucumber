package ru.appline.framework.pages;

import org.junit.Assert;
import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import ru.appline.framework.managers.PageManager;
import ru.appline.framework.managers.TestPropManager;

import java.util.NoSuchElementException;
import java.util.concurrent.TimeUnit;

import static java.util.concurrent.TimeUnit.SECONDS;
import static ru.appline.framework.managers.DriverManager.getDriver;
import static ru.appline.framework.utils.PropConst.IMPLICITLY_WAIT;

public class BasePage {


    /**
     * Конструктор позволяющий инициализировать все странички и их эелементы помеченные анотацией {@link FindBy}
     * Подробнее можно просмотреть в класс {@link PageFactory}
     *
     * @see FindBy
     * @see PageFactory
     * @see PageFactory#initElements(WebDriver, Object)
     */
    public BasePage() {
        PageFactory.initElements(getDriver(), this);
    }


    /**
     * Менеджер страничек
     *
     * @see PageManager
     */
    protected PageManager app = PageManager.getPageManager();

    private final TestPropManager props = TestPropManager.getTestPropManager();

    /**
     * Объект явного ожидания
     * При применении будет ожидать задонного состояния 10 секунд с интервалом в 1 секунду
     *
     * @see WebDriverWait
     */
    protected WebDriverWait wait = new WebDriverWait(getDriver(), 10, 1000);


    /**
     * Метод для смещения до середины экрана
     * @param x - параметр координат по горизонтали
     * @param  y - параметр координатр по вертикали
     */
     public void scrollWithOffset(WebElement element, int x, int y) {
         String code = "window.scroll(" + (element.getLocation().x + x) + ","
                 + (element.getLocation().y + y) + ");";
         ((JavascriptExecutor) getDriver()).executeScript(code, element, x, y);
     }

        /**
         * Объект для имитации реального поведения мыши или клавиатуры
         *
         * @see Actions
         */
        protected Actions action = new Actions(getDriver());

        /**
         * Объект для выполнения любого js кода
         *
         * @see JavascriptExecutor
         */
        protected JavascriptExecutor js = (JavascriptExecutor) getDriver();

        /**
         * Явное ожидание состояния кликабельности элемента
         *
         * @param element - веб-элемент который требует проверки на кликабельность
         * @return WebElement - возвращаем тот же веб элемент что был передан в функцию
         * @see WebDriverWait
         * @see org.openqa.selenium.support.ui.FluentWait
         * @see org.openqa.selenium.support.ui.Wait
         * @see ExpectedConditions
         */
        protected WebElement elementToBeClickable(WebElement element) {
            return wait.until(ExpectedConditions.elementToBeClickable(element));
        }

        protected WebElement elementToBeVisible(WebElement element) {
            return wait.until(ExpectedConditions.visibilityOf(element));
        }

        /**
         * Общий метод по заполнению полей с датой
         *
         * @param field - веб-елемент поле с датой
         * @param value - значение вводимое в поле с датой
         */
        public void fillDateField(WebElement field, String value) {
            scrollToElementJs(field);
            field.sendKeys(value);
        }
    /**
     * Функция позволяющая скролить до любого элемента с помощью js
     *
     * @param element - веб-элемент странички
     * @see JavascriptExecutor
     */
    public void scrollToElementJs(WebElement element) {
        JavascriptExecutor javaScriptExecutor = (JavascriptExecutor) getDriver();
        javaScriptExecutor.executeScript("arguments[0].scrollIntoView(true)", element);
    }

    /**
     * Общий метод по заполнения полей ввода
     *
     * @param element - веб-елемент поле ввода
     * @param value - значение вводимое в поле
     */
    public void fillInputField(WebElement element, String value) {
        scrollToElementJs(element);
        element.sendKeys(Keys.CONTROL, "a");
        element.sendKeys(value);
        Assert.assertEquals("Поле было заполнено некорректно",
                value, element.getAttribute("value"));
    }
    public boolean isElementExists(By by) {
        boolean flag = false;
        try{
            getDriver().manage().timeouts().implicitlyWait(1, SECONDS);
            getDriver().findElement(by);
            flag = true;
        } catch (NoSuchElementException ignore) {
        } finally {
            getDriver().manage().timeouts().implicitlyWait(Integer.parseInt(props.getProperty(IMPLICITLY_WAIT)), TimeUnit.SECONDS);
        }
        return flag;
    }

}

