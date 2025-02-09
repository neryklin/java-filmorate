package ru.yandex.practicum.filmorate.storage;

import lombok.Getter;
import org.springframework.stereotype.Component;
import ru.yandex.practicum.filmorate.model.User;

import java.util.HashMap;
import java.util.Map;

@Component
@Getter
public class InMemoryUserStorage implements UserStorage,CommonStorageMetods {
    private Map<Long, User> users = new HashMap<>();

    @Override
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

    @Override
    public boolean containsKeyUser(User user) {
        return users.containsKey(user.getId());
    }

    @Override
    public User save(User user) {
        users.put(user.getId(), user);
        return user;
    }

    @Override
    public long getNextId(Map<Long, ?> map) {
        return CommonStorageMetods.super.getNextId(map);
    }
}
