package ru.itword.replica.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.itword.replica.model.entity.FileEntity;

/**
 * Created by Itword on 23.07.2017.
 */
@Repository
public interface FileEntityDao extends JpaRepository<FileEntity, Long>{

}
