package ru.yandex.practicum.filmorate.model;

import jakarta.validation.*;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import java.time.LocalDate;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class FilmTest {

    private static final Validator validator;
    static {
        ValidatorFactory validatorFactory=Validation.buildDefaultValidatorFactory();
        validator = validatorFactory.usingContext().getValidator();
    }

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
        Set<ConstraintViolation<Film>> violations = validator.validate(film);
        assertTrue(violations.isEmpty(),"ghghghg");
     //   film.setDuration(-1);
     //   System.out.println(film);
     //   assertEquals(film.getDuration(),-44,"Ошибка");
    }
}