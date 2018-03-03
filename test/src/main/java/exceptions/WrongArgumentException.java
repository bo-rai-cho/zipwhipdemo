package exceptions;

import org.springframework.web.client.RestClientException;

/**
 * @author Nikolay Ponomarev
 */
public class WrongArgumentException extends RestClientException {

    public WrongArgumentException() {
        super("Wrong argument");
    }
}
