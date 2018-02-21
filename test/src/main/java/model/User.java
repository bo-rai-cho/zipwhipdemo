package model;

import lombok.Builder;
import lombok.Data;

import java.util.UUID;
import java.util.concurrent.ThreadLocalRandom;

@Data
@Builder
public class User {

    private String lastName;
    private String birthday;
    private Long lastUpdated;
    private String loc; // location
    private String phoneKey;
    private String phoneNumber;
    private String email;
    private String carrier;
    private Long dateCreated;
    private String fullName;
    private String mobileNumber;
    private String firstName;
    private String notes;

    public static User random() {
        return User.builder()
                .lastName(UUID.randomUUID().toString())
                .birthday(UUID.randomUUID().toString())
                .lastUpdated(ThreadLocalRandom.current().nextLong())
                .loc(UUID.randomUUID().toString())
                // and so on
                .build();
    }
}
