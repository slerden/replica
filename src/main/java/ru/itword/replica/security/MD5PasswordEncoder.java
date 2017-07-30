package ru.itword.replica.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import ru.itword.replica.service.api.UserService;
/**
 * Created by Itword on 26.04.2017.
 */

public class MD5PasswordEncoder implements PasswordEncoder {

    @Autowired
    UserService userService;

    @Override
    public String encode(CharSequence rawPassword) {
        return userService.encodePassword(String.valueOf(rawPassword));
    }

    @Override
    public boolean matches(CharSequence rawPassword, String encodedPassword) {
        return encode(rawPassword).equals(encodedPassword);
    }
}
