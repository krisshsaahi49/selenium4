import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.net.MalformedURLException;
import java.util.List;

public class WebElementsTest {
    private static WebDriver driver;

    private String URL = "https://www.krisshsaahi.dev";
    private String SMC = "https://smc-booking-pfw.netlify.app/";
    private String LIFESOURCE = "https://lifesource.netlify.app/";
    private String testURL = "https://trytestingthis.netlify.app/";

    @BeforeClass
    public void setUp() throws MalformedURLException {
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        driver.manage().window().maximize();
    }

    @Test
    public void seleniumWebElement(){
        driver.get(URL);
        WebElement HireMe = driver.findElement(By.cssSelector("a[href='/#contact']"));
        HireMe.click();
    }

    @Test
    public void seleniumFindElements(){
        driver.get(testURL);
        List<WebElement> elements = driver.findElements(By.tagName("select"));
        elements.forEach(e -> System.out.println(e.getText()));
    }

    @Test
    public void eleWithinElement(){
        driver.get(testURL);
        WebElement fieldSet = driver.findElement(By.xpath("(//fieldset)[2]"));
        WebElement scroll = fieldSet.findElement(By.id("a"));
        System.out.println(scroll.getAttribute("value").toString());
    }

    @Test
    public void handlingScrollAndActiveElement  (){
        driver.get(testURL);
        WebElement scroll = driver.findElement(By.id("a"));
        int inputValue = Integer.parseInt(scroll.getAttribute("value"));
        System.out.println(inputValue);
        // increse the scroll to 75
        scroll.click();
        int size = 100;
        for(int i =1; i<=size-inputValue; i++ ){
            scroll.sendKeys(Keys.ARROW_RIGHT);
        }
        System.out.println(driver.switchTo().activeElement().getAttribute("value").toString());

    }

    @AfterClass
    public void tearDown() {
        driver.quit();
    }
}
