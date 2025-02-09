package ru.yandex.practicum.filmorate.storage;

import ru.yandex.practicum.filmorate.model.Film;
import ru.yandex.practicum.filmorate.model.User;

import java.util.Collection;


public interface UserStorage {
    public User update(User user);
    public boolean containsKeyUser(User user);
    public User save(User user);

}
