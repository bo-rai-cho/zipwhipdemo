# zipwhipdemo
Demo project for Zipwhip

### Usage

```java
@Autowired
private UserClient userClient;

public void example() {

    String session = userClient.login("username", "password");
    UserInfo userInfo = userClient.get(session);
    User user = userInfo.getUser();

    if (user != null) {
        System.out.println("First name: " + userInfo.getUser().getFirstName());
        System.out.println("Last name: " + userInfo.getUser().getLastName());
        System.out.println("Phone number: " + userInfo.getUser().getPhoneNumber());
    }
}
```

## Running the tests

mvn clean test

### Test groups

Basic examples of user client test
