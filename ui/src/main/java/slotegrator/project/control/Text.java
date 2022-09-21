package slotegrator.project.control;

import org.openqa.selenium.By;

public class Text extends Control<Text> {
    public Text(By locator, IControl<?> parent) {
        super(locator, parent);
    }

    public String getText() {
        return getWebElement().getText();
    }
}
