package com.example.servicediplom.mapper;

import com.example.servicediplom.dto.user.NewUserRequest;
import com.example.servicediplom.dto.user.UserDto;
import com.example.servicediplom.entities.User;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface UserMapperDto {
    List<UserDto> toListDto(List<User> users);

    UserDto toDto (User user);

    User toUser(UserDto userDto);

    NewUserRequest toCreateDto(User user);

    User toUserDto (NewUserRequest newUserRequest);
}
