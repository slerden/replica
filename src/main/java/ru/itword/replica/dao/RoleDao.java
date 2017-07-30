package ru.itword.replica.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.itword.replica.model.entity.Role;

/**
 * Created by Itword on 29.07.2017.
 */
@Repository
public interface RoleDao extends JpaRepository<Role, Long> {
    Role findByName(String name);
}
