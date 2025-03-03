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


    public User addFriends(Long id, Long othetId) {
        User user = userRepository.findById(id)
                        .orElseThrow(() -> new NotFoundException("User not found " + id));
        User fUser = userRepository.findById(othetId)
                        .orElseThrow(() -> new NotFoundException("User not found " + othetId));
        return friendRepository.addFriend(user, fUser);
    }


    public User deleteFriends(Long id, Long othetId) {
        User user = userRepository.findById(id)
                        .orElseThrow(() -> new NotFoundException("User not found " + id));
        User fUser = userRepository.findById(othetId)
                        .orElseThrow(() -> new NotFoundException("User not found " + othetId));
        return friendRepository.deleteFriend(user, fUser);
    }

//    public Optional<Set<User>> getCommonFriends(Long friend1, Long friend2) {
//        Set<User> commonFriend;
//        if (inMemoryFriendStorage.containsUserById(friend1) && inMemoryFriendStorage.containsUserById(friend2)) {
//            commonFriend = inMemoryFriendStorage.getCommonFriends(friend1, friend2);
//        } else {
//            commonFriend = null;
//        }
//        return Optional.ofNullable(commonFriend);
//    }

    public Optional<List<User>> getFriends(Long id) {
        User user = userRepository.findById(id)
                        .orElseThrow(() -> new NotFoundException("User not found " + id));
        return Optional.ofNullable(friendRepository.getFriendById(user));
    }

}
