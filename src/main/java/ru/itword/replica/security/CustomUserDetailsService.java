package ru.itword.replica.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import ru.itword.replica.dao.UserDao;
import ru.itword.replica.model.entity.Role;
import ru.itword.replica.model.entity.User;


import java.util.ArrayList;
import java.util.List;

/**
 * Created by Oleg on 16.01.2017.
 */
@Service
public class CustomUserDetailsService implements UserDetailsService {

    @Autowired
    private UserDao userDao;

    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        User user = userDao.findByUsername(username);

        if(user !=  null) {
            List<SimpleGrantedAuthority> auths = new ArrayList<SimpleGrantedAuthority>();
            for (Role role : user.getRoles()) {
                auths.add(new SimpleGrantedAuthority(role.getName()));
            }
            return new org.springframework.security.core.userdetails.User(user.getUsername(), user.getPassword(), auths);
        }
        throw new UsernameNotFoundException("Username " + username + " is not found");
    }

    public UserDao getUserDao() {
        return userDao;
    }

    public void setUserDao(UserDao userDao) {
        this.userDao = userDao;
    }
}
