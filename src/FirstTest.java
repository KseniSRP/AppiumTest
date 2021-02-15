import io.appium.java_client.AppiumDriver;
import io.appium.java_client.android.AndroidDriver;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.net.URL;

public class FirstTest {
    private AppiumDriver driver;

    @Before
    public void setUp() throws Exception {
        DesiredCapabilities capabilities = new DesiredCapabilities();
        capabilities.setCapability("platformName", "Android");
        capabilities.setCapability("deviceName", "AndroidTestDevice");
        capabilities.setCapability("platformVersion", "8.0");
        capabilities.setCapability("automationName", "Appium");
        capabilities.setCapability("appPackage", "org.wikipedia");
        capabilities.setCapability("appActivity", ".main.MainActivity");
        capabilities.setCapability("app", "/Users/ksenia/Desktop/AppiumAutomation/apks/org.wikipedia.apk");

        driver = new AndroidDriver(new URL("http://127.0.0.1:4723/wd/hub"), capabilities);

    }


    @After
    public void tearDown() {
        driver.quit();
    }

   @Test
    public void firstTest()
    {
        waitForElementAndClick(
                By.xpath("//*[contains(@text,'Search Wikipedia')]"),
                "Cannot find Search Wikipedia",
                5);
        waitForElementAndSendKeys(
                By.xpath("//*[contains(@text,'Search…')]"),
                "Java",
                "Cannot find Search input",
                5);
        waitForElementPresent(
                By.xpath("//*[@resource-id='org.wikipedia:id/page_list_item_container']//*[@text='Object-oriented programming language']"),
                "Cannot find Object-oreinted programming language in topic Java",
                15);
    }


    @Test
    public void testCancelSearch() {
        waitForElementAndClick(
                By.id("org.wikipedia:id/search_container"),
                "Cannot find Search Wikipedia",
                5);
        waitForElementAndClick(
                By.id("org.wikipedia:id/search_close_btn"),
                "Cannot find X to cancel search",
                5
        );

        waitForElementNotPresent(
                By.id("org.wikipedia:id/search_close_btn"),
                "X is still present of the page",
                5
        );

    }


    @Test
    public void  TestAssertElementHasText() {
        assertElementHasText(
                By.xpath("//*[contains(@text,'Search Wikipedia')]"),
                "Search Wikipedia",
                "The text 'Search Wikipedia' was not found in the search bar",
                10);
    }

    @Test
    public void  TestSearchAndCancel() {
          waitForElementAndClick(
                  By.id("org.wikipedia:id/search_container"),
                  "Cannot find Search Wikipedia",
                  5);

          waitForElementAndSendKeys(
                  By.id("org.wikipedia:id/search_container"),
                  "Russia",
                  "Cannot find Search input",
                  5);

          waitForElementPresent(
                  By.id("org.wikipedia:id/page_list_item_container"),
                  "No search results",
                  15);

          waitForElementAndClick(
                  By.id("org.wikipedia:id/search_close_btn"),
                  "Cannot find X to cancel search",
                  5);

          waitForElementAndClick(
                  By.id("org.wikipedia:id/search_container"),
                  "Cannot find Search Wikipedia",
                  5);


    }

 @Test
 public void  TestSearchJava() {
     waitForElementAndClick(
             By.id("org.wikipedia:id/search_container"),
             "Cannot find Search Wikipedia",
             5);
     waitForElementAndSendKeys(
             By.id("org.wikipedia:id/search_container"),
             "Java",
             "Cannot find Search input",
             15);

    WebElement result =  waitForElementPresent(
            By.xpath("//*[@resource-id='org.wikipedia:id/page_list_item_container']//*[@text='Java']"),
            "No search results",
            15);

    String result_text = result.getText();

    Assert.assertEquals("Not all search results contain the word 'Java'",
                        "Java",result_text);

 }




    private WebElement waitForElementPresent(By by, String error_message, long timeoutInSeconds) {
        WebDriverWait wait = new WebDriverWait(driver, timeoutInSeconds);
        wait.withMessage(error_message + "\n");
        return wait.until(ExpectedConditions.presenceOfElementLocated(by));
    }

    private WebElement waitForElementPresent(By by, String error_message) {
        return waitForElementPresent(by, error_message, 5);
    }

    private WebElement waitForElementAndClick(By by, String error_message, long timeoutInSeconds) {
        WebElement element = waitForElementPresent(by, error_message, 5);
        element.click();
        return element;
    }

    private WebElement waitForElementAndSendKeys(By by, String value, String error_message, long timeoutInSeconds) {
        WebElement element = waitForElementPresent(by, error_message, 5);
        element.sendKeys(value);
        return element;
    }



    private boolean waitForElementNotPresent(By by, String error_message, long timeoutInSeconds) {
        WebDriverWait wait = new WebDriverWait(driver, timeoutInSeconds);
        wait.withMessage(error_message + "\n");
        return wait.until(
                ExpectedConditions.invisibilityOfElementLocated(by)
        );
    }




    private String assertElementHasText(By by, String text, String error_message, long timeoutInSeconds)
    {
        WebElement actual_result = waitForElementPresent(
                by,
                error_message,
                5);
        String actual_text = actual_result.getAttribute("text");
        String expected_text = text;
        Assert.assertEquals("Ожидаемый результат не совпадает с текущим", expected_text, actual_text);
        return expected_text;
    }

    private String assertElementTrue(By by, String text, String error_message, long timeoutInSeconds)
    {
        WebElement actual_result = waitForElementPresent(
                by,
                error_message,
                5);
        String actual_text = actual_result.getAttribute("text");
        String expected_text = text;
        Assert.assertTrue(expected_text==actual_text);
        return expected_text;
    }


}
