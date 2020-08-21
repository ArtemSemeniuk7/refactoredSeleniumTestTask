package signUpTest;

public class MailGenerator {
    private static final String mailSign = "@gmail.com";
    private String browserName;
    private int mailNumber;

    public MailGenerator(String browserName) {
        this.browserName = browserName.toLowerCase();
    }

    public String generateEmail() {
        String emailString = browserName + "randomemail" + ++mailNumber + mailSign;
        return emailString;
    }
}
