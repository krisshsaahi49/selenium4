import io.github.bonigarcia.wdm.WebDriverManager;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.safari.SafariDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.io.File;
import java.io.IOException;
import java.util.Set;

public class BrowserActions {

    private static WebDriver driver;
    private String URL = "https://www.krisshsaahi.dev";
    private String SMC = "https://smc-booking-pfw.netlify.app/";

    private String LIFESOURCE = "https://lifesource.netlify.app/";

    @BeforeClass
    public void setUp() {
        WebDriverManager.chromedriver().setup();
//        WebDriverManager.safaridriver().setup();;
        driver = new ChromeDriver();
//        driver = new SafariDriver();
        driver.manage().window().maximize();
    }

    @Test   // handle page open, forward, backward and refresh
    public void openWebPage() throws InterruptedException {
        driver.get(URL);
        System.out.println(driver.getTitle()+" : "+driver.getCurrentUrl());
        Thread.sleep(2000);
        driver.navigate().to(SMC);
        driver.navigate().back();
        Thread.sleep(2000);
        driver.navigate().forward();
        Thread.sleep(2000);
        driver.navigate().refresh();
        Thread.sleep(2000);
        System.out.println(driver.getTitle()+" : "+driver.getCurrentUrl());
    }

    @Test   // switch between two windows and naviagte back to original window
    public void windowHandling() throws InterruptedException {
        driver.navigate().to(URL);
        System.out.println(driver.getTitle()+" : "+driver.getCurrentUrl());
        String originalWindow = driver.getWindowHandle();
        driver.switchTo().newWindow(WindowType.WINDOW);
        Thread.sleep(2000);
        driver.navigate().to(SMC);
        System.out.println(driver.getTitle()+" : "+driver.getCurrentUrl());
        Thread.sleep(2000);
        driver.switchTo().window(originalWindow);
        System.out.println(driver.getTitle()+" : "+driver.getCurrentUrl());
        Thread.sleep(2000);
    }

    @Test   // open 3 tabs and switch to each tab
    public void tabHandling() throws InterruptedException {
        // TAB 1
        driver.navigate().to(URL);
        String orginalTab = driver.getWindowHandle();
        Thread.sleep(2000);
        System.out.println("TAB1 : "+driver.getTitle());

        // TAB 2
        System.out.println("Opening tab 2 ........");
        driver.switchTo().newWindow(WindowType.TAB);
        driver.get(SMC);
        String smcTab = driver.getWindowHandle();
        Thread.sleep(2000);
        System.out.println("TAB2 : "+driver.getTitle());

        // TAB 3
        System.out.println("Opening tab 3 ...... ");
        driver.switchTo().newWindow(WindowType.TAB);
        driver.get(LIFESOURCE);
        String lifesourceTab = driver.getWindowHandle();
        Thread.sleep(2000);
        System.out.println("TAB3 : "+driver.getTitle());

        // switch back to tab 1
        driver.switchTo().window(orginalTab);
        Thread.sleep(2000);
        System.out.println(driver.getTitle());
        Assert.assertEquals("Home | krisshsaahi", driver.getTitle());

        // switch back to tab 3
        driver.switchTo().window(lifesourceTab);
        Thread.sleep(2000);
        System.out.println(driver.getTitle());
        Assert.assertEquals("Life Source", driver.getTitle());

        // switch back to tab 2
        driver.switchTo().window(smcTab);
        Thread.sleep(2000);
        System.out.println(driver.getTitle());
        Assert.assertEquals("PFWSMC", driver.getTitle());
    }

    @Test   // navigate between tabs without knowing tab handles
    public void tabHandlewithoutWindowHandle() throws InterruptedException {
        // TAB 1
        driver.navigate().to(URL);
        String orginalTab = driver.getWindowHandle();
        Thread.sleep(2000);
        System.out.println("TAB1 : "+driver.getTitle());

        // TAB 2
        System.out.println("Opening tab 2 ........");
        driver.switchTo().newWindow(WindowType.TAB);
        driver.get(SMC);
        String smcTab = driver.getWindowHandle();
        Thread.sleep(2000);
        System.out.println("TAB2 : "+driver.getTitle());

        // TAB 3
        System.out.println("Opening tab 3 ...... ");
        driver.switchTo().newWindow(WindowType.TAB);
        driver.get(LIFESOURCE);
        String lifesourceTab = driver.getWindowHandle();
        Thread.sleep(2000);
        System.out.println("TAB3 : "+driver.getTitle());

        Set<String> tabHandles = driver.getWindowHandles();
        for (String a : tabHandles
             ) {
            driver.switchTo().window(a);
            System.out.println("TAB id : "+a);
            Thread.sleep(2000);
            if(driver.getTitle().equals("Life Source")){
                System.out.println("Switched to TAB 2 i.e, PFW SMC");
                Thread.sleep(5000);
                break;
            }
        }
    }

    @Test
    public void iframe() throws InterruptedException {
        String url = "https://relume-cloneable.webflow.io/";
        driver.get(url);
        Thread.sleep(10000);
        WebElement frameEle = driver.findElement(By.cssSelector("div.figma-embed.w-embed.w-iframe:nth-of-type(2) iframe"));
//        driver.switchTo().frame(0);
        driver.switchTo().frame(frameEle);
        driver.findElement(By.xpath("//span[contains(@class,'svg-container mobile_viewer_app--fullscreenIcon--e8320')]")).click();
        Thread.sleep(2000);
    }

    @Test
    public void windowManagement() throws InterruptedException {
        driver.navigate().to(URL);
        int h = driver.manage().window().getSize().height;
        int w = driver.manage().window().getSize().width;
        System.out.println("Height : "+h+" || "+"Width : "+w);

        driver.manage().window().setSize(new Dimension(500, 500));
        Thread.sleep(2000);

        driver.manage().window().setSize(new Dimension(800, 800));
        Thread.sleep(2000);

        driver.manage().window().fullscreen();
        Thread.sleep(2000);

        driver.manage().window().setSize(new Dimension(700, 700));
        Thread.sleep(2000);

        int x = driver.manage().window().getPosition().x;
        int y = driver.manage().window().getPosition().y;
        System.out.println("Position - X: "+x+" ||  Y: "+y);

        driver.manage().window().setPosition(new Point(570, 570));
        Thread.sleep(3000);
    }

    @Test
    public void getScreenshot() throws IOException {
        driver.navigate().to(URL);
        File file = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
        FileUtils.copyFile(file,new File("./src/test/resources/image.png"));

        // partial screenshot
        WebElement ele = driver.findElement(By.cssSelector("div.grid"));
        File partialFile = ele.getScreenshotAs(OutputType.FILE);
        FileUtils.copyFile(partialFile, new File("./src/test/resources/partialImg.png"));
    }

    @AfterClass
    public void tearDown() {
        driver.quit();
    }

}
