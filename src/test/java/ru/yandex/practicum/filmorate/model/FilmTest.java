package ru.yandex.practicum.filmorate.model;

import org.junit.jupiter.api.Test;

import java.io.FilterOutputStream;
import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

class FilmTest {

    @Test
    void setId() {
    }

    @Test
    void setName() {
    }

    @Test
    void setDescription() {
    }

    @Test
    void setReleaseDate() {
    }

    @Test
    void setDuration() {
        Film film = new Film(2,"xcxcb","dvsd", LocalDate.now(),-44);
        film.setDuration(-1);
        System.out.println(film);
        assertEquals(film.getDuration(),-1,"Ошибка");
    }
}