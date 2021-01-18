package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class UserProfilePage extends PageBase{
    @FindBy(className = "div.user__thumb")
    public WebElement userAvatar;

    @FindBy(xpath = "/html/body/ureed-root/ureed-navbar/nav/div/div[3]/div/ul/li[3]/img")
    public WebElement userMenu_btn;

    @FindBy(css = "button.user-list-items:nth-child(6)")
    public WebElement logout_btn;

    public UserProfilePage(WebDriver driver) {
        super(driver);
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    public HomePage logout()
    {
//        fluentWait(userMenu_btn ,10, driver);
        click(userMenu_btn);
        click(logout_btn);

        return new HomePage(driver);
    }
}
