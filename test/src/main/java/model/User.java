package model;

import lombok.Data;

@Data
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
}
