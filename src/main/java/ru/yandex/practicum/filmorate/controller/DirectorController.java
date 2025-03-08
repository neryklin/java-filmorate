package ru.yandex.practicum.filmorate.controller;

import jakarta.validation.Valid;
import jakarta.validation.constraints.Min;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import ru.yandex.practicum.filmorate.dto.FilmDto;
import ru.yandex.practicum.filmorate.dto.NewFilmRequest;
import ru.yandex.practicum.filmorate.dto.UpdateFilmRequest;
import ru.yandex.practicum.filmorate.model.Director;
import ru.yandex.practicum.filmorate.service.DirectorService;
import ru.yandex.practicum.filmorate.service.FilmService;

import java.util.Collection;

@Slf4j
@RestController
@RequiredArgsConstructor
@Validated
public class DirectorController {

    private final DirectorService directorService;

    @GetMapping("/directors")
    @ResponseStatus(HttpStatus.OK)
    public Collection<Director> directors() {
        return directorService.getDirectors();
    }

    @GetMapping("/directors/{id}")
    @ResponseStatus(HttpStatus.OK)
    public Director director(@PathVariable @Min(0) Long id) {
        return directorService.getDirectorById(id);
    }

    @PostMapping("/directors")
    @ResponseStatus(HttpStatus.CREATED)
    public Director create(@Valid @RequestBody Director newDirectorRequest) {
        log.info("start create director: {}", newDirectorRequest);
        return directorService.createDirector(newDirectorRequest);
    }

    @PutMapping("/directors")
    @ResponseStatus(HttpStatus.OK)
    public Director update(@Valid @RequestBody Director updateDirectorRequest) {
        log.info("start update director: {}", updateDirectorRequest);
        return directorService.updateDirector(updateDirectorRequest.getId(), updateDirectorRequest);

    }


}
