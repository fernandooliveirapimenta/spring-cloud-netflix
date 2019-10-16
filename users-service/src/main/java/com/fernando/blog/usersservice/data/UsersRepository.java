package com.fernando.blog.usersservice.data;

import org.springframework.data.repository.CrudRepository;

public interface UsersRepository extends CrudRepository<UserEntity, Long> {
}
