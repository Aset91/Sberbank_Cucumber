package steps;

import io.cucumber.datatable.DataTable;
import io.cucumber.java.ru.Когда;
import io.cucumber.java.ru.Тогда;
import ru.appline.framework.managers.PageManager;
public class Steps {


    private PageManager pageManager = PageManager.getPageManager();


    @Когда("Открыть главную страницу")
    public void getInitialPage() {
        pageManager.getStartPage();
        pageManager.getStartPage().closeCookiesWindow();
    }

    @Когда("^Открыть меню (.*)$")
    public void openMortgageSubmenu(String value) {
        pageManager.getStartPage().selectBaseMenu(value);
    }

    @Когда("^Выбрать подменю (.*)$")
    public void chooseReadyEstate(String value) {
        pageManager.getStartPage().selectSubMenu(value);
        pageManager.getMortgagePage().checkOpenPage();
        pageManager.getMortgagePage().scrollToForm();
        pageManager.getMortgagePage().switchWindows();
    }

    @Когда("^Перейти к ипотечному калькулятору и заполнить форму поле/значение$")
    public void fillFields(DataTable dataTable) {
        dataTable.cells().forEach(
                raw -> {
                    pageManager.getMortgagePage().fillField(raw.get(0), raw.get(1));
                }
        );
    }

    @Когда("^Убрать лишние чекбоксы$")
    public void removeCheckbox(DataTable dataTable) {
//        dataTable.cells().forEach(
//                raw -> {
//                    pageManager.getMortgagePage().removeCheckboxes(raw.get(0));
//                }
//        );
        pageManager.getMortgagePage().removeCheckboxes("Страхование жизни и здоровья");
        pageManager.getMortgagePage().removeCheckboxes("Скидка 0,3% при покупке квартиры на ДомКлик");
        pageManager.getMortgagePage().removeCheckboxes("Электронная регистрация сделки");
    }

    @Тогда("^Проверить рассчеты$")

    public void checkingAmountFields(DataTable dataTable) {
        dataTable.cells().forEach(
                raw -> {
                    pageManager.getMortgagePage().checkCalculations(raw.get(0), raw.get(1));
                }
        );
    }
}
