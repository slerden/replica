package ru.itword.replica.rest.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.ConversionService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import ru.itword.replica.model.entity.User;
import ru.itword.replica.model.web.MessageDto;
import ru.itword.replica.model.web.UserDto;
import ru.itword.replica.service.api.UserService;

/**
 * Created by Itword on 29.07.2017.
 */
@RestController
public class ProfileController {

    @Autowired
    ConversionService conversionService;

    @Autowired
    UserService userService;

    @GetMapping(value = "/profile")
    public MessageDto getProfile(){
        return new MessageDto(conversionService.convert(userService.getCurrentUser(), UserDto.class));
    }

    @PostMapping(value = "/signup")
    public MessageDto signup(@RequestBody UserDto userDto){
        return new MessageDto(conversionService.convert(userService.saveUser(conversionService.convert(userDto, User.class)), UserDto.class));
    }

}
