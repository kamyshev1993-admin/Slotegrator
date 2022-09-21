package slotegrator.project.control;

import org.openqa.selenium.By;

public class Button extends Control<Button> {

    public Button(By locator, IControl<?> parent) {
        super(locator, parent);
    }

    public void click() {
        getWebElement().click();
    }
}
