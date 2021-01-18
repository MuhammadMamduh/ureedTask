package pages;

import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.events.EventFiringWebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.time.Duration;
import java.util.List;

public class PageBase {

    protected WebDriver driver ;
    public JavascriptExecutor jse ;
    public Select select ;
    public Actions action ;

    // create constructor
    public PageBase(WebDriver driver)
    {
        this.driver= driver;
        PageFactory.initElements(driver, this);
    }

    // Helpers
    public WebElement extractElement(By elementBy)
    {
        return driver.findElement(elementBy);
    }

    public List<WebElement> extractList(By elementBy)
    {
        return driver.findElements(elementBy);
    }

    // Common Functions
    public static WebElement click(WebElement element)
    {
        element.click();

        return element;
    }
    public WebElement hoverOnElement(WebElement element, WebDriver driver)
    {
        action = new Actions(driver);

        action.moveToElement(element).build().perform();

        return element;
    }
    public static void scrollToElement(WebDriver driver, WebElement element)
    {
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("arguments[0].scrollIntoView();", element);
    }
    public static WebElement sendText(WebElement textElement , String value)
    {
        textElement.sendKeys(value);
        System.out.println("Inputing: " + value);

        return textElement;
    }
    public static WebElement clearText(WebElement element)
    {
        element.clear();

        return element;
    }
    public static void clickEnter()
    {
        Robot robot= null;
        try {
            robot = new Robot();
        } catch (AWTException e) {
            e.printStackTrace();
        }
        robot.keyPress(KeyEvent.VK_ENTER);
    }
    public void scrollToBottom()
    {
        jse.executeScript("scrollBy(0,2500)");
    }

    public static void clickElementWhenClickable(By locator, int timeout, EventFiringWebDriver driver)
    {
        WebElement element = null;
        WebDriverWait wait = new WebDriverWait(driver, timeout);
        element = wait.until(ExpectedConditions.elementToBeClickable(locator));
        element.click();
    }
    public static void fluentWait(WebElement element, int timeout, WebDriver driver)
    {
        FluentWait wait = new FluentWait(driver)
                .withTimeout(Duration.ofSeconds(timeout))
                .pollingEvery(Duration.ofSeconds(1))
                .ignoring(NoSuchElementException.class);
        wait.until(ExpectedConditions.visibilityOf(element));
    }
}
