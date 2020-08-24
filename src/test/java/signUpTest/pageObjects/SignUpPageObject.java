package signUpTest.pageObjects;

import org.openqa.selenium.WebDriver;

public class SignUpPageObject {

    private final static String URL = "https://id.atlassian.com/login";
    private final static String EXPECTED_TITLE = "Log in to continue - Log in with Atlassian account";
    private final String BROWSER_NAME;
    private final WebDriver DRIVER;

    public SignUpPageObject(WebDriver DRIVER, String BROWSER_NAME) {
        this.DRIVER = DRIVER;
        this.BROWSER_NAME = BROWSER_NAME;
    }

    public static String getURL() {
        return URL;
    }

    public static String getEXPECTED_TITLE() {
        return EXPECTED_TITLE;
    }

    public String getBROWSER_NAME() {
        return BROWSER_NAME;
    }

    public WebDriver getDRIVER() {
        return DRIVER;
    }
}
