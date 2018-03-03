package user;

import clients.UserClient;
import configuration.ClientsConfig;
import configuration.RestConfig;
import exceptions.ExternalServiceException;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;


@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {ClientsConfig.class, RestConfig.class})
public class UserClientTest {

    @Autowired
    private UserClient userClient;

    @Test (expected = ExternalServiceException.class)
    public void testWrongLogin() {
        String sessionKey = userClient.login("username", "password");
        Assert.assertNull(sessionKey);
    }

    @Test (expected = ExternalServiceException.class)
    public void testEmptyPassword() {
        String sessionKey = userClient.login("username", "");
        Assert.assertNull(sessionKey);
    }

    @Test (expected = ExternalServiceException.class)
    public void testEmptyUsername() {
        String sessionKey = userClient.login("", "password");
        Assert.assertNull(sessionKey);
    }
}
