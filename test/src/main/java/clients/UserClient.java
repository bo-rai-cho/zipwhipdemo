package clients;


import configuration.ApplicationProperties;
import exceptions.ExternalServiceException;
import lombok.extern.slf4j.Slf4j;
import model.ResponseWrapper;
import model.User;
import model.UserInfo;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

@Slf4j
public class UserClient {

    private final RestTemplate restTemplate;
    private final ApplicationProperties applicationProperties;

    public UserClient(RestTemplate restTemplate, ApplicationProperties applicationProperties) {
        this.restTemplate = restTemplate;
        this.applicationProperties = applicationProperties;
    }

    public String login(String username, String password) {

        String url = userUrlBuilder("login")
                .queryParam("username", username)
                .queryParam("password", password)
                .toUriString();

        ResponseEntity<ResponseWrapper<String>> re = restTemplate.exchange(
                url,
                HttpMethod.POST,
                null,
                new ParameterizedTypeReference<ResponseWrapper<String>>() {});

        validate(re);
        return re.getBody().getResponse();
    }

    public UserInfo getUserInfo(String sessionKey) {

        String url = userUrlBuilder("get").toUriString();

        ResponseEntity<ResponseWrapper<UserInfo>> re = restTemplate.exchange(
                url,
                HttpMethod.POST,
                null,
                new ParameterizedTypeReference<ResponseWrapper<UserInfo>>() {});

        validate(re);
        return re.getBody().getResponse();
    }

    public User save(User user) {
        return null;
    }

    public Object logout(String sessionKey) {
        return null;
    }

    public Object setSignature(String signature) {
        return null;
    }

    private UriComponentsBuilder userUrlBuilder(String... pathSegment) {
        return userUrlBuilder().pathSegment(pathSegment);
    }

    private UriComponentsBuilder userUrlBuilder() {

        return UriComponentsBuilder
                .fromHttpUrl(applicationProperties.getApiBaseUrl())
                .path("user");
    }

    private void validate(ResponseEntity response) {

        if (response.getStatusCode().is5xxServerError()) {
            try {
                throw new ExternalServiceException();
            } catch (ExternalServiceException e) {
                log.error(e.getMessage(), e);
            }
        }
    }
}
