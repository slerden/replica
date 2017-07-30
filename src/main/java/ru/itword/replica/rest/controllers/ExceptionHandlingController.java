package ru.itword.replica.rest.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import ru.itword.replica.exceptions.ValidationException;
import ru.itword.replica.model.web.MessageDto;
import ru.itword.replica.service.api.MessageCodeFormingService;
import ru.itword.replica.service.validation.enums.MessageSourceAttribute;

import java.util.*;

/**
 * Created by Itword on 28.07.2017.
 */
@RestController
@ControllerAdvice
public class ExceptionHandlingController {

    @Autowired
    MessageCodeFormingService messageCodeFormingService;

    @Autowired
    private MessageSource messageSource;

    @ExceptionHandler(ValidationException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public MessageDto proceedValidationException(ValidationException ex) {
        MessageDto result = new MessageDto();
        Map<String, List<String>> fieldErrors = new HashMap<>();
        List<String> errors = new ArrayList<String>();
        List<String> messages = new ArrayList<String>();

        for (ObjectError objectError : ex.getErrors().getAllErrors()) {
            MessageSourceAttribute errorType = messageCodeFormingService.getErrorType(objectError.getCode());
            if (errorType == MessageSourceAttribute.FIELD) {
                String fieldName = messageCodeFormingService.getFieldName(objectError.getCode());
                fieldErrors.computeIfAbsent(fieldName, k -> new ArrayList<>());
                fieldErrors.get(fieldName).add(getMessage(objectError.getCode()));
            } else if (errorType == MessageSourceAttribute.ERROR) {
                errors.add(getMessage(objectError.getCode()));
            } else if (errorType == MessageSourceAttribute.MESSAGE) {
                messages.add(getMessage(objectError.getCode()));
            }
        }
        result.setErrors(errors);
        result.setFieldErrors(fieldErrors);
        result.setMessages(messages);
        return result;
    }

    @ExceptionHandler(UsernameNotFoundException.class)
    @ResponseStatus(HttpStatus.FORBIDDEN)
    public MessageDto proceedUsernameNotFoundException(UsernameNotFoundException ex) {
        MessageDto result = new MessageDto();
        List<String> messages = new ArrayList<String>();
        messages.add(getMessage(ex.getMessage()));
        result.setMessages(messages);
        return result;
    }

    private String getMessage(String code) {
        return messageSource.getMessage(code, null, LocaleContextHolder.getLocale());
    }
}
