package com.example.servicediplom.mapper.user;

import com.example.servicediplom.entities.User;
import com.example.servicediplom.dto.user.NewUserRequest;
import com.example.servicediplom.dto.user.UserDto;
import org.mapstruct.BeanMapping;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;

@Mapper(componentModel = "spring")
public interface UserMapperDto {
    UserDto toDto (User user);

    User toUser(UserDto userDto);

    NewUserRequest toUpdateDto(User user);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    void updateUserDto(UserDto dto, @MappingTarget User user);
}
