package ru.yandex.practicum.filmorate.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import ru.yandex.practicum.filmorate.dto.NewUserRequest;
import ru.yandex.practicum.filmorate.dto.UpdateUserRequest;
import ru.yandex.practicum.filmorate.dto.UserDto;
import ru.yandex.practicum.filmorate.service.UserService;

import java.util.Collection;

@Slf4j
@RestController
@RequestMapping("/users")
@RequiredArgsConstructor
@Validated
public class UserController {

    private final UserService userService;

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public Collection<UserDto> users() {
        return userService.getUsers();
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public UserDto create(@Valid @RequestBody NewUserRequest newUserRequest) {
        log.info("start create user: {}", newUserRequest);
        return userService.createUser(newUserRequest);
    }

    @PutMapping
    public UserDto updateUser(@Valid @RequestBody UpdateUserRequest updateUserRequest) {
        //@PathVariable("userId") long userId,
        log.info("start update user: {}", updateUserRequest);
        //userService.updateUser(1L, user);
        return userService.updateUser(updateUserRequest.getId(), updateUserRequest);
        //    return new UserDto();
    }

//    @GetMapping("/users/{id}")
//    @ResponseStatus(HttpStatus.OK)
//    public User user(@PathVariable @Min(0) Long id) {
//        return userService.getInMemoryUserStorage().getUserById(id).get();
//    }
//
//    @PutMapping("/users")
//    @ResponseStatus(HttpStatus.OK)
//    public User update(@Valid @RequestBody User user) {
//        log.info("start update film: {}", user);
//        userService.update(user);
//        log.info("stop update film: {}", user);
//        return user;
//    }
//
//    @PostMapping("/users")
//    @ResponseStatus(HttpStatus.CREATED)
//    public User create(@Valid @RequestBody User user) {
//        log.info("start create film: {}", user);
//        userService.save(user);
//        log.info("stop create film: {}", user);
//        return user;
//    }
}
