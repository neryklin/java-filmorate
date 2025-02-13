package ru.yandex.practicum.filmorate.storage;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.stereotype.Component;
import ru.yandex.practicum.filmorate.exception.NotFoundException;
import ru.yandex.practicum.filmorate.model.Film;
import ru.yandex.practicum.filmorate.model.User;

import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

@Component
@Getter
@AllArgsConstructor
public class InMemoryLikeStorage {
    private final Map<Film, Set<User>> likes;
    private final InMemoryFilmStorage inMemoryFilmStorage;
    private final InMemoryUserStorage inMemoryUserStorage;


    public Film addLike(Long id, Long userId) {
        Film film;
        User user;
        if (inMemoryFilmStorage.containsKeyFilms(inMemoryFilmStorage.getFilmById(id).get())
                && inMemoryUserStorage.containsKeyUser(inMemoryUserStorage.getUserById(userId).get())) {
            film = inMemoryFilmStorage.getFilmById(id).get();
            user = inMemoryUserStorage.getUserById(userId).get();
        } else {
            throw new NotFoundException("film or user not found");
        }

        if (likes.containsKey(film)) {
            likes.get(film).add(user);
        } else {
            Set<User> likeSet = new HashSet<>();
            likeSet.add(user);
            likes.put(film, likeSet);
        }
        return film;
    }


    public Film delLike(Long id, Long userId) {
        Film film;
        User user;
        if (inMemoryFilmStorage.containsKeyFilms(inMemoryFilmStorage.getFilmById(id).get())
                && inMemoryUserStorage.containsKeyUser(inMemoryUserStorage.getUserById(userId).get())) {
            film = inMemoryFilmStorage.getFilmById(id).get();
            user = inMemoryUserStorage.getUserById(id).get();
        } else {
            throw new NotFoundException("film or user not found");
        }

        if (likes.containsKey(film)) {
            likes.get(film).remove(user);
        }
        return film;
    }

    public boolean containsFilmById(Long idFilm) {
        return likes.containsKey(inMemoryFilmStorage.getFilmById(idFilm).get());
    }

    public List<Film> getTopFilms(int count) {
        List<Film> topFilms = likes.entrySet().stream()
                .sorted((o1, o2) -> o2.getValue().size() - o1.getValue().size())
                .map(o -> o.getKey())
                .limit(count)
                .collect(Collectors.toList());
        return topFilms;
    }
}
