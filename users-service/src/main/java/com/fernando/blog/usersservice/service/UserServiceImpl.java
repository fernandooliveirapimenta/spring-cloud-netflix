package com.fernando.blog.usersservice.service;

import com.fernando.blog.usersservice.data.UserEntity;
import com.fernando.blog.usersservice.data.UsersRepository;
import com.fernando.blog.usersservice.dto.UserDTO;
import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class UserServiceImpl implements UsersService {

    private final UsersRepository usersRepository;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;

    public UserServiceImpl(UsersRepository usersRepository,
                           BCryptPasswordEncoder bCryptPasswordEncoder) {
        this.usersRepository = usersRepository;
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
    }

    @Override
    public UserDTO createUser(UserDTO userDTO) {
        userDTO.setUserId(UUID.randomUUID().toString());
        ModelMapper modelMapper = new ModelMapper();
        modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);
        UserEntity userEntity = modelMapper.map(userDTO, UserEntity.class);
        userEntity.setEncryptedPassword(bCryptPasswordEncoder.encode(userDTO.getPasswordName()));
        usersRepository.save(userEntity);
        return modelMapper.map(userEntity, UserDTO.class);
    }
}
