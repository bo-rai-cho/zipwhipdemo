package model.request;


import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;

/**
 * @author Nikolay Ponomarev
 */
public final class HttpHeadersBuilder {

    private final HttpHeaders headers;

    public HttpHeadersBuilder() {
        this.headers = new HttpHeaders();
    }

    public HttpHeadersBuilder contentType(MediaType mediaType) {
        this.headers.setContentType(mediaType);
        return this;
    }

    public HttpHeaders build() {
        return headers;
    }
}
