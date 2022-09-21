package slotegrator.project.appLogic;

import slotegrator.project.pages.LoginPage;
import slotegrator.project.pages.MainPage;
import slotegrator.project.pages.PlayersPage;

public class PageManager {
    public LoginPage getLoginPage() {
        return new LoginPage();
    }

    public MainPage getMainPage() {
        return new MainPage();
    }

    public PlayersPage getPlayersPage() {
        return new PlayersPage();
    }
}
