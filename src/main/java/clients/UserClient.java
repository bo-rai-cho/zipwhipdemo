package clients;


import lombok.extern.slf4j.Slf4j;
import model.User;
import model.UserInfo;
import model.request.FormUrlEncodedData;
import model.response.*;
import org.springframework.http.ResponseEntity;
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

        FormUrlEncodedData data = new FormUrlEncodedData()
                .add("username", username)
                .add("password", password);

        return restOperations.postForEntity("/user/login", data, LoginResponse.class).getBody().getResponse();
    }

    public User save(String sessionKey, String firstName, String lastName, String notes, String email) {

        FormUrlEncodedData data = FormUrlEncodedData.sessionData(sessionKey)
                .add("firstName", firstName)
                .add("lastName", lastName)
                .add("notes", notes)
                .add("email", email);

        return restOperations.postForEntity("/user/save", data, UserResponse.class).getBody().getResponse();
    }

    public User save(String sessionKey) {
        return save(sessionKey, null, null, null, null);
    }

    public UserInfo get(String sessionKey) {

        FormUrlEncodedData data = FormUrlEncodedData.sessionData(sessionKey);

        ResponseEntity<UserInfoResponse> re = restOperations.postForEntity("/user/get", data, UserInfoResponse.class);
        return re.getBody().getResponse();
    }

    public void setSignature(String sessionKey, String signature) {

        FormUrlEncodedData data = FormUrlEncodedData.sessionData(sessionKey)
                .add("signature", signature);

        restOperations.postForEntity("/user/signature/set", data, SignatureSetResponse.class);
    }

    public void logout(String sessionKey) {

        FormUrlEncodedData data = FormUrlEncodedData.sessionData(sessionKey);

        restOperations.postForEntity("user/logout", data, LogoutResponse.class);
    }
}
