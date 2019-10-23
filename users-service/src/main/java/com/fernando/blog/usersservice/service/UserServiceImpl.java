package com.fernando.blog.usersservice.service;

import com.fernando.blog.usersservice.data.UserEntity;
import com.fernando.blog.usersservice.data.UsersRepository;
import com.fernando.blog.usersservice.dto.UserDTO;
import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
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

    @Override
    public UserDTO getUserDetailsByEmail(String email) {
        final UserEntity byEmail = usersRepository.findByEmail(email);
        return new ModelMapper().map(byEmail, UserDTO.class);
    }

    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
        final UserEntity byEmail = usersRepository.findByEmail(s);

        if(byEmail == null) {
            throw new UsernameNotFoundException(s);
        }

        return new User(byEmail.getEmail(),
                byEmail.getEncryptedPassword(),
                true,
                true,
                true,
                true,
                new ArrayList<>());

    }
}
