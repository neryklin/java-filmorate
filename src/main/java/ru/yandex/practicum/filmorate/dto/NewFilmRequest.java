package ru.yandex.practicum.filmorate.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;
import lombok.Data;
import ru.yandex.practicum.filmorate.model.Genre;
import ru.yandex.practicum.filmorate.model.Mpa;
import ru.yandex.practicum.filmorate.validation.AfterOrEqualData;

import java.time.LocalDate;
import java.util.HashSet;


@Data
public class NewFilmRequest {
    HashSet<Genre> genres;
    @NotBlank
    private String name;
    @Size(max = 200)
    private String description;
    @AfterOrEqualData("1895-12-28")
    private LocalDate releaseDate;
    @Positive
    private int duration;
    @NotNull
    private Mpa mpa;
}

