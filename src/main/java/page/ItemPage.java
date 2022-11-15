package page;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class ItemPage  extends BasePage{
    @FindBy(xpath = "//div[@class='product-name']/h1")
    private WebElement itemName;

    public String getItemName(){
        return itemName.getText();
    }
}
