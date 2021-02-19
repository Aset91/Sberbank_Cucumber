package ru.appline.framework.pages;

import org.junit.Assert;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import static java.lang.Thread.sleep;
import static ru.appline.framework.managers.DriverManager.getDriver;

public class MortgagePage extends BasePage {
    @FindBy(xpath = "//div[@data-test-id='discounts']//div[text()]")
    private WebElement textAddService;
    @FindBy(xpath = "//h2[contains(.,'Рассчитайте ипотеку')]")
    private WebElement textTitleCalculator;
    @FindBy(xpath = "//div[@class='dc-input__label-4-9-1' and text()='Стоимость недвижимости']/..//input")
    private WebElement propertyPrice;
    @FindBy(xpath = "//div[@class='dc-input__label-4-9-1' and text()='Первоначальный взнос']/..//input")
    private WebElement initialPayment;
    @FindBy(xpath = "//div[@class='dc-input__label-4-9-1' and text()='Срок кредита']/..//input")
    private WebElement terms;
    @FindBy(xpath = "//span[@class='_1ZfZYgvLm4KBWPL41DOSO' and text()='Страхование жизни и здоровья']/../..//input")
    private WebElement lifeInsuranceCheckbox;
    @FindBy(xpath = "//span[@class='_1ZfZYgvLm4KBWPL41DOSO' and text()='Молодая семья']/../..//input")
    private WebElement youngFamilyCheckbox;
    @FindBy(xpath = "//span[@class='_1ZfZYgvLm4KBWPL41DOSO' and text()='Скидка 0,3% при покупке квартиры на ДомКлик']/../..//input")
    private WebElement domClickDiscount;
    @FindBy(xpath = "//span[@class='_1ZfZYgvLm4KBWPL41DOSO' and text()='Электронная регистрация сделки']/../..//input")
    private WebElement eRegistration;
    @FindBy(xpath = "//li[@class='_2oHcdFLGCjojtWqwTIofQG']//span[@data-e2e-id='mland-calculator/medium-result-credit-sum']//span")
    private WebElement creditAmount;
    @FindBy(xpath = "//li[@class='_2oHcdFLGCjojtWqwTIofQG']//span[@data-e2e-id='mland-calculator/medium-result-monthly-payment']//span")
    private WebElement monthlyPayment;
    @FindBy(xpath = "//li[@class='_2oHcdFLGCjojtWqwTIofQG']//span[@data-e2e-id='mland-calculator/medium-result-required-income']//span")
    private WebElement requiredSalary;
    @FindBy(xpath = "//li[@class='_2oHcdFLGCjojtWqwTIofQG']//span[@data-e2e-id='mland-calculator/medium-result-credit-rate']//span")
    private WebElement interestRate;


    /**
     * Проверка заголовка страницы
     */
    public MortgagePage checkOpenPage() {
        Assert.assertEquals("Заголовок не соответствует ожидаемому",
                "Ипотека на готовое жилье — СберБанк", getDriver().getTitle());
        return this;
    }


    /**
     * Переключение окон
     */
    public MortgagePage switchWindows() {
        getDriver().switchTo().frame("iFrameResizer0");
        return this;
    }
    public MortgagePage switchMainWindows() {
        getDriver().switchTo().parentFrame();
        return this;
    }

    public void waitUntilLoaded() {
        try {
            sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public MortgagePage scrollToForm() {
        scrollToElementJs(propertyPrice);
        return this;
    }

    /**
     * Заполнить форму
     *
     * @param text  название поля
     * @param value значение
     * @return MortgagePage
     */

    public MortgagePage fillField(String text, String value) {
        WebElement element = null;
        switch (text) {
            case "Стоимость недвижимости":
                waitUntilLoaded();
                scrollToElementJs(propertyPrice);
                fillInputField(propertyPrice, "5 180 000");
                element = propertyPrice;
                break;
            case "Первоначальный взнос":
                waitUntilLoaded();
                scrollToElementJs(initialPayment);
                fillInputField(initialPayment, "3 058 000");
                element = initialPayment;
                break;
            case "Срок кредита":
                waitUntilLoaded();
                scrollToElementJs(terms);
                fillInputField(terms, "30");
                element = terms;
                break;
            default:
                Assert.fail("поле " + text + " отсутствует");
        }
        Assert.assertEquals("Поле " + text + " заполнено неверно",
                value, element.getAttribute("value"));
        return this;
    }

    /**
     * Убрать ненужные галочки
     *
     * @param text название кнопки
     */

    public MortgagePage removeCheckboxes(String text) {
        scrollToElementJs(textAddService);
        WebElement element = null;
        switch (text) {
            case "Страхование жизни и здоровья":
                element = lifeInsuranceCheckbox;
                break;
            case "Скидка 0,3% при покупке квартиры на ДомКлик":
                element = domClickDiscount;
                break;
            case "Электронная регистрация сделки":
                element = eRegistration;
                break;
        }
        waitUntilLoaded();
        if (element.getAttribute("ariaChecked").equals("true")) {
            element.click();
        }
        return this;
    }

    /**
     * Проверка расчетных данных
     *
     * @param text  название поля
     * @param value значение
     */

    public MortgagePage checkCalculations(String text, String value) {
        switchMainWindows();
        scrollToElementJs(textTitleCalculator);
        switchWindows();
        WebElement element = null;
        switch (text) {
            case "Сумма кредита":
                element = creditAmount;
                break;

            case "Ежемесячный платеж":
                element = monthlyPayment;
                break;

            case "Необходимый доход":
                element = requiredSalary;
                break;

            case "Процентная ставка":
                element = interestRate;
                break;
        }
        Assert.assertEquals("Поле " + element.getText() + " было заполнено некорректно",
                value, element.getAttribute("textContent").replaceAll("[^{\\d,}]", ""));

        return this;
    }


}


