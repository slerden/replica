package ru.itword.replica.rest.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.http.HttpStatus;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import ru.itword.replica.exceptions.ValidationException;
import ru.itword.replica.model.web.MessageDto;
import ru.itword.replica.service.validation.enums.MesssageSourceAttribute;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Itword on 28.07.2017.
 */
@RestController
@ControllerAdvice
public class ExceptionHandlingController {

    private final static String ERROR_ATTRIBUE = MesssageSourceAttribute.ERROR.getAttribute();
    private final static String MESSAGE_ATTRIBUE = MesssageSourceAttribute.MESSAGE.getAttribute();

    @Autowired
    private MessageSource messageSource;

    @ExceptionHandler(ValidationException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public MessageDto proceedValidationException(ValidationException ex){
        MessageDto result = new MessageDto();
        Map<String, String> fieldErrors = new HashMap<String, String>();
        List<String> errors = new ArrayList<String>();
        List<String> messages = new ArrayList<String>();

        for (ObjectError objectError : ex.getErrors().getAllErrors()) {
            if(objectError instanceof FieldError){
                fieldErrors.put(((FieldError) objectError).getField(), getMessage(objectError.getCode()));
            }
            else if(objectError.getCode().startsWith(ERROR_ATTRIBUE)){
                errors.add(getMessage(objectError.getCode()));
            }
            else if (objectError.getCode().startsWith(MESSAGE_ATTRIBUE)){
                messages.add(getMessage(objectError.getCode()));
            }
        }
        result.setErrors(errors);
        result.setFieldErrors(fieldErrors);
        result.setMessages(messages);
        return result;
    }
    private String getMessage(String code){
        return messageSource.getMessage(code, null, LocaleContextHolder.getLocale());
    }
}
