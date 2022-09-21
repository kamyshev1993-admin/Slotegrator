package slotegrator.project.appLogic;

import org.openqa.selenium.WebDriver;
import slotegrator.project.helper.NavigationHelper;

public class HelperManager {
    private final NavigationHelper navigationHelper;

    public HelperManager(WebDriver webDriver, PageManager pageManager) {
        navigationHelper = new NavigationHelper(webDriver, pageManager);
    }

    public NavigationHelper getNavigationHelper() {
        return navigationHelper;
    }
}
