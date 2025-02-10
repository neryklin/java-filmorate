package ru.yandex.practicum.filmorate.service;

import org.springframework.stereotype.Component;
import ru.yandex.practicum.filmorate.model.Film;
import ru.yandex.practicum.filmorate.model.User;

import java.util.Set;
@Component
public interface LikeService {
    public Film addLike(Film film, User user);
    public Film delLike(Film film, User user);
    public Set<Long> getTopFilms();
}
