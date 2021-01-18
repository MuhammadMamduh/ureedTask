package tests;

import org.openqa.selenium.NoSuchElementException;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.NoInjection;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;
import pages.HomePage;
import pages.UserProfilePage;
import org.testng.annotations.DataProvider;

public class Test1 extends TestsBase{

    @DataProvider
    public Object[][] credentialsProvider()
    {
        Object [][] data = new Object [1][3];

        data [0][0] = "muhammad.mma.oz@gmail.com";		data [0][1] = "A1b2c3d4@";		data [0][2] = true;
//        data [1][0] = "muhammad.mma.oz@gmail.com";		data [1][1] = "wrongPassword";	data [1][2] = false;

        return data;
    }

    @Test(priority = 0, dataProvider = "credentialsProvider")
    public void login(String email, String password, boolean success) throws InterruptedException, NoSuchElementException {
        HomePage homePage = new HomePage(driver);
        UserProfilePage userProfilePage = homePage.openLoginModal().login(email, password);

        Assert.assertTrue(userProfilePage.userMenu_btn.isDisplayed(), "Passed");
    }

    @Test (dependsOnMethods = "login")
    public void logout ()
    {
        UserProfilePage userProfilePage = new UserProfilePage(driver);
        userProfilePage.logout();
    }
}
