package selenium.automation.test;


import static org.junit.Assert.fail;

import java.util.NoSuchElementException;
import java.util.concurrent.TimeUnit;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.NoAlertPresentException;
import org.openqa.selenium.phantomjs.PhantomJSDriver;
import org.openqa.selenium.remote.DesiredCapabilities;

public class PetClinicTest {
  //private WebDriver driver;
  private PhantomJSDriver driver;
  private String baseUrl;
  private boolean acceptNextAlert = true;
  private StringBuffer verificationErrors = new StringBuffer();

  @Before
  public void setUp() throws Exception {
    //driver = new ChromeDriver();
	DesiredCapabilities capabilities = DesiredCapabilities.phantomjs();
    driver = new PhantomJSDriver(capabilities);
    baseUrl = "http://10.10.10.10";
    driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
  }

  @Test
  public void testPetClinic() throws Exception {
    driver.get(baseUrl + "/petclinic/");
    driver.findElement(By.linkText("Find owners")).click();
    driver.findElement(By.linkText("Add Owner")).click();
    driver.findElement(By.id("firstName")).clear();
    driver.findElement(By.id("firstName")).sendKeys("siva");
    driver.findElement(By.id("lastName")).clear();
    driver.findElement(By.id("lastName")).sendKeys("chedde");
    driver.findElement(By.id("address")).clear();
    driver.findElement(By.id("address")).sendKeys("electronic city");
    driver.findElement(By.id("city")).clear();
    driver.findElement(By.id("city")).sendKeys("bangalore");
    driver.findElement(By.id("telephone")).clear();
    driver.findElement(By.id("telephone")).sendKeys("99999");
    driver.findElement(By.cssSelector("button[type=\"submit\"]")).click();
    driver.findElement(By.linkText("Find owners")).click();
    driver.findElement(By.name("lastName")).clear();
    driver.findElement(By.name("lastName")).sendKeys("chedde");
    driver.findElement(By.cssSelector("button[type=\"submit\"]")).click();
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
