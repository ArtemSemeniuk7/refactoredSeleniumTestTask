package signUpTest.pageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class SignUpPageObject {

    private final static String URL = "https://id.atlassian.com/login";
    private final static String EXPECTED_TITLE = "Log in to continue - Log in with Atlassian account";
    private String browserName;
    private WebDriver driver;

    @FindBy(id = "login-submit")
    private WebElement signIn;

    @FindBy(id = "username")
    private WebElement enterEmail;

    @FindBy(id = "signup-submit")
    private WebElement signUp;

    public SignUpPageObject(WebDriver driver, String browserName) {
        this.driver = driver;
        this.browserName = browserName;
        PageFactory.initElements(driver, this);
    }

    public static String getURL() {
        return URL;
    }

    public static String getExpectedTitle() {
        return EXPECTED_TITLE;
    }

    public String getBrowserName() {
        return browserName;
    }

    public WebDriver getDriver() {
        return driver;
    }

    public WebElement getSignIn() {
        return signIn;
    }

    public WebElement getEnterEmail() {
        return enterEmail;
    }

    public WebElement getSignUp() {
        return signUp;
    }
}
