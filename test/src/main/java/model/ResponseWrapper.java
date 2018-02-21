package model;

import lombok.Data;

@Data
public class ResponseWrapper<T> {

    private Boolean success;
    private T response;
}
