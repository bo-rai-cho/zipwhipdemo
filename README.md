# zipwhipdemo
Demo project for Zipwhip

### Usage

```java
@Autowired
private UserClient userClient;

public void example() {

String sessionKey = userClient.login("username", "password");
UserInfo userInfo = userClient.get(sessionKey);
System.out.println("First name: " + userInfo.getUser().getFirstName());
System.out.println("Last name: " + userInfo.getUser().getLastName());
System.out.println("Last name: " + userInfo.getUser().getPhoneNumber());
userClient.logout(sessionKey);
}
```

## Running the tests

mvn clean test

### Test groups

Basic examples of user client test
