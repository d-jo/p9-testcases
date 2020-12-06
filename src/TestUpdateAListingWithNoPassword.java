package edu.uno.tests.case3;

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
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

public class TestUpdateAListingWithNoPassword {
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
  public void testUpdateAListingWithNoPassword() throws Exception {
	  driver.get("http://localhost:7000/");
	    driver.findElement(By.linkText("Create")).click();
	    driver.findElement(By.id("title")).click();
	    driver.findElement(By.id("title")).clear();
	    driver.findElement(By.id("title")).sendKeys("Operating Systems");
	    driver.findElement(By.id("seller_name")).clear();
	    driver.findElement(By.id("seller_name")).sendKeys("Carly Cameron");
	    driver.findElement(By.id("description")).clear();
	    driver.findElement(By.id("description")).sendKeys("Minix 3 design and implementation");
	    driver.findElement(By.id("isbn")).clear();
	    driver.findElement(By.id("isbn")).sendKeys("938-0-12-801733-3");
	    driver.findElement(By.id("price")).clear();
	    driver.findElement(By.id("price")).sendKeys("112.30");
	    driver.findElement(By.id("category")).click();
	    new Select(driver.findElement(By.id("category"))).selectByVisibleText("Computer Science");
	    driver.findElement(By.id("category")).click();
	    driver.findElement(By.id("listing_password")).click();
	    driver.findElement(By.id("listing_password")).clear();
	    driver.findElement(By.id("listing_password")).sendKeys("listingpassword");
	    driver.findElement(By.id("confirm_listing_password")).clear();
	    driver.findElement(By.id("confirm_listing_password")).sendKeys("listingpassword");
	    driver.findElement(By.xpath("//button[@onclick='submit_form()']")).click();
	    driver.findElement(By.linkText("Update Details of this Listing")).click();
	    
	    driver.findElement(By.xpath("//button[@onclick='update_form()']")).click();
	    WebDriverWait wait = new WebDriverWait(driver, 10); 
	    wait.until(ExpectedConditions.alertIsPresent());
	    assertEquals("Wrong password. Please try again.", closeAlertAndGetItsText());
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
