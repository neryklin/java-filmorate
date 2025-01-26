package ru.yandex.practicum.filmorate.controller;

import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;
import ru.yandex.practicum.filmorate.exception.NotFoundException;
import ru.yandex.practicum.filmorate.exception.ValidationException;
import ru.yandex.practicum.filmorate.model.User;

import java.time.LocalDate;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;



@Slf4j
@RestController
@RequestMapping("/users")
public class UserController {
    private final Map<Long, User> users = new HashMap<>();

    @GetMapping
    public Collection<User> Users(){
        return users.values();
    }
    @PutMapping
    public User update(@Valid @RequestBody User user){
        if (users.containsKey(user.getId())) {
            User oldUser = users.get(user.getId());
            oldUser.setEmail(user.getEmail());
            oldUser.setLogin(user.getLogin());
            oldUser.setBirthday(user.getBirthday());
            oldUser.setName(user.getName());
            return oldUser;
        }
        throw new NotFoundException("Пользователь с id "+user.getId()+" не найден");
    }

    @PostMapping
    public User create(@Valid @RequestBody User user){
        if (checkUserBirthdayValid(user) && checkUserLoginValid(user)) {
            user.setId(getNextId());
            if (user.getName()==null) {
                user.setName(user.getLogin());
            }
            users.put(user.getId(), user);
        }
        return user;
    }

    public boolean checkUserLoginValid(User user){
        if(user.getLogin().contains(" ")) {
            throw new ValidationException("Ошибка содержаться пробелы");
        }
        return true;
    }

    public boolean checkUserBirthdayValid(User user){
        if (user.getBirthday().isAfter(LocalDate.now())) {
            throw new ValidationException("Дата рождения не может быть в будущем");
        }
    return true;
    }

    private long getNextId() {
        long currentMaxId = users.keySet()
                .stream()
                .mapToLong(id -> id)
                .max()
                .orElse(0);
        return ++currentMaxId;
    }
}
