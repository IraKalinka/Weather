import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import org.testng.annotations.Test;
import org.testng.asserts.Assertion;

import java.util.*;

public class IraKalinkaTest {

/**  TC_1_1  - Тест кейс:
1.  Открыть страницу https://openweathermap.org/
2.  Набрать в строке поиска город Paris
3.  Нажать пункт меню Search
4.  Из выпадающего списка выбрать Paris, FR
5.  Подтвердить, что заголовок изменился на "Paris, FR" */

 @Test
    public void testH2Tag_WhenSearchingCityCountry() throws InterruptedException {

        System.setProperty("webdriver.chrome.driver", "C:\\KIT\\Test\\Automation\\JavaForBeg\\chromedriver.exe");
        WebDriver driver = new ChromeDriver();
        driver.manage().window().maximize();

        String url = "https://openweathermap.org/";
        String cityName = "Paris";
        String expectedResult = "Paris, FR";

        driver.get(url);
        Thread.sleep(5000);

        WebElement searchCityField = driver.findElement(
                By.xpath("//div[@id = 'weather-widget']//input[@placeholder='Search city']"));
        searchCityField.click();
        searchCityField.sendKeys(cityName);
        Thread.sleep(5000);

        WebElement searchButton = driver.findElement(
                By.xpath("//div[@id='weather-widget']//button[@type='submit']"));
        searchButton.click();
        Thread.sleep(1000);

        WebElement parisFrChoiceInDropdownMenu = driver.findElement(
                By.xpath("//ul[@class='search-dropdown-menu']/li/span[text()='Paris, FR ']"));
        parisFrChoiceInDropdownMenu.click();

        WebElement h2CityCountryHeader = driver.findElement(
                By.xpath("//div[@id='weather-widget']//h2")
        );

        Thread.sleep(2000);
        String actualResult = h2CityCountryHeader.getText();

        Assert.assertEquals(actualResult, expectedResult);

        Thread.sleep(5000);

        driver.quit();
    }


/**  TC_11_01
1.  Открыть базовую ссылку
2.  Нажать на пункт меню Guide
3.  Подтвердить, что вы перешли на страницу со ссылкой https://openweathermap.org/guide
    и что title этой страницы OpenWeatherMap API guide - OpenWeatherMap  */

    @Test
    public void testTitleTag_WhenClickingOnGuideLink() throws InterruptedException {

        System.setProperty("webdriver.chrome.driver", "C:\\KIT\\Test\\Automation\\JavaForBeg\\chromedriver.exe");
        WebDriver driver = new ChromeDriver();
        driver.manage().window().maximize();

        String baseUrl = "https://openweathermap.org/";
        String expectedResult1 = "OpenWeatherMap API guide - OpenWeatherMap";
        String expectedResult2 = "https://openweathermap.org/guide";

        driver.get(baseUrl);
        Thread.sleep(5000);

        WebElement guideLink = driver.findElement(
                By.xpath("//div[@id='desktop-menu']//li/a[@href='/guide']"));
        guideLink.click();

        String actualResult1 = driver.getTitle();
        String actualResult2 = driver.getCurrentUrl();

        Assert.assertEquals(actualResult1, expectedResult1);
        Assert.assertEquals(actualResult2, expectedResult2);

        driver.quit();
    }

/**  TC_11_02
1.  Открыть базовую ссылку
2.  Нажать на единицы измерения Imperial: °F, mph
3.  Подтвердить, что температура для города показана в Фаренгейтах
*/

 @Test
    public void testCurrentTempAppearsInFahrenheit() throws InterruptedException {

        System.setProperty("webdriver.chrome.driver", "C:\\KIT\\Test\\Automation\\JavaForBeg\\chromedriver.exe");
        WebDriver driver = new ChromeDriver();
        driver.manage().window().maximize();

        String baseUrl = "https://openweathermap.org/";
        boolean expectedResult = true;

        driver.get(baseUrl);
        Thread.sleep(5000);

        WebElement switchContainerOptionImperial = driver.findElement(
                By.xpath("//div[@class='switch-container']//div[text()='Imperial: °F, mph']"));
        switchContainerOptionImperial.click();
        Thread.sleep(3000);

        WebElement currentTempHeading = driver.findElement(
                By.xpath("//div[@class='current-temp']//span[@class='heading']"));
        //Thread.sleep(2000);

        String actualResult = currentTempHeading.getText();

        Assert.assertEquals(actualResult.contains("F"), expectedResult);

        driver.quit();
    }

/**TC_11_03
 1. Открыть базовую ссылку
 2. Подтвердить, что внизу страницы есть панель с текстом “We use cookies which are essential for the site to work.
    We also use non-essential cookies to help us improve our services. Any data collected is anonymised.
    You can allow all cookies or manage them individually.”
 3. Подтвердить, что на панели внизу страницы есть 2 кнопки “Allow all” и “Manage cookies” */

 @Test
    public void testFooterPanelTextAndButtonsExist() throws InterruptedException {

    System.setProperty("webdriver.chrome.driver", "C:\\KIT\\Test\\Automation\\JavaForBeg\\chromedriver.exe");
    WebDriver driver = new ChromeDriver();
    driver.manage().window().maximize();

    String baseUrl = "https://openweathermap.org/";
    String expectedResult1 = "We use cookies which are essential for the site to work. " +
            "We also use non-essential cookies to help us improve our services. Any data collected is anonymised. " +
            "You can allow all cookies or manage them individually.";
    boolean expectedResult2 = true;

    driver.get(baseUrl);
    Thread.sleep(5000);

    WebElement footerPanelDescription = driver.findElement(
            By.xpath("//div[@id='stick-footer-panel']//p[@class='stick-footer-panel__description']"));

    String actualResult1 = footerPanelDescription.getText();
    Assert.assertEquals(actualResult1, expectedResult1);

    WebElement footerPanelContainer = driver.findElement(
            By.xpath("//div[@id='stick-footer-panel']//div[@class='stick-footer-panel__btn-container']"));

    String actualResult2 = footerPanelContainer.getText();
    Assert.assertEquals(actualResult2.contains("Allow all"), expectedResult2);
    Assert.assertEquals(actualResult2.contains("Manage cookies"),expectedResult2);

    driver.quit();
}

/** TC_11_04
 1.  Открыть базовую ссылку
 2.  Подтвердить, что в меню Support есть 3 подменю с названиями “FAQ”, “How to start” и “Ask a question” */

 @Test
    public void testSupportDropDownHasLinks() throws InterruptedException {

     System.setProperty("webdriver.chrome.driver", "C:\\KIT\\Test\\Automation\\JavaForBeg\\chromedriver.exe");
     WebDriver driver = new ChromeDriver();
     driver.manage().window().maximize();

     String baseUrl = "https://openweathermap.org/";
     List<String> expectedResult = Arrays.asList("FAQ", "How to start", "Ask a question");

     driver.get(baseUrl);
     Thread.sleep(5000);

     WebElement supportDropdownMenu = driver.findElement(By.id("support-dropdown"));
     supportDropdownMenu.click();
     Thread.sleep(2000);

     WebElement dropdownMenuVisible = driver.findElement(
             By.xpath("//li[@class='with-dropdown']/ul[@class='dropdown-menu dropdown-visible']"));

     List<WebElement> hRefList = dropdownMenuVisible.findElements(By.tagName("a"));
     List<String> actualResult = new ArrayList<>();
     for (WebElement link : hRefList) { actualResult.add(link.getText()); }

     Assert.assertEquals(actualResult, expectedResult);

     driver.quit();
 }

 /** TC_11_05
  1. Открыть базовую ссылку
  2. Нажать пункт меню Support → Ask a question
  3. Заполнить поля Email, Subject, Message
  4. Не подтвердив CAPTCHA, нажать кнопку Submit
  5. Подтвердить, что пользователю будет показана ошибка “reCAPTCHA verification failed, please try again.” */

 @Test
    public void testRecaptchaVerificationErrorMessage() throws InterruptedException {

     System.setProperty("webdriver.chrome.driver", "C:\\KIT\\Test\\Automation\\JavaForBeg\\chromedriver.exe");
     WebDriver driver = new ChromeDriver();
     driver.manage().window().maximize();
     String baseUrl = "https://openweathermap.org/";

     String email = "test@test.com";
     String subject = "Other";
     String messageText = "Message";
     String expectedResult = "reCAPTCHA verification failed, please try again.";

     driver.get(baseUrl);
     Thread.sleep(3000);

     WebElement supportDropdownMenu = driver.findElement(
             By.xpath("//div[@id='desktop-menu']//li/div[@id='support-dropdown']"));
     supportDropdownMenu.click();
     Thread.sleep(2000);

     WebElement hrefAskAQuestion = driver.findElement(
             By.xpath("//div[@id='desktop-menu']//li/a[@href='https://home.openweathermap.org/questions']"));
     hrefAskAQuestion.click();
     //Thread.sleep(1000);

     Iterator<String> iter = driver.getWindowHandles().iterator();
     iter.next();
     driver.switchTo().window(iter.next());
//     driver.get("https://home.openweathermap.org/questions");      ---- это не работает. открывается второе окно, но код исполн в первом
//     Thread.sleep(3000);

     WebElement emailTextbox = driver.findElement(By.id("question_form_email"));
     emailTextbox.click();
     emailTextbox.sendKeys(email);
     Thread.sleep(1000);

     Select subjectTextbox = new Select(driver.findElement(
     By.xpath("//div[@class='form-group select required question_form_subject']//select[@id='question_form_subject']")));
     subjectTextbox.selectByValue(subject);
     Thread.sleep(1000);

//     WebElement subjectTextbox = driver.findElement(By.id("question_form_subject"));
//             //By.xpath("//div[@class='form-group select required question_form_subject']//select[@id='question_form_subject']"));
//     subjectTextbox.click();
//     Thread.sleep(3000);
//                                                                                  -----предыдущий селект ззаменяет эти два драйвера
//     WebElement subjectOptionChoice = driver.findElement(
//             By.xpath("//select[@id='question_form_subject']/option[@value='Other']"));
//     subjectOptionChoice.click();
//     Thread.sleep(3000);

     WebElement messageTextArea = driver.findElement(By.id("question_form_message"));
     messageTextArea.click();
     messageTextArea.sendKeys(messageText);
     Thread.sleep(1000);

     WebElement submitButton = driver.findElement(By.xpath("//div[@class='col-sm-8']//input[@type='submit']"));
     submitButton.click();
     Thread.sleep(1000);


     WebElement errorMessage = driver.findElement(By.xpath("//div[@class='help-block']"));
     Thread.sleep(1000);

     String actualResult = errorMessage.getText();

     Assert.assertEquals(actualResult, expectedResult);

     driver.quit();
 }

/** TC_11_06
 1.  Открыть базовую ссылку
 2.  Нажать пункт меню Support → Ask a question
 3. Оставить значение по умолчанию в checkbox Are you an OpenWeather user?
 4. Оставить пустым поле Email
 5. Заполнить поля  Subject, Message
 6. Подтвердить CAPTCHA
 7. Нажать кнопку Submit
 8. Подтвердить, что в поле Email пользователю будет показана ошибка “can't be blank” */

    @Test
    public void testEmailTextboxBlankErrorMessage() throws InterruptedException {

    System.setProperty("webdriver.chrome.driver", "C:\\KIT\\Test\\Automation\\JavaForBeg\\chromedriver.exe");
    WebDriver driver = new ChromeDriver();
    driver.manage().window().maximize();

    String baseUrl = "https://openweathermap.org/";
    String subject = "Other";
    String messageText = "Message";
    String expectedResult = "can't be blank";

    driver.get(baseUrl);
    Thread.sleep(5000);

    WebElement supportDropdownMenu = driver.findElement(By.id("support-dropdown"));
    supportDropdownMenu.click();
    Thread.sleep(2000);

    WebElement hrefAskAQuestion = driver.findElement(
             By.xpath("//div[@id='desktop-menu']//li/a[@href='https://home.openweathermap.org/questions']"));
    hrefAskAQuestion.click();
    Thread.sleep(2000);

        Iterator<String> iter = driver.getWindowHandles().iterator();
        iter.next();
        driver.switchTo().window(iter.next());
        Thread.sleep(3000);

    Select subjectTextbox = new Select(driver.findElement(By.id("question_form_subject")));
    subjectTextbox.selectByValue(subject);
    Thread.sleep(3000);

    WebElement messageTextArea = driver.findElement(By.id("question_form_message"));
    messageTextArea.click();
    messageTextArea.sendKeys(messageText);
    Thread.sleep(3000);


    WebElement reCaptchaIframe = driver.findElement(By.xpath("//iframe[@title='reCAPTCHA']"));
    Thread.sleep(2000);

        driver.switchTo().frame(reCaptchaIframe);
        Thread.sleep(2000);
        driver.findElement(By.id("recaptcha-anchor")).click();
        Thread.sleep(2000);
        driver.switchTo().defaultContent();
        Thread.sleep(2000);

    WebElement submitButton = driver.findElement(
            By.xpath("//input[@data-disable-with='Create Question form']"));
    submitButton.click();
    Thread.sleep(1000);

    WebElement cantBeBlankMessage = driver.findElement(
            By.xpath("//span[@class='help-block']"));

    String actualResult3 = cantBeBlankMessage.getText();
    Assert.assertEquals(actualResult3, expectedResult);

        driver.quit();
    }

/** TC_11_07
1.  Открыть базовую ссылку
2.  Нажать на единицы измерения Imperial: °F, mph
3.  Нажать на единицы измерения Metric: °C, m/s
4.  Подтвердить, что в результате этих действий, единицы измерения температуры изменились с F на С */


    @Test
    public void testCurrentTemperatureChangesWhenSwitching() throws InterruptedException {

        System.setProperty("webdriver.chrome.driver", "C:\\KIT\\Test\\Automation\\JavaForBeg\\chromedriver.exe");
        WebDriver driver = new ChromeDriver();
        driver.manage().window().maximize();

        String baseUrl = "https://openweathermap.org/";
        boolean expectedResult = true;

        driver.get(baseUrl);
        Thread.sleep(5000);

        WebElement optionImperial = driver.findElement(
                By.xpath("//div[@class='switch-container']//div[text()='Imperial: °F, mph']"));
        optionImperial.click();
        Thread.sleep(3000);

        WebElement currentTempHeading = driver.findElement(
                By.xpath("//div[@class='current-temp']//span[@class='heading']"));

        Assert.assertEquals(currentTempHeading.getText().contains("F"), expectedResult);

        WebElement optionMetric = driver.findElement(
                By.xpath("//div[@class='switch-container']//div[text()='Metric: °C, m/s']"));
        optionMetric.click();
        Thread.sleep(3000);

        Assert.assertEquals(currentTempHeading.getText().contains("C"), expectedResult);

        driver.quit();
    }

/** TC_11_08
 1.  Открыть базовую ссылку
 2.  Нажать на лого компании
 3.  Дождаться, когда произойдет перезагрузка сайта, и подтвердить, что текущая ссылка не изменилась */

    @Test
    public void testBaseUrlNotChangeWhenClickingOnLogo() throws InterruptedException {

    System.setProperty("webdriver.chrome.driver", "C:\\KIT\\Test\\Automation\\JavaForBeg\\chromedriver.exe");
    WebDriver driver = new ChromeDriver();
    driver.manage().window().maximize();

    String baseUrl = "https://openweathermap.org/";

    driver.get(baseUrl);
    Thread.sleep(5000);

    WebElement logo = driver.findElement(By.xpath("//li[@class='logo']"));
    logo.click();
    Thread.sleep(3000);

    Assert.assertEquals(driver.getCurrentUrl(), baseUrl);

    driver.quit();
}


/**    TC_11_09
1.  Открыть базовую ссылку
2.  В строке поиска в навигационной панели набрать “Rome”
3.  Нажать клавишу Enter
4.  Подтвердить, что вы перешли на страницу в ссылке которой содержатся слова “find” и “Rome”
5.  Подтвердить, что в строке поиска на новой странице вписано слово “Rome” */

    @Test
    public void testUrlContainsFindAndRome() throws InterruptedException {

    System.setProperty("webdriver.chrome.driver", "C:\\KIT\\Test\\Automation\\JavaForBeg\\chromedriver.exe");
    WebDriver driver = new ChromeDriver();
    driver.manage().window().maximize();

    String baseUrl = "https://openweathermap.org/";
    String cityName = "Rome";
    boolean newUrl = true;

    driver.get(baseUrl);
    Thread.sleep(5000);

    WebElement weatherForm = driver.findElement(
            By.xpath("//div[@id='desktop-menu']//input[@placeholder='Weather in your city']"));
    weatherForm.click();
    weatherForm.sendKeys(cityName);
    weatherForm.sendKeys(Keys.ENTER);
    Thread.sleep(5000);

    Assert.assertEquals(driver.getCurrentUrl().contains("find"), newUrl);
    Assert.assertEquals(driver.getCurrentUrl().contains("Rome"), newUrl);
    Assert.assertEquals(driver.findElement(By.id("search_str")).getAttribute("value"), cityName);

    driver.quit();
    }

/** TC_11_10
 1.  Открыть базовую ссылку
 2.  Нажать на пункт меню API
 3.  Подтвердить, что на открывшейся странице пользователь видит 30 оранжевых кнопок */

    @Test
    public void testOrangeButtonsQuantity() throws InterruptedException {

    System.setProperty("webdriver.chrome.driver", "C:\\KIT\\Test\\Automation\\JavaForBeg\\chromedriver.exe");
    WebDriver driver = new ChromeDriver();
    driver.manage().window().maximize();

    String baseUrl = "https://openweathermap.org/";

        driver.get(baseUrl);
        Thread.sleep(5000);
        int numButton = 30;

    driver.findElement(By.linkText("API")).click();
    Thread.sleep(5000);

    Assert.assertEquals(driver.findElements(
    By.xpath("//a[contains(@class, 'btn_block orange round') or contains(@class, 'ow-btn round btn-orange') ]")).size(),
            numButton);
    
        driver.quit();
    }

 }
