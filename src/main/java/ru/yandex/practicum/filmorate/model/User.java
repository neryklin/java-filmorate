package ru.yandex.practicum.filmorate.model;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Past;
import lombok.AllArgsConstructor;
import lombok.Data;
import ru.yandex.practicum.filmorate.validation.NoWhitespace;

import java.time.LocalDate;


@Data
@AllArgsConstructor
public class User {
    long id;
    @NotBlank
    @Email(message = "адрес не корректный")
    String email;
    @NotBlank(message = "пусто?")
    @NoWhitespace
    String login;
    String name;
    @Past
    LocalDate birthday;
}
