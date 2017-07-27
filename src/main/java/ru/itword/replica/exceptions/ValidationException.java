package ru.itword.replica.exceptions;

import org.springframework.validation.Errors;

/**
 * Created by Itword on 27.07.2017.
 */
public class ValidationException extends RuntimeException {
    private Errors errors;

    public ValidationException(Errors errors){
        super();
        this.errors = errors;
    }

    public Errors getErrors() {
        return errors;
    }

    public void setErrors(Errors errors) {
        this.errors = errors;
    }
}
