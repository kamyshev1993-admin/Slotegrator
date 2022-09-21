package slotegrator.project.appLogic;

import lombok.Getter;
import org.openqa.selenium.WebDriver;

public class ApplicationManager {
    @Getter
    private final PageManager pageManager;
    @Getter
    private final HelperManager helperManager;
    @Getter
    private final WebDriver driver;

    private ApplicationManager() {
        driver = WebDriverFactory.getInstance().getWebDriver();
        pageManager = new PageManager();
        helperManager = new HelperManager(driver, pageManager);
    }

    private static volatile ApplicationManager instance;

    public static ApplicationManager getInstance() {
        ApplicationManager result = instance;
        if (result != null) {
            return result;
        }
        synchronized (ApplicationManager.class) {
            if (instance == null) {
                instance = new ApplicationManager();
            }
            return instance;
        }
    }
}
