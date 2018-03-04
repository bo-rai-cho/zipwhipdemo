package model.request;

import org.springframework.http.HttpEntity;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;

/**
 * @author Nikolay Ponomarev
 */
public final class EntityUrlEncodedData extends HttpEntity<MultiValueMap<String, String>> {

    public EntityUrlEncodedData() {
        super(new LinkedMultiValueMap<>(), HttpHeadersFactory.appFormUrlEncoded());
    }

    public static EntityUrlEncodedData session(String sessionKey) {
        return new EntityUrlEncodedData().add("session", sessionKey);
    }

    public EntityUrlEncodedData add(String parameter, String value) {

        if (parameter != null && value != null) {
            getBody().add(parameter, value);
        }
        return this;
    }
}
