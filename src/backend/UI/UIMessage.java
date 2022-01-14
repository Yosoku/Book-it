package backend.UI;

/**
 * An enum for printing different messages to the console
 *
 * @author Fwteinos Wawaroutas
 */
public enum UIMessage {
    SIGN_IN_SUCCESS("You have successfully logged in!"),
    SIGN_IN_FAILED("Oops,that's not a match"),
    SIGN_UP_FAILED("We're so sorry but there is already an account under those credentials,please sign up again"),
    SIGN_UP_SUCCESS("Your account has been created"),
    CONFIRMATION_FAILED("Your account has not been confirmed yet." +
            " You will be redirected to your inbox to look for a confirmation email."),
    ENTRY_NOT_FOUND("Entry was not found. Perhaps double check if the parameters were correct?"),
    ENTRY_DELETED("Entry was deleted succesfully"),
    LOADING("The application is loading,please wait..."),
    FEATURE_UNAVAILABLE("This feature is being currently developed"),
    SAVING("The application is saving all progress. Please don't exit this window");
    public String message;

    UIMessage(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }
}
