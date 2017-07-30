package ru.itword.replica.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.util.DigestUtils;
import ru.itword.replica.dao.RoleDao;
import ru.itword.replica.dao.UserDao;
import ru.itword.replica.model.entity.Role;
import ru.itword.replica.model.entity.User;
import ru.itword.replica.service.api.UserService;
import ru.itword.replica.service.validation.aop.ValidatedArg;
import ru.itword.replica.service.validation.validators.UserRegistrationValidator;

import java.util.HashSet;

/**
 * Created by Itword on 30.07.2017.
 */
@Service
public class UserServiceImpl implements UserService {

    private final static String DEFAULT_USER_ROLE = "USER";

    @Autowired
    RoleDao roleDao;

    @Autowired
    private UserDao userDao;

    @Override
    public User saveUser(@ValidatedArg(UserRegistrationValidator.class) User user) {
        HashSet<Role> roles = new HashSet<>();
        roles.add(roleDao.findByName(DEFAULT_USER_ROLE));
        user.setRoles(roles);
        return userDao.save(user);
    }

    @Override
    public User getCurrentUser() {
        String username = SecurityContextHolder.getContext().getAuthentication().getName();
        User result = userDao.findByUsername(username);
        if(result == null) throw new UsernameNotFoundException("message.user_not_found");
        return result;
    }

    @Override
    public String encodePassword(String password) {
        return DigestUtils.md5DigestAsHex(password.getBytes());
    }

    public UserDao getUserDao() {
        return userDao;
    }

    public void setUserDao(UserDao userDao) {
        this.userDao = userDao;
    }
}
