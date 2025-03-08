package ru.yandex.practicum.filmorate.dal;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;
import ru.yandex.practicum.filmorate.model.Film;
import ru.yandex.practicum.filmorate.model.Mpa;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Repository
public class MpaRepository extends BaseRepository<Mpa> {
    private static final String FIND_ALL_QUERY = "SELECT * FROM mpa";
    private static final String FIND_BY_ID_QUERY = "SELECT * FROM mpa WHERE id = ?";
    private static final String INSERT_QUERY = "INSERT INTO mpa(name)" +
            " VALUES (?)";

    public MpaRepository(JdbcTemplate jdbc, RowMapper<Mpa> mapper) {

        super(jdbc, mapper);
    }

    public List<Mpa> findAll() {

        return findMany(FIND_ALL_QUERY);
    }

    public Optional<Mpa> findById(long mpaId) {
        return findOne(FIND_BY_ID_QUERY, mpaId);
    }

//    public Film save(Film film) {
//        Long id = insert(
//                INSERT_QUERY,
//                film.getName(),
//                film.getDescription(),
//                LocalDate.from(film.getReleaseDate()),
//                film.getDuration(),
//                film.getMpa()
//        );
//        film.setId(id);
//        return film;
//    }


}
