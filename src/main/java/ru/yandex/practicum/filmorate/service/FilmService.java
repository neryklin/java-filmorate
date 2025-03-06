package ru.yandex.practicum.filmorate.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.yandex.practicum.filmorate.dal.FilmGenreRepository;
import ru.yandex.practicum.filmorate.dal.FilmRepository;
import ru.yandex.practicum.filmorate.dal.GenreRepository;
import ru.yandex.practicum.filmorate.dto.FilmDto;
import ru.yandex.practicum.filmorate.dto.NewFilmRequest;
import ru.yandex.practicum.filmorate.dto.UpdateFilmRequest;
import ru.yandex.practicum.filmorate.exception.NotFoundException;
import ru.yandex.practicum.filmorate.exception.ValidationException;
import ru.yandex.practicum.filmorate.mapper.FilmMapper;
import ru.yandex.practicum.filmorate.model.Film;

import java.util.Collection;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class FilmService {

    private final FilmRepository filmRepository;
    private final FilmGenreRepository filmGenreRepository;
    private final GenreRepository genreRepository;
    private final GenreService genreService;
    private final MpaService mpaService;


    public Collection<FilmDto> getFilms() {
        return filmRepository.findAll()
                .stream()
                .map(FilmMapper::mapToFilmDto)
                .collect(Collectors.toList());
    }

    public FilmDto getFilmsById(Long id) {
        return FilmMapper.mapToFilmDto(filmRepository.findById(id).get());
    }


    public FilmDto createFilm(NewFilmRequest request) {
        if (request.getName() == null || request.getName().isEmpty()) {
            throw new ValidationException("Название должено быть указано");
        }
        if (mpaService.getMpaById(request.getMpa().getId()) == null) {
            throw new NotFoundException("MPA not found ");
        }

        Film film = FilmMapper.mapToFilm(request);
        film = filmRepository.save(film);
        if (request.getGenres() != null && genreService.checkGerneList(request.getGenres()) != false) {
            Long id = filmGenreRepository.save(film);
        }

        return FilmMapper.mapToFilmDto(film);
    }

    public FilmDto updateFilm(long filmId, UpdateFilmRequest request) {
        Film updatedFilm = filmRepository.findById(filmId)
                .map(film -> FilmMapper.updateFilmFields(film, request))
                .orElseThrow(() -> new NotFoundException("Фильм не найден"));
        updatedFilm = filmRepository.update(updatedFilm);
        return FilmMapper.mapToFilmDto(updatedFilm);
    }

}
