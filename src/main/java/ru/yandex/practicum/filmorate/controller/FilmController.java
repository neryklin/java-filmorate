package ru.yandex.practicum.filmorate.controller;

import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;
import ru.yandex.practicum.filmorate.exception.NotFoundException;
import ru.yandex.practicum.filmorate.model.Film;
import ru.yandex.practicum.filmorate.storage.Storage;

import java.util.Collection;


@Slf4j
@RestController
@RequestMapping("/films")
public class FilmController {
    Storage storage = new Storage();

    @GetMapping
    public Collection<Film> films() {
        return storage.getFilms().values();
    }

    @PostMapping
    public Film create(@Valid @RequestBody Film film) {
        log.info("start create film: {}", film);
        film.setId(storage.getNextId(storage.getFilms()));
        storage.save(film);
        log.info("stop create film: {}", film);
        return film;
    }

    @PutMapping
    public Film update(@Valid @RequestBody Film film) {
        log.info("start update film: {}", film);
        if (storage.containsKeyFilms(film)) {
            storage.update(film);
            log.info("stop update film: {}", film);
            return film;
        }
        throw new NotFoundException("error update film: {" + film + "}");
    }


}
