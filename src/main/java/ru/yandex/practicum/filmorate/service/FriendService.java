package ru.yandex.practicum.filmorate.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import ru.yandex.practicum.filmorate.exception.NotFoundException;
import ru.yandex.practicum.filmorate.model.User;
import ru.yandex.practicum.filmorate.storage.InMemoryFriendStorage;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class FriendService {
private final InMemoryFriendStorage inMemoryFriendStorage;

    public User addFriends(Long id, Long othetId) {
        return inMemoryFriendStorage.addFriends(id,othetId);
    }


    public User delFriends(Long owner, Long friend) {
        if (inMemoryFriendStorage.containsUserById(owner)) {
            return inMemoryFriendStorage.delFriends(owner,friend);
        } else {
            throw new NotFoundException("not found owner : {" + owner + "}");
        }
    }

    public Optional<Set<User>> getCommonFriends(Long friend1, Long friend2) {
        Set<User> commonFriend;
        if (inMemoryFriendStorage.containsUserById(friend1) && inMemoryFriendStorage.containsUserById(friend2)) {
            commonFriend=inMemoryFriendStorage.getCommonFriends(friend1,friend2);
        } else {
            commonFriend = null;
        }

        return Optional.ofNullable(commonFriend);
    }

}
