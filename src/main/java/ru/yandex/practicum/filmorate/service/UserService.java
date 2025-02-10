package ru.yandex.practicum.filmorate.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import ru.yandex.practicum.filmorate.model.User;
import ru.yandex.practicum.filmorate.storage.InMemoryUserStorage;

@Component
@RequiredArgsConstructor
public class UserService {
final InMemoryUserStorage inMemoryUserStorage;

    public User update(User user) {
        if (inMemoryUserStorage.containsKeyUser(user)) {
            inMemoryUserStorage.update(user);
            return user;
        }
        return user;
    }

    public User save(User user) {
        inMemoryUserStorage.save(user);
        return user;
    }

    public User getUserById(Long id){
        return inMemoryUserStorage.getUserById(id).get();
    }
}
