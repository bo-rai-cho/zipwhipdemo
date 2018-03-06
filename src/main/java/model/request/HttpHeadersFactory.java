package model.request;


import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;

/**
 * @author Nikolay Ponomarev
 */
public final class HttpHeadersFactory {

    private HttpHeadersFactory() {
        // prevent instantiation - util class
    }

    public static HttpHeaders contentType(MediaType mediaType) {

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(mediaType);
        return headers;
    }

    public static HttpHeaders appFormUrlEncoded() {

        return contentType(MediaType.APPLICATION_FORM_URLENCODED);
    }
}
