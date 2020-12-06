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

public class TestPurchaseListingInvalidInput {
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
  public void testPurchaseListingInvalidInput() throws Exception {
    driver.get("http://localhost:7000/");
    driver.findElement(By.linkText("Create")).click();
    driver.findElement(By.id("title")).click();
    driver.findElement(By.id("title")).clear();
    driver.findElement(By.id("title")).sendKeys("LIST_VALID");
    driver.findElement(By.id("seller_name")).clear();
    driver.findElement(By.id("seller_name")).sendKeys("valid");
    driver.findElement(By.id("description")).clear();
    driver.findElement(By.id("description")).sendKeys("valid test description good condition author name");
    driver.findElement(By.id("isbn")).click();
    driver.findElement(By.id("isbn")).clear();
    driver.findElement(By.id("isbn")).sendKeys("12345678901");
    driver.findElement(By.id("price")).click();
    driver.findElement(By.id("price")).clear();
    driver.findElement(By.id("price")).sendKeys("32");
    driver.findElement(By.id("listing_password")).click();
    driver.findElement(By.id("listing_password")).clear();
    driver.findElement(By.id("listing_password")).sendKeys("1234567890123");
    driver.findElement(By.id("confirm_listing_password")).click();
    driver.findElement(By.id("confirm_listing_password")).clear();
    driver.findElement(By.id("confirm_listing_password")).sendKeys("1234567890123");
    driver.findElement(By.xpath("//button[@onclick='submit_form()']")).click();
    driver.findElement(By.id("buyer_name")).click();
    driver.findElement(By.id("price-div")).click();
    driver.findElement(By.id("purchase-btn")).click();
    assertEquals("Error: Name must be at least 2 characters\nBilling info must be at least 8 characters\nShipping must be at least 8 characters\n", closeAlertAndGetItsText());
  }

  @After
  public void tearDown() throws Exception {
    driver.quit();
    String verificationErrorString = verificationErrors.toString();
    if (!"".equals(verificationErrorString)) {
      fail(verificationErrorString);
    }
  }

  private boolean isElementPresent(By by) {
    try {
      driver.findElement(by);
      return true;
    } catch (NoSuchElementException e) {
      return false;
    }
  }

  private boolean isAlertPresent() {
    try {
      driver.switchTo().alert();
      return true;
    } catch (NoAlertPresentException e) {
      return false;
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
