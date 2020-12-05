import java.util.regex.Pattern;
import java.io.File;
import java.util.concurrent.TimeUnit;
import org.junit.*;
import static org.junit.Assert.*;
import static org.hamcrest.CoreMatchers.*;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.Select;

public class TestPurchaseListingValid {
  private WebDriver driver;
  private String baseUrl;
  private boolean acceptNextAlert = true;
  private StringBuffer verificationErrors = new StringBuffer();
  @Before
  public void setUp() throws Exception {
	ChromeOptions options = new ChromeOptions();
	
	String os = System.getProperty("os.name").toLowerCase();
	System.err.println(os);
	if (os.contains("windows")) {
		System.setProperty("webdriver.chrome.driver", "lib/win/chromedriver.exe");
	} else if (os.contains("linux")){
		System.setProperty("webdriver.chrome.driver", "lib/linux/chromedriver");
		options.setBinary(new File("/usr/bin/chromium-browser"));
	} else {
		System.setProperty("webdriver.chrome.driver", "lib/mac/chromedriver");
	}
	 
    options.addArguments("start-maximized");
	driver = new ChromeDriver(options);
	
    baseUrl = "https://www.google.com/";
    driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
  }

  @Test
  public void testPurchaseLisitingValidInput() throws Exception {
    driver.get("http://localhost:7000/");
    Thread.sleep(200);

    driver.findElement(By.linkText("Create")).click();
    Thread.sleep(200);

    driver.findElement(By.id("title")).click();
    driver.findElement(By.id("title")).clear();
    driver.findElement(By.id("title")).sendKeys("Computer Neworking");
    Thread.sleep(200);

    driver.findElement(By.id("seller_name")).clear();
    driver.findElement(By.id("seller_name")).sendKeys("Shihang");
    Thread.sleep(200);

    driver.findElement(By.id("description")).clear();
    driver.findElement(By.id("description")).sendKeys("Good condition. By Kurose and Ross");
    Thread.sleep(200);

    driver.findElement(By.id("isbn")).click();
    driver.findElement(By.id("isbn")).clear();
    driver.findElement(By.id("isbn")).sendKeys("0133594149");
    Thread.sleep(200);

    driver.findElement(By.id("price")).clear();
    driver.findElement(By.id("price")).sendKeys("60");
    Thread.sleep(200);

    driver.findElement(By.id("category")).click();
    new Select(driver.findElement(By.id("category"))).selectByVisibleText("Computer Science");
    driver.findElement(By.id("category")).click();
    driver.findElement(By.id("listing_password")).click();
    driver.findElement(By.id("listing_password")).clear();
    driver.findElement(By.id("listing_password")).sendKeys("passwordpassword123");
    driver.findElement(By.id("confirm_listing_password")).click();
    driver.findElement(By.id("confirm_listing_password")).clear();
    driver.findElement(By.id("confirm_listing_password")).sendKeys("passwordpassword123");
    Thread.sleep(200);

    driver.findElement(By.xpath("//button[@onclick='submit_form()']")).click();
    Thread.sleep(400);

    driver.findElement(By.id("buyer_name")).click();
    driver.findElement(By.id("buyer_name")).clear();
    driver.findElement(By.id("buyer_name")).sendKeys("Peter Pan");
    Thread.sleep(400);

    driver.findElement(By.id("buyer_billing")).clear();
    driver.findElement(By.id("buyer_billing")).sendKeys("Omaha, NE");
    Thread.sleep(200);

    driver.findElement(By.id("buyer_shipping")).click();
    driver.findElement(By.id("buyer_shipping")).clear();
    driver.findElement(By.id("buyer_shipping")).sendKeys("Omaha, NE");
    Thread.sleep(500);

    driver.findElement(By.id("purchase-btn")).click();
    Thread.sleep(500);

    assertEquals("The listing has been purchased", closeAlertAndGetItsText());
    Thread.sleep(500);

  }

  @After
  public void tearDown() throws Exception {
    driver.quit();
    String verificationErrorString = verificationErrors.toString();
    if (!"".equals(verificationErrorString)) {
      fail(verificationErrorString);
    }
  }

  private String closeAlertAndGetItsText() {
    try {
      Alert alert = driver.switchTo().alert();
      String alertText = alert.getText();
      if (acceptNextAlert) {
        alert.accept();
      } else {
        alert.dismiss();
      }
      return alertText;
    } finally {
      acceptNextAlert = true;
    }
  }
}
