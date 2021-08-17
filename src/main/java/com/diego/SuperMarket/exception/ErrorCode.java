package com.diego.SuperMarket.exception;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;
import lombok.RequiredArgsConstructor;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ErrorCode {
//    INVENTORY_INEXISTENT(0, "The ID provided does not correspond to an existing inventory");

    private final int status;
    private final String message;
    private String stackTrace;
    private List<ValidationError> errors;

    @Data
    private static class ValidationError {
        private final String field;
        private final String message;
    }

    public void addValidationError(String field, String message){
        if(Objects.isNull(errors)){
            errors = new ArrayList<>();
        }
        errors.add(new ValidationError(field, message));
    }
}
