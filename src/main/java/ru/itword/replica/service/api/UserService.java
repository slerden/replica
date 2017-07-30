package ru.itword.replica.service.api;

import ru.itword.replica.model.entity.User;

/**
 * Created by Itword on 29.07.2017.
 */
public interface UserService {
    User saveUser(User user);
    User getCurrentUser();
    String encodePassword(String password);
}
