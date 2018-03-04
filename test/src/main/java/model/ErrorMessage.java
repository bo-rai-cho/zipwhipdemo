package model;

import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * @author Nikolay Ponomarev
 */
@Data
@AllArgsConstructor
public final class ErrorMessage {

    private Integer code;
    private String desc; // description
}
