package ru.yandex.practicum.filmorate.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.yandex.practicum.filmorate.dal.MpaRepository;
import ru.yandex.practicum.filmorate.exception.NotFoundException;
import ru.yandex.practicum.filmorate.model.Mpa;

import java.util.Collection;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class MpaService {

    private final MpaRepository mpaRepository;

    public Collection<Mpa> getMpas() {
        return mpaRepository.findAll()
                .stream()
                .collect(Collectors.toList());
    }

    public Mpa getMpaById(Long id) {
        Mpa mpa = mpaRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Mpa не найден"));
        return mpa;
    }
}
