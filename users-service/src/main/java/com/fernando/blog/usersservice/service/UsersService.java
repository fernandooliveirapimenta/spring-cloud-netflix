package com.fernando.blog.usersservice.service;

import com.fernando.blog.usersservice.dto.UserDTO;
import org.springframework.security.core.userdetails.UserDetailsService;

public interface UsersService extends UserDetailsService {

    UserDTO createUser(UserDTO userDTO);
    UserDTO getUserDetailsByEmail(String email);
}
