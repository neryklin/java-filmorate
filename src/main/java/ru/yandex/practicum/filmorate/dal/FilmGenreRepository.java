package ru.yandex.practicum.filmorate.dal;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;
import ru.yandex.practicum.filmorate.model.Film;
import ru.yandex.practicum.filmorate.model.Genre;

import java.util.*;

@Repository
@ComponentScan
public class FilmGenreRepository {
    //  private final JdbcTemplate jdbc2;
    private static final String FIND_ALL_QUERY = "SELECT FILM_ID, genre.id ,genre.name FROM FILMGENRE " +
            "JOIN genre ON GENRE_ID =GENRE.ID ";
    private static final String FIND_BY_ID_QUERY = """
            SELECT GENRE.ID,
            GENRE.NAME
            FROM (
            SELECT * FROM filmgenre WHERE film_id = :film_id
            )
            JOIN genre ON GENRE_ID =GENRE.ID
            ORDER BY GENRE.ID""";

    private static final String INSERT_QUERY = "INSERT INTO filmgenre(film_id, genre_id)" +
            " VALUES (:film_id, :genre_id)";
    private static final String DELETE_QUERY = "INSERT INTO filmgenre(film_id, genre_id)" +
            " VALUES (?, ?)";
    private final NamedParameterJdbcTemplate jdbc;

    public FilmGenreRepository(NamedParameterJdbcTemplate jdbc) {
        this.jdbc = jdbc;

    }

    public Optional<List<HashSet<Genre>>> getGenreByFilmId(Long filmid) {
        MapSqlParameterSource map = new MapSqlParameterSource();
        map.addValue("film_id", filmid);
        HashSetGenreRowMapper hashSetGenreRowMapper = new HashSetGenreRowMapper();
        return Optional.ofNullable(jdbc.query(FIND_BY_ID_QUERY, map, hashSetGenreRowMapper));
    }

    public Long save(Film film) {

        Map<String, Object>[] batchOfInputs = new HashMap[film.getGenres().size()];
        int count = 0;
        for (Genre genre : film.getGenres()) {
            Map<String, Object> map = new HashMap();
            map.put("film_id", film.getId());
            map.put("genre_id", genre.getId());
            batchOfInputs[count++] = map;

        }
        jdbc.batchUpdate(INSERT_QUERY, batchOfInputs);
        return film.getId();

    }


}
