package model;

import lombok.Data;

@Data
public final class Token {

    private String message;
    private String fingerprint;
    private Long device;
    private Long contact;
}
