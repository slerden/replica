package ru.itword.replica.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.DigestUtils;
import org.springframework.validation.Validator;
import org.springframework.web.multipart.MultipartFile;
import ru.itword.replica.dao.FileEntityDao;
import ru.itword.replica.model.entity.FileEntity;
import ru.itword.replica.model.enums.FileExtension;
import ru.itword.replica.service.ByteConversationService;
import ru.itword.replica.service.api.FileService;
import ru.itword.replica.service.validation.FileValidator;
import ru.itword.replica.service.validation.aop.ValidatedArg;

/**
 * Created by Itword on 22.07.2017.
 */
@Service
public class FileServiceImpl implements FileService {


    @Autowired
    FileEntityDao dao;

    @Autowired
    ByteConversationService byteConversationService;


    public FileEntity getFile(Long id) {
        return null;
    }

    public <T extends MultipartFile> FileEntity saveFile(@ValidatedArg(FileValidator.class) T file) {
        FileEntity fileEntity = new FileEntity();
            try {
                byte[] bytes = file.getBytes();
                fileEntity.setData(byteConversationService.convertToBytes(bytes));
                fileEntity.setMd5Hash(DigestUtils.md5DigestAsHex(bytes));
                fileEntity.setFileExtension(getFileExtension(file.getOriginalFilename()));
                fileEntity.setName(file.getOriginalFilename());
                dao.save(fileEntity);
            } catch (Exception e) {
                return null;
            }
        return fileEntity;
    }

    public FileExtension getFileExtension(String fileName){
        for (FileExtension fileExtension : FileExtension.values()) {
            if (fileName.matches("^.+\\." + fileExtension.name().toLowerCase() + "$")) {
                return fileExtension;
            }
        }
        return null;
    }
}
