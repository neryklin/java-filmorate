package ru.yandex.practicum.filmorate.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import ru.yandex.practicum.filmorate.dto.FilmDto;
import ru.yandex.practicum.filmorate.dto.NewFilmRequest;
import ru.yandex.practicum.filmorate.dto.UpdateFilmRequest;
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
    public Collection<FilmDto> films() {
        return filmService.getFilms();
    }

//    @GetMapping("/films/{id}")
//    @ResponseStatus(HttpStatus.OK)
//    public FilmDto film(@PathVariable @Min(0) Long id) {
//        return filmService.getFilmById(id).get();
//    }

    @PostMapping("/films")
    @ResponseStatus(HttpStatus.CREATED)
    public FilmDto create(@Valid @RequestBody NewFilmRequest newFilmRequest) {
        log.info("start create film: {}", newFilmRequest);
        return filmService.createFilm(newFilmRequest);
    }

    @PutMapping("/films")
    @ResponseStatus(HttpStatus.OK)
    public FilmDto update(@Valid @RequestBody UpdateFilmRequest updateFilmRequest) {
        log.info("start update film: {}", updateFilmRequest);
        return filmService.updateFilm(updateFilmRequest.getId(), updateFilmRequest);

    }


}
