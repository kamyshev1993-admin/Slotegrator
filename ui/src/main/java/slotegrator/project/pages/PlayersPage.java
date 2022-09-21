package slotegrator.project.pages;

import lombok.Getter;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import slotegrator.project.control.Control;
import slotegrator.project.control.ControlCollection;
import slotegrator.project.control.Link;
import slotegrator.project.control.Text;
import slotegrator.project.dictionaries.SortPlayersEnum;

import java.util.List;

public class PlayersPage extends BasePage {
    public PlayersPage sortTable(SortPlayersEnum sortPlayersEnum) {
        getTable().sortBy(sortPlayersEnum);
        getWaiter().until(ExpectedConditions.invisibilityOfElementLocated(By.xpath("//div[@class='grid-view grid-view-loading']")));
        return this;
    }

    public List<Table.Row> getRows() {
        return getTable().getRows().getMappedWebElements();
    }

    private Table getTable() {
       return new Table(By.xpath("//table[@class='table table-hover table-striped table-bordered table-condensed']"));
    }

    public static class Table extends Control<Table> {
        private final Link userNameHeader = create().link(By.xpath("//a[text()='Username']"));
        private final Link externalIdHeader = create().link(By.xpath("//a[text()='External ID']"));
        private final Link nameHeader = create().link(By.xpath("//a[text()='Name']"));
        private final Link lastNameHeader = create().link(By.xpath("//a[text()='Last name']"));
        private final Link emailHeader = create().link(By.xpath("//a[text()='E-mail']"));
        private final Link phoneHeader = create().link(By.xpath("//a[text()='Phone']"));
        private final Link hallHeader = create().link(By.xpath("//a[text()='Hall']"));
        private final Link regDateHeader = create().link(By.xpath("//a[text()='Registration date']"));
        private final Link lastVisitHeader = create().link(By.xpath("//a[text()='Last visit']"));
        private final Link verifiedPlayerHeader = create().link(By.xpath("//a[text()='Verified player']"));
        private final Link statusHeader = create().link(By.xpath("//a[text()='Status']"));

        protected Table(By locator) {
            super(locator);
        }

        public ControlCollection<Row> getRows() {
            return create().collection(Row.class, By.xpath("//tbody/tr"));
        }

        public void sortBy(SortPlayersEnum sortPlayersEnum) {
            Link link;
            switch (sortPlayersEnum) {
                case USERNAME:
                    link = userNameHeader;
                    break;
                case EXTERNAL_ID:
                    link = externalIdHeader;
                    break;
                case NAME:
                    link = nameHeader;
                    break;
                case LAST_NAME:
                    link = lastNameHeader;
                    break;
                case E_MAIL:
                    link = emailHeader;
                    break;
                case PHONE:
                    link = phoneHeader;
                    break;
                case HALL:
                    link = hallHeader;
                    break;
                case REGISTRATION_DATE:
                    link = regDateHeader;
                    break;
                case LAST_VISIT:
                    link = lastVisitHeader;
                    break;
                case VERIFIED_PLAYER:
                    link = verifiedPlayerHeader;
                    break;
                case STATUS:
                    link = statusHeader;
                    break;
                default:
                    throw new IllegalArgumentException("illegal SortPlayersEnum");
            }
            link.waitDisplayed().select();
        }

        public static class Row extends Control<Row> {
            @Getter
            private final Text number = create().text(By.xpath("./td[1]"));
            @Getter
            private final Link userName = create().link(By.xpath("//td[2]/a"));
            @Getter
            private final Text externalId = create().text(By.xpath("./td[3]"));
            @Getter
            private final Text name = create().text(By.xpath("./td[4]"));
            @Getter
            private final Text lastName = create().text(By.xpath("./td[5]"));
            @Getter
            private final Text email = create().text(By.xpath("./td[6]"));
            @Getter
            private final Text phone = create().text(By.xpath("./td[7]"));
            @Getter
            private final Text hall = create().text(By.xpath("./td[8]"));
            @Getter
            private final Text balance = create().text(By.xpath("./td[9]"));
            @Getter
            private final Text regDate = create().text(By.xpath("./td[10]"));
            @Getter
            private final Text lastVisit = create().text(By.xpath("./td[11]"));
            @Getter
            private final Text lastVisitIp = create().text(By.xpath("./td[12]"));
            @Getter
            private final Text verifiedPlayer = create().text(By.xpath("//td[13]/span"));
            @Getter
            private final Text online = create().text(By.xpath("//td[14]/span"));
            @Getter
            private final Text status = create().text(By.xpath("//td[15]/span"));

            protected Row(WebElement webElement) {
                super(webElement);
            }
        }
    }
}
