package ru.yandex.practicum.filmorate.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Past;
import lombok.Data;
import ru.yandex.practicum.filmorate.validation.NoWhitespace;

import java.time.LocalDate;

@Data
public class NewUserRequest {
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
