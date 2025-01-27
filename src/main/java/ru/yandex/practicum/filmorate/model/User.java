package ru.yandex.practicum.filmorate.model;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

//import javax.validation.constraints.Past;
import java.time.LocalDate;


@Data
public class User {
    long id;
    @Email(message = "адрес не корректный")
    String email;
    @NotBlank(message = "пусто?")
    String login;
    String name;
    LocalDate birthday;
}
