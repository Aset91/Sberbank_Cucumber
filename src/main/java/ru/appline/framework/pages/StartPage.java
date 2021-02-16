package ru.appline.framework.pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;


/**
 * Start page - Стартовая страница "https://www.sberbank.ru/ru/person"
 */

public class StartPage extends  BasePage {

    @FindBy(xpath = "//button[@class='kitt-cookie-warning__close']")
    WebElement cookies;
    @FindBy(xpath = "//a[@aria-label='Ипотека']")
    WebElement mortgage;
    @FindBy(xpath = "//a[@data-cga_click_top_menu='Ипотека_Ипотека на готовое жильё_type_important']")
    WebElement readyEstate;

    /**
     * Закрыть окно Cookies
     */
    public StartPage closeCookiesWindow() {
        cookies.click();
        return this;
    }

    /**
     * В верхнем меню "нажать" на Ипотека - дождаться открытия выпадающего меню
     */
    public StartPage openMortgageWindow() {
        mortgage.click();
        return this;
    }

    /**
     * выбрать "Ипотека на готовое жилье"
     * @return
     */
    public MortgagePage chooseReadyEstateSubmenu(){
     //   action.moveToElement(readyEstate).click().build().perform();
        readyEstate.click();
        return app.getMortgagePage();
    }

}
