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

    public ErrorMessage serverError() {
        return null; // We don't have anything in case of server error
    }

    public ErrorMessage singleContactFailure() {
        return new ErrorMessage(-703, "Bad arguments");
    }
}
