package ru.yandex.practicum.filmorate.model;


import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import ru.yandex.practicum.filmorate.validation.AfterOrEqualData;

import java.time.LocalDate;
import java.util.HashSet;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class Film {


    private long id;
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
    private HashSet<Genre> genres;
}
