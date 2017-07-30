package ru.itword.replica.service.validation.validators;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ObjectError;
import org.springframework.validation.Validator;
import ru.itword.replica.dao.UserDao;
import ru.itword.replica.model.entity.User;
import ru.itword.replica.service.api.MessageCodeFormingService;
import ru.itword.replica.service.validation.enums.MessageSourceAttribute;

/**
 * Created by Itword on 30.07.2017.
 */
@Component
public class UserRegistrationValidator implements Validator {

    @Autowired
    MessageCodeFormingService messageCodeService;

    @Autowired
    UserDao userDao;

    @Override
    public boolean supports(Class<?> aClass) {
        return aClass.equals(User.class);
    }

    @Override
    public void validate(Object o, Errors errors) {
        if(o == null) errors.reject(messageCodeService.formErrorCode("user_not_found"));
        User user = (User) o;
        if(user.getRoles() != null) errors.reject(messageCodeService.formErrorCode("permission_denied.role_settings"));
        if(userDao.findByUsername(user.getUsername()) != null){
            errors.reject(messageCodeService.formFieldErrorCode("username", "exists"));
        }
        if(user.getUsername() == null || !user.getUsername().matches("^[a-zA-Z0-9-_\\.]{3,32}$")) {
            errors.reject(messageCodeService.formFieldErrorCode("username","regex"));
        }
        if(user.getPassword() == null || !user.getPassword().matches("^(?=.*\\d)(?=.*[a-z])(?=.*[A-Z])[\\w\\d]{6,32}$")){
            errors.reject(messageCodeService.formFieldErrorCode("password", "regex"));
        }
    }
}
