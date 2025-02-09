package ru.yandex.practicum.filmorate.controller;

import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;
import ru.yandex.practicum.filmorate.exception.NotFoundException;
import ru.yandex.practicum.filmorate.model.User;
import ru.yandex.practicum.filmorate.storage.InMemoryUserStorage;

import java.util.Collection;


@Slf4j
@RestController
@RequestMapping("/users")
public class UserController {

    private InMemoryUserStorage inMemoryUserStorage = new InMemoryUserStorage();

    @GetMapping
    public Collection<User> users() {
        return inMemoryUserStorage.getUsers().values();
    }

    @PutMapping
    public User update(@Valid @RequestBody User user) {
        log.info("start update film: {}", user);
        if (inMemoryUserStorage.containsKeyUser(user)) {
            inMemoryUserStorage.update(user);
            log.info("stop update film: {}", user);
            return user;
        }
        throw new NotFoundException("error update user: {" + user + "}");
    }

    @PostMapping
    public User create(@Valid @RequestBody User user) {
        log.info("start create film: {}", user);
        user.setId(inMemoryUserStorage.getNextId(inMemoryUserStorage.getUsers()));
        user.setName(user.getName() == null ? user.getLogin() : user.getName());
        inMemoryUserStorage.save(user);
        log.info("stop create film: {}", user);
        return user;
    }
}
