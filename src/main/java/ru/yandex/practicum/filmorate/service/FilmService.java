package ru.yandex.practicum.filmorate.service;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import ru.yandex.practicum.filmorate.exception.NotFoundException;
import ru.yandex.practicum.filmorate.model.Film;
import ru.yandex.practicum.filmorate.storage.InMemoryFilmStorage;

@Component
@Getter
@RequiredArgsConstructor
public class FilmService {
    private final InMemoryFilmStorage inMemoryFilmStorage;

    public Film save(Film film) {
        inMemoryFilmStorage.save(film);
        return film;
    }

    public Film update(Film film) {
        if (inMemoryFilmStorage.containsKeyFilms(film)) {
            inMemoryFilmStorage.update(film);
            return film;
        } else {
            throw new NotFoundException("film not found");
        }

    }
}
