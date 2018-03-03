package clients.handlers;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import exceptions.ExternalServiceException;
import lombok.extern.slf4j.Slf4j;
import model.ErrorMessage;
import model.responses.ErrorResponse;
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

        switch (response.getStatusCode()) {
            case INTERNAL_SERVER_ERROR:
            case BAD_REQUEST:
                return true;
        }
        return false;
    }

    @Override
    public void handleError(ClientHttpResponse response) throws IOException {

        ErrorResponse er = convert(response.getBody());

        assert er != null;
        assert !er.getSuccess();

        ErrorMessage em = er.getResponse();

        switch (response.getStatusCode()) {

            case INTERNAL_SERVER_ERROR:
            case BAD_REQUEST:
                assert em == null;
                throw new ExternalServiceException();
            default:
                throw new RestClientException("Unknown exception");
        }
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
