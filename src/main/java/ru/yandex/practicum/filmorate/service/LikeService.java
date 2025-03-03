package ru.yandex.practicum.filmorate.service;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import ru.yandex.practicum.filmorate.dal.FilmRepository;
import ru.yandex.practicum.filmorate.dal.LikeRepository;
import ru.yandex.practicum.filmorate.dal.UserRepository;
import ru.yandex.practicum.filmorate.exception.NotFoundException;
import ru.yandex.practicum.filmorate.model.Film;
import ru.yandex.practicum.filmorate.model.User;

@Component
@Getter
@RequiredArgsConstructor
public class LikeService {
    final LikeRepository likeRepository;
    final FilmRepository filmRepository;
    final UserRepository userRepository;

    public void addLike(Long id, Long userId) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new NotFoundException("User not found " + userId));
        Film film = filmRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Film not found " + id));
        likeRepository.addLike(film, user);
    }

    public void deleteLike(Long id, Long userId) {
        User user = userRepository.findById(userId)
        .orElseThrow(() -> new NotFoundException("User not found " + userId));
        Film film = filmRepository.findById(id)
        .orElseThrow(() -> new NotFoundException("Film not found " + id));
        likeRepository.deleteLike(film, user);
    }


//    public List<Film> getTopFilms(int count) {
//        return inMemoryLikeStorage.getTopFilms(count);
//    }
}
