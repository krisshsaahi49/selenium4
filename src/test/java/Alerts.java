import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.Wait;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.time.Duration;

public class Alerts {

    private static WebDriver driver;
    private String URL = "https://the-internet.herokuapp.com/javascript_alerts";

    @BeforeClass
    public void setUp() {
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
    }

    @Test
    public void alerts() throws InterruptedException {
        driver.get(URL);
        driver.findElement(By.cssSelector("button[onclick='jsAlert()']")).click();
        Thread.sleep(2000);
        Wait<WebDriver> fluentWait = new FluentWait<>(driver).withTimeout(Duration.ofSeconds(10)).ignoring(NoSuchElementException.class);
        System.out.println(fluentWait.until(ExpectedConditions.alertIsPresent()).getText());
        fluentWait.until(ExpectedConditions.alertIsPresent()).accept();


        driver.findElement(By.cssSelector("button[onclick='jsConfirm()']")).click();
        Thread.sleep(2000);
        Alert alert2 = driver.switchTo().alert();
        alert2 = fluentWait.until(ExpectedConditions.alertIsPresent());
        System.out.println(alert2.getText());
        alert2.dismiss();

        driver.findElement(By.cssSelector("button[onclick='jsPrompt()']")).click();
        Thread.sleep(2000);
        Alert alert3 = driver.switchTo().alert();
        alert3 = fluentWait.until(ExpectedConditions.alertIsPresent());
        alert3.sendKeys("Krisshsaahi");
        Thread.sleep(3000);
        alert3.accept();
        System.out.println(driver.findElement(By.id("result")).getText());
    }

    @AfterClass
    public void tearDown() {
        driver.quit();
    }
}
