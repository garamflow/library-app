package com.group.libraryapp.service.user;

import com.group.libraryapp.dto.user.UserCreateRequest;
import com.group.libraryapp.dto.user.UserResponse;
import com.group.libraryapp.dto.user.UserUpdateRequest;
import com.group.libraryapp.repository.user.UserJdbcRepository;
import org.springframework.jdbc.core.JdbcTemplate;

import java.util.List;

public class UserServiceV1 {

    private final UserJdbcRepository userJdbcRepository;

    public UserServiceV1(JdbcTemplate jdbcTemplate) {
        userJdbcRepository = new UserJdbcRepository(jdbcTemplate);
    }

    public void saveUser(UserCreateRequest request) {
        userJdbcRepository.saveUser(request.getName(), request.getAge());
    }

    public List<UserResponse> getUsers() {
        return userJdbcRepository.getUsers();
    }

    public void updateUser(UserUpdateRequest request) {
        if(userJdbcRepository.isUserNotExist(request.getId())) {
            throw new IllegalArgumentException();
        }

        userJdbcRepository.updateUserName(request.getName(), request.getId());
    }

    public void deleteUser(String name) {
        if(userJdbcRepository.isUserNotExist(name)) {
            throw new IllegalArgumentException();
        }

        userJdbcRepository.deleteUser(name);
    }
}
