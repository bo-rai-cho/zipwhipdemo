package model.responses;

import lombok.Data;

@Data
public abstract class ResponseWrapper<T> {

    private Boolean success;
    private T response;
}
