package ru.yandex.practicum.filmorate.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import ru.yandex.practicum.filmorate.dal.FriendRepository;
import ru.yandex.practicum.filmorate.dal.UserRepository;
import ru.yandex.practicum.filmorate.exception.NotFoundException;
import ru.yandex.practicum.filmorate.model.User;

import java.util.List;
import java.util.Optional;

@Component
@RequiredArgsConstructor
public class FriendService {
    private final FriendRepository friendRepository;
    private final UserRepository userRepository;


    public Optional<User> addFriends(Long id, Long othetId) {
        User user = checkGetUser(id);
        User fUser = checkGetUser(othetId);
        return Optional.ofNullable(friendRepository.addFriend(user, fUser));
    }


    public Optional<User> deleteFriends(Long id, Long othetId) {
        User user = checkGetUser(id);
        User fUser = checkGetUser(othetId);
        return Optional.ofNullable(friendRepository.deleteFriend(user, fUser));
    }

    public Optional<List<User>> getCommonFriends(Long friend1, Long friend2) {
        User user1 = checkGetUser(friend1);
        User user2 = checkGetUser(friend2);
        return Optional.ofNullable(friendRepository.getCommonFriends(user1, user2));
    }

    public Optional<List<User>> getFriends(Long id) {
        User user = checkGetUser(id);
        return Optional.ofNullable(friendRepository.getFriendById(user));
    }

    public User checkGetUser(Long id) {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("User not found " + id));
        return user;
    }

}
