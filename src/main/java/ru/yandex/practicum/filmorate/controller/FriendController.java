package ru.yandex.practicum.filmorate.controller;

import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import ru.yandex.practicum.filmorate.exception.NotFoundException;
import ru.yandex.practicum.filmorate.model.User;
import ru.yandex.practicum.filmorate.service.FriendService;
import ru.yandex.practicum.filmorate.storage.InMemoryFriendStorage;
import ru.yandex.practicum.filmorate.storage.InMemoryUserStorage;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;


@Slf4j
@RestController
@RequestMapping("/friends")
@AllArgsConstructor
public class FriendController {

   // private final InMemoryFriendStorage inMemoryFriendStorage;
    private final FriendService friendService;


    ///----- вызываем сервис ----- вызываем стородже
    ///----- вызываем сервис ----- вызываем стородже
    ///----- вызываем сервис ----- вызываем стородже
    ///----- вызываем сервис ----- вызываем стородже

//    public User addFriends(User owner,User friend);
//    public User delFriends(User owner,User friend);
//    public Set<User> getCommonFriends(User friend1, User friend2);

    @GetMapping("/users/{id}/friends/common/{otherId}")
    public Collection<User> getCommonFriends(@PathVariable Long id, Long otherId ) {
        return friendService.getCommonFriends(id,otherId).get();
    }

    @PostMapping("/users/{id}/friends/{friendId}")
    @ResponseStatus(HttpStatus.CREATED)
    public User addFriends(@PathVariable Long id, Long friendId ) {
        return friendService.addFriends(id,friendId);
    }

    @DeleteMapping("/users/{id}/friends/{friendId}")
    @ResponseStatus(HttpStatus.OK)
    public User delFriends(@PathVariable Long id, Long friendId ) {
        return friendService.delFriends(id,friendId);
    }
}
