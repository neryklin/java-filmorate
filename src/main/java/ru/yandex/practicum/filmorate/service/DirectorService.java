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
import ru.yandex.practicum.filmorate.model.Genre;
import ru.yandex.practicum.filmorate.model.Mpa;

import java.util.Collection;
import java.util.HashSet;
import java.util.List;
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


    public Director updateDirector(long directorID, Director request) {
        Director updatedFDirector= directorRepository.findById(directorID)
                .orElseThrow(() -> new NotFoundException("Director не найден"));
        updatedFDirector.setName(request.getName());
        return directorRepository.update(updatedFDirector);
    }

    public Director createDirector(Director request) {
        if (request.getName() == null || request.getName().isEmpty()) {
            throw new ValidationException("Название должено быть указано");
        }
        return directorRepository.create(request);
    }


    public boolean deleteDirector(Long id) {
        return directorRepository.delete(id);
    }

//    public boolean checkDirectorList(HashSet<Director> setDirector) {
//        List<Long> arrayListDirector = getDirectors().stream()
//                .map(Director::getId)
//                .toList();
//        for (Director director : setDirector) {
//            if (arrayListDirector.contains(director.getId()) == false) {
//                throw new NotFoundException("Director не найден");
//            }
//        }
//        return true;
//    }

}
