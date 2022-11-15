package page;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class DesktopPage  extends BasePage{
    @FindBy(xpath = "//div[@class='picture']//a[@href='/build-your-own-computer']")
    public WebElement buttonPC;
    @FindBy(xpath = "//div[@data-productid='1']//button[@class='button-2 product-box-add-to-cart-button']")
    public WebElement buttonAddTOCart;

    public void clickButtonPC(){
        buttonPC.click();
    }

    public void clickButtonAddTOCart(){
        buttonAddTOCart.click();
    }

    public WebElement getButtonPC() {
        return buttonPC;
    }

    public WebElement getButtonAddTOCart() {
        return buttonAddTOCart;
    }
}
