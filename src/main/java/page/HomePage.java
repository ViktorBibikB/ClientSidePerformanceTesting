package page;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import static waiter.Waiters.waitForPageLoadComplete;

public class HomePage extends BasePage{
    @FindBy(xpath = "//ul[@class='top-menu notmobile']//a[@href='/computers']")
    private WebElement buttonComputers;
    @FindBy(id = "newsletter-subscribe-button")
    private WebElement buttonSubscribe;

    public void clickButtonComputers(){
        buttonComputers.click();
    }

    public WebElement getButtonComputers() {
        return buttonComputers;
    }

    public WebElement getButtonSubscribe() {
        return buttonSubscribe;
    }
}
