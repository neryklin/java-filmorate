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
class UserTest {

    private static final Validator validator;

    static {
        ValidatorFactory validatorFactory = Validation.buildDefaultValidatorFactory();
        validator = validatorFactory.usingContext().getValidator();
    }

    @Test
    void checktEmailBlank() {
        User user = new User(2, "", "login", "name", LocalDate.now().minusDays(1));
        Set<ConstraintViolation<User>> violations = validator.validate(user);
        assertFalse(violations.isEmpty(), "Нельзя создать пустую почту");
    }

    @Test
    void checktEmailNotBlank() {
        User user = new User(2, "emailemail.com", "login", "name", LocalDate.now().minusDays(1));
        Set<ConstraintViolation<User>> violations = validator.validate(user);
        assertFalse(violations.isEmpty(), "Нельзя создать почту не по шаблоку");
    }

    @Test
    void checktEmailValid() {
        User user = new User(2, "email@email.com", "login", "name", LocalDate.now().minusDays(1));
        Set<ConstraintViolation<User>> violations = validator.validate(user);
        assertTrue(violations.isEmpty(), "ошибка создания почты  по шаблоку");
    }

    @Test
    void checktLoginBlank() {
        User user = new User(2, "email@email.com", "", "name", LocalDate.now().minusDays(1));
        Set<ConstraintViolation<User>> violations = validator.validate(user);
        assertFalse(violations.isEmpty(), "Нельзя создать пустую почту");
    }

    @Test
    void checktLoginWhitespace() {
        User user = new User(2, "email@email.com", "log in", "name", LocalDate.now().minusDays(1));
        Set<ConstraintViolation<User>> violations = validator.validate(user);
        assertFalse(violations.isEmpty(), "Нельзя создать почту не по шаблоку");
    }

    @Test
    void checktLoginValid() {
        User user = new User(2, "email@email.com", "login", "name", LocalDate.now().minusDays(1));
        Set<ConstraintViolation<User>> violations = validator.validate(user);
        assertTrue(violations.isEmpty(), "ошибка создания почты  по шаблоку");
    }

    @Test
    void checktBirthdayValid() {
        User user = new User(2, "email@email.com", "login", "name", LocalDate.now().minusDays(1));
        Set<ConstraintViolation<User>> violations = validator.validate(user);
        assertTrue(violations.isEmpty(), "ошибка создания почты  по шаблоку");
    }

    @Test
    void checktBirthdayFuture() {
        User user = new User(2, "email@email.com", "login", "name", LocalDate.now().plusDays(1));
        Set<ConstraintViolation<User>> violations = validator.validate(user);
        assertFalse(violations.isEmpty(), "ошибка создания почты  по шаблоку");
    }
}