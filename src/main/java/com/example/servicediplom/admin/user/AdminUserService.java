package com.example.servicediplom.admin.user;

import com.example.servicediplom.dto.user.NewUserRequest;
import com.example.servicediplom.dto.user.UserDto;

import java.util.List;

public interface AdminUserService {

    List<UserDto> getInformationUsers(List<Long> ids, Integer from, Integer size);

    UserDto createUser(NewUserRequest newUserRequest);

    void deleteUser(Long userId);
}
