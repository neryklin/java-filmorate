package ru.yandex.practicum.filmorate.controller;

import jakarta.validation.constraints.Min;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import ru.yandex.practicum.filmorate.model.Genre;
import ru.yandex.practicum.filmorate.service.GenreService;

import java.util.Collection;

@Slf4j
@RestController
@RequiredArgsConstructor
@Validated
public class GenreController {

    private final GenreService genreService;

    @GetMapping("/genres")
    @ResponseStatus(HttpStatus.OK)
    public Collection<Genre> getGenres() {

        return genreService.getGenres();
    }

    @GetMapping("/genres/{id}")
    @ResponseStatus(HttpStatus.OK)
    public Genre getGenreById(@PathVariable @Min(0) Long id) {

        return genreService.getGenreById(id);
    }
}
