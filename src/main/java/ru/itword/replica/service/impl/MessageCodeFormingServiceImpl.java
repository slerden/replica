package ru.itword.replica.service.impl;

import org.springframework.stereotype.Service;
import ru.itword.replica.service.api.MessageCodeFormingService;
import ru.itword.replica.service.validation.enums.MessageSourceAttribute;

/**
 * Created by Itword on 30.07.2017.
 */
@Service
public class MessageCodeFormingServiceImpl implements MessageCodeFormingService{

    private final static String ERROR_ATTRIBUE = MessageSourceAttribute.ERROR.getAttribute();
    private final static String MESSAGE_ATTRIBUE = MessageSourceAttribute.MESSAGE.getAttribute();
    private final static String FIELD_ATTRIBUE = MessageSourceAttribute.FIELD.getAttribute();

    @Override
    public String formMessageCode(String code) {
        return MESSAGE_ATTRIBUE + code;
    }

    @Override
    public String formErrorCode(String code) {
        return ERROR_ATTRIBUE + code;
    }

    @Override
    public String formFieldErrorCode(String fieldName, String fieldCode) {
        return FIELD_ATTRIBUE + fieldName + "." + fieldCode;
    }

    @Override
    public MessageSourceAttribute getErrorType(String code) {
        if(code.startsWith(FIELD_ATTRIBUE)) return MessageSourceAttribute.FIELD;
        if(code.startsWith(MESSAGE_ATTRIBUE)) return MessageSourceAttribute.MESSAGE;
        if(code.startsWith(ERROR_ATTRIBUE)) return MessageSourceAttribute.ERROR;
        return null;
    }

    @Override
    public String getFieldName(String code) {
        if(getErrorType(code) == MessageSourceAttribute.FIELD){
            String[] split = code.split("\\.");
            return split[1];
        }
        return null;
    }
}
