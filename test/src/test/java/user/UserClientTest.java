package user;

import clients.UserClient;
import configuration.ClientsConfig;
import configuration.RestConfig;
import org.junit.Before;
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

    @Before
    public void login() {
        // login here
    }

    @Test
    public void getUserInfo() {
        userClient.login("1", "1");
    }
}
