package clients;


import lombok.extern.log4j.Log4j;
import model.User;
import model.UserInfo;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;


@Log4j
public final class UserClient {

    private final RestTemplate restTemplate;

    public UserClient(final RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    public String login(String username, String password) {

        ResponseEntity<String> responseEntity = restTemplate.postForEntity("/user/get", null, String.class);
        return responseEntity.getBody();
    }

    public UserInfo getUserInfo(String sessionKey) {
        return new UserInfo();
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
}
