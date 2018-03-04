package model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public final class Settings {

    @JsonProperty("sendMessage_keepOpen")
    private Boolean sendMessageKeepOpen;

    @JsonProperty("corkboard_receive")
    private Boolean corkboardReceive;
}
