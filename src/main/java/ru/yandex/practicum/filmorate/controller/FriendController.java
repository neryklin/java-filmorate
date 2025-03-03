package ru.yandex.practicum.filmorate.controller;

import jakarta.validation.Valid;
import jakarta.validation.constraints.Min;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import ru.yandex.practicum.filmorate.model.User;
import ru.yandex.practicum.filmorate.service.FriendService;

import java.util.Collection;


@Slf4j
@RestController
@RequestMapping
@AllArgsConstructor
@Validated
public class FriendController {

    private final FriendService friendService;

    @GetMapping("/users/{id}/friends")
    public Collection<User> getFriends(@PathVariable @Min(0) Long id) {
        log.info("get friends id: {}", id);
        return friendService.getFriends(id).get();
    }

//    @GetMapping("/users/{id}/friends/common/{otherId}")
//    public Collection<User> getCommonFriends(@PathVariable @Min(0) Long id, @PathVariable @Min(0) Long otherId) {
//        log.info("get common friends id: {} {}", id, otherId);
//        return friendService.getCommonFriends(id, otherId).get();
//    }

    @PutMapping("/users/{id}/friends/{friendId}")
    @ResponseStatus(HttpStatus.OK)
    public User addFriends(@PathVariable @Min(0) Long id, @PathVariable @Min(0) Long friendId) {
        log.info("add friends id: {} {}", id, friendId);
        return friendService.addFriends(id, friendId);
    }

    @DeleteMapping("/users/{id}/friends/{friendId}")
    @ResponseStatus(HttpStatus.OK)
    public User deleteFriends(@Valid @PathVariable @Min(0) Long id, @PathVariable @Min(0) Long friendId) {
        log.info("del friends id: {} {}", id, friendId);
        return friendService.deleteFriends(id, friendId);
    }
}

