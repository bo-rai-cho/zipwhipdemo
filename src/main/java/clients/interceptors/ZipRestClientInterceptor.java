package clients.interceptors;


import lombok.extern.slf4j.Slf4j;

import org.springframework.http.HttpRequest;
import org.springframework.http.client.ClientHttpRequestExecution;
import org.springframework.http.client.ClientHttpRequestInterceptor;
import org.springframework.http.client.ClientHttpResponse;

import java.io.*;
import java.nio.charset.Charset;
import java.util.stream.Collectors;

@Slf4j
public class ZipRestClientInterceptor implements ClientHttpRequestInterceptor{

    @Override
    public ClientHttpResponse intercept(HttpRequest request, byte[] body, ClientHttpRequestExecution execution) throws IOException {

        traceRequest(request, body);
        ClientHttpResponse response = execution.execute(request, body);
        traceResponse(response);
        return response;
    }

    private void traceRequest(HttpRequest request, byte[] body) throws IOException {

        log.debug("Request [{}] {}", request.getMethod(), request.getURI());
        log.debug("Request headers {}", request.getHeaders());
        if (body.length > 0) {
            log.debug("Request body {}", new String(body, Charset.forName("UTF-8")));
        } else {
            log.debug("Request body is empty");
        }
    }

    private void traceResponse(ClientHttpResponse response) throws IOException {

        log.debug("Response status [{}] {}", response.getStatusCode().toString(), response.getStatusText());
        log.debug("Response body {}", convert(response.getBody(), Charset.defaultCharset()));
    }

    private String convert(InputStream inputStream, Charset charset) throws IOException {

        try (BufferedReader br = new BufferedReader(new InputStreamReader(inputStream, charset))) {
            return br.lines().collect(Collectors.joining(System.lineSeparator()));
        }
    }
}
