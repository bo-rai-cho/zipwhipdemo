package exceptions;

import org.springframework.web.client.RestClientException;

/**
 * @author Nikolay Ponomarev
 */
public class AccessDeniedException extends RestClientException {

    public AccessDeniedException() {
        super("Access denied");
    }
}
