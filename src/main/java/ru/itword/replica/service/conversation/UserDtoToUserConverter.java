package ru.itword.replica.service.conversation;

import org.springframework.core.convert.converter.Converter;
import ru.itword.replica.model.entity.User;
import ru.itword.replica.model.web.UserDto;

/**
 * Created by Itword on 30.07.2017.
 */
public class UserDtoToUserConverter implements Converter<UserDto, User> {
    @Override
    public User convert(UserDto userDto) {
        User user = new User();
        user.setUsername(userDto.getUsername());
        user.setPassword(userDto.getPassword());
        return user;
    }
}
