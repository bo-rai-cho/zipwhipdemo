package clients;

import lombok.Getter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestOperations;

/**
 * @author Nikolay Ponomarev
 */
@Slf4j
@Getter
public abstract class RestClient {

    private final RestOperations restOperations;

    public RestClient(RestOperations restOperations) {
        this.restOperations = restOperations;
    }

    <T> ResponseEntity<T> post(String url, Class<T> clazz) {
        return restOperations.postForEntity(url, null, clazz);
    }
}
