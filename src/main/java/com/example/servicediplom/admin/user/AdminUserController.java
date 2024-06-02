package com.example.servicediplom.admin.user;

import com.example.servicediplom.dto.user.NewUserRequest;
import com.example.servicediplom.dto.user.UserDto;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.PositiveOrZero;
import lombok.RequiredArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "/admin/users")
@RequiredArgsConstructor
public class AdminUserController {

    private final AdminUserService adminUserService;

    @GetMapping()
    public List<UserDto> getInformationUsers(@RequestParam (required = false, defaultValue = "0") List<Integer> ids, //TODO (required = false, defaultValue = "0")
                                             //TODO сделал чтобы избежать ошибки если будет 0
                                @RequestParam(required = false, defaultValue = "0") @PositiveOrZero Integer from,
                                @RequestParam(required = false, defaultValue = "10") @Positive Integer size) {
        return adminUserService.getInformationUsers(ids,from,size);
    }

    @PostMapping()
    public UserDto create(@RequestBody @Validated NewUserRequest newUserRequest) {
        return adminUserService.create(newUserRequest);
    }

    @DeleteMapping("/{userId}")
    public void delete(@PathVariable Long userId) {
        adminUserService.deleteUser(userId);
    }
}
