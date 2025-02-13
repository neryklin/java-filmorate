package ru.yandex.practicum.filmorate.controller;

import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import ru.yandex.practicum.filmorate.model.Film;
import ru.yandex.practicum.filmorate.service.LikeService;

import java.util.Collection;


@Slf4j
@RestController
@RequestMapping
@AllArgsConstructor
public class LikeController {

    private final LikeService likeService;

    @GetMapping("/films/popular")
    public Collection<Film> getTopFilm(@Valid @RequestParam(defaultValue = "10") int count) {
        log.info("get top film {}", count);
        return likeService.getTopFilms(count);
    }

    @PutMapping("/films/{id}/like/{userId}")
    @ResponseStatus(HttpStatus.OK)
    public Film addLike(@Valid @PathVariable Long id, @PathVariable Long userId) {
        log.info("add like id: {} {}", id, userId);
        return likeService.addLike(id, userId);
    }

    @DeleteMapping("/films/{id}/like/{userId}")
    @ResponseStatus(HttpStatus.OK)
    public Film delLike(@Valid @PathVariable Long id, @PathVariable Long userId) {
        log.info("del like id: {} {}", id, userId);
        return likeService.delLike(id, userId);
    }
}
