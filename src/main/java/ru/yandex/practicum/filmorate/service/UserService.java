package ru.yandex.practicum.filmorate.service;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import ru.yandex.practicum.filmorate.exception.NotFoundException;
import ru.yandex.practicum.filmorate.model.User;
import ru.yandex.practicum.filmorate.storage.InMemoryUserStorage;

@Component
@Getter
@RequiredArgsConstructor
public class UserService {
    final InMemoryUserStorage inMemoryUserStorage;


    public User update(User user) {
        if (inMemoryUserStorage.containsKeyUser(user)) {
            inMemoryUserStorage.update(user);
            return user;
        } else {
            throw new NotFoundException("user not found");
        }

    }

    public User save(User user) {
        inMemoryUserStorage.save(user);
        return user;
    }

}
