package model;

import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
public final class UserInfo {

    private Settings settings;
    private User user;
    private List<Group> groups;
}
