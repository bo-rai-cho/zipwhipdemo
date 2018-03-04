package clients.handlers;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import exceptions.ExternalServiceException;
import exceptions.WrongArgumentException;
import lombok.extern.slf4j.Slf4j;
import model.ErrorMessage;
import model.ErrorType;
import model.responses.ErrorResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.client.ClientHttpResponse;
import org.springframework.web.client.ResponseErrorHandler;
import org.springframework.web.client.RestClientException;

import java.io.IOException;
import java.io.InputStream;


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

        ErrorResponse er = convert(response.getBody()); // Try convert this response to error message

        if (er != null && er.getResponse() != null) {
            assert !er.getSuccess() : "Success status [true]";
            throwBusinessError(er.getResponse(), response.getStatusCode());
        } else {
            throwServiceError(response.getStatusCode());
        }
    }

    private void throwServiceError(HttpStatus status) {

        switch (status) {

            case INTERNAL_SERVER_ERROR:
            case BAD_REQUEST:
                throw new ExternalServiceException();
            default:
                throw new RestClientException("Unknown exception");
        }
    }

    private void throwBusinessError(ErrorMessage receivedMessage, HttpStatus receivedStatus) {

        int businessCode = receivedMessage.getCode();

        switch (businessCode) {
            case -703:
                assertBusinessError(receivedMessage, receivedStatus, ErrorType.BUSINESS_ERROR);
                throw new WrongArgumentException();
        }
    }

    private void assertBusinessError(ErrorMessage messageReceived, HttpStatus statusReceived, ErrorType expectedErrorType) {

        assert statusReceived == expectedErrorType.getStatus() :
                "Expected HTTP status " + expectedErrorType.getStatus();

        assert messageReceived.getDesc().equals(expectedErrorType.getMessage()) :
                "Expected error description " + expectedErrorType.getMessage();

        assert messageReceived.getCode() == expectedErrorType.getCode() :
                "Expected error code " + expectedErrorType.getCode();
    }

    private ErrorResponse convert(InputStream is) throws IOException {

        try {
            return objectMapper.readValue(is, ErrorResponse.class);
        } catch (JsonParseException | JsonMappingException e) {
            log.error(e.getMessage(), e);
        }

        return null;
    }
}
