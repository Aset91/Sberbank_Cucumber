package ru.appline.framework.pages;

import org.junit.Assert;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import static java.lang.Thread.sleep;
import static ru.appline.framework.managers.DriverManager.getDriver;

public class MortgagePage extends BasePage {

    @FindBy(xpath = "//div[@class='dc-input__label-4-9-1' and text()='Стоимость недвижимости']/..//input")
    public WebElement propertyPrice;
    @FindBy(xpath = "//div[@class='dc-input__label-4-9-1' and text()='Первоначальный взнос']/..//input")
    public WebElement initialPayment;
    @FindBy(xpath = "//div[@class='dc-input__label-4-9-1' and text()='Срок кредита']/..//input")
    public WebElement terms;
    @FindBy(xpath = "//span[@class='_1ZfZYgvLm4KBWPL41DOSO' and text()='Страхование жизни и здоровья']/../..//input")
    public WebElement lifeInsuranceCheckbox;
    @FindBy(xpath = "//span[@class='_1ZfZYgvLm4KBWPL41DOSO' and text()='Молодая семья']/../..//input")
    public WebElement youngFamilyCheckbox;
    @FindBy(xpath = "//span[@class='_1ZfZYgvLm4KBWPL41DOSO' and text()='Скидка 0,3% при покупке квартиры на ДомКлик']/../..//input")
    public WebElement domClickDiscount;
    @FindBy(xpath = "//span[@class='_1ZfZYgvLm4KBWPL41DOSO' and text()='Электронная регистрация сделки']/../..//input")
    public WebElement eRegistration;
    @FindBy(xpath = "//li[@class='_2oHcdFLGCjojtWqwTIofQG']//span[@data-e2e-id='mland-calculator/medium-result-credit-sum']//span")
    public WebElement creditAmount;
    @FindBy(xpath = "//li[@class='_2oHcdFLGCjojtWqwTIofQG']//span[@data-e2e-id='mland-calculator/medium-result-monthly-payment']//span")
    public WebElement monthlyPayment;
    @FindBy(xpath = "//li[@class='_2oHcdFLGCjojtWqwTIofQG']//span[@data-e2e-id='mland-calculator/medium-result-required-income']//span")
    public WebElement requiredSalary;
    @FindBy(xpath = "//li[@class='_2oHcdFLGCjojtWqwTIofQG']//span[@data-e2e-id='mland-calculator/medium-result-credit-rate']//span")
    public WebElement interestRate;

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
     * @param text
     * @param value
     * @return MortgagePage
     */

    public MortgagePage fillField(String text, String value) {
        WebElement element = null;
        switch (text) {
            case "Стоимость недвижимости":
                try {
                    sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                scrollToElementJs(propertyPrice);
                fillInputField(propertyPrice, "5 180 000");
                element = propertyPrice;
                break;
            case "Первоначальный взнос":
                try {
                    sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                scrollToElementJs(initialPayment);
                fillInputField(initialPayment, "3 058 000");
                element = initialPayment;
                break;
            case "Срок кредита":
                try {
                    sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
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
     */

    public MortgagePage removeCheckboxes() {
        lifeInsuranceCheckbox.click();
        domClickDiscount.click();
        eRegistration.click();
        return this;
    }

    /**
     * Проверка расчетных данных
     */
    public MortgagePage checkCalculations() {
        try {
            sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        Assert.assertEquals("Поле 'Сумма кредита' было заполнено некорректно",
                "2122000", creditAmount.getAttribute("textContent").replaceAll("[^{\\d,}]", ""));
        Assert.assertEquals("Поле 'Ежемесячный платеж' было заполнено некорректно",
                "16922", monthlyPayment.getAttribute("textContent").replaceAll("[^{\\d,}]", ""));
        Assert.assertEquals("Поле 'Необходимый доход' было заполнено некорректно",
                "21784", requiredSalary.getAttribute("textContent").replaceAll("[^{\\d,}]", ""));
        Assert.assertEquals("Поле 'Процентная ставка' было заполнено некорректно",
                "11", interestRate.getAttribute("textContent").replaceAll("[^{\\d,}]", ""));
        return this;
    }


}
