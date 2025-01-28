package ru.yandex.practicum.filmorate.model;

import jakarta.validation.ConstraintViolation;
import jakarta.validation.Validation;
import jakarta.validation.Validator;
import jakarta.validation.ValidatorFactory;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDate;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

@SpringBootTest
class FilmTest {

    private static final Validator validator;

    static {
        ValidatorFactory validatorFactory = Validation.buildDefaultValidatorFactory();
        validator = validatorFactory.usingContext().getValidator();
    }

    @Test
    void checktNameBlank() {
        Film film = new Film(2, "", "1", LocalDate.now(), 44);
        Set<ConstraintViolation<Film>> violations = validator.validate(film);
        assertFalse(violations.isEmpty(), "Нельзя использовать фильv без названия");
    }

    @Test
    void checktNameNotBlank() {
        Film film = new Film(2, "g", "1", LocalDate.now(), 44);
        Set<ConstraintViolation<Film>> violations = validator.validate(film);
        assertTrue(violations.isEmpty(), "ошибка создания фильма с названием");
    }

    @Test
    void checktDescription201() {
        Film film = new Film(2, "test", "1".repeat(201), LocalDate.now(), 44);
        Set<ConstraintViolation<Film>> violations = validator.validate(film);
        assertFalse(violations.isEmpty(), "Нельзя создать описание больше 200 символов");
    }

    @Test
    void checktDescription190() {
        Film film = new Film(2, "test", "1".repeat(190), LocalDate.now(), 44);
        Set<ConstraintViolation<Film>> violations = validator.validate(film);
        assertTrue(violations.isEmpty(), "ошибка создания описание 190 символов");
    }

    @Test
    void checktDescription200() {
        Film film = new Film(2, "test", "1".repeat(200), LocalDate.now(), 44);
        Set<ConstraintViolation<Film>> violations = validator.validate(film);
        assertTrue(violations.isEmpty(), "Нельзя создать описание 200 символов");
    }

    @Test
    void checktReleaseDateFail() {
        Film film = new Film(2, "test", "test", LocalDate.of(1890, 12, 28), 44);
        Set<ConstraintViolation<Film>> violations = validator.validate(film);
        assertFalse(violations.isEmpty(), "Нельзя создать фильм раньше изобретения");
    }

    @Test
    void checktDurationNegative() {
        Film film = new Film(2, "test", "test", LocalDate.now(), -44);
        Set<ConstraintViolation<Film>> violations = validator.validate(film);
        assertFalse(violations.isEmpty(), "Не должно быть отрицательной длительности.");
    }

    @Test
    void checktDurationZero() {
        Film film = new Film(2, "test", "test", LocalDate.now(), 0);
        Set<ConstraintViolation<Film>> violations = validator.validate(film);
        assertFalse(violations.isEmpty(), "Ошибка создания фильма с 0 длительностью");
    }

    @Test
    void checktDurationPositive() {
        Film film = new Film(2, "test", "test", LocalDate.now(), 1);
        Set<ConstraintViolation<Film>> violations = validator.validate(film);
        assertTrue(violations.isEmpty(), "Ошибка создания фильма с положительной длительностью");
    }
}