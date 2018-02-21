package user;

import clients.UserClient;
import configuration.ClientsConfig;
import configuration.RestConfig;
import model.UserInfo;
import org.junit.Assert;
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

        UserInfo userInfo = userClient.getUserInfo("sessionKey");
        Assert.assertNotNull(userInfo.getUser());
        Assert.assertNotNull(userInfo.getGroups());
        Assert.assertNotNull(userInfo.getSettings());
        // and so on
    }
}
