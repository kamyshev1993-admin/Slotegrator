package slotegrator.project.control;

import org.openqa.selenium.By;

public class Input extends Control<Input> {
    public Input(By locator, IControl<?> parent) {
        super(locator, parent);
    }
    public void send(String value) {
        getWebElement().sendKeys(value);
    }
}
