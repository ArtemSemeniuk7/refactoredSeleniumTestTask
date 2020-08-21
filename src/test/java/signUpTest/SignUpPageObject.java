package signUpTest;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.util.ArrayList;
import java.util.concurrent.*;
import static org.junit.Assert.assertEquals;

public class SignUpPageObject {

    private final static String url = "https://id.atlassian.com/login";
    private final static String expectedTitle = "Log in to continue - Log in with Atlassian account";
    private ArrayList<String> writeList;
    private WebDriver driver;

    public SignUpPageObject(WebDriver driver) {
        this.driver = driver;
    }

    public void open() {
        driver.get(url);
    }

    public void getStartedConcurent(int numberOfThreads, String browserName) throws ExecutionException, InterruptedException {

        WebDriverWait webDriverWait = new WebDriverWait(driver, 300);
        MailGenerator mailGenerator = new MailGenerator(browserName);

        ExecutorService executorService = Executors.newFixedThreadPool(numberOfThreads);
        Callable <String> callable = new Callable<String>() {
            @Override
            public String call() {
                open();

                String currentTitle = driver.getTitle();
                String currentEmail = mailGenerator.generateEmail();
                driver.findElement(By.id("username")).
                        sendKeys(currentEmail);

                assertEquals(currentTitle, expectedTitle);

                long startTime = System.currentTimeMillis();

                WebElement signIn = driver.findElement(By.id("login-submit"));
                signIn.click();
                webDriverWait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.id("signup-submit")));
                WebElement submit = driver.findElement(By.id("signup-submit"));

                long endtime = System.currentTimeMillis();

                assertEquals(submit.getText(), "Sign up");

                return browserName + " time : " + (endtime - startTime) +
                        "millis, email : "  + currentEmail +
                        " | number of thread : " + numberOfThreads + "  --- TEST PASSED";
            }
        };

        writeList = new ArrayList<>();
        for (int i = 0; i < numberOfThreads; i++) {
            Future<String> future = executorService.submit(callable);
            String log = future.get();
            System.out.println(log);
            writeList.add(log);
        }

        ToFile toFile = new ToFile(writeList);
        toFile.writeToFile();
    }

    public WebDriver getDriver() {
        return driver;
    }
}
