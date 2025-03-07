package ru.yandex.practicum.filmorate.model;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Past;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import ru.yandex.practicum.filmorate.validation.NoWhitespace;

import java.time.LocalDate;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class User {
    private long id;
    @NotBlank
    @Email(message = "адрес не корректный")
    private String email;
    @NotBlank(message = "пусто?")
    @NoWhitespace
    private String login;
    private String name;
    @Past
    private LocalDate birthday;

}
