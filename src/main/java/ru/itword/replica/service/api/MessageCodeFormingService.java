package ru.itword.replica.service.api;

import ru.itword.replica.service.validation.enums.MessageSourceAttribute;

/**
 * Created by Itword on 30.07.2017.
 */
public interface MessageCodeFormingService {
    String formMessageCode(String code);
    String formErrorCode(String code);
    String formFieldErrorCode(String fieldName, String fieldCode);
    MessageSourceAttribute getErrorType(String code);
    String getFieldName(String code);
}
