package exceptions;



public class ExternalServiceException extends Exception {

    public ExternalServiceException() {
        super("Something is wrong on the server side");
    }
}
