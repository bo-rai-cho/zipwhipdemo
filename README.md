# zipwhipdemo
Demo project for Zipwhip

### Usage

```java
@Autowired
private UserClient userClient;

public void example() {

String sessionKey = userClient.login("username", "password");
UserInfo userInfo = userClient.get(sessionKey);
System.out.println(userInfo.getUser().getFirstName());
userClient.logout(sessionKey);
}
```

## Running the tests

mvn clean test

### Test groups

Basic examples of user client test
