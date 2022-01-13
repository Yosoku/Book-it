package config;

import java.awt.*;

public class Configurations {
    public static final int WINDOW_WIDTH = 1280;// Toolkit.getDefaultToolkit().getScreenSize().width;
    public static final int WINDOW_HEIGHT = 1024;//Toolkit.getDefaultToolkit().getScreenSize().height;
    public static final String USERNAME_REGEX = "\\w*[a-zA-Z]\\w*";
    public static final String PASSWORD_REGEX = "\\b\\w{8,}\\b";
    public static final String NAME_REGEX = "^[a-zA-Z'-]+$";
    public static final String EMAIL_REGEX = "(?:[a-z0-9!#$%&'*+/=?^_`{|}~-]+(?:\\.[a-z0-9!#$%&'*+/=^_`{|}~-]+)*" +
            "|\"(?:[\\x01-\\x08\\x0b\\x0c\\x0e-\\x1f\\x21\\x23-\\x5b\\x5d-\\x7f]" +
            "|\\\\[\\x01-\\x09\\x0b\\x0c\\x0e-\\x7f])*\")" +
            "@" +
            "(?:(?:[a-z0-9](?:[a-z0-9-]*[a-z0-9])?\\.)+[a-z0-9](?:[a-z0-9-]*[a-z0-9])?" +
            "|\\[(?:(?:(2(5[0-5]|[0-4][0-9])" +
            "|1[0-9][0-9]|[1-9]?[0-9]))\\.){3}(?:(2(5[0-5]|[0-4][0-9])\n" +
            "|1[0-9][0-9]|[1-9]?[0-9])|[a-z0-9-]*[a-z0-9]:(?:" +
            "[\\x01-\\x08\\x0b\\x0c\\x0e-\\x1f\\x21-\\x5a\\x53-\\x7f]" +
            "|\\\\[\\x01-\\x09\\x0b\\x0c\\x0e-\\x7f])+)\\])"; //disgusting
    public static final String AGE_REGEX = "^\\d{2}$";
    public static final String PHONE_REGEX = "^\\d{10}$";
    public static final Color INVALID_TEXT_COLOR = Color.red;
    public static final Color VALID_TEXT_COLOR = Color.GREEN;
    public static final String POSITIVE_INTEGER_REGEX = "^[0-9]\\d*$";
    public static final String ANY_WORD = "^[A-Za-z]*$";
    public static final String ADDRESS_REGEX = "^[A-Za-z]+\\s[0-9]{1,3}$";
    public static final String ANY_TEXT_REGEX = ".*";
    public static final String CREDIT_CARD_REGEX = "^(?:4[0-9]{12}(?:[0-9]{3})?|[25][1-7][0-9]{14}|6(?:011|5[0-9][0-9])" +
            "[0-9]{12}|3[47][0-9]{13}|3(?:0[0-5]|[68][0-9])[0-9]{11}|(?:2131|1800|35\\d{3})\\d{11})$";
    public static final String CVV_REGEX = "^[0-9]{3}$";
    public static final String CONFIRMATION_MESSAGE = "Hello read this email to confirm your account";
    public static final String CONFIRMATION_MESSAGE_SUBJECT = "Authentication email";
}
