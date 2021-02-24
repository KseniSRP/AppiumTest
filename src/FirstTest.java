import io.appium.java_client.AppiumDriver;
import io.appium.java_client.TouchAction;
import io.appium.java_client.android.AndroidDriver;
import org.apache.bcel.verifier.exc.AssertionViolatedException;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.ScreenOrientation;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.net.URL;
import java.util.List;

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
        driver.rotate(ScreenOrientation.PORTRAIT);

    }


    @After
    public void tearDown() {
        driver.quit();
    }

    @Test
    public void firstTest() {
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

        waitForElementAndSendKeys(
                By.xpath("//*[contains(@text,'Search…')]"),
                "Java",
                "Cannot find Search input",
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
    public void TestAssertElementHasText() {
        assertElementHasText(
                By.xpath("//*[contains(@text,'Search Wikipedia')]"),
                "Search Wikipedia",
                "The text 'Search Wikipedia' was not found in the search bar",
                10);
    }

    @Test
    public void TestSearchAndCancel() {
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
    public void TestSearchJava() {
        waitForElementAndClick(
                By.id("org.wikipedia:id/search_container"),
                "Cannot find Search Wikipedia",
                5);
        waitForElementAndSendKeys(
                By.id("org.wikipedia:id/search_container"),
                "Java",
                "Cannot find Search input",
                15);

        WebElement result = waitForElementPresent(
                By.xpath("//*[@resource-id='org.wikipedia:id/page_list_item_container']//*[@text='Java']"),
                "No search results",
                15);

        String result_text = result.getText();


        Assert.assertEquals("Not all search results contain the word 'Java'",
                "Java", result_text);

    }


    @Test
    public void testSwipeArticle() {
        waitForElementAndClick(
                By.xpath("//*[contains(@text,'Search Wikipedia')]"),
                "Cannot find Search Wikipedia",
                5);

        waitForElementAndSendKeys(
                By.xpath("//*[contains(@text,'Search…')]"),
                "Java",
                "Cannot find Search input",
                5);

        waitForElementAndClick(
                By.xpath("//*[@resource-id='org.wikipedia:id/page_list_item_container']//*[@text='Object-oriented programming language']"),
                "Cannot find Object-oreinted programming language in topic Java",
                15);

        waitForElementPresent(
                By.id("org.wikipedia:id/view_page_title_text"),
                "Can not article title");

        swipeUp(2000);
        swipeUp(2000);
        swipeUp(2000);
        swipeUp(2000);
        swipeUp(2000);


    }

    @Test
    public void testSwipeQuickArticle() {
        waitForElementAndClick(
                By.xpath("//*[contains(@text,'Search Wikipedia')]"),
                "Cannot find Search Wikipedia",
                5);

        waitForElementAndSendKeys(
                By.xpath("//*[contains(@text,'Search…')]"),
                "Appium",
                "Cannot find Search input",
                10);


        waitForElementAndClick(
                By.xpath("//*[@resource-id='org.wikipedia:id/page_list_item_title'][@text='Appium']"),
                "Cannot find title Appium",
                10);

        waitForElementPresent(
                By.id("org.wikipedia:id/view_page_title_text"),
                "Cannot article title");

        swipeUpToFindElement(
                By.xpath("//*[@text='View page in browser']"),
                "Cannot find the end of the article",
                20);


    }


    @Test

    public void saveFirstArticleToMyList() {
        waitForElementAndClick(
                By.xpath(("//*[contains(@text, 'Search Wikipedia')]")),
                "Cannot find 'Search Wikipedia' input'",
                5
        );

        waitForElementAndSendKeys(
                By.xpath("//*[contains(@text,'Search…')]"),
                "Appium",
                "Cannot find Search input",
                10
        );


        waitForElementAndClick(
                By.xpath("//*[@resource-id='org.wikipedia:id/page_list_item_title'][@text='Appium']"),
                "Cannot find title Appium",
                10
        );

        waitForElementPresent(
                By.id("org.wikipedia:id/view_page_title_text"),
                "Cannot article title",
                5
        );

        waitForElementAndClick(
                By.xpath("//android.widget.ImageView[@content-desc='More options']"),
                "Не нашли кнопку меню",
                7
        );

        waitForElementAndClick(
                By.xpath("//android.widget.TextView[@text='Add to reading list']"),
                "Не нашли такой пункт меню",
                5
        );

        waitForElementAndClick(
                By.id("org.wikipedia:id/onboarding_button"),
                "не нашли кнопку Got it",
                5
        );

        waitForElementAndClean(
                By.id("org.wikipedia:id/text_input"),
                "Не нашли строку для ввода названия папки",
                5
        );

        String name_folder = "MYtest";
        waitForElementAndSendKeys(
                By.id("org.wikipedia:id/text_input"),
                name_folder,
                "не смогли ввести название папки",
                5
        );

        waitForElementAndClick(
                By.id("android:id/button1"),
                "не нашли кнопку добавления create_button",
                5
        );


        waitForElementAndClick(
                By.xpath("//android.widget.ImageButton[@content-desc='Navigate up']"),
                "не нашли кнопку закрытия элемента",
                5
        );

        waitForElementAndClick(
                By.xpath("//android.widget.FrameLayout[@content-desc='My lists']"),
                "Не нашли в табе кнопки перехода в мой лист",
                10
        );

        waitForElementAndClick(
                By.xpath("//*[@resource-id='org.wikipedia:id/item_container'][@text='" + name_folder + "']"),
                "Не нашли папку в моем листе",
                10
        );

        waitForElementPresent(
                By.xpath("//*[@text='Appium']"),
                "не нашли записи в листе",
                5
        );

        swipeElementToLeft(
                By.xpath("//*[@text='Appium']"),
                "Не нашли запрошенную статью"
        );

        waitForElementNotPresent(
                By.xpath("//*[@text=test]"),
                "Удаленная статья отсутствует в списке",
                5
        );

    }

    @Test
    public void testAmountOfNotElementSearch() {
        waitForElementAndClick(
                By.xpath(("//*[contains(@text, 'Search Wikipedia')]")),
                "Cannot find 'Search Wikipedia' input'",
                5
        );

        String search_line = "Linkin Park Discography";
        waitForElementAndSendKeys(
                By.xpath("//*[contains(@text,'Search…')]"),
                search_line,
                "Cannot find Search input",
                10
        );

        String search_result_locator = "//*[@resource-id='org.wikipedia:id/search_results_list']/*[@resource-id='org.wikipedia:id/page_list_item_container']";

        waitForElementPresent(
                By.xpath(search_result_locator),
                "Cannot find anything by the request" + search_line,
                15
        );
        int amount_of_search_results = getAmountOfElements(By.xpath(search_result_locator));

        Assert.assertTrue(
                "We found too few results",
                amount_of_search_results > 0);


    }

    @Test

    public void testAmountOfEmptySearch()
    {
        waitForElementAndClick(
                By.xpath(("//*[contains(@text, 'Search Wikipedia')]")),
                "Cannot find 'Search Wikipedia' input'",
                5
        );

        String search_line = "appium";
        waitForElementAndSendKeys(
                By.xpath("//*[contains(@text,'Search…')]"),
                search_line,
                "Cannot find Search input",
                10
        );

        String search_result_locator = "//*[@resource-id='org.wikipedia:id/search_results_list']/*[@resource-id='org.wikipedia:id/page_list_item_container']";
        String empty_result_label = "//*[@text='No results found']";

        waitForElementPresent(By.xpath(empty_result_label),
                "Cannot found empty result label by the request " + search_line,
                15
        );

        assertNotElementForPresent(
                By.xpath(search_result_locator),
                "We've found some result by request "  + search_line
        );
    }


    @Test
    public void testChangeScreenOrientationSearchResults()
    {

        waitForElementAndClick(
                By.xpath(("//*[contains(@text, 'Search Wikipedia')]")),
                "Cannot find 'Search Wikipedia' input'",
                5
        );

        String search_line = "Appium";
        waitForElementAndSendKeys(
                By.xpath("//*[contains(@text,'Search…')]"),
                search_line,
                "Cannot find Search input",
                10
        );

        waitForElementAndClick(
                By.xpath("//*[@resource-id='org.wikipedia:id/page_list_item_title'][@text='Appium']"),
                "Cannot find title Appium",
                10
        );

        String title_before_rotation = waitForElementAndGetAttribute(
                By.id("org.wikipedia:id/view_page_title_text"),
                "text",
                "Cannot find title of article",
                15
                );
        driver.rotate(ScreenOrientation.LANDSCAPE);

        String title_after_rotation = waitForElementAndGetAttribute(
                By.id("org.wikipedia:id/view_page_title_text"),
                "text",
                "Cannot find title of article",
                15
        );

        Assert.assertEquals("Article title have been change after screen rotation",
                title_before_rotation,
                title_after_rotation
        );
        driver.rotate(ScreenOrientation.PORTRAIT);

        String title_after_second_rotation = waitForElementAndGetAttribute(
                By.id("org.wikipedia:id/view_page_title_text"),
                "text",
                "Cannot find title of article",
                15
        );

        Assert.assertEquals("Article title have been change after screen rotation",
                title_before_rotation,
                title_after_second_rotation
        );

    }


    @Test
    public void testCheckSearchArticleInBackground ()
    {   waitForElementAndClick(
            By.xpath("//*[contains(@text,'Search Wikipedia')]"),
            "Cannot find Search Wikipedia",
            5);

        waitForElementAndSendKeys(
                By.xpath("//*[contains(@text,'Search…')]"),
                "Appium",
                "Cannot find Search input",
                10);


        waitForElementPresent(
                By.xpath("//*[@resource-id='org.wikipedia:id/page_list_item_title'][@text='Appium']"),
                "Cannot find title Appium",
                10);

    driver.runAppInBackground(2);

        waitForElementPresent(
                By.xpath("//*[@resource-id='org.wikipedia:id/page_list_item_title'][@text='Appium']"),
                "Cannot find article after returning in background",
                10);

    }


    @Test
    public void workHomeEx5() {
        //кликаем в строку ввода

        waitForElementAndClick(
                By.xpath("//*[contains(@text, 'Search Wikipedia')]"),
                "Cannot find 'Search Wikipedia' input'",
                5
        );

        //в строку поиска вводим текст 'Java'
        waitForElementAndSendKeys(
                By.xpath("//*[contains(@text,'Search…')]"),
                "Java",
                "Cannot find Search input",
                10
        );

        //ищем в результатах поиска слово Java
        waitForElementAndClick(
                By.xpath("//*[@resource-id='org.wikipedia:id/page_list_item_title'][@text='Java']"),
                "Cannot find title Java",
                10
        );


        waitForElementPresent(
                By.id("org.wikipedia:id/view_page_title_text"),
                "Cannot article title",
                5
        );

        // тап по кнопке просмотра меню действий со статьей
        waitForElementAndClick(
                By.xpath("//android.widget.ImageView[@content-desc='More options']"),
                "Cannot find button 'More options'",
                5
        );

        //выбираем действие добавления стати в наш лист
        waitForElementAndClick(
                By.xpath("/hierarchy/android.widget.FrameLayout/android.widget.FrameLayout/android.widget.ListView/android.widget.LinearLayout[3]/android.widget.RelativeLayout/android.widget.TextView"),
                "Cannot find menu item 'Add to reading list'",
                5
        );


        waitForElementAndClick(
                By.id("org.wikipedia:id/onboarding_button"),
                "Cannot find button Got it",
                5
        );


        waitForElementAndClean(
                By.id("org.wikipedia:id/text_input"),
                "Cannot find input for folder name",
                5
        );

        String name_folder = "Folder for articles on Java";

        // вводим название для нашей папки хранения статей
        waitForElementAndSendKeys(
                By.id("org.wikipedia:id/text_input"),
                name_folder,
                "Could not enter folder name",
                5
        );

        waitForElementAndClick(
                By.id("android:id/button1"),
                "Cannot find the add button 'create_button'",
                5
        );

        // закрываем ранее найденную статью
        waitForElementAndClick(
                By.xpath("//android.widget.ImageButton[@content-desc='Navigate up']"),
                "Cannot find the close button [x]",
                5
        );

        //повторяю поиск
        waitForElementAndClick(
                By.xpath(("//*[contains(@text, 'Search Wikipedia')]")),
                "Cannot find 'Search Wikipedia' input'",
                5
        );

        waitForElementAndSendKeys(
                By.xpath("//*[contains(@text,'Search…')]"),
                "JavaScript",
                "Cannot find Search input",
                10
        );

        waitForElementAndClick(
                By.xpath("//*[@resource-id='org.wikipedia:id/page_list_item_title'][@text='JavaScript']"),
                "Cannot find title JavaScript",
                10
        );

        waitForElementPresent(
                By.id("org.wikipedia:id/view_page_title_text"),
                "Cannot article title",
                5
        );

        waitForElementAndClick(
                By.xpath("//android.widget.ImageView[@content-desc='More options']"),
                "Cannot find button 'More options'",
                5
        );

        waitForElementAndClick(
                By.xpath("/hierarchy/android.widget.FrameLayout/android.widget.FrameLayout/android.widget.ListView/android.widget.LinearLayout[3]/android.widget.RelativeLayout/android.widget.TextView"),
                "Cannot find menu item 'Add to reading list'",
                5
        );

        waitForElementAndClick(
                By.xpath("//android.widget.TextView[@text='" + name_folder + "']"),
                "Cannot find the folder with the name '" + name_folder + "'",
                5
        );

        // закрываем ранее найденную статью
        waitForElementAndClick(
                By.xpath("//android.widget.ImageButton[@content-desc='Navigate up']"),
                "Cannot find the close button [x]",
                5
        );

        waitForElementAndClick(
                By.xpath("//android.widget.FrameLayout[@content-desc='My lists']"),
                "Cannot find the buttons go to my list in the navigation bar",
                5
        );


        waitForElementAndClick(
                By.id("org.wikipedia:id/item_container"),
                "Cannot find a folder'" + name_folder + "' in my list",
                15
        );

        waitForElementPresent(
                By.xpath("//*[@text='Java']"),
                "Не нашли записи в листе",
                5
        );

        swipeElementToLeft(
                By.xpath("//*[@text='Java']"),
                "Не нашли запрошенную статью"
        );


        waitForElementPresent(
                By.xpath("//*[@resource-id='org.wikipedia:id/page_list_item_title'][@text='JavaScript']"),
                "Статья JavaScript отсутствует в списке",
                5

        );

        waitForElementNotPresent(
                By.xpath("//*[@text=Java]"),
                "Удаленная статья Java присутствует в списке",
                10
        );

    }



    @Test
    public void workHomeEx6()
    {
        //кликаем в строку ввода

        waitForElementAndClick(
                By.xpath("//*[contains(@text, 'Search Wikipedia')]"),
                "Cannot find 'Search Wikipedia' input'",
                5
        );

        //в строку поиска вводим текст 'Java'
        waitForElementAndSendKeys(
                By.xpath("//*[contains(@text,'Search…')]"),
                "Java",
                "Cannot find Search input",
                10
        );

        //ищем в результатах поиска слово Java
        waitForElementAndClick(
                By.xpath("//*[@resource-id='org.wikipedia:id/page_list_item_title'][@text='Java']"),
                "Cannot find title Java",
                10
        );

        assertElementPresent(
                By.id("org.wikipedia:id/view_page_title_text"),
                "Cannot find element"
        );

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


    private WebElement waitForElementAndClean(By by, String error_messagge, long timeOutSeconds) {
        WebElement element = waitForElementPresent(by, error_messagge);
        element.clear();
        return (element);

    }


    private String assertElementHasText(By by, String text, String error_message, long timeoutInSeconds) {
        WebElement actual_result = waitForElementPresent(
                by,
                error_message,
                5);
        String actual_text = actual_result.getAttribute("text");
        String expected_text = text;
        Assert.assertEquals("Ожидаемый результат не совпадает с текущим", expected_text, actual_text);
        return expected_text;
    }

    protected void swipeUp(int timeOfSwipe) {
        TouchAction action = new TouchAction(driver);
        Dimension size = driver.manage().window().getSize();
        int x = size.width / 2;
        int start_y = (int) (size.height * 0.8);
        int end_y = (int) (size.height * 0.2);
        action
                .press(x, start_y)
                .waitAction(timeOfSwipe)
                .moveTo(x, end_y)
                .release()
                .perform();
    }

    protected void swipeUpQuick() {
        swipeUp(200);
    }


    protected void swipeUpToFindElement(By by, String error_message, int max_swipe) {

        while (driver.findElements(by).size() == 0) {
            int already_swiped = 0;
            if (already_swiped > max_swipe) {
                waitForElementPresent(by, "Can not element swiping up. \n" + error_message, 0);
                return;
            }
            swipeUpQuick();
            ++already_swiped;
        }

    }

    protected void swipeElementToLeft(By by, String error_message) {
        WebElement element = waitForElementPresent(
                by,
                error_message,
                10);
        int left_x = element.getLocation().getX();
        int right_x = left_x + element.getSize().getWidth();
        int upper_y = element.getLocation().getY();
        int lower_y = upper_y + element.getSize().getWidth();
        int middle_y = (upper_y + lower_y) / 2;
        TouchAction action = new TouchAction(driver);
        action
                .press(right_x, middle_y)
                .waitAction(300)
                .moveTo(left_x, middle_y)
                .release()
                .perform();
    }


    protected int getAmountOfElements(By by) {
        List elements = driver.findElements(by);
        return elements.size();
    }

    private void assertNotElementForPresent (By by, String error_message)
    {
        int amount_of_elements = getAmountOfElements(by);
        if  (amount_of_elements > 0) {
            String default_message = "An element" + by.toString() +  "supposed to be not present";
            throw new AssertionError(default_message+' '+ error_message);
        }
    }

    private void assertElementPresent (By by, String error_message)
    {
       if (driver.findElements(by).size() == 0){
           String default_message = "Element " + by.toString() + "not found ";
            throw new AssertionError(default_message + ' ' + error_message);
       }


    }

    private String waitForElementAndGetAttribute (By by, String attribute, String error_message, long timeoutInSeconds)
    {
       WebElement element = waitForElementPresent(by,error_message,timeoutInSeconds);
       return element.getAttribute(attribute);
    }
}