package ru.appline.framework.managers;

import ru.appline.framework.pages.MortgagePage;
import ru.appline.framework.pages.StartPage;

public class PageManager {
    private static PageManager pageManager;
    StartPage startPage;
    MortgagePage mortgagePage;

    /**
     * Конструктор синглтона
     */
    private PageManager(){
    }
    /**
     * Ленивая инициализация PageManager
     *
     * @return PageManager
     */
    public static PageManager getPageManager(){
        if(pageManager == null) {
            pageManager = new PageManager();
        }
         return pageManager;
    }

    /**
     * Ленивая инициализация StartPage
     * @return StartPage
     */
    public StartPage getStartPage(){
        if(startPage == null) {
            startPage = new StartPage();
        }
        return startPage;
    }

    /**
     * Ленивая инициализация MortgagePage
     * @return MortgagePage
     */
    public MortgagePage getMortgagePage() {
        if(mortgagePage == null){
            mortgagePage = new MortgagePage();
        }
        return mortgagePage;
    }

    /**
     * Сброс менеджера страничек
     */
    public static void disablePageManager() {
        pageManager = null;
    }
}
