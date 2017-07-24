package ru.itword.replica.service.validation.resolvers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.validation.BindingResult;
import org.springframework.validation.Errors;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import ru.itword.replica.model.web.MessageDto;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Itword on 23.07.2017.
 */
@Service
public class BindingResultResolverImpl implements BindingResultResolver<MessageDto>{

    private final static String ERROR_ATTRIBUE = "error.";
    private final static String MESSAGE_ATTRIBUE = "message.";


    @Autowired
    private MessageSource messageSource;

    public MessageDto resolveBindingResult(Errors errors) {
        MessageDto messageDto = new MessageDto();
        return resolveBindingResult(errors, messageDto);
    }

    public MessageDto resolveBindingResult(Errors bindingResult, MessageDto messageDto) {
        Map<String, String> fieldErrors = new HashMap<String, String>();
        List<String> errors = new ArrayList<String>();
        List<String> messages = new ArrayList<String>();

        for (ObjectError objectError : bindingResult.getAllErrors()) {
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
        messageDto.setErrors(errors);
        messageDto.setFieldErrors(fieldErrors);
        messageDto.setMessages(messages);
        return messageDto;
    }

    private String getMessage(String code){
        return messageSource.getMessage(code, null, LocaleContextHolder.getLocale());
    }
}
