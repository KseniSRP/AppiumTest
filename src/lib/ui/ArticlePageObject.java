package lib.ui;

import io.appium.java_client.AppiumDriver;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

public class ArticlePageObject extends MainPageObject {

    private  static final String
    TITLE = "org.wikipedia:id/view_page_title_text",
    FOOTER_ELEMENT  = "//*[@text='View page in browser']",
    OPTIONS_BUTTON = "org.wikipedia:id/view_page_title_text",
    OPTIONS_ADD_TO_MY_LIST_BUTTON = "//android.widget.ImageView[@content-desc='More options']",
    ONBOARDING_BUTTON = "org.wikipedia:id/onboarding_button",
    ADD_TO_MY_LIST_OVERLAY = "//android.widget.TextView[@text='Add to reading list']",
    MY_LIST_NAME_INPUTE = "org.wikipedia:id/text_input",
    MY_LIST_OK_BUTTON = "android:id/button1",
    CLOSE_ARTICLE_BUTTON = "//android.widget.ImageButton[@content-desc='Navigate up']",
    MY_LIST_IN_NAVIGATION_BAR =  "//android.widget.FrameLayout[@content-desc='My lists']",
    MY_LIST_ITEM = "org.wikipedia:id/item_container",
    ARTICLE_TITLE_TPL = "//*[@text='{TITLE}']";



    /*TEMPLATES METHODS*/
    private static String getResultSearchElement(String title)
    {
        return  ARTICLE_TITLE_TPL.replace("{TITLE}", title);
    }
    /*TEMPLATES METHODS*/

    public ArticlePageObject(AppiumDriver driver)
    {
        super(driver);
    }

    public WebElement waitForTitleElement()
    {
        return this.waitForElementPresent(By.id(TITLE),
                "Cannot find article title on page!",
                15);
    }

    public WebElement presentForTitleElement()
    {
        return this.waitForElementPresent(By.id(TITLE),
                "Cannot find article title on page!");
    }





    public String getArticleTitle()
    {
        WebElement title_element = waitForTitleElement();
        return title_element.getAttribute("text");
    }

    public void swipeToFooter()
    {
        this.swipeUpToFindElement(
                By.xpath(FOOTER_ELEMENT),
                "Cannot find the end of article",
                20
        );
    }

    public void addArticleToMyList(String name_of_folder)
    {
        this.waitForElementPresent(
                By.id(OPTIONS_BUTTON),
                "Cannot article title",
                5
        );

        this.waitForElementAndClick(
                By.xpath(OPTIONS_ADD_TO_MY_LIST_BUTTON),
                "Не нашли кнопку меню",
                7
        );

        this.waitForElementAndClick(
                By.xpath(ADD_TO_MY_LIST_OVERLAY),
                "Не нашли такой пункт меню",
                5
        );

        this.waitForElementAndClick(
                By.id(ONBOARDING_BUTTON),
                "не нашли кнопку Got it",
                5
        );

        this.waitForElementAndClean(
                By.id(MY_LIST_NAME_INPUTE),
                "Не нашли строку для ввода названия папки",
                5
        );

        this.waitForElementAndSendKeys(
                By.id(MY_LIST_NAME_INPUTE),
                name_of_folder,
                "не смогли ввести название папки",
                5
        );

        this.waitForElementAndClick(
                By.id(MY_LIST_OK_BUTTON),
                "не нашли кнопку добавления create_button",
                5
        );

    }

    public void closeArticle()
    {
        this.waitForElementAndClick(
                By.xpath(CLOSE_ARTICLE_BUTTON),
                "не нашли кнопку закрытия элемента",
                5
        );
    }

    public void addArticleInOldMyList (String name_of_folder)
    {

        waitForElementAndClick(
                By.xpath(OPTIONS_ADD_TO_MY_LIST_BUTTON),
                "Cannot find button 'More options'",
                5
        );

        waitForElementAndClick(
                By.xpath(ADD_TO_MY_LIST_OVERLAY),
                "Cannot find menu item 'Add to reading list'",
                5
        );

        waitForElementAndClick(
                By.xpath("//android.widget.TextView[@text='" + name_of_folder + "']"),
                "Cannot find the folder with the name '" + name_of_folder + "'",
                5
        );


    }

    public void openMyList (String name_of_folder)

    {
        this.waitForElementAndClick(
                By.xpath(MY_LIST_IN_NAVIGATION_BAR),
                "Cannot find the buttons go to my list in the navigation bar",
                5
        );


        this.waitForElementAndClick(
                By.id(MY_LIST_ITEM),
                "Cannot find a folder'" + name_of_folder + "' in my list",
                15
        );
    }

    public void deleteArticleInMyList (String article_title1, String article_title2)
    {
        String xpath_title1 = getResultSearchElement(article_title1);
        this.waitForElementPresent(
                By.xpath(xpath_title1),
                "Не нашли записи в листе",
                5
        );

        this.swipeElementToLeft(
                By.xpath(xpath_title1),
                "Не нашли запрошенную статью"
        );


       String xpath_title2 = getResultSearchElement(article_title2);
        this.waitForElementPresent(
                By.xpath(xpath_title2),
                "Статья отсутствует в списке",
                5

        );

        this.waitForElementNotPresent(
                By.xpath(xpath_title1),
                "Удаленная статья присутствует в списке",
                10
        );


}

}
