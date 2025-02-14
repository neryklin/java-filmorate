package ru.yandex.practicum.filmorate.storage;

import lombok.Getter;
import org.springframework.stereotype.Component;
import ru.yandex.practicum.filmorate.exception.NotFoundException;
import ru.yandex.practicum.filmorate.model.Film;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

@Component
@Getter
public class InMemoryFilmStorage implements CommonStorageMetods {

    private Map<Long, Film> films = new HashMap<>();


    public Film save(Film film) {
        film.setId(getNextId(films));
        films.put(film.getId(), film);
        return film;
    }


    public boolean containsKeyFilms(Film film) {
        return films.containsKey(film.getId());
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

    @Override
    public long getNextId(Map<Long, ?> map) {
        return CommonStorageMetods.super.getNextId(map);
    }

    public Optional<Film> getFilmById(Long id) {
        Optional<Film> film = Optional.ofNullable(films.get(id));
        film.orElseThrow(() -> new NotFoundException("film not finde " + id));
        return film;
    }
}
