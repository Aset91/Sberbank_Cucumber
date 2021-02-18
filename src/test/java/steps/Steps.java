package steps;

import io.cucumber.datatable.DataTable;
import io.cucumber.java.ru.Когда;
import io.cucumber.java.ru.Тогда;
import ru.appline.framework.managers.PageManager;
public class Steps {


    private PageManager pageManager = PageManager.getPageManager();


    @Когда("^Открыть главную страницу$")
    public void getInitialPage() {
        pageManager.getStartPage();
        pageManager.getStartPage().closeCookiesWindow();
    }

    @Когда("^Открыть подменю Ипотека$")
    public void openMortgageSubmenu() {
        pageManager.getStartPage().openMortgageWindow();
    }

    @Когда("^Выбрать готовое жилье$")
    public void chooseReadyEstate() {
        pageManager.getStartPage().chooseReadyEstateSubmenu();
        pageManager.getMortgagePage().checkOpenPage();
        pageManager.getMortgagePage().switchWindows();
        pageManager.getMortgagePage().scrollToForm();
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
        dataTable.cells().forEach(
                raw -> {
                    pageManager.getMortgagePage().removeCheckboxes(raw.get(0), raw.get(1));
                }
        );
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
