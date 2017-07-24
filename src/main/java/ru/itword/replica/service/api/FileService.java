package ru.itword.replica.service.api;

import org.springframework.web.multipart.MultipartFile;
import ru.itword.replica.model.entity.FileEntity;
import ru.itword.replica.model.enums.FileExtension;

/**
 * Created by Itword on 23.07.2017.
 */
public interface FileService {
    FileEntity getFile(Long id);
    <T extends MultipartFile> FileEntity saveFile(T stream);
    FileExtension getFileExtension(String fileName);
}
