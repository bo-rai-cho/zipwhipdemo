package clients.interceptors;


import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpRequest;
import org.springframework.http.client.ClientHttpRequestExecution;
import org.springframework.http.client.ClientHttpRequestInterceptor;
import org.springframework.http.client.ClientHttpResponse;

import java.io.IOException;
import java.nio.charset.Charset;

@Slf4j
public class ClientCustomInterceptor implements ClientHttpRequestInterceptor{

    @Override
    public ClientHttpResponse intercept(HttpRequest request, byte[] body, ClientHttpRequestExecution execution) throws IOException {

        traceRequest(request, body);
        ClientHttpResponse response = execution.execute(request, body);
        traceResponse(response);
        return response;
    }

    private void traceRequest(HttpRequest request, byte[] body) {

        log.debug("Request [{}] {}", request.getMethod(), request.getURI());
        if (body.length > 0) {
            log.debug("Request body {}", new String(body, Charset.forName("UTF-8")));
        } else {
            log.debug("Request body empty");
        }
    }

    private void traceResponse(ClientHttpResponse response) throws IOException {
        log.debug("Response status [{}] {}", response.getStatusCode().toString(), response.getStatusText());
    }
}
