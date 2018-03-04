package model.request;

import org.springframework.http.HttpEntity;
import org.springframework.http.MediaType;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;

/**
 * @author Nikolay Ponomarev
 */
public class FormUrlEncodedData extends HttpEntity<MultiValueMap<String, String>> {

    public FormUrlEncodedData() {
        super(new LinkedMultiValueMap<>(),
                new HttpHeadersBuilder().contentType(MediaType.APPLICATION_FORM_URLENCODED).build());
    }

    public FormUrlEncodedData add(String parameter, String value) {
        if (parameter != null && value != null) {
            getBody().add(parameter, value);
        }
        return this;
    }

    public static FormUrlEncodedData sessionData(String sessionKey) {
        return new FormUrlEncodedData().add("sessionKey", sessionKey);
    }
}
