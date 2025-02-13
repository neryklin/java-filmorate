package ru.yandex.practicum.filmorate.service;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import ru.yandex.practicum.filmorate.exception.NotFoundException;
import ru.yandex.practicum.filmorate.model.Film;
import ru.yandex.practicum.filmorate.storage.InMemoryLikeStorage;

import java.util.List;

@Component
@Getter
@RequiredArgsConstructor
public class LikeService {
    final InMemoryLikeStorage inMemoryLikeStorage;

    public Film addLike(Long id, Long userId) {
        return inMemoryLikeStorage.addLike(id, userId);
    }


    public Film delLike(Long id, Long userId) {
        if (inMemoryLikeStorage.containsFilmById(id)) {
            return inMemoryLikeStorage.delLike(id, userId);
        } else {
            throw new NotFoundException("film not found");
        }

    }


    public List<Film> getTopFilms(int count) {
        return inMemoryLikeStorage.getTopFilms(count);
    }
}
