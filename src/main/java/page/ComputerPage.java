package page;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import static waiter.Waiters.waitForPageLoadComplete;

public class ComputerPage  extends BasePage{
    @FindBy(xpath = "//h2[@class='title']//a[@href='/desktops']")
    private WebElement desktopButton;

    public void clickDesktopButton(){
        desktopButton.click();
    }

    public WebElement getDesktopButton() {
        return desktopButton;
    }
}
