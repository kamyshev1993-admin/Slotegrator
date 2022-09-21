package slotegrator.project.control;

import org.openqa.selenium.WebElement;

public interface IControl<T extends IControl<T>> {
    WebElement getWebElement();
    T waitDisplayed();

    default boolean isDisplayed() {
        return getWebElement().isDisplayed();
    }

    default boolean isEnabled() {
       return getWebElement().isEnabled();
    }
}
