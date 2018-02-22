package clients.interceptors;


import exceptions.ExternalServiceException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.client.ClientHttpRequestExecution;
import org.springframework.http.client.ClientHttpRequestInterceptor;
import org.springframework.http.client.ClientHttpResponse;

import java.io.IOException;
import java.nio.charset.Charset;

@Slf4j
public class ClientCustomInterceptor implements ClientHttpRequestInterceptor{

    @Override
    public ClientHttpResponse intercept(HttpRequest request, byte[] body, ClientHttpRequestExecution execution) throws IOException {

        request.getHeaders().setContentType(MediaType.APPLICATION_FORM_URLENCODED);
        traceRequest(request, body);
        ClientHttpResponse response = execution.execute(request, body);
        traceResponse(response);
        return response;
    }

    private void traceRequest(HttpRequest request, byte[] body) {

        log.debug("Request [{}] {}", request.getMethod(), request.getURI());
        log.debug("Request headers {}", request.getHeaders());
        if (body.length > 0) {
            log.debug("Request body {}", new String(body, Charset.forName("UTF-8")));
        } else {
            log.debug("Request body empty");
        }
    }

    private void traceResponse(ClientHttpResponse response) throws IOException {
        log.debug("Response status [{}] {}", response.getStatusCode().toString(), response.getStatusText());
        if (!response.getStatusCode().is2xxSuccessful()) {
            try {
                validate(response);
            } catch (ExternalServiceException e) {
                log.error(e.getMessage(), e);
            }
        }
    }

    /**
     * All-in-one validator of responses
     *
     * @param response Client response
     * @throws IOException IOException
     * @throws ExternalServiceException External service exception
     */
    private void validate(ClientHttpResponse response) throws IOException, ExternalServiceException {

        HttpStatus status = response.getStatusCode();

        switch (status) {
            case INTERNAL_SERVER_ERROR:
                throw new ExternalServiceException();
                // and so on
        }
    }
}
