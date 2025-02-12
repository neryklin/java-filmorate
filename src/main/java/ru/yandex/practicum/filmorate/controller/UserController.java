package ru.yandex.practicum.filmorate.controller;

import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import ru.yandex.practicum.filmorate.model.User;
import ru.yandex.practicum.filmorate.service.UserService;
import ru.yandex.practicum.filmorate.storage.InMemoryUserStorage;


import java.util.Map;

//*Задание для самостоятельной работы
//*Для контроллеров PostController и UserController добавьте аннотацию @ResponseStatus
// таким образом, чтобы методы, обрабатывающие POST-запросы на создание пользователей и
// публикаций возвращали ответ с кодом статуса 201 CREATED.
//*        Аннотации, изученные в этой теме, помогут вам создавать разные контроллеры
// для ваших сервисов. Следующий шаг — научиться работать с составными данными,
// такими как файлы. Вперёд!

@Slf4j
@RestController
@RequestMapping("/users")
public class UserController {

    private UserService userService = new UserService(new InMemoryUserStorage());
    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public Map<Long, User> users() {
        return userService.getUsers();
    }

    @PutMapping
    @ResponseStatus(HttpStatus.OK)
    public User update(@Valid @RequestBody User user) {
        log.info("start update film: {}", user);
        userService.update(user);
        log.info("stop update film: {}", user);
        return user;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public User create(@Valid @RequestBody User user) {
        log.info("start create film: {}", user);
        userService.save(user);
        log.info("stop create film: {}", user);
        return user;
    }
}
