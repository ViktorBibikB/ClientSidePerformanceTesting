package manager;

import driver.DriverSingleton;
import navigationtiming.PerfNavigationTiming;
import org.openqa.selenium.WebDriver;
import page.ComputerPage;
import page.DesktopPage;
import page.HomePage;
import page.ItemPage;

public class PageFactoryManager {
    public static final HomePage HOME_PAGE = new HomePage();
    public static final ComputerPage COMPUTER_PAGE = new ComputerPage();
    public static final DesktopPage DESKTOP_PAGE = new DesktopPage();
    public static final ItemPage ITEM_PAGE = new ItemPage();
    public static final PerfNavigationTiming PERF_NAVIGATION_TIMING = new PerfNavigationTiming();
}
