import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.PageLoadStrategy;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class PageLoadingStrategy {

    public static WebDriver driver;
    private String URL = "https://www.krisshsaahi.dev";

    @BeforeClass
    public void setUp() {
        WebDriverManager.chromedriver().setup();
        ChromeOptions options = new ChromeOptions();
//        options.setPageLoadStrategy(PageLoadStrategy.NORMAL);
        options.setPageLoadStrategy(PageLoadStrategy.EAGER);
//        options.setPageLoadStrategy(PageLoadStrategy.NONE);
        driver = new ChromeDriver();
        driver.manage().window().maximize();
    }

    @Test
    public void pageLoadingStrategy(){
        driver.get(URL);
        WebElement role = driver.findElement(By.cssSelector(".index-module_type__E-SaG"));
        System.out.println(role.getText());
    }

    @AfterClass
    public void tearDown() {
        driver.quit();
    }
}
