package ru.yandex.practicum.filmorate.controller;

import jakarta.validation.constraints.Min;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import ru.yandex.practicum.filmorate.service.LikeService;


@Slf4j
@RestController
@RequestMapping
@AllArgsConstructor
@Validated
public class LikeController {

    private final LikeService likeService;

//    @GetMapping("/films/popular")
//    public Collection<Film> getTopFilm(@RequestParam(defaultValue = "10") @Min(0) int count) {
//        log.info("get top film {}", count);
////        return likeService.getTopFilms(count);
//    }

    @PutMapping("/films/{id}/like/{userId}")
    @ResponseStatus(HttpStatus.OK)
    public void addLike(@PathVariable @Min(0) Long id, @PathVariable @Min(0) Long userId) {
        log.info("add like id: {} {}", id, userId);
        likeService.addLike(id, userId);
    }

    @DeleteMapping("/films/{id}/like/{userId}")
    @ResponseStatus(HttpStatus.OK)
    public void deleteLike(@PathVariable @Min(0) Long id, @PathVariable @Min(0) Long userId) {
        log.info("del like id: {} {}", id, userId);
        likeService.deleteLike(id, userId);
    }
}
