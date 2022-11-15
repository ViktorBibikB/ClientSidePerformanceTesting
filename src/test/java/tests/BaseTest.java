package tests;

import driver.DriverSingleton;
import io.github.bonigarcia.wdm.WebDriverManager;
import logiclayer.BusinessLogicLayer;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeClass;

import java.util.concurrent.TimeUnit;


public class BaseTest {
    public BusinessLogicLayer businessLogicLayer;
    private static final String BASE_URL = "https://demo.nopcommerce.com/";

    @BeforeClass
    public void profileSetUp(){
        WebDriverManager.chromedriver().setup();
        DriverSingleton.getDriver();
        DriverSingleton.getDriver().manage().window().maximize();
        DriverSingleton.getDriver().manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        DriverSingleton.getDriver().get(BASE_URL);

        businessLogicLayer = getBusinessLogicLayer();
    }

    @AfterTest
    public void tearDown() {
        DriverSingleton.getDriver().quit();
    }

    public BusinessLogicLayer getBusinessLogicLayer(){
        return new BusinessLogicLayer();
    }
}
