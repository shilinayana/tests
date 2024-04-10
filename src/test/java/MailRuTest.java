import Page.MailHomePage;
import Page.MailLoginPage;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.PageFactory;

import java.time.Duration;

public class MailRuTest {
    static WebDriver driver;
    private String email = "testuser.24@mail.ru";
    private String password = "HelloWorld!";
    private String invalidEmail = "пппппппп.24@mail.ru";


    @Before
    public void setUp() {
        System.setProperty("webdriver.chrome.driver", "src/main/resources/chromedriver");
        driver = new ChromeDriver();
    }

    @After
    public void tearDown() {
        driver.quit();
    }

    @Test
    public void inputValidLoginPassword() {
        MailLoginPage loginPage = PageFactory.initElements(driver, MailLoginPage.class);
        try {
            loginPage.signIn(email, password);
        } catch (NullPointerException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void noLogin() {
        MailLoginPage loginPage = PageFactory.initElements(driver, MailLoginPage.class);
        Assert.assertEquals(loginPage.signIn(), "Поле «Имя аккаунта» должно быть заполнено");
    }

    @Test
    public void inputValidLoginWithoutPassword() {
        MailLoginPage loginPage = PageFactory.initElements(driver, MailLoginPage.class);
        Assert.assertEquals(loginPage.signIn(email), "Поле «Пароль» должно быть заполнено");
    }

    @Test
    public void invalidLogin() {
        MailLoginPage loginPage = PageFactory.initElements(driver, MailLoginPage.class);
        Assert.assertEquals(loginPage.signIn(invalidEmail), "Такой аккаунт не зарегистрирован");
    }

    @Test
    public void findLetter() {
        MailLoginPage loginPage = PageFactory.initElements(driver, MailLoginPage.class);
        loginPage.signIn(email, password);
        MailHomePage homePage = PageFactory.initElements(driver, MailHomePage.class);
        try {
            homePage.clickLetter();
        } catch (NullPointerException e) {
            System.out.println("Указанное письмо не найдено");
        }

    }

    @Test
    public void checkAuthor() {
        MailLoginPage loginPage = PageFactory.initElements(driver, MailLoginPage.class);
        loginPage.signIn(email, password);
        MailHomePage homePage = PageFactory.initElements(driver, MailHomePage.class);
        try {
            homePage.clickLetter();
            driver.manage().timeouts().implicitlyWait(Duration.ofMillis(600));
        } catch (NullPointerException e) {
            System.out.println("Указанное письмо не найдено");
        }
        org.testng.Assert.assertEquals(homePage.getAuthor(),"Яна Шилина");
    }

    @Test
    public void checkTheme() {
        MailLoginPage loginPage = PageFactory.initElements(driver, MailLoginPage.class);
        loginPage.signIn(email, password);
        MailHomePage homePage = PageFactory.initElements(driver, MailHomePage.class);
        try {
            homePage.clickLetter();
            driver.manage().timeouts().implicitlyWait(Duration.ofMillis(600));
        } catch (NullPointerException e) {
            System.out.println("Указанное письмо не найдено");
        }
        org.testng.Assert.assertEquals(homePage.getTheme(),"Первое сообщение");
        }

    @Test
    public void checkLetter() {
        MailLoginPage loginPage = PageFactory.initElements(driver, MailLoginPage.class);
        loginPage.signIn(email, password);
        MailHomePage homePage = PageFactory.initElements(driver, MailHomePage.class);
        try {
            homePage.clickLetter();
            driver.manage().timeouts().implicitlyWait(Duration.ofMillis(600));
        } catch (NullPointerException e) {
            System.out.println("Указанное письмо не найдено");
        }
        org.testng.Assert.assertEquals(homePage.getLetterText(),"Привет!\n" + "Это твое первое сообщение \uD83D\uDE04");
    }
}
