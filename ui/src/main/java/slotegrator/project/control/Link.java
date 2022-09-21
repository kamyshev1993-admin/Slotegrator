package slotegrator.project.control;

import org.openqa.selenium.By;

public class Link extends Control<Link> {

    public Link(By locator, IControl<?> parent) {
        super(locator, parent);
    }

    public void select() {
        getWebElement().click();
    }

    public String getLinkName() {
        return getWebElement().getText();
    }
}
