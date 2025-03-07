package ru.yandex.practicum.filmorate.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.yandex.practicum.filmorate.dal.GenreRepository;
import ru.yandex.practicum.filmorate.exception.NotFoundException;
import ru.yandex.practicum.filmorate.model.Genre;

import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class GenreService {

    private final GenreRepository genreRepository;

    public Collection<Genre> getGenres() {
        return genreRepository.findAll()
                .stream()
                .collect(Collectors.toList());
    }

    public Genre getGenreById(Long id) {
        Genre genre = genreRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Genre не найден"));
        return genre;
    }

    public boolean checkGerneList(HashSet<Genre> setGenre) {
        List<Long> arrayListGenre = getGenres().stream()
                .map(Genre::getId)
                .toList();
        for (Genre genre : setGenre) {
            if (arrayListGenre.contains(genre.getId()) == false) {
                throw new NotFoundException("Genre не найден");
            }
        }
        return true;
    }
}
