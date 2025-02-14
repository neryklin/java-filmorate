package ru.yandex.practicum.filmorate.controller;

import jakarta.validation.Valid;
import jakarta.validation.constraints.Min;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import ru.yandex.practicum.filmorate.model.Film;
import ru.yandex.practicum.filmorate.service.FilmService;

import java.util.Collection;

@Slf4j
@RestController
@RequiredArgsConstructor
@Validated
public class FilmController {

    private final FilmService filmService;

    @GetMapping("/films")
    @ResponseStatus(HttpStatus.OK)
    public Collection<Film> films() {
        return filmService.getInMemoryFilmStorage().getFilms().values();
    }

    @GetMapping("/films/{id}")
    @ResponseStatus(HttpStatus.OK)
    public Film film(@PathVariable @Min(0) Long id) {
        return filmService.getInMemoryFilmStorage().getFilmById(id).get();
    }

    @PostMapping("/films")
    @ResponseStatus(HttpStatus.CREATED)
    public Film create(@Valid @RequestBody Film film) {
        log.info("start create film: {}", film);
        filmService.save(film);
        log.info("stop create film: {}", film);
        return film;
    }

    @PutMapping("/films")
    @ResponseStatus(HttpStatus.OK)
    public Film update(@Valid @RequestBody Film film) {
        log.info("start update film: {}", film);
        filmService.update(film);
        log.info("stop update film: {}", film);
        return film;

    }


}
