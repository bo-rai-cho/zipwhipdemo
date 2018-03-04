package model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

import static org.springframework.http.HttpStatus.BAD_REQUEST;

/**
 * @author Nikolay Ponomarev
 */
@Getter
@AllArgsConstructor
public enum ErrorType {

    BUSINESS_ERROR(BAD_REQUEST, -703, "Wrong arguments");

    private final HttpStatus status;
    private final int code;
    private final String message;
}
