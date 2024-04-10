package Page;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;


public class MailHomePage {

    private WebDriver driver;
    public MailHomePage (WebDriver driver){
        this.driver=driver;
    }

    @FindBy(xpath = "//span[text()='Первое сообщение']")
    private WebElement firstMessage;

    @FindBy(xpath = "//span[@class='letter-contact letter-contact_pony-mode']")
    private WebElement author;

    @FindBy(xpath = "//div[@class='thread__subject-line']//h2")
    private WebElement theme;

    @FindBy(xpath = "//div[@dir='ltr']")
    private WebElement letterText;

    public void clickLetter (){
        firstMessage.click();
    }

    public String getAuthor() { return author.getText(); }

    public String getTheme() {
         return theme.getText();
    }

    public String getLetterText() {
        return letterText.getText();
    }
}
