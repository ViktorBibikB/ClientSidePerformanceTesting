package page;

import driver.DriverSingleton;
import org.openqa.selenium.support.PageFactory;

public class BasePage {
    public BasePage(){
        PageFactory.initElements(DriverSingleton.getDriver(), this);
    }

}
