package steps;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.openqa.selenium.ElementClickInterceptedException;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import pages.HomePage;
import pages.UserProfilePage;
import tests.TestsBase;

public class userLogin extends TestsBase {

    @Given("the user in the home page")
    public void the_user_in_the_home_page() {
    }
    @When("The User clicks on Login button")
    public void the_user_clicks_the_login_button() {
        HomePage homePage = new HomePage(driver);
        try
        {
            homePage.openLoginModal();
        }
        catch (ElementClickInterceptedException e) {
        }
        catch (StaleElementReferenceException e2)
        {

        }

    }
    @When("The User enters his credentials {string} & {string}")
    public void the_user_enters_his_credentials(String email, String password) {
        HomePage homePage = new HomePage(driver);
        homePage.login(email, password);
    }

    @Then("The UserProfilePage should get displayed successfully")
    public void the_user_profile_page_should_get_displayed_successfully() {
        UserProfilePage userProfilePage = new UserProfilePage(driver);
        Assert.assertEquals(userProfilePage.userMenu_btn.isDisplayed(), true);
        userProfilePage.logout();
    }

}
