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

public class TestClickSingleListing {
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
  public void testClickSingleListing() throws Exception {
    driver.get("http://localhost:7000/");
    Thread.sleep(200);

    driver.findElement(By.linkText("Introduction to Algorithms")).click();
    Thread.sleep(200);

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
