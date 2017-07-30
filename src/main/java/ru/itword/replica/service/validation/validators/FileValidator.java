package ru.itword.replica.service.validation.validators;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.commons.CommonsMultipartFile;
import ru.itword.replica.model.enums.FileExtension;
import ru.itword.replica.service.api.FileService;
import ru.itword.replica.service.validation.enums.MessageSourceAttribute;

/**
 * Created by Itword on 23.07.2017.
 */
@Component("fileValidator")
public class FileValidator implements Validator{

    private final static String ERROR_ATTRIBUE = MessageSourceAttribute.ERROR.getAttribute();
    private final static String MESSAGE_ATTRIBUE = MessageSourceAttribute.MESSAGE.getAttribute();

    @Autowired
    FileService fileService;

    public boolean supports(Class<?> aClass) {
        return aClass.equals(CommonsMultipartFile.class);
    }

    public void validate(Object o, Errors errors) {
        if(o == null) {
            errors.reject(ERROR_ATTRIBUE+"null");
            return;
        }
        MultipartFile file = ((CommonsMultipartFile)o);
        if(file.isEmpty()) {
            errors.reject(ERROR_ATTRIBUE+"file.empty");
        }
        if(!isAcceptableFileExtension(file.getOriginalFilename())) {
            errors.reject(ERROR_ATTRIBUE+"file.extension.wrong");
        }
    }

    private boolean isAcceptableFileExtension(String fileName){
        for (FileExtension fileExtension : FileExtension.values()) {
            if (fileName.matches("^.+\\." + fileExtension.name().toLowerCase() + "$")) {
                return true;
            }
        }
        return false;
    }
}
