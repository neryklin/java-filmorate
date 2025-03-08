package ru.yandex.practicum.filmorate;

import org.springframework.stereotype.Component;
import ru.yandex.practicum.filmorate.model.Film;
import ru.yandex.practicum.filmorate.model.Genre;
import ru.yandex.practicum.filmorate.model.Mpa;
import ru.yandex.practicum.filmorate.model.User;

import java.time.LocalDate;
import java.util.HashSet;

@Component
public class GenereteTestDate {
    public User getTestUser() {
        User testUser = new User();
        testUser.setId(1L);
        testUser.setName("name");
        testUser.setEmail("email@mail.ru");
        testUser.setLogin("login");
        testUser.setBirthday(LocalDate.of(2000, 01, 01));
        return testUser;
    }

    public Film getTestFilm() {
        Film testFilm = new Film();
        testFilm.setId(1L);
        testFilm.setName("film_name1");
        testFilm.setDescription("description1");
        testFilm.setReleaseDate(LocalDate.of(2000, 01, 03));
        testFilm.setDuration(135);
        testFilm.setMpa(new Mpa(1, "G"));
        testFilm.setGenres(new HashSet<Genre>());
        return testFilm;
    }

}
