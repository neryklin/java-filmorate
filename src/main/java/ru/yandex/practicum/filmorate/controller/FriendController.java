package ru.yandex.practicum.filmorate.controller;

import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import ru.yandex.practicum.filmorate.exception.NotFoundException;
import ru.yandex.practicum.filmorate.model.User;
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

    private final InMemoryFriendStorage inMemoryFriendStorage;



    ///----- вызываем сервис ----- вызываем стородже
    ///----- вызываем сервис ----- вызываем стородже
    ///----- вызываем сервис ----- вызываем стородже
    ///----- вызываем сервис ----- вызываем стородже

//    public User addFriends(User owner,User friend);
//    public User delFriends(User owner,User friend);
//    public Set<User> getCommonFriends(User friend1, User friend2);

    @GetMapping("/users/{id}/friends/common/{otherId}")
    public Collection<User> getCommonFriends(@PathVariable Long id, Long otherId ) {
        return inMemoryFriendStorage.getCommonFriends(id,otherId);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public User create(@Valid @RequestBody User user) {

    }

//    @Override
//    public User addFriends(User owner, User friend) {
//        if (friends.containsKey(owner.getId())) {
//            friends.get(owner.getId()).add(friend.getId());
//        }else {
//            Set<Long> friendSet = new HashSet<>();
//            friendSet.add(friend.getId());
//            friends.put(owner.getId(), friendSet);
//        }
//        return friend;
//    }
//
//    @Override
//    public User delFriends(User owner, User friend) {
//        if (friends.containsKey(owner.getId())) {
//            friends.get(owner.getId()).remove(friend.getId());
//        }
//        return friend;
//    }
//
//    @Override
//    public Set<Long> getCommonFriends(User friend1, User friend2) {
//        Set<Long> friend2Set = friends.get(friend2.getId());
//        Set<Long> commonFriend = friends.get(friend1.getId()).stream()
//                .filter(o->friend2Set.contains(o))
//                .collect(Collectors.toSet());
//        return commonFriend;
//    }
}
