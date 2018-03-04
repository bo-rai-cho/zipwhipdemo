package clients.handlers;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import exceptions.AccessDeniedException;
import exceptions.ExternalServiceException;
import exceptions.WrongArgumentException;
import lombok.extern.slf4j.Slf4j;
import model.ErrorMessage;
import model.ErrorType;
import model.response.ErrorResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.client.ClientHttpResponse;
import org.springframework.web.client.ResponseErrorHandler;
import org.springframework.web.client.RestClientException;

import java.io.IOException;
import java.io.InputStream;

import static model.ErrorType.BUSINESS_ERROR;


/**
 * @author Nikolay Ponomarev
 */
@Slf4j
public class ZipRestTestErrorHandler implements ResponseErrorHandler {

    private final ObjectMapper objectMapper;

    public ZipRestTestErrorHandler(ObjectMapper objectMapper) {
        this.objectMapper = objectMapper;
    }

    @Override
    public boolean hasError(ClientHttpResponse response) throws IOException {
        return !response.getStatusCode().is2xxSuccessful();
    }

    @Override
    public void handleError(ClientHttpResponse response) throws IOException {

        HttpStatus status = response.getStatusCode();

        ErrorResponse er = convert(response.getBody(), ErrorResponse.class); // Try convert this response to error message

        if (er != null && er.getResponse() != null) {
            businessError(status, er.getResponse());
        } else {
            serviceError(status);
        }
    }

    private void serviceError(HttpStatus status) {

        switch (status) {

            case INTERNAL_SERVER_ERROR:
            case BAD_REQUEST:
                throw new ExternalServiceException();

            case FORBIDDEN:
                throw new AccessDeniedException();

            default:
                throw new RestClientException("Unknown exception");
        }
    }

    private void businessError(HttpStatus receivedStatus, ErrorMessage receivedMessage) {

        int businessCode = receivedMessage.getCode();

        switch (businessCode) {

            case -703:
                assertError(BUSINESS_ERROR, receivedMessage, receivedStatus);
                throw new WrongArgumentException();
        }
    }

    private void assertError(ErrorType expectedErrorType, ErrorMessage messageReceived, HttpStatus statusReceived) {

        assert statusReceived == expectedErrorType.getStatus() : "Expected HTTP status " + expectedErrorType.getStatus();
        assert messageReceived.getDesc().equals(expectedErrorType.getMessage()) : "Expected error description " + expectedErrorType.getMessage();
        assert messageReceived.getCode() == expectedErrorType.getCode() : "Expected error code " + expectedErrorType.getCode();
    }

    private <T> T convert(InputStream is, Class<T> clazz) throws IOException {

        try {
            return objectMapper.readValue(is, clazz);
        } catch (JsonParseException | JsonMappingException e) {
            log.error(e.getMessage(), e);
        }

        return null;
    }
}
