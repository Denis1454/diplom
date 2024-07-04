package com.example.servicediplom.admin.user;

import com.example.servicediplom.dto.user.NewUserRequest;
import com.example.servicediplom.dto.user.UserDto;
import com.example.servicediplom.entities.User;
import com.example.servicediplom.exception.NotFoundException;
import com.example.servicediplom.mapper.UserMapperDto;
import com.example.servicediplom.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class AdminUserServiceImpl implements AdminUserService {
    private static final Logger log = LoggerFactory.getLogger(AdminUserServiceImpl.class);
    private final UserRepository userRepository;
    private final UserMapperDto userMapperDto;

    @Override
    public List<UserDto> getInformationUsers(List<Long> ids, Integer from, Integer size) {
        log.info(String.format("Начало операции getInformationUsers, ids = %s ", ids));
        Pageable pageable = PageRequest.of(from, size);
        List<User> usersId = userRepository.findAllByIdIn(ids, pageable);
        List<UserDto> userDtoList = userMapperDto.toListDto(usersId);
        log.info("Конец операции getInformationUsers" + userDtoList);
        return userDtoList;
    }

    @Override
    public UserDto createUser(NewUserRequest newUserRequest) {
        log.info(String.format("Начало операции createUser, %s ", newUserRequest));
        User save = userRepository.save(userMapperDto.toUserDto(newUserRequest));
        UserDto userDto = userMapperDto.toDto(save);
        log.info("Конец операции createUser" + userDto);
        return userDto;
    }

    @Override
    public void deleteUser(Long userId) {
        log.info(String.format("Начало операции deleteUser, userId = %s ", userId));
        if (userRepository.existsById(userId)) {
            userRepository.deleteById(userId);
        } else {
            throw new NotFoundException(String.format("Пользователя с id=%s не существует", userId));
        }
    }
}
