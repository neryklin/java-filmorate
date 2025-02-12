package ru.yandex.practicum.filmorate.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import ru.yandex.practicum.filmorate.exception.NotFoundException;
import ru.yandex.practicum.filmorate.model.User;
import ru.yandex.practicum.filmorate.storage.InMemoryUserStorage;

import java.util.Collection;
import java.util.Map;
import java.util.Optional;

@Component
@RequiredArgsConstructor
public class UserService {
final InMemoryUserStorage inMemoryUserStorage;

    public Map<Long, User> getUsers() {
        return inMemoryUserStorage.getUsers();
    }
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

    public Optional<User> getUserById(Long id){
        return inMemoryUserStorage.getUserById(id);
    }
}
