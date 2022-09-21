package steps;

import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.ru.И;
import io.cucumber.java.ru.Когда;
import io.cucumber.java.ru.Тогда;
import org.assertj.core.api.Assertions;
import slotegrator.project.ConfigQA;
import slotegrator.project.appLogic.ApplicationManager;
import slotegrator.project.dictionaries.SortPlayersEnum;
import slotegrator.project.pages.LoginPage;
import slotegrator.project.pages.MainPage;
import slotegrator.project.pages.PlayersPage;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;

public class StepsDefinition {
    protected ConfigQA configQA;
    protected ApplicationManager manager;
    private List<PlayersPage.Table.Row> rows;

    @Before
    public void init() {
        configQA = ConfigQA.getInstance();
        manager = ApplicationManager.getInstance();
    }

    @Когда("^Авторизоваться в админке$")
    public void authorize() {
        LoginPage loginPage = manager.getHelperManager().getNavigationHelper().openLoginPage();
        loginPage.login(configQA.getUserName(), configQA.getPassword());
    }

    @И("^Открыть список игроков$")
    public void openPlayerList() {
        MainPage mainPage = manager.getHelperManager().getNavigationHelper().getMainPage();
        mainPage.chooseOnlinePlayersBox();
    }

    @И("^Отсортировать таблицу по \"([^\"]*)\"$")
    public void sortTable(SortPlayersEnum sortPlayersEnum) {
        PlayersPage playersPage = manager.getHelperManager().getNavigationHelper().getPlayersPage();
        rows = playersPage.sortTable(sortPlayersEnum).getRows();
    }

    @Тогда("^Таблица отсортирована верно по \"([^\"]*)\"$")
    public void checkingSort(SortPlayersEnum sortPlayersEnum) {
        List<String> mappedValues = rows.stream().map(a-> getFunction(sortPlayersEnum).apply(a)).collect(Collectors.toList());
        List<String> mappedValuesForSort = new ArrayList<>(mappedValues);
        Collections.sort(new ArrayList<>(mappedValuesForSort));
        Assertions.assertThat(mappedValues).isEqualTo(mappedValuesForSort);
    }

    public Function<PlayersPage.Table.Row, String> getFunction(SortPlayersEnum sortPlayersEnum) {
        switch (sortPlayersEnum) {
            case USERNAME:
                return (a) -> a.getUserName().getLinkName();
            case EXTERNAL_ID:
                return (a) -> a.getExternalId().getText();
            case NAME:
                return (a) -> a.getName().getText();
            case LAST_NAME:
                return (a) -> a.getLastName().getText();
            case E_MAIL:
                return (a) -> a.getEmail().getText();
            case PHONE:
                return (a) -> a.getPhone().getText();
            case HALL:
                return (a) -> a.getHall().getText();
            case REGISTRATION_DATE:
                return (a) -> a.getRegDate().getText();
            case LAST_VISIT:
                return (a) -> a.getLastVisit().getText();
            case VERIFIED_PLAYER:
                return (a) -> a.getVerifiedPlayer().getText();
            case STATUS:
                return (a) -> a.getStatus().getText();
            default:throw new IllegalArgumentException("illegal sort player enum");
        }
    }


    @After
    public void closeBrowser() {
        manager.getDriver().close();
    }

}
