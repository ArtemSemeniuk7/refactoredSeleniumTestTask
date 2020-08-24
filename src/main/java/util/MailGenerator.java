package util;

public class MailGenerator {
    private static final String mailSign = "@gmail.com";
    private static int mailNumber;

    public static String generateEmail(String browserName) {
        return browserName + "randomemail" + ++mailNumber + mailSign;
    }
}
