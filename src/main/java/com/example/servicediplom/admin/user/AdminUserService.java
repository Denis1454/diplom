package com.example.servicediplom.admin.user;

import com.example.servicediplom.dto.user.NewUserRequest;
import com.example.servicediplom.dto.user.UserDto;

import java.util.List;

public interface AdminUserService {


    List<UserDto> getInformationUsers(List<Integer> ids, Integer from, Integer size);

    UserDto create(NewUserRequest newUserRequest);

    void deleteUser(Long userId);
}
