package pages;

import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class HomePage extends PageBase{
    @FindBy(linkText = "Sign in")
    WebElement signIn_btn;

    @FindBy(id = "username")
    WebElement email_txtField;

    @FindBy(id = "password")
    WebElement password_txtField;

    @FindBy(xpath = "/html/body/ureed-root/ureed-header/ureed-signin-modal/div/div/div/div/div[2]/form/div[3]/button")
    WebElement login_btn;

    @FindBy(className = "div[class=\"error-message ng-star-inserted\"] > span.ng-star-inserted")
    public WebElement loginErrorMsg;

    public HomePage(WebDriver driver) {
        super(driver);
        this.driver = driver;
        PageFactory.initElements(driver, this);
//        jse = (JavascriptExecutor) driver;
//        action = new Actions(driver);
    }

    public HomePage openLoginModal() {

            click(signIn_btn);
        return new HomePage(driver);
    }
    public UserProfilePage login(String email, String password)
    {
        // Make sure that nothing is written in the
        clearText(email_txtField);
        clearText(password_txtField);

        sendText(email_txtField, email);
        sendText(password_txtField, password);

        click(login_btn);

        return new UserProfilePage(driver);
    }


}
