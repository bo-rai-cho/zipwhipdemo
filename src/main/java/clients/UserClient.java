package clients;


import lombok.extern.slf4j.Slf4j;
import model.responses.LoginResponse;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestOperations;

@Slf4j
public final class UserClient {

    private final RestOperations restOperations;

    public UserClient(final RestOperations restOperations) {
        this.restOperations = restOperations;
    }

    /**
     * Login
     *
     * @param username Username
     * @param password Password
     * @return Session key
     */
    public String login(String username, String password) {

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);

        MultiValueMap<String, String> map = new LinkedMultiValueMap<>();

        if (username != null) {
            map.add("username", username);
        }
        if (password != null) {
            map.add("password", password);
        }

        HttpEntity<MultiValueMap<String, String>> request = new HttpEntity<>(map, headers);

        return restOperations.postForEntity("/user/login", request, LoginResponse.class).getBody().getResponse();
    }
}
