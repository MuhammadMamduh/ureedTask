package tests;

import io.cucumber.testng.AbstractTestNGCucumberTests;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.safari.SafariDriver;
import org.testng.ITestResult;
import org.testng.annotations.*;
import org.testng.asserts.SoftAssert;
import pages.HomePage;

import java.util.HashMap;
import java.util.concurrent.TimeUnit;

public class TestsBase extends AbstractTestNGCucumberTests {
    public static WebDriver driver ;
    protected JavascriptExecutor js;
    protected HomePage homePage;
    protected SoftAssert softAssert;
    protected String osName = System.getProperty("os.name").toLowerCase();

    public static String downloadPath = System.getProperty("user.dir") + "\\Downloads";

    public static FirefoxOptions firefoxOption() {
        FirefoxOptions option = new FirefoxOptions();
        option.addPreference("browser.download.folderList", 2);
        option.addPreference("browser.download.dir", downloadPath);
        option.addPreference("browser.helperApps.neverAsk.saveToDisk", "application/pdf");
        option.addPreference("browser.download.manager.showWhenStarting", false);
        option.addPreference("pdfjs.disabled", true);
        return option;
    }

    public static ChromeOptions chromeOption() {
        ChromeOptions options = new ChromeOptions();
        HashMap<String, Object> chromePrefs = new HashMap<String, Object>();
        chromePrefs.put("profile.default.content_settings.popups", 0);
        chromePrefs.put("download.default_directory", downloadPath);
        options.setExperimentalOption("prefs", chromePrefs);
        options.setCapability(CapabilityType.ACCEPT_SSL_CERTS, true);
        return options;
    }

    @BeforeSuite
    @Parameters({"browser"})
    public void startDriver(@Optional("chrome") String browserName) throws InterruptedException {

        // Print the OS Name
        System.out.println("OS: " + osName);

        // Setup system properties
        if(this.osName.contains("mac"))
        {
            if (browserName.equalsIgnoreCase("chrome"))
            {
                System.setProperty("webdriver.chrome.driver", "src/main/resources/drivers/chromedriver");
                driver = new ChromeDriver(chromeOption());
            }
            else if(browserName.equalsIgnoreCase("firefox"))
            {
                 System.setProperty("webdriver.gecko.driver", "src/main/resources/drivers/geckodriver");
                 driver = new FirefoxDriver(firefoxOption());
            }
        }
        else if(this.osName.equals("windows"))
        {
            System.setProperty("webdriver.chrome.driver", "src/main/resources/drivers/chromedriver_windows.exe");
            driver = new ChromeDriver();

            System.out.println(System.getProperty("os.name").toLowerCase());
        }
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(60, TimeUnit.SECONDS);
        driver.get("https://ureed.com/en");
    }
    @AfterSuite
    public void tearDown()
    {
        // Clean up all cookies.
//        driver.manage().deleteAllCookies();
        driver.quit();

    }

    // take screenshot when test case fail and add it in the Screenshot folder
    @AfterMethod
    public void screenshotOnFailure(ITestResult result)
    {
        if (result.getStatus() == ITestResult.FAILURE)
        {
            System.out.println("Failed!");
            System.out.println("Taking Screenshot....");
//            Helper.captureScreenshot(driver, result.getName());
        }
    }
}
