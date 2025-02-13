package ru.yandex.practicum.filmorate.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import ru.yandex.practicum.filmorate.exception.NotFoundException;
import ru.yandex.practicum.filmorate.model.User;
import ru.yandex.practicum.filmorate.storage.InMemoryFriendStorage;

import java.util.Optional;
import java.util.Set;

@Component
@RequiredArgsConstructor
public class FriendService {
    private final InMemoryFriendStorage inMemoryFriendStorage;

    public User addFriends(Long id, Long othetId) {
        if (inMemoryFriendStorage.getInMemoryUserStorage().containsUserById(id)
                && inMemoryFriendStorage.getInMemoryUserStorage().containsUserById(othetId)) {
            inMemoryFriendStorage.addFriends(id, othetId);
            return inMemoryFriendStorage.addFriends(othetId, id);
        } else {
            throw new NotFoundException("not found owner : {" + id + " " + othetId + "}");
        }
    }


    public User delFriends(Long owner, Long friend) {
        if (inMemoryFriendStorage.containsUserById(owner) && inMemoryFriendStorage.containsUserById(friend)) {
            inMemoryFriendStorage.delFriends(owner, friend);
            return inMemoryFriendStorage.delFriends(friend, owner);
        } else {
            throw new NotFoundException("not found owner : {" + owner + " " + friend + "}");
        }
    }

    public Optional<Set<User>> getCommonFriends(Long friend1, Long friend2) {
        Set<User> commonFriend;
        if (inMemoryFriendStorage.containsUserById(friend1) && inMemoryFriendStorage.containsUserById(friend2)) {
            commonFriend = inMemoryFriendStorage.getCommonFriends(friend1, friend2);
        } else {
            commonFriend = null;
        }
        return Optional.ofNullable(commonFriend);
    }

    public Optional<Set<User>> getFriends(Long owner) {
        if (inMemoryFriendStorage.containsUserById(owner)) {
            return Optional.ofNullable(inMemoryFriendStorage.getFriends(owner));
        } else {
            throw new NotFoundException("not found owner : {" + owner + "}");
        }
    }

}
