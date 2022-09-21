package slotegrator.project.pages;
import lombok.Getter;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import slotegrator.project.appLogic.WebDriverFactory;
import slotegrator.project.control.ControlFactory;
import slotegrator.project.dictionaries.TimeoutEnum;

public abstract class BasePage {
    private final WebDriver webDriver = WebDriverFactory.getInstance().getWebDriver();
    @Getter
    private final WebDriverWait waiter = new WebDriverWait(webDriver,
            java.time.Duration.ofSeconds(TimeoutEnum.TIME_OUT_IN_SECONDS_FOR_FIND_WEB_ELEMENT.getTime()));

    private final ControlFactory controlFactory = new ControlFactory();

    protected ControlFactory create() {
        return controlFactory;
    }
}
