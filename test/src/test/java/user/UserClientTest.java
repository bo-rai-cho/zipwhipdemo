package user;

import clients.UserClient;
import configuration.ApplicationProperties;
import configuration.ClientsConfig;
import configuration.RestConfig;
import model.User;
import model.UserInfo;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.web.client.RestTemplate;

import java.util.UUID;

import static org.mockito.Matchers.anyString;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
// @RunWith(SpringJUnit4ClassRunner.class)
// @ContextConfiguration(classes = {ClientsConfig.class, RestConfig.class})
public class UserClientTest {

    @Mock
    // @Autowired
    private UserClient userClient = new UserClient(new RestTemplate(), new ApplicationProperties());

    private static final UserInfo EXPECTED_USER_INFO = UserInfo.builder().user(User.random()).build();

    @Test
    public void getUserInfo() {
        when(userClient.login(anyString(), anyString())).thenReturn(UUID.randomUUID().toString());
        String sessionKey = userClient.login("username", "password");

        when(userClient.getUserInfo(sessionKey)).thenReturn(EXPECTED_USER_INFO);

        UserInfo ui = userClient.getUserInfo(userClient.login("username", "password"));
        // Here is an example of assertion
        Assert.assertEquals(EXPECTED_USER_INFO.getUser().getFirstName(), ui.getUser().getFirstName());
        Assert.assertEquals(EXPECTED_USER_INFO.getUser().getPhoneNumber(), ui.getUser().getPhoneNumber());
        Assert.assertEquals(EXPECTED_USER_INFO.getSettings(), ui.getSettings());
    }

    @Test (expected = RuntimeException.class)
    public void wrongLogin() {
        // Setup mock
        when(userClient.login("username", "password")).thenThrow(new RuntimeException());

        // Just an example
        userClient.login("username", "password");
    }
}
