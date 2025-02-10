package ru.yandex.practicum.filmorate.storage;

import lombok.Getter;
import org.springframework.stereotype.Component;
import ru.yandex.practicum.filmorate.model.Film;
import ru.yandex.practicum.filmorate.model.User;
import ru.yandex.practicum.filmorate.service.LikeService;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

@Component
@Getter
public class InMemoryLikeStorage implements LikeService {
    private Map<Long, Set<Long>> likes = new HashMap<>();

    @Override
    public Film addLike(Film film, User user) {
        if (likes.containsKey(film.getId())) {
            likes.get(film.getId()).add(user.getId());
        }else {
            Set<Long> likeSet = new HashSet<>();
            likeSet.add(user.getId());
            likes.put(film.getId(), likeSet);
        }
        return film;
    }

    @Override
    public Film delLike(Film film, User user) {
        if (likes.containsKey(film.getId())) {
            likes.get(film.getId()).remove(user.getId());
        }
        return film;
    }

    @Override
    public Set<Long> getTopFilms() {
        Set<Long> topFilms = likes.entrySet().stream()
                .sorted((o1, o2) -> o1.getValue().size()-o2.getValue().size())
                .map(o -> o.getKey())
                .limit(10)
                .collect(Collectors.toSet());
        return topFilms;
    }
}
