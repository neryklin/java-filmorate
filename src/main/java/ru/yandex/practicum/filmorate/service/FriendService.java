package ru.yandex.practicum.filmorate.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
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

    public User addFriends(User owner, User friend) {
        inMemoryFriendStorage.addFriends(owner,friend);
        return friend;
    }


    public User delFriends(User owner, User friend) {
        if (inMemoryFriendStorage.containsKeyOwner(owner)) {
           inMemoryFriendStorage.delFriends(owner,friend);
        }
        return friend;
    }

    public Optional<Set<User>> getCommonFriends(User friend1, User friend2) {
        Set<User> commonFriend;
        if (inMemoryFriendStorage.containsKeyOwner(friend1) && inMemoryFriendStorage.containsKeyOwner(friend2)) {
            commonFriend=inMemoryFriendStorage.getCommonFriends(friend1,friend2);
        } else {
            commonFriend = null;
        }

        return Optional.of(commonFriend);
    }

}
