package ru.yandex.practicum.filmorate.controller;

import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;
import ru.yandex.practicum.filmorate.exception.NotFoundException;
import ru.yandex.practicum.filmorate.model.Film;
import ru.yandex.practicum.filmorate.storage.InMemoryFilmStorage;

import java.util.Collection;


@Slf4j
@RestController
@RequestMapping("/films")
public class FilmController {
    private InMemoryFilmStorage inMemoryFilmStorage = new InMemoryFilmStorage();

    @GetMapping
    public Collection<Film> films() {
        return inMemoryFilmStorage.getFilms().values();
    }

    @PostMapping
    public Film create(@Valid @RequestBody Film film) {
        log.info("start create film: {}", film);
        film.setId(inMemoryFilmStorage.getNextId(inMemoryFilmStorage.getFilms()));
        inMemoryFilmStorage.save(film);
        log.info("stop create film: {}", film);
        return film;
    }

    @PutMapping
    public Film update(@Valid @RequestBody Film film) {
        log.info("start update film: {}", film);
        if (inMemoryFilmStorage.containsKeyFilms(film)) {
            inMemoryFilmStorage.update(film);
            log.info("stop update film: {}", film);
            return film;
        }
        throw new NotFoundException("error update film: {" + film + "}");
    }


}
