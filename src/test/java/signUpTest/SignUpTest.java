package signUpTest;

import org.junit.After;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxBinary;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import java.util.concurrent.ExecutionException;

public class SignUpTest {
    private SignUpPageObject firefoxSignUpPageObject;
    private SignUpPageObject chromeSignUpPageObject;

    @Test
    public void mozillaTest() throws ExecutionException, InterruptedException {
        System.setProperty("webdriver.gecko.driver",
                "/home/a/IdeaProjects/seleniumTestTask/src/main/resources/geckodriver-v0.27.0-linux64/geckodriver");

        FirefoxBinary firefoxBinary = new FirefoxBinary();
        FirefoxOptions firefoxOptions = new FirefoxOptions();

        firefoxOptions.setBinary(firefoxBinary);
        firefoxOptions.setHeadless(true);

        WebDriver driver = new FirefoxDriver(firefoxOptions);
        firefoxSignUpPageObject = new SignUpPageObject(driver);

        firefoxSignUpPageObject.getStartedConcurent(1, "firefox");
        firefoxSignUpPageObject.getStartedConcurent(2, "firefox");
        firefoxSignUpPageObject.getStartedConcurent(5, "firefox");
        firefoxSignUpPageObject.getStartedConcurent(10,"firefox");
    }

    @Test
    public void chromeTest() throws ExecutionException, InterruptedException {
        System.setProperty("webdriver.chrome.driver",
                "/home/a/IdeaProjects/seleniumTestTask/src/main/resources/chromedriver_linux64/chromedriver");
        ChromeOptions chromeOptions = new ChromeOptions();
        chromeOptions.addArguments("headless");

        WebDriver driver = new ChromeDriver(chromeOptions);
        chromeSignUpPageObject = new SignUpPageObject(driver);

        chromeSignUpPageObject.getStartedConcurent(1, "chrome");
        chromeSignUpPageObject.getStartedConcurent(2, "chrome");
        chromeSignUpPageObject.getStartedConcurent(5, "chrome");
        chromeSignUpPageObject.getStartedConcurent(10, "chrome");
    }

    @After
    public void end() {
        firefoxSignUpPageObject.getDriver().quit();
        chromeSignUpPageObject.getDriver().quit();
    }
}
