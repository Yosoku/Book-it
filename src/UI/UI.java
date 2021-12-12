package UI;

import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public abstract class UI {


    protected String request;
    public abstract void show();


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


    public String getRequest(){return request;}

    public static void LOG(UIMessage errorMessage) {
        System.out.println(errorMessage.getMessage());
    }

    //TODO Expand with dictionary file
    protected String profanityFilter(String message){
        String filterRegex = "(fuck|faggot|nigger|ass|asshole|kill|holocaust|hitler|ISIS|bomb|cunt|terrorism)";
        return message.replaceAll(filterRegex,"****");
    }
}
