package com.company;

import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.safari.SafariDriver;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.nio.file.Paths;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.WebDriverWait;

public class BasicSeleniumTestNGTest {

  /**
   * Sets up the Chrome driver.
   */
  public static void initChromeDriver() {
    // Proper version of chromedriver can be downloaded from here:
    // https://chromedriver.chromium.org
    final String pathToDriver = Paths.get("chromedriver").toAbsolutePath().toString();
    System.setProperty("webdriver.chrome.driver", pathToDriver);
  }

  /**
   * Sets up the Firefox driver.
   */
  public static void initFirefoxDriver() {
    // Proper version of geckodriver can be downloaded from here:
    // https://github.com/mozilla/geckodriver/releases
    final String pathToGeckoDriver = Paths.get("geckodriver").toAbsolutePath().toString();
    System.setProperty("webdriver.gecko.driver", pathToGeckoDriver);
  }

  @BeforeClass
  public static void setUpClass() throws Exception {
    initChromeDriver();
  }

  @Test
  public void testMain() throws Exception {
    testGeneric(new SafariDriver());
  }

  @Test
  public void testMain1() throws Exception {
    testGeneric(new ChromeDriver());
  }

  public void testGeneric(final WebDriver driver) throws Exception {
    // Create a new instance of the Firefox driver
    // Notice that the remainder of the code relies on the interface,
    // not the implementation.


    // And now use this to visit Google
    driver.get("http://www.google.com");
    // Alternatively the same thing can be done like this
    // driver.navigate().to("http://www.google.com");

    // Find the text input element by its name
    final WebElement element = driver.findElement(By.name("q"));

    // Enter something to search for
    element.sendKeys("Cheese!");

    // Now submit the form. WebDriver will find the form for us from the element
    element.submit();

    // Check the title of the page
    System.out.println("Page title is: " + driver.getTitle());

    // Google's search is rendered dynamically with JavaScript.
    // Wait for the page to load, timeout after 10 seconds
    (new WebDriverWait(driver, 10)).until(new ExpectedCondition<Boolean>() {
      public Boolean apply(WebDriver d) {
        return d.getTitle().toLowerCase().startsWith("cheese!");
      }
    });

    // Should see: "cheese! - Google Search"
    System.out.println("Page title is: " + driver.getTitle());

    //Close the browser
    driver.quit();
  }

}
