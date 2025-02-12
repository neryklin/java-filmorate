package ru.yandex.practicum.filmorate.storage;



import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.stereotype.Component;
import ru.yandex.practicum.filmorate.model.Film;
import ru.yandex.practicum.filmorate.model.User;
import ru.yandex.practicum.filmorate.service.FriendService;

import java.util.*;
import java.util.stream.Collectors;

@Component
@Getter
@AllArgsConstructor
public class InMemoryFriendStorage {
    private Map<User, Set<User>> friends = new HashMap<>();
    private InMemoryUserStorage inMemoryUserStorage;



    public User addFriends(Long id, Long otherId) {
        User owner = inMemoryUserStorage.getUserById(id).get();
        User friend = inMemoryUserStorage.getUserById(otherId).get();
        if (friends.containsKey(owner.getId())) {
         friends.get(owner.getId()).add(friend);
        }else {
            Set<User> friendSet = new HashSet<>();
            friendSet.add(friend);
            friends.put(owner, friendSet);
        }
        return friend;
    }
    public boolean containsUser(User user) {
        return friends.containsKey(user);
    }

    public boolean containsUserById(Long idUser) {
        return friends.containsKey(inMemoryUserStorage.getUserById(idUser));
    }

    public User delFriends(Long id, Long otherId) {
        User owner = inMemoryUserStorage.getUserById(id).get();
        User friend = inMemoryUserStorage.getUserById(otherId).get();
        if (friends.containsKey(owner)) {
            friends.get(owner).remove(friend);
        }
        return friend;
    }


    public Set<User> getCommonFriends(Long id, Long otherId) {
        User friend1 = inMemoryUserStorage.getUserById(id).get();
        User friend2 = inMemoryUserStorage.getUserById(otherId).get();
        Set<User> friend2Set = friends.get(friend2);
        Set<User> commonFriend = friends.get(friend1).stream()
                .filter(o->friend2Set.contains(o))
                .collect(Collectors.toSet());
        return commonFriend;
    }
}
