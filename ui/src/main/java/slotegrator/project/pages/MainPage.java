package slotegrator.project.pages;

import org.openqa.selenium.By;
import slotegrator.project.control.Link;

public class MainPage extends BasePage {
    private final Link onlinePlayersLink = create().link(By.xpath("//p[text()='Players online / total']"));

    public PlayersPage chooseOnlinePlayersBox() {
        onlinePlayersLink.select();
        return new PlayersPage();
    }
}
