package ru.yandex.practicum.filmorate.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.yandex.practicum.filmorate.dal.DirectorRepository;
import ru.yandex.practicum.filmorate.dal.MpaRepository;
import ru.yandex.practicum.filmorate.dto.FilmDto;
import ru.yandex.practicum.filmorate.dto.NewFilmRequest;
import ru.yandex.practicum.filmorate.dto.UpdateFilmRequest;
import ru.yandex.practicum.filmorate.exception.NotFoundException;
import ru.yandex.practicum.filmorate.exception.ValidationException;
import ru.yandex.practicum.filmorate.mapper.FilmMapper;
import ru.yandex.practicum.filmorate.model.Director;
import ru.yandex.practicum.filmorate.model.Film;
import ru.yandex.practicum.filmorate.model.Mpa;

import java.util.Collection;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class DirectorService {

    private final DirectorRepository directorRepository;

    public Collection<Director> getDirectors() {
        return directorRepository.findAll()
                .stream()
                .collect(Collectors.toList());
    }

    public Director getDirectorById(Long id) {
        Director director = directorRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Director не найден"));
        return director;
    }


    public Director updateDirector(long directorID, Director request) {
        Director updatedFDirector= directorRepository.findById(directorID)
                .orElseThrow(() -> new NotFoundException("Фильм не найден"));
        updatedFDirector.setName(request.getName());
        return directorRepository.update(updatedFDirector);
    }

    public Director createDirector(Director request) {
        if (request.getName() == null || request.getName().isEmpty()) {
            throw new ValidationException("Название должено быть указано");
        }
        return directorRepository.create(request);
    }


}
