package com.fernando.blog.usersservice.controllers;

import com.fernando.blog.usersservice.dto.UserDTO;
import com.fernando.blog.usersservice.model.CreateUserRequestModel;
import com.fernando.blog.usersservice.model.CreateUserResponseModel;
import com.fernando.blog.usersservice.model.UserResponseModel;
import com.fernando.blog.usersservice.service.UsersService;
import org.modelmapper.ModelMapper;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/users")
public class UsersController {

    private final Environment environment;
    private final UsersService usersService;

    public UsersController(Environment environment,
                           UsersService usersService) {
        this.environment = environment;
        this.usersService = usersService;
    }

    @GetMapping("/status/check")
    public String status() {
       return "Working on port: "
               + environment.getProperty("local.server.port") +
               ", with token = " + environment.getProperty("token.secret");
    }

    @PostMapping(
            consumes = { MediaType.APPLICATION_XML_VALUE, MediaType.APPLICATION_JSON_VALUE},
            produces = { MediaType.APPLICATION_XML_VALUE, MediaType.APPLICATION_JSON_VALUE}
    )
    public ResponseEntity<CreateUserResponseModel> createUser(
           @Valid @RequestBody
                   CreateUserRequestModel create) {

        ModelMapper modelMapper = new ModelMapper();

        UserDTO dto = modelMapper.map(create, UserDTO.class);
        final UserDTO createdUser = usersService.createUser(dto);
        final CreateUserResponseModel returnValue = modelMapper.map(createdUser, CreateUserResponseModel.class);

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(returnValue);
    }

    @GetMapping(value = "{userId}")
    public ResponseEntity<UserResponseModel> getUser(@PathVariable String userId) {
        UserDTO userDTO = usersService.getUserByUserId(userId);
        if(userDTO == null)
           return ResponseEntity.ok().build();
        UserResponseModel returnValue = new
                ModelMapper().map(userDTO, UserResponseModel.class);
        return ResponseEntity.status(HttpStatus.OK).body(returnValue);
    }
}
