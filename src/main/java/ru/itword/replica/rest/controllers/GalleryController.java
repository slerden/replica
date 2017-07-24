package ru.itword.replica.rest.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.DataBinder;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;
import org.springframework.validation.annotation.Validated;
import org.springframework.validation.beanvalidation.LocalValidatorFactoryBean;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import ru.itword.replica.dao.FileEntityDao;
import ru.itword.replica.model.entity.FileEntity;
import ru.itword.replica.model.web.MessageDto;
import ru.itword.replica.service.api.FileService;
import ru.itword.replica.service.validation.FileValidator;
import ru.itword.replica.service.validation.resolvers.BindingResultResolver;

import javax.validation.Valid;
import java.io.IOException;

/**
 * Created by Itword on 23.07.2017.
 */
@RestController
@RequestMapping(value = "/gallery")
public class GalleryController {

    @Autowired
    FileService fileService;

    @Autowired
    private
    BindingResultResolver<MessageDto> bindingResultResolver;

    @Autowired
    LocalValidatorFactoryBean validatorFactoryBean;

    @InitBinder
    public void initBinder(WebDataBinder dataBinder){
        dataBinder.setValidator(new FileValidator());
    }

    @GetMapping(value = "/look/{id}", produces = MediaType.IMAGE_PNG_VALUE)
    public byte[] look(@PathVariable Long id) throws IOException {
        return null;
    }

    @GetMapping(value = "/read/{id}")
    public String read(@PathVariable Long id) throws IOException {
        return null;
    }



    @RequestMapping(value="/upload", method= RequestMethod.POST, consumes = "multipart/form-data")
    public MessageDto handleFileUpload(
           @Validated @RequestBody MultipartFile file){
        MessageDto messageDto = new MessageDto();
//        if(errors.hasErrors()){
//            messageDto.setSuccess(false);
//            return bindingResultResolver.resolveBindingResult(errors, messageDto);
//        }
//        else messageDto.setSuccess(true);
        fileService.saveFile(file);
        return messageDto;
    }


}
