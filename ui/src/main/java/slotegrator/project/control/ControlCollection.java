package slotegrator.project.control;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import slotegrator.project.appLogic.WebDriverFactory;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.List;
import java.util.stream.Collectors;

public class ControlCollection <T extends IControl<T>> {

    private final Class<T> clazz;
    private final By locator;
    private final IControl<?> parent;
    private List<WebElement> collection;

    private final WebDriver webDriver = WebDriverFactory.getInstance().getWebDriver();

    ControlCollection(Class<T> clazz, By locator, IControl<?> parent) {
        this.clazz = clazz;
        this.locator = locator;
        this.parent = parent;
    }

    public List<WebElement> getWebElements() {
        if (collection == null) {
            if(parent == null) {
                collection = webDriver.findElements(locator);
            } else {
                collection = parent.getWebElement().findElements(locator);
            }
        }
        return collection;
    }

    public List<T> getMappedWebElements() {
        return getWebElements().stream().map(this::instantiate).collect(Collectors.toList());
    }

    private T instantiate(WebElement element) {
        try {
            Constructor<T> constructor = clazz.getDeclaredConstructor(WebElement.class);
            constructor.setAccessible(true);
            return constructor.newInstance(element);
        } catch (NoSuchMethodException | InstantiationException | IllegalAccessException | InvocationTargetException e) {
            e.printStackTrace();
        }
        return null;
    }
}
