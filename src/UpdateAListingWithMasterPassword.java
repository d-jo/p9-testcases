package edu.uno.tests.case3;

import java.util.regex.Pattern;
import java.util.concurrent.TimeUnit;
import org.junit.*;
import static org.junit.Assert.*;
import static org.hamcrest.CoreMatchers.*;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

public class UpdateAListingWithMasterPassword {
  private WebDriver driver;
  private String baseUrl;
  private boolean acceptNextAlert = true;
  private StringBuffer verificationErrors = new StringBuffer();

  @Before
  public void setUp() throws Exception {
	  System.setProperty("webdriver.chrome.driver", "C:\\Users\\carly\\Downloads\\project_webapp_testing\\project_webapp_testing\\lib\\win\\chromedriver.exe");
      driver = new ChromeDriver(); // FirefoxDriver();
      baseUrl = "http://localhost:7000/listing/9";
      driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
  }

  @Test
  public void testUpdateAListingWithMasterPassword() throws Exception {
    driver.get("http://localhost:7000/listing/9");
    driver.findElement(By.linkText("Update Details of this Listing")).click();
    driver.findElement(By.id("listing_password")).click();
    driver.findElement(By.id("title")).click();
    driver.findElement(By.id("title")).clear();
    driver.findElement(By.id("title")).sendKeys("Operating Systems Updated with master");
    driver.findElement(By.id("listing_password")).click();
    driver.findElement(By.id("listing_password")).clear();
    driver.findElement(By.id("listing_password")).sendKeys("masterpassword");
    driver.findElement(By.xpath("//button[@onclick='update_form()']")).click();
    WebDriverWait wait = new WebDriverWait(driver, 10); 
    wait.until(ExpectedConditions.alertIsPresent());
    
    assertEquals("You have successfully updated your listing.", closeAlertAndGetItsText());
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
