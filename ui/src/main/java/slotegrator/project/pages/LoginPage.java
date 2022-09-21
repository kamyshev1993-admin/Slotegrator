package slotegrator.project.pages;

import org.openqa.selenium.By;
import slotegrator.project.control.Button;
import slotegrator.project.control.Input;

public class LoginPage extends BasePage {
    private final Input loginInput = create().input(By.id("UserLogin_username"));
    private final Input passwordInput = create().input(By.id("UserLogin_password"));
    private final Button submitButton = create().button(By.xpath("//input[@class='btn btn-primary btn-lg btn-block']"));

    public MainPage login(String userName, String password) {
        loginInput.send(userName);
        passwordInput.send(password);
        submitButton.click();
        return new MainPage();
    }
}
