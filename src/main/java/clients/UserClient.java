package clients;


import lombok.extern.slf4j.Slf4j;
import model.User;
import model.UserInfo;
import model.request.EntityUrlEncodedData;
import model.response.*;
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

        EntityUrlEncodedData data = new EntityUrlEncodedData()
                .add("username", username)
                .add("password", password);

        return restOperations.postForEntity("/user/login", data, LoginResponse.class).getBody().getResponse();
    }

    /**
     * Save user
     *
     * @param session Session key
     * @param firstName First name (optional)
     * @param lastName Last name (optional)
     * @param notes Notes about the user (optional)
     * @param email Email associated with this user (optional)
     * @return This user info
     */
    public User save(String session, String firstName, String lastName, String notes, String email) {

        EntityUrlEncodedData data = EntityUrlEncodedData.session(session)
                .add("firstName", firstName)
                .add("lastName", lastName)
                .add("notes", notes)
                .add("email", email);

        return restOperations.postForEntity("/user/save", data, UserResponse.class).getBody().getResponse();
    }

    /**
     * Save user
     *
     * @param session Session key
     * @return This user info
     */
    public User save(String session) {
        return save(session, null, null, null, null);
    }

    /**
     * Get info about the user associated with the given session key
     *
     * @param session Session key
     * @return User info
     */
    public UserInfo get(String session) {

        EntityUrlEncodedData data = EntityUrlEncodedData.session(session);

        return restOperations.postForEntity("/user/get", data, UserInfoResponse.class).getBody().getResponse();
    }

    /**
     * Set signature
     *
     * @param session Session key
     * @param signature Signature
     */
    public void setSignature(String session, String signature) {

        EntityUrlEncodedData data = EntityUrlEncodedData.session(session)
                .add("signature", signature);

        restOperations.postForEntity("/user/signature/set", data, SignatureSetResponse.class);
    }

    /**
     * Logout from a session
     *
     * @param session Session key
     */
    public void logout(String session) {

        EntityUrlEncodedData data = EntityUrlEncodedData.session(session);

        restOperations.postForEntity("/user/logout", data, LogoutResponse.class);
    }
}
