package UI;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public abstract class UI {


    protected String request;
    public abstract void show();

    //TODO maybe split in to Log UI and UserUI
    public String getInput(String message, String regex) {
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher;
        Scanner scanner = new Scanner(System.in);
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

    protected String profanityFilter(String message){
        String filterRegex = "(fuck|faggot|nigger|ass|asshole|kill|holocaust|hitler|ISIS|bomb|cunt|terrorism)";
        return message.replaceAll(filterRegex,"****");
    }
}
