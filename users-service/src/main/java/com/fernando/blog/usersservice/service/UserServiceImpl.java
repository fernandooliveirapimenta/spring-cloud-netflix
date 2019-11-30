package com.fernando.blog.usersservice.service;

import com.fernando.blog.usersservice.data.AlbumHttpClient;
import com.fernando.blog.usersservice.data.UserEntity;
import com.fernando.blog.usersservice.data.UsersRepository;
import com.fernando.blog.usersservice.dto.UserDTO;
import com.fernando.blog.usersservice.model.AlbumResponseModel;
import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.UUID;

@Service
public class UserServiceImpl implements UsersService {

    private final UsersRepository usersRepository;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;
    private final RestTemplate restTemplate;
    private final Environment environment;
    private final AlbumHttpClient albumHttpClient;

    public UserServiceImpl(UsersRepository usersRepository,
                           BCryptPasswordEncoder bCryptPasswordEncoder, RestTemplate restTemplate,
                           Environment environment, AlbumHttpClient albumHttpClient) {
        this.usersRepository = usersRepository;
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
        this.restTemplate = restTemplate;
        this.environment = environment;
        this.albumHttpClient = albumHttpClient;
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
    public UserDTO getUserByUserId(String userId) {
        UserEntity userEntity = usersRepository.findByUserId(userId);

        if(userEntity == null)
            return null;
        final UserDTO map = new ModelMapper().map(userEntity, UserDTO.class);

//        final String albumUrl = String.format(
//                Objects.requireNonNull(
//                        environment.getProperty("albums.url")), userId);
//        final ResponseEntity<List<AlbumResponseModel>> retorno=
//                restTemplate.exchange(albumUrl,
//                HttpMethod.GET, null,
//                new ParameterizedTypeReference<List<AlbumResponseModel>>() {
//                });

        List<AlbumResponseModel> retorno= albumHttpClient.getAlbums(userId);
        map.setAlbums(retorno);
        return map;
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
