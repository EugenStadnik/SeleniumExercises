import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.opera.OperaDriver;

import java.util.concurrent.TimeUnit;

public class MainClass {

    static {
        System.setProperty("webdriver.gecko.driver", "drivers/geckodriver");
        System.setProperty("webdriver.chrome.driver", "drivers/chromedriver");
        System.setProperty("webdriver.opera.driver", "drivers/operadriver");
    }
    private static WebDriver driver = new ChromeDriver();

    public static void main(String[] args) throws InterruptedException {
        //System.out.println(Filler.field);
        //initWebDriver();
        //starlMostDriversOneByOne();
        //exploreDriverMethods();
        //workWithElements();
        //workWithButtons();
        //workWithTextFields();
        //workWithLinks();
        workWithCheckBoxesAndRadioButtons();
    }

    private static void initWebDriver() throws InterruptedException {
        WebDriver driver = new ChromeDriver();
        Thread.currentThread().sleep(3000);     //Do NOT do that, use implicit or explicit wait
        driver.quit();
    }

    private static void starlMostDriversOneByOne() throws InterruptedException {
        WebDriver driver = new FirefoxDriver();
        driver.get("https://www.mozilla.org/");
        Thread.currentThread().sleep(3000);     //Do NOT do that, use implicit or explicit wait
        driver.quit();
        driver = new ChromeDriver();
        driver.get("https://www.google.com/");
        Thread.currentThread().sleep(3000);     //Do NOT do that, use implicit or explicit wait
        driver.quit();
        driver = new OperaDriver();
        driver.get("https://www.opera.com/");
        Thread.currentThread().sleep(3000);     //Do NOT do that, use implicit or explicit wait
        driver.close();                             //Actually close the focused window but does not terminates the process
        driver.quit();                              //Actually gentelly terminates the session requesting dispose()
        //driver.dispose();                         //Unexisting method someone ill fantasy
    }

    private static void exploreDriverMethods() throws InterruptedException {
        WebDriver driver = new ChromeDriver();
        Thread.currentThread().sleep(3000);     //Do NOT do that, use implicit or explicit wait
        driver.get("https://www.google.com/");
        Thread.currentThread().sleep(3000);     //Do NOT do that, use implicit or explicit wait
        driver.navigate().to("https://www.yahoo.com/");
        Thread.currentThread().sleep(3000);     //Do NOT do that, use implicit or explicit wait

        System.out.println(driver.getCurrentUrl());
        System.out.println(driver.getTitle());

        driver.navigate().back();
        Thread.currentThread().sleep(3000);     //Do NOT do that, use implicit or explicit wait
        driver.navigate().forward();
        Thread.currentThread().sleep(3000);     //Do NOT do that, use implicit or explicit wait
        driver.navigate().refresh();
        Thread.currentThread().sleep(3000);     //Do NOT do that, use implicit or explicit wait

        driver.manage().window().fullscreen();
        Thread.currentThread().sleep(3000);     //Do NOT do that, use implicit or explicit wait
        driver.manage().window().setSize(new Dimension(800, 600));
        Thread.currentThread().sleep(3000);     //Do NOT do that, use implicit or explicit wait
        driver.manage().window().maximize();
        Thread.currentThread().sleep(3000);     //Do NOT do that, use implicit or explicit wait

        driver.quit();
    }

    private static void workWithElements() {
        WebDriver driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.get("https://en.wikipedia.org/wiki/Main_Page");
        //Implicitly waits defined time until full page is loaded completely. After page is loaded driver stops to wait
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);

        WebElement link = driver.findElement(By.linkText("Log in"));                //Using text in the link
        WebElement link2 = driver.findElement(By.partialLinkText("anyone"));        //Using part of text in the link
        WebElement searchField = driver.findElement(By.name("search"));             //Using @name attribute
        WebElement searchButton = driver.findElement(By.className("searchButton")); //Using @class attribute
        WebElement li = driver.findElement(By.id("ca-viewsource"));                 //Using @id attribute
        //Unstable way there could be a lot of tags with the same name. First element will be retrieved
        WebElement input = driver.findElement(By.tagName("input"));                 //Using <tag> name
        //Using css and omitting intermediate locations (syntax: tag#id tag.class )
        WebElement element = driver.findElement(By.cssSelector("form#searchform input#searchInput"));
        //Using css and strong chain of locations (syntax: tag#id > tag.class.otherClass.anotherClass )
        WebElement element1 = driver.findElement(By.cssSelector("form#searchform > div#simpleSearch > input.searchButton"));
        WebElement logo = driver.findElement(By.xpath("//div[@id=\"mw-panel\"]/div[@id=\"p-logo\"]/a"));

        driver.quit();
    }

    private static void workWithButtons() throws InterruptedException {
        WebDriver driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.get("https://en.wikipedia.org/wiki/Main_Page");
        //Implicitly waits defined time until full page is loaded completely. After page is loaded driver stops to wait
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        driver.findElement(By.xpath("//input[@id=\"searchButton\"]")).click();
        Thread.currentThread().sleep(3000);     //Do NOT do that, use implicit or explicit wait
        driver.get("https://github.com/");
        //Implicitly waits defined time until full page is loaded completely. After page is loaded driver stops to wait
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        //Only If <tag> contains @type="submit" use submit() method to WebElement
        WebElement button = driver.findElement(By.xpath(
                "//form[@class=\"home-hero-signup text-gray-dark js-signup-form js-signup-form-submit\"]//button"
        ));
        System.out.println("Button text is: " + button.getText());
        //button.submit();
        driver.findElement(By.cssSelector("a.HeaderMenu-link.no-underline.mr-3")).click();
        Thread.currentThread().sleep(3000);     //Do NOT do that, use implicit or explicit wait
        driver.navigate().back();
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        driver.findElement(By.xpath("//a[text()=\"Features\"]")).click();
        try {
            //org.openqa.selenium.JavascriptException: javascript error: Unable to find owning document
            driver.findElement(By.xpath("//a[text()=\"Features\"]")).submit();
        } catch (JavascriptException jse) {
            System.out.println(jse.getMessage());
        }
        Thread.currentThread().sleep(3000);     //Do NOT do that, use implicit or explicit wait

        driver.quit();
    }

    private static void workWithTextFields() throws InterruptedException {
        WebDriver driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.get("https://en.wikipedia.org/wiki/Main_Page");
        //Implicitly waits defined time until full page is loaded completely. After page is loaded driver stops to wait
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        //Fill textfield
        driver.findElement(By.xpath("//input[@id=\"searchInput\"]")).sendKeys("Selenium WebDriver");
        //Click search
        driver.findElement(By.xpath("//input[@id=\"searchButton\"]")).click();
        Thread.currentThread().sleep(3000);     //Do NOT do that, use implicit or explicit wait
        //Get value of textfield
        System.out.println(driver.findElement(By.xpath("//input[@id=\"ooui-php-1\"]")).getAttribute("value"));
        //Clear textfield
        driver.findElement(By.xpath("//input[@id=\"ooui-php-1\"]")).clear();
        Thread.currentThread().sleep(3000);     //Do NOT do that, use implicit or explicit wait

        driver.get("https://github.com/");
        //Implicitly waits defined time until full page is loaded completely. After page is loaded driver stops to wait
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        driver.findElement(By.xpath("//input[@id=\"user[login]\"]")).sendKeys("testUsername");
        driver.findElement(By.xpath("//input[@id=\"user[password]\"]")).sendKeys("testPass");
        Thread.currentThread().sleep(3000);     //Do NOT do that, use implicit or explicit wait
        WebElement button = driver.findElement(By.xpath(
                "//form[@class=\"home-hero-signup text-gray-dark js-signup-form js-signup-form-submit\"]//button"
        ));
        button.submit();
        Thread.currentThread().sleep(3000);     //Do NOT do that, use implicit or explicit wait

        driver.get("https://www.facebook.com/");
        //Implicitly waits defined time until full page is loaded completely. After page is loaded driver stops to wait
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        driver.findElement(By.xpath("//input[@id=\"email\"]")).sendKeys("testMail");
        System.out.println("Mail is: " + driver.findElement(By.xpath("//input[@id=\"email\"]")).getAttribute("value"));
        driver.findElement(By.xpath("//input[@id=\"pass\"]")).sendKeys("testPass");
        Thread.currentThread().sleep(3000);     //Do NOT do that, use implicit or explicit wait
        driver.findElement(By.xpath("//button[@id=\"u_0_b\"]")).submit();
        Thread.currentThread().sleep(10000);     //Do NOT do that, use implicit or explicit wait

        driver.quit();
    }

    private static void workWithLinks() throws InterruptedException {
        WebDriver driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.get("https://en.wikipedia.org/wiki/Main_Page");
        //Implicitly waits defined time until full page is loaded completely. After page is loaded driver stops to wait
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        WebElement link = driver.findElement(By.xpath("//li[@id=\"n-aboutsite\"]/a"));
        System.out.println(link.getText());
        link.click();
        Thread.currentThread().sleep(3000);     //Do NOT do that, use implicit or explicit wait

        driver.get("https://github.com/");
        //Implicitly waits defined time until full page is loaded completely. After page is loaded driver stops to wait
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        driver.findElement(By.xpath("//a[text()=\"Marketplace\"]")).click();
        Thread.currentThread().sleep(3000);     //Do NOT do that, use implicit or explicit wait

        driver.get("https://www.facebook.com/");
        //Implicitly waits defined time until full page is loaded completely. After page is loaded driver stops to wait
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        driver.findElement(By.xpath("//div[@class=\"_6ltj\"]/a")).click();
        try {
            //Only If <tag> contains @type="submit" use submit() method to WebElement
            driver.findElement(By.xpath("//a[@title=\"Russian\"]")).submit();
        } catch (JavascriptException jse) {
            System.out.println(jse.getMessage());
        }
        Thread.currentThread().sleep(3000);     //Do NOT do that, use implicit or explicit wait

        driver.quit();
    }

    private static void workWithCheckBoxesAndRadioButtons() throws InterruptedException {
        driver.manage().window().maximize();
        driver.get("http://jkorpela.fi/www/testel.html");
        //Implicitly waits defined time until full page is loaded completely. After page is loaded driver stops to wait
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        Thread.currentThread().sleep(3000);     //Do NOT do that, use implicit or explicit wait
        WebElement element = driver.findElement(By.xpath("//h2[text()=\"Character test\"]"));
        Actions actions = new Actions(driver);
        actions.moveToElement(element);
        actions.perform();
        Thread.currentThread().sleep(3000);     //Do NOT do that, use implicit or explicit wait
        //Set checkbox
        selectInput(" Checkbox 1");
        Thread.currentThread().sleep(3000);     //Do NOT do that, use implicit or explicit wait
        //Unset checkbox
        unselectInput(" Checkbox 1");
        Thread.currentThread().sleep(3000);     //Do NOT do that, use implicit or explicit wait
        //Set radio button
        selectInput(" Radio button 1");
        Thread.currentThread().sleep(3000);     //Do NOT do that, use implicit or explicit wait

        driver.quit();
    }

    public static void selectInput(String name) {
        String xpath = "//label[text()=\"%s\"]/input";
        System.out.println("The " + name + " is selected: " + driver.findElement(By.xpath(String.format(xpath, name))).isSelected());
        if(!driver.findElement(By.xpath(String.format(xpath, name))).isSelected()) {
            System.out.println("Setting " + name);
            driver.findElement(By.xpath(String.format(xpath, name))).click();
        } else {
            System.out.println("The " + name + " is already set.");
        }
        System.out.println("The " + name + " is selected: " + driver.findElement(By.xpath(String.format(xpath, name))).isSelected());
    }

    public static void unselectInput(String name) {
        String xpath = "//label[text()=\"%s\"]/input";
        System.out.println("The " + name + " is selected: " + driver.findElement(By.xpath(String.format(xpath, name))).isSelected());
        if(driver.findElement(By.xpath(String.format(xpath, name))).isSelected()) {
            System.out.println("Unsetting " + name);
            driver.findElement(By.xpath(String.format(xpath, name))).click();
        } else {
            System.out.println("The " + name + " is already unset.");
        }
        System.out.println("The " + name + " is selected: " + driver.findElement(By.xpath(String.format(xpath, name))).isSelected());
    }

}
/**
 * XPath:
 * Test page: https://en.wikipedia.org/wiki/Main_Page
 *
 * Absolute xpath:
 * / - root element (specific element name (only /html is available) or * should be defined)
 * /* - same as /html (1 element is found)
 * /html/body/footer/div - strong chain (1 element is found)
 * /html/body/footer/div/ - error invalid xpath
 * /html/body/div/a - strong chain (1 element is found)
 * /html/body/div/* - find all elements in /html/body/div/ (10 elements are found)
 * /nav - no <nav> tag in the root path (no one element is found)
 *
 * Relative xpath:
 * // - any inner element passing root (specific element name or * should be defined)
 * //* - locates all elements (1020 elements are found)
 * //body - relative xpath (passing /html root element) (1 element is found)
 * //body//a - combination of relative xpaths (337 elements are found)
 * //div/h1 - combination of relative xpath and strong chain (1 element is found)
 * //body//a/* - find all elements in any <body> and any internal <a> (73 elements are found)
 * //xsd - no <xsd> tag in all the document (no one element is found)
 *
 * Using attributes:
 * //*[@id="p-logo"] - find any element by attribute (1 element is found)
 * //*[@role="banner"] - any attribute could be used (1 the same element is found)
 * //*[@id="mw-panel"]/div[@role="banner"]/a - combination with strong chain (1 element is found)
 * the <a> tag inside the <div> tag with specified attribute inside
 * (*) any tag with specified attribute inside all the document
 * //*[@name="code"] - no any tag containing name="code" attribute in all the document
 * (no one element is found)
 *
 * Using axis:
 * Ancestors:
 * /html/body/div/a - returns anchor
 * /html/body/div/a/ancestor::* - find all previous tags (all 3 parent elements are found)
 * /html/body/div/a/ancestor-or-self::* - find all previous tags and the <a> tag itself (4 elements are found)
 * //*[@id="p-lang"]/div/ul/li[7]/a - (1 element is found)
 * //*[@id="p-lang"]/div/ul/li[7]/a/ancestor-or-self::a - (1 the same element is found)
 * //*[@id="p-lang"]/div/ul/li[7]/a/ancestor::a - (no one element is found)
 * //*[@id="p-lang"]/div/ul/li[7]/a/ancestor::div - (all 3 parent <div> elements are found)
 * /html/body/div/a/ancestor::*[1] - same as //*[@id="top"]/parent::* or /html/body/div/a/..
 * <b>INDEXES ARE HUMAN READABLE NOT MACHINE READABLE /html/body/div/a/ancestor::*[0] - WILL RETRIEVE NULL</b>
 * /html/body/div/a/ancestor::body - find previous <body> tag (1 element is found)
 * //div[@id="centralNotice"]/ancestor::*[@id="content"] - finds second parent element by it's id attribute (1 element is found)
 * /html/body/div/a/ancestor::a - find previous <a> tag (no one element is found)
 * Siblings:
 * //li[@class="interlanguage-link interwiki-de"] - finds link to Deutsch wiki
 * //li[@class="interlanguage-link interwiki-de"]/following-sibling::* - finds all followed languages linkages at the same level
 * //li[@class="interlanguage-link interwiki-de"]/preceding-sibling::* - finds all preceding languages linkages at the same level
 * Parent:
 * /html/body/div/a/parent::div - find previous <div> tag (1 element is found)
 * //*[@id="top"]/parent::* - find any previous tag (1 the same element is found)
 * /html/body/div/a/parent::body - find previous <body> tag (no one element is found)
 * /html/body/div/a/.. - find any previous tag (1 element is found)
 */ //*[*/@id="top"] - find parent element of element containing id="top" attribute
/** the same as //*[@id="top"]/parent::* or //*[@id="top"]/..
 * You could combine axes at any valid order:
 * //a[@title="German" and @lang="de"]/parent::li/following-sibling::li[1]/ancestor::nav[@id="p-lang"]
 *
 * Using current node:
 * /html/body/div/a/. - invokes itself
 * But:
 * /html/body/div//div and /html/body/div/.//div will retrieve the same result
 * Also:
 * /html/body/div[3]//div and /html/body/div[3]/.//div will retrieve the same result
 * And what is the semantic - I don't know
 *
 * Using functions:
 * //*[@id="p-lang"]/div//li[last()] - returns the last element in languages list
 * //a[text()="Main page"] - return elements by known wrapped text
 * //li[@class="interlanguage-link interwiki-de"] - finds only Deutsch element of the list
 * //li[contains(@class, "interlanguage-link")] - finds all languages list elements
 * functions combination:
 * //*[contains(text(), " in ")] - finds all elements contain " in " in the text
 * //a[starts-with(@title, "E")] - finds all <a> elements which title attribute starts with "e" or "E" letter
 * //*[starts-with(text(), "The")] - finds all elements which text starts with "The"
 * //a[starts-with(@title, "E")] and //a[starts-with(@title, "E")][last()] - retrieves the same result so:
 * (//a[starts-with(@title, "E")])[last()] - finds last <a> element which title attribute starts with "e" or "E" letter
 *
 * Narrowing elements found:
 * Using strong chain:
 * //a - (332 elements are found)
 * //div/a - (29 elements are found)
 * //body/div/a - (1 element is found)
 * Using indexes:
 * /html/body/div - (6 elements are found)
 * /html/body/div[1] - (The first of previous 6 elements is found)
 * (//div)[1] - find first <div> from all <div>s in the document
 * (//div)[] - error invalid xpath
 * <b>INDEXES ARE HUMAN READABLE NOT MACHINE READABLE /html/body/div[0] - WILL RETRIEVE NULL</b>
 * Using attributes:
 * /html/body/div - (6 elements are found)
 * /html/body/div[@id="content"] - attribute is used (The third of previous 6 elements is found)
 * Combining attributes:
 * //*[@class="vector-menu vector-menu-portal portal"] - (5 elements are found)
 * //*[@class="vector-menu vector-menu-portal portal" and @aria-labelledby="p-coll-print_export-label"]
 * - (1 element is found)
 *
 * Also any narrowing chains could be used in any valid combinations
 * using functions, axis, .. parents and absolute or relative xpathes.
 * The main goal is to achieve the required result.
 *
 */