package ru.yandex.practicum.filmorate.storage;

import lombok.Getter;
import org.springframework.stereotype.Component;
import ru.yandex.practicum.filmorate.model.Film;

import java.util.HashMap;
import java.util.Map;

@Component
@Getter
public class InMemoryFilmStorage implements FilmStorage, CommonStorageMetods {

    private Map<Long, Film> films = new HashMap<>();

    @Override
    public Film save(Film film) {
        films.put(film.getId(), film);
        return film;
    }


    @Override
    public boolean containsKeyFilms(Film film) {
        return films.containsKey(film.getId());
    }

    @Override
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

    @Override
    public long getNextId(Map<Long, ?> map) {
        return CommonStorageMetods.super.getNextId(map);
    }
}
