package exceptions;


import org.springframework.web.client.RestClientException;

public class ExternalServiceException extends RestClientException {

    public ExternalServiceException() {
        super("Remote server error");
    }
}
