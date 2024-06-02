package com.example.servicediplom.admin.user;

import com.example.servicediplom.dto.user.NewUserRequest;
import com.example.servicediplom.dto.user.UserDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class AdminUserServiceImpl implements AdminUserService {

    @Override
    public List<UserDto> getInformationUsers(List<Integer> ids, Integer from, Integer size) {
        return null;
    }

    @Override
    public UserDto create(NewUserRequest newUserRequest) {
        return null;
    }

    @Override
    public void deleteUser(Long userId) {

    }
}
