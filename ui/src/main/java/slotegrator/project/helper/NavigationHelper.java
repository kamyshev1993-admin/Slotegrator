package slotegrator.project.helper;

import org.openqa.selenium.WebDriver;
import slotegrator.project.ConfigQA;
import slotegrator.project.appLogic.PageManager;
import slotegrator.project.pages.LoginPage;
import slotegrator.project.pages.MainPage;
import slotegrator.project.pages.PlayersPage;

public class NavigationHelper {
    private final WebDriver driver;

    private final PageManager pageManager;
    public NavigationHelper(WebDriver webDriver, PageManager pageManager) {
        this.driver = webDriver;
        this.pageManager = pageManager;
    }

    public LoginPage openLoginPage() {
        try {
            driver.get(ConfigQA.getInstance().getLoginUrl());
        }
        catch (Exception e) {
            e.printStackTrace();
        }
        return pageManager.getLoginPage();
    }

    public MainPage getMainPage() {
        return pageManager.getMainPage();
    }

    public PlayersPage getPlayersPage() {
        return pageManager.getPlayersPage();
    }
}
