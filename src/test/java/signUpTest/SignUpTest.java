package signUpTest;

import io.qameta.allure.*;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxBinary;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import signUpTest.pageObjects.SignUpPageObject;
import util.MailGenerator;
import util.ToFile;
import java.util.ArrayList;
import java.util.concurrent.*;
import static org.junit.Assert.assertEquals;

public class SignUpTest {
    private SignUpPageObject signUpPageObject;
    private final ArrayList<String> writeList = new ArrayList<>();
    private WebDriver driver;

    @FindBy(id = "login-submit")
    private WebElement signIn;

    @FindBy(id = "signup-submit")
    private WebElement submit;

    @FindBy(id = "username")
    private WebElement enterEmail;


    @Epic("TESTING FOR https://id.atlassian.com/login tasks")
    @Feature(value = "Testing of Firefox")
    @Severity(SeverityLevel.BLOCKER)
    @Description("In this test we will login with correct email. We automated test case and measured the action time")
    @Test
    public void firefoxTest() throws ExecutionException, InterruptedException {
        System.setProperty("webdriver.gecko.driver",
                "src/main/resources/geckodriver-v0.27.0-linux64/geckodriver");

        FirefoxBinary firefoxBinary = new FirefoxBinary();
        FirefoxOptions firefoxOptions = new FirefoxOptions();
        firefoxOptions.setBinary(firefoxBinary);
        firefoxOptions.setHeadless(true);

        driver = new FirefoxDriver(firefoxOptions);
        signUpPageObject = new SignUpPageObject(driver, "firefox");

        PageFactory.initElements(driver, this);

        concurrentRun(1);
        concurrentRun(2);
        concurrentRun(5);
        //concurrentRun(10);
    }

    @Epic("TESTING FOR https://id.atlassian.com/login tasks")
    @Feature(value = "Testing of Chrome")
    @Severity(SeverityLevel.BLOCKER)
    @Description("In this test we will login with correct email and measure the action time")
    @Test
    public void chromeTest() throws ExecutionException, InterruptedException {
        System.setProperty("webdriver.chrome.driver",
                "src/main/resources/chromedriver_linux64/chromedriver");
        ChromeOptions chromeOptions = new ChromeOptions();
        chromeOptions.addArguments("headless");

        driver = new ChromeDriver(chromeOptions);
        signUpPageObject = new SignUpPageObject(driver, "chrome");

        PageFactory.initElements(driver, this);

        concurrentRun(1);
        concurrentRun(2);
        concurrentRun(5);
        //concurrentRun(10);
    }

    @Step("Open connection")
    public void open() {
        signUpPageObject.getDRIVER().get(SignUpPageObject.getURL());
        String currentTitle = driver.getTitle();
        assertEquals(currentTitle, SignUpPageObject.getEXPECTED_TITLE());
    }

    @Step("Log in in site")
    public String login () {
        WebDriverWait webDriverWait = new WebDriverWait(driver, 300);

        String currentEmail = MailGenerator.generateEmail(signUpPageObject.getBROWSER_NAME());
        enterEmail.sendKeys(currentEmail);

        long startTime = System.currentTimeMillis();

        signIn.click();
        webDriverWait.until(ExpectedConditions.
                visibilityOf(submit));

        long endtime = System.currentTimeMillis();

        assertEquals(submit.getText(), "Sign up");

        return signUpPageObject.getBROWSER_NAME() + " time : " + (endtime - startTime) +
                "millis, email : " + currentEmail +
                " | number of thread : ";
    }

    @Step
    public void concurrentRun(int numberOfThreads) throws ExecutionException, InterruptedException {
        ExecutorService executorService = Executors.newFixedThreadPool(numberOfThreads);
        Callable<String> callable = () -> {
            open();
            return login() + numberOfThreads + "  --- TEST PASSED";
        };

        writeList.clear();
        for (int i = 0; i < numberOfThreads; i++) {
            Future<String> future = executorService.submit(callable);
            String log = future.get();
            writeList.add(log);
        }
        ToFile.writeToFile(writeList);
    }

    @AfterEach
    public void end() {
        System.out.println("---- end of " + signUpPageObject.getBROWSER_NAME() + " test ----");
        driver.quit();
    }
}
