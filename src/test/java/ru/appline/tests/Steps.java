package ru.appline.tests;

import io.cucumber.datatable.DataTable;
import io.cucumber.java.ru.Когда;
import io.cucumber.java.ru.Тогда;
import ru.appline.framework.managers.PageManager;

public class Steps {

        private PageManager pageManager = PageManager.getPageManager();


        @Когда("^Открыть главную страницу '(.*)'$")
        public void getInitialPage() {
            pageManager.getStartPage();
        }
        @Когда("^Закрыть окно Cookies '(.*)'$")
        public void closeCookiesWindow() {
            pageManager.getStartPage().closeCookiesWindow();
        }
        @Когда("^Открыть подменю Ипотека '(.*)'$")
        public  void openMortgageSubmenu() {
            pageManager.getStartPage().openMortgageWindow();
        }
        @Когда("^Выбрать готовое жилье '(.*)'$")
        public void chooseReadyEstate() {
            pageManager.getStartPage().chooseReadyEstateSubmenu();
        }
        @Тогда("^Проверка заголовка страницы '(.*)'$")
        public void checkPageTitle() {
            pageManager.getMortgagePage().checkOpenPage();
        }
        @Когда("^Переключение на окно с рассчетом показателей '(.*)'$")
        public void switchToCalculationsWindow() {
            pageManager.getMortgagePage().switchWindows();
        }
        @Когда("^Скролл до окна с рассчетами '(.*)'$")
        public void scrollToCalculationsWindow(){
            pageManager.getMortgagePage().scrollToForm();
        }
        @Когда("^Заполняем форму поле/значение$")
        public void fillOutFields(DataTable dataTable){
            dataTable.cells().forEach(
                    raw -> {
                        pageManager.getMortgagePage().fillField(raw.get(0), raw.get(1));
                    }
            );
        }
        @Когда("^Убрать лишние чекбоксы '(.*)'$")
        public void removeCheckbox() {
            pageManager.getMortgagePage().removeCheckboxes();
        }
        @Тогда("^Проверить рассчеты '(.*)'$")
        public void checkNumbers() {
            pageManager.getMortgagePage().checkCalculations();
        }

    }
