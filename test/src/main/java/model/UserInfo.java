package model;

import lombok.Data;

import java.util.List;

@Data
public class UserInfo {

    private Settings settings;
    private User user;
    private List<Group> groups;
}
