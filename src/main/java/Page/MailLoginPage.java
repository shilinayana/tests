package Page;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import java.time.Duration;


public class MailLoginPage {

    final Duration WAIT = Duration.ofSeconds(5);
    private WebDriver driver;
    private String password;

    public MailLoginPage(WebDriver driver){
        this.driver=driver;
    }

        @FindBy(xpath = "//button[text()='Войти']")
                WebElement signInButton;

        @FindBy(xpath = "//iframe[@class='ag-popup__frame__layout__iframe']")
                WebElement iframe;

        @FindBy(xpath = "//input[@name='username']")
                WebElement login;

        @FindBy(xpath = "//div[@class='submit-block-wrap']//button")
                WebElement inputPassButton;

        @FindBy(xpath = "//input[@name='password']")
                WebElement pass;

        @FindBy(xpath = "//span[text()='Войти']")
                WebElement loginButton;

        @FindBy(xpath = "//div[@data-test-id='error-footer-text']//small")
        WebElement loginError;

        @FindBy(xpath = "//div[@data-test-id='password-input-error']")
                WebElement passwordError;



        public void signIn (String email, String password) {
            driver.get("https://mail.ru/");
            driver.manage().timeouts().implicitlyWait(WAIT);
            signInButton.click();
            driver.switchTo().frame(iframe);
            login.sendKeys(email);
            inputPassButton.click();
            driver.manage().timeouts().implicitlyWait(WAIT);
            pass.sendKeys(password);
            inputPassButton.click();
            driver.switchTo().defaultContent();
            driver.manage().timeouts().implicitlyWait(WAIT);
        }


        public String signIn () {
            driver.get("https://mail.ru/");
            driver.manage().timeouts().implicitlyWait(WAIT);
            signInButton.click();
            driver.switchTo().frame(iframe);
            inputPassButton.click();
            return loginError.getText();
        }

        public String signIn (String email) {
            driver.get("https://mail.ru/");
            driver.manage().timeouts().implicitlyWait(WAIT);
            signInButton.click();
            driver.switchTo().frame(iframe);
            login.sendKeys(email);
            inputPassButton.click();
            if (loginError.isDisplayed()){
                return loginError.getText();
            }
            else {
                driver.manage().timeouts().implicitlyWait(WAIT);
                loginButton.click();
                return passwordError.getText();
            }

        }




}
