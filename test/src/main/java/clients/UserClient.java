package clients;


import lombok.extern.slf4j.Slf4j;
import model.responses.LoginResponse;
import org.springframework.http.HttpMethod;
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

        return restOperations.exchange(
                "/user/login?username={username}&password={password}",
                HttpMethod.POST,
                null,
                LoginResponse.class,
                username, password
        ).getBody().getResponse();
    }
}
