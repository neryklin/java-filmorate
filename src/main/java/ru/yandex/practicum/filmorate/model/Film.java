package ru.yandex.practicum.filmorate.model;

import jakarta.validation.Valid;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.hibernate.validator.constraints.Length;

import java.time.LocalDate;


/**
 * Film.
 */
@Data
@AllArgsConstructor
public class Film {
    long id;
    @NotBlank
    String name;
    @Size(max=200)
    String description;
    LocalDate releaseDate;
    @Positive
    int duration;
}
