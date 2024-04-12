import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WindowType;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;


public class Navigate {
    public static WebDriver driver;

    @BeforeClass
    public void setUp(){
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
    }

    @Test
    public void navigate(){
        String url = "https://lifesource.netlify.app/";
        driver.manage().window().maximize();
        driver.navigate().to(url);
        System.out.println(driver.getTitle());
        Assert.assertEquals(driver.getTitle(),"Life Source");
        driver.switchTo().newWindow(WindowType.TAB);
        driver.get("https://bing.com");
        driver.switchTo().newWindow(WindowType.WINDOW);
        driver.get("https://www.krisshsaahi.dev/");
        try {
            Thread.sleep(10000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    @AfterClass
    public void tearDown(){
        driver.quit();
    }

}
