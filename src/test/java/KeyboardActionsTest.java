import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Action;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.time.Duration;

public class KeyboardActionsTest {
    private static WebDriver driver;

    private String URL = "https://www.krisshsaahi.dev";
    private String SMC = "https://smc-booking-pfw.netlify.app/";
    private String LIFESOURCE = "https://lifesource.netlify.app/";
    private String testURL = "https://trytestingthis.netlify.app/";

    @BeforeClass
    public void setUp() {
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        driver.manage().window().maximize();
    }

    @Test
    public void keyBoard1() throws InterruptedException {
        driver.get(URL);
        Actions actions = new Actions(driver);
        WebElement sendMessage = driver.findElement(By.cssSelector("form > div:nth-of-type(3) + button"));
        WebElement subject = driver.findElement(By.cssSelector("#subject"));
        WebElement message = driver.findElement(By.cssSelector("#message"));
        WebElement name = driver.findElement(By.cssSelector("input[placeholder*='your_mail']"));
        Action action = actions.scrollToElement(sendMessage).moveToElement(name).click().keyDown(Keys.SHIFT)
                .sendKeys("krishna").keyUp(Keys.SHIFT).sendKeys("saahi@google.com").moveToElement(subject).click().sendKeys("test").build();
        action.perform();
        subject.clear();
        actions.sendKeys(subject, "Subject").moveToElement(message).click().sendKeys("This is a test message").moveToElement(sendMessage).click().perform();
        WebDriverWait wait =new WebDriverWait(driver, Duration.ofSeconds(5));
        WebElement emailConfirmation = driver.findElement(By.xpath("//p[contains(text(),'Email sent successfully!')]"));
        wait.until(ExpectedConditions.textToBePresentInElement(emailConfirmation,"Email sent successfully!"));
        Assert.assertEquals(emailConfirmation.getText(),"Email sent successfully!");
    }

    @Test
    public void otherKeyboardActions() {
        driver.get(URL);
        Actions actions = new Actions(driver);

    }

    @AfterClass
    public void tearDown() {
        driver.quit();
    }
}
