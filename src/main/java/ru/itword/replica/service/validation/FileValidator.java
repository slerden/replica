package ru.itword.replica.service.validation;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.commons.CommonsMultipartFile;
import ru.itword.replica.model.enums.FileExtension;
import ru.itword.replica.service.api.FileService;

/**
 * Created by Itword on 23.07.2017.
 */
@Component("fileValidator")
public class FileValidator implements Validator{

    @Autowired
    FileService fileService;

    public boolean supports(Class<?> aClass) {
        return aClass.equals(CommonsMultipartFile.class);
    }

    public void validate(Object o, Errors errors) {
        if(o == null) {
            errors.reject("errors.null");
            return;
        }
        MultipartFile file = (MultipartFile)o;
        if(file.isEmpty()) {
            errors.reject("errors.file.empty");
        }
        if(fileService.getFileExtension(file.getOriginalFilename()) == null) errors.reject("errors.file.extension.wrong");
    }
}
