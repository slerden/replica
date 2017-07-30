package ru.itword.replica.service.conversation;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.core.convert.converter.Converter;
import ru.itword.replica.model.entity.Role;
import ru.itword.replica.model.entity.User;
import ru.itword.replica.model.web.UserDto;

import java.util.HashSet;

/**
 * Created by Itword on 30.07.2017.
 */
public class UserToUserDtoConverter implements Converter<User, UserDto> {

    @Autowired
    private MessageSource messageSource;

    @Override
    public UserDto convert(User user) {
        UserDto userDto = new UserDto();
        userDto.setRoles(new HashSet<>());
        userDto.setUsername(user.getUsername());
        for (Role role : user.getRoles()) {
            String message = messageSource.getMessage(role.getCode(), null, LocaleContextHolder.getLocale());
            userDto.getRoles().add(message);
        }
        return userDto;
    }

    public MessageSource getMessageSource() {
        return messageSource;
    }

    public void setMessageSource(MessageSource messageSource) {
        this.messageSource = messageSource;
    }


}
