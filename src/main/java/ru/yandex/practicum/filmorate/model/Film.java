package ru.yandex.practicum.filmorate.model;


import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import ru.yandex.practicum.filmorate.validation.ReleaseDate;

import java.time.LocalDate;


@Data
@AllArgsConstructor
public class Film {
    private long id;
    @NotBlank
    private String name;
    @Size(max = 200)
    private String description;
    @ReleaseDate()
    private LocalDate releaseDate;
    @Positive
    private int duration;
}
