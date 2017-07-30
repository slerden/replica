package ru.itword.replica.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.itword.replica.model.entity.User;

/**
 * Created by Itword on 29.07.2017.
 */
@Repository
public interface UserDao extends JpaRepository<User, Long> {
    User findByUsername(String username);
}
