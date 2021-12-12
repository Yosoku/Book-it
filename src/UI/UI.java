package UI;

import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
/**
 * Abstract class that represents a User Interface and provides a show method to show the UI and a getInput method
 * to get valid input
 *
 * @author Fwteinos Wawaroutas
 */
public abstract class UI {


    protected String request;

    public abstract void show();

    /**
     * Method to get regulated input using regular expressions
     * @param regex regex to match
     * @param message the message to print if input doesnt match
     */
    public String getInput(String message, String regex) {
        Scanner scanner = new Scanner(System.in);
        if (regex.equals("")) {
            System.out.println(message);
            return scanner.nextLine();
        }
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher;
        String input;
        do {
            System.out.println(message);
            input = scanner.nextLine();
            matcher = pattern.matcher(input);
        } while (!matcher.matches());
        return input;
    }


    public String getRequest() {
        return request;
    }

    /**
     * Prints a message to the console
     */
    public static void LOG(UIMessage message) {
        System.out.println(message.getMessage());
    }

    /**
     * Remove profanities from message
     *
     * @param message the message to remove profanity
     */
    //TODO Expand with dictionary file
    protected String profanityFilter(String message) {
        String filterRegex = "(fuck|faggot|nigger|ass|asshole|kill|holocaust|hitler|ISIS|bomb|cunt|terrorism)";
        return message.replaceAll(filterRegex, "****");
    }
}
