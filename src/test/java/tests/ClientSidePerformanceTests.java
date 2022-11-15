package tests;

import org.testng.Assert;
import org.testng.annotations.Test;

import java.io.IOException;

public class ClientSidePerformanceTests extends BaseTest {
    private final static String ITEM_NAME = "Build your own computer";
    @Test (priority = 0)
    public void openHomePage() throws IOException {
        Assert.assertTrue(businessLogicLayer.openHomePage());
    }

    @Test (priority = 1)
    public void openComputerPage() throws IOException {
        Assert.assertTrue(businessLogicLayer.openComputerPage());
    }

    @Test (priority = 2)
    public void openDesktopPage() throws IOException {
        Assert.assertTrue(businessLogicLayer.openDesktopPage());
    }

    @Test (priority = 3)
    public void openItemPage() throws IOException {
        Assert.assertEquals(businessLogicLayer.openItemPage(), ITEM_NAME);
    }
}
