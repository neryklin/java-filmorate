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



    public User addFriends(User owner, User friend) {
        if (friends.containsKey(owner.getId())) {
         friends.get(owner.getId()).add(friend);
        }else {
            Set<User> friendSet = new HashSet<>();
            friendSet.add(friend);
            friends.put(owner, friendSet);
        }
        return friend;
    }
    public boolean containsKeyOwner(User user) {
        return friends.containsKey(user.getId());
    }

    public User delFriends(User owner, User friend) {
        if (friends.containsKey(owner)) {
            friends.get(owner).remove(friend);
        }
        return friend;
    }


    public Set<User> getCommonFriends(Long id, Long otherId) {
        User friend1 = inMemoryUserStorage.getUserById(id);
        User friend2 = inMemoryUserStorage.getUserById(otherId);
        Set<User> friend2Set = friends.get(friend2);
        Set<User> commonFriend = friends.get(friend1).stream()
                .filter(o->friend2Set.contains(o))
                .collect(Collectors.toSet());
        return commonFriend;
    }
}
