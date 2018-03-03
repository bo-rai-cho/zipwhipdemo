package clients;


import configuration.ApplicationProperties;
import lombok.extern.slf4j.Slf4j;
import model.responses.LoginResponse;
import org.springframework.web.client.RestOperations;
import org.springframework.web.util.UriComponentsBuilder;

@Slf4j
public class UserClient extends RestClient {

    private final ApplicationProperties applicationProperties;

    public UserClient(ApplicationProperties applicationProperties, RestOperations restOperations) {
        super(restOperations);
        this.applicationProperties = applicationProperties;
    }

    /**
     * Login
     *
     * @param username Username
     * @param password Password
     * @return Session key
     */
    public String login(String username, String password) {

        String url = userUrlBuilder("login")
                .queryParam("username", username)
                .queryParam("password", password)
                .toUriString();

        return post(url, LoginResponse.class).getBody().getResponse();
    }

    private UriComponentsBuilder userUrlBuilder(String... pathSegment) {

        return UriComponentsBuilder.fromHttpUrl(applicationProperties.getApiBaseUrl())
                .path("user")
                .pathSegment(pathSegment);
    }
}
