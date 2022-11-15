package logiclayer;

import java.io.IOException;

import static manager.PageFactoryManager.*;
import static waiter.Waiters.waitForPageLoadComplete;

public class BusinessLogicLayer {

    public boolean openHomePage() throws IOException {
        waitForPageLoadComplete(5);
        PERF_NAVIGATION_TIMING.writeToFile("Home Page", "homePagePerf");
        return HOME_PAGE.getButtonComputers().isEnabled();
    }

    public boolean openComputerPage() throws IOException {
        waitForPageLoadComplete(5);
        HOME_PAGE.clickButtonComputers();
        waitForPageLoadComplete(5);
        PERF_NAVIGATION_TIMING.writeToFile("Computers Page", "computerPagePerf");
        return COMPUTER_PAGE.getDesktopButton().isEnabled();
    }

    public boolean openDesktopPage() throws IOException {
        waitForPageLoadComplete(5);
        COMPUTER_PAGE.clickDesktopButton();
        waitForPageLoadComplete(5);
        PERF_NAVIGATION_TIMING.writeToFile("Desktop Page", "desktopPagePerf");
        return DESKTOP_PAGE.getButtonPC().isEnabled();
    }

    public String openItemPage() throws IOException {
        waitForPageLoadComplete(5);
        DESKTOP_PAGE.clickButtonPC();
        waitForPageLoadComplete(5);
        PERF_NAVIGATION_TIMING.writeToFile("Items Page", "itemPagePerf");
        return ITEM_PAGE.getItemName();
    }

}
