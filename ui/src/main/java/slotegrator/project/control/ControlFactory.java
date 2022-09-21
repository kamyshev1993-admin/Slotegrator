package slotegrator.project.control;

import org.openqa.selenium.By;

public class ControlFactory {

    private IControl<?> parent;

    public ControlFactory() {
    }

    ControlFactory(IControl<?> parent) {
        this.parent = parent;
    }

    public Input input(By locator) {
        return new Input(locator, parent);
    }

    public Button button(By locator) {
        return new Button(locator, parent);
    }

    public Link link(By locator) {
        return new Link(locator, parent);
    }

    public Text text(By locator) {
        return new Text(locator, parent);
    }

    public <T extends IControl<T>> ControlCollection<T> collection(Class<T> clazz, By locator) {
        return new ControlCollection<>(clazz, locator, parent);
    }
}
