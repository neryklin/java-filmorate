package ru.yandex.practicum.filmorate;

import lombok.RequiredArgsConstructor;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.jdbc.JdbcTest;
import org.springframework.context.annotation.Import;
import ru.yandex.practicum.filmorate.dal.FilmRepository;
import ru.yandex.practicum.filmorate.dal.UserRepository;
import ru.yandex.practicum.filmorate.model.Film;
import ru.yandex.practicum.filmorate.model.User;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;


@JdbcTest
@AutoConfigureTestDatabase
@RequiredArgsConstructor(onConstructor_ = @Autowired)
@Import({UserRepository.class, GenereteTestDate.class})
class FilmoRateApplicationTests {
    private final UserRepository userRepository;
    private final FilmRepository filmRepository;
    private final GenereteTestDate genereteTestDate;


    //user test
    @Test
    public void testFindUserById() {
        Optional<User> testUser = Optional.ofNullable(genereteTestDate.getTestUser());
        Optional<User> userOptional = userRepository.findById(1L);
        assertThat(userOptional)
                .isPresent()
                .usingRecursiveComparison()
                .isEqualTo(testUser);
    }

    @Test
    public void testfindByEmail() {
        Optional<User> testUser = Optional.ofNullable(genereteTestDate.getTestUser());
        Optional<User> userOptional = userRepository.findByEmail("email@mail.ru");
        assertThat(userOptional)
                .isPresent()
                .usingRecursiveComparison()
                .isEqualTo(testUser);
    }

    @Test
    public void testUpdateUser() {
        Optional<User> testUser = Optional.ofNullable(genereteTestDate.getTestUser());
        testUser.get().setName("NEW NAME");
        userRepository.update(testUser.get());
        Optional<User> userOptional = userRepository.findById(testUser.get().getId());
        assertThat(userOptional)
                .isPresent()
                .usingRecursiveComparison()
                .isEqualTo(testUser);
    }

    //film test
    @Test
    public void testFindFilmById() {
        Optional<Film> testFilm = Optional.ofNullable(genereteTestDate.getTestFilm());
        Optional<Film> filmOptional = filmRepository.findById(1L);
        assertThat(filmOptional)
                .isPresent()
                .usingRecursiveComparison()
                .isEqualTo(testFilm);
    }


    @Test
    public void testUpdateFilm() {
        Optional<Film> testFilm = Optional.ofNullable(genereteTestDate.getTestFilm());
        testFilm.get().setName("NEW NAME");
        filmRepository.update(testFilm.get());
        Optional<Film> userOptional = filmRepository.findById(testFilm.get().getId());
        assertThat(userOptional)
                .isPresent()
                .usingRecursiveComparison()
                .isEqualTo(testFilm);
    }


}