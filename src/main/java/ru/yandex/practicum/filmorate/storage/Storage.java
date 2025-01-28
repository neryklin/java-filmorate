package ru.yandex.practicum.filmorate.storage;


import lombok.Getter;
import ru.yandex.practicum.filmorate.model.Film;
import ru.yandex.practicum.filmorate.model.User;

import java.util.HashMap;
import java.util.Map;

@Getter
public class Storage {
    private static Storage instance;
    private Map<Long, Film> films = new HashMap<>();
    private Map<Long, User> users = new HashMap<>();

    public Storage() {
    }

    public static Storage getInstance() {
        if (instance == null) {
            instance = new Storage();
        }
        return instance;
    }

    public User save(User user) {
        users.put(user.getId(), user);
        return user;
    }

    public Film save(Film film) {
        films.put(film.getId(), film);
        return film;
    }

    public boolean containsKeyFilms(Film film) {
        return films.containsKey(film.getId());
    }

    public boolean containsKeyUser(User user) {
        return users.containsKey(user.getId());
    }

    public Film update(Film film) {
        if (containsKeyFilms(film)) {
            Film oldFilm = films.get(film.getId());
            oldFilm.setName(film.getName());
            oldFilm.setDescription(film.getDescription());
            oldFilm.setReleaseDate(film.getReleaseDate());
            oldFilm.setDuration(film.getDuration());
            return oldFilm;
        }
        return film;
    }

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


    public long getNextId(Map<Long, ?> map) {
        long currentMaxId = map.keySet()
                .stream()
                .mapToLong(id -> id)
                .max()
                .orElse(0);
        return ++currentMaxId;
    }
}
