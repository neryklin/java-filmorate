package ru.yandex.practicum.filmorate.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import ru.yandex.practicum.filmorate.model.Film;
import ru.yandex.practicum.filmorate.storage.InMemoryFilmStorage;

@Component
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
        }
        return film;
    }
}
