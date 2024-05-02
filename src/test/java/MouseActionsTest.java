import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.interactions.WheelInput;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.time.Duration;

public class MouseActionsTest {

    private static WebDriver driver;

    private String URL = "https://www.krisshsaahi.dev";
    private String SMC = "https://smc-booking-pfw.netlify.app/";
    private String LIFESOURCE = "https://lifesource.netlify.app/";
    private String testURL = "https://selenium08.blogspot.com/2020/01/drag-drop.html";

    @BeforeClass
    public void setUp() {
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        driver.manage().window().maximize();
    }

    @Test
    public void mouseActions() throws InterruptedException {

        driver.get(testURL);
        Actions actions = new Actions(driver);
        // Drag and Drop
        WebElement drag = driver.findElement(By.id("draggable"));
        WebElement drop = driver.findElement(By.id("droppable"));
        actions.dragAndDrop(drag,drop).build().perform();
        Thread.sleep(5000);

    }

    @Test
    public void mouseClickAndHold() throws InterruptedException {

        driver.get("https://selenium08.blogspot.com/2020/01/click-and-hold.html");
        Actions actions = new Actions(driver);
        //Click and Hold
        WebElement drag = driver.findElement(By.name("B"));
        WebElement drop = driver.findElement(By.name("L"));
        actions.moveToElement(drag).pause(Duration.ofSeconds(2)).clickAndHold().moveToElement(drop).perform();
        Thread.sleep(5000);
    }

    @Test
    public void mouseScroll(){
        driver.get(URL);
        Actions actions = new Actions(driver);

    }

    @AfterClass
    public void tearDown() {
        driver.quit();
    }
}
