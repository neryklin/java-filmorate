package ru.yandex.practicum.filmorate.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import ru.yandex.practicum.filmorate.model.User;
import ru.yandex.practicum.filmorate.service.UserService;

import java.util.Collection;

@Slf4j
@RestController
@RequestMapping
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @GetMapping("/users")
    @ResponseStatus(HttpStatus.OK)
    public Collection<User> users() {
        return userService.getInMemoryUserStorage().getUsers().values();
    }

    @PutMapping("/users")
    @ResponseStatus(HttpStatus.OK)
    public User update(@Valid @RequestBody User user) {
        log.info("start update film: {}", user);
        userService.update(user);
        log.info("stop update film: {}", user);
        return user;
    }

    @PostMapping("/users")
    @ResponseStatus(HttpStatus.CREATED)
    public User create(@Valid @RequestBody User user) {
        log.info("start create film: {}", user);
        userService.save(user);
        log.info("stop create film: {}", user);
        return user;
    }
}
