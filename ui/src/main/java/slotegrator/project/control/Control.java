package slotegrator.project.control;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import slotegrator.project.appLogic.WebDriverFactory;
import slotegrator.project.dictionaries.TimeoutEnum;

public abstract class Control<T extends Control<T>> implements IControl<T> {
    private By locator;
    private WebElement webElement;
    private IControl<?> parent;
    private final WebDriver webDriver = WebDriverFactory.getInstance().getWebDriver();
    private final WebDriverWait waiter = new WebDriverWait(webDriver,
            java.time.Duration.ofSeconds(TimeoutEnum.TIME_OUT_IN_SECONDS_FOR_FIND_WEB_ELEMENT.getTime()));

    private ControlFactory factory;

    protected Control(By locator) {
        this.locator = locator;
    }

    protected Control(WebElement webElement) {
        this.webElement = webElement;
    }

    protected Control(By locator, IControl<?> parent) {
        this.locator = locator;
        this.parent = parent;
    }

    public ControlFactory create() {
        if (factory == null) {
            factory = new ControlFactory(this);
        }
        return factory;
    }

    @Override
    public T waitDisplayed() {
       waiter.until(ExpectedConditions.visibilityOf(getWebElement()));
       return (T) this;
    }

    @Override
    public WebElement getWebElement() {
        if (webElement == null) {
            if(parent == null) {
                waiter.until(ExpectedConditions.presenceOfElementLocated(locator));
                webElement = webDriver.findElement(locator);
            } else {
                webElement = parent.getWebElement().findElement(locator);
            }
        }
        return webElement;
    }
}
