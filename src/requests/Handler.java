package requests;

/**
 * An interface representing a request handler to handle different types of requests like Customer requests,Broker requests
 * etc
 *
 * @author Edward Koulakidis
 */
@FunctionalInterface
public interface Handler {
    void handleRequests();
}
