package ru.itword.replica.rest.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import ru.itword.replica.model.web.MessageDto;
import ru.itword.replica.service.api.FileService;

import java.io.IOException;

/**
 * Created by Itword on 23.07.2017.
 */
@RestController
@RequestMapping(value = "/gallery")
public class GalleryController {

    @Autowired
    FileService fileService;


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
           @RequestBody MultipartFile file){
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
