import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.Browser;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.locators.RelativeLocator;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.net.MalformedURLException;
import java.net.URL;
import java.time.Duration;

public class SeleiumWait {
    private static WebDriver driver;
    private String URL = "https://www.krisshsaahi.dev";
    private String SMC = "https://smc-booking-pfw.netlify.app/";
    private String LIFESOURCE = "https://lifesource.netlify.app/";

    @BeforeClass
    public void setUp() throws MalformedURLException {
//        DesiredCapabilities capabilities = new DesiredCapabilities();
//        capabilities.setBrowserName("firefox");
//        driver = new RemoteWebDriver(new URL("http://localhost:4444/wd/hub"), capabilities);
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        driver.manage().window().maximize();
    }

    @Test
    public void seleniumWait(){
        driver.get(URL);
        // implicit wait
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        WebElement role = driver.findElement(By.cssSelector(".index-module_type__E-SaG"));

        //Explicit wait
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(a ->role.getText().equals("Qa Automation Engineer"));
        Assert.assertEquals(role.getText(),"Qa Automation Engineer");
        System.out.println(role.getText());

        wait.until(ExpectedConditions.textToBePresentInElement(role, "Krishna Saahi"));
        Assert.assertEquals(role.getText(),"Krishna Saahi");
        System.out.println(role.getText());

        wait.until(ExpectedConditions.textToBePresentInElement(role, "Machine Learning Developer"));
        Assert.assertEquals(role.getText(),"Machine Learning Developer");
        System.out.println(role.getText());

        System.out.println(wait.until(ExpectedConditions.textToBePresentInElementLocated(By.cssSelector(".index-module_type__E-SaG"), "Web Developer")));

        //Fluent wait
        Wait<WebDriver> fluentWait = new FluentWait<WebDriver>(driver).withTimeout(Duration.ofSeconds(1)).ignoring(NoSuchElementException.class);
        fluentWait.until(ExpectedConditions.elementToBeClickable(By.linkText("About"))).click();
        fluentWait.until(ExpectedConditions.elementToBeClickable(By.xpath("//p[text()='QA']//ancestor::button"))).click();
        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    @AfterClass
    public void tearDown() {
        driver.quit();
    }
}
