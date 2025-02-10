package ru.yandex.practicum.filmorate.storage;

import lombok.Getter;
import org.springframework.stereotype.Component;
import ru.yandex.practicum.filmorate.exception.NotFoundException;
import ru.yandex.practicum.filmorate.model.User;
import ru.yandex.practicum.filmorate.service.UserService;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

@Component
@Getter
public class InMemoryUserStorage implements CommonStorageMetods {
    private Map<Long, User> users = new HashMap<>();

    public User update(User user) {
        if (containsKeyUser(user)) {
            User oldUser = users.get(user.getId());
            oldUser.setEmail(user.getEmail());
            oldUser.setLogin(user.getLogin());
            oldUser.setBirthday(user.getBirthday());
            oldUser.setName(user.getName());
            return oldUser;
        }
        return user;
    }

    public boolean containsKeyUser(User user) {
        return users.containsKey(user.getId());
    }

    public User save(User user) {
        users.put(user.getId(), user);
        return user;
    }

    @Override
    public long getNextId(Map<Long, ?> map) {
        return CommonStorageMetods.super.getNextId(map);
    }

    public Optional<User> getUserById(Long id) {
        Optional<User> user = Optional.of(users.get(id)).
                orElseThrow(()->new NotFoundException("user not finde "+id))
        return user;
    }
}
