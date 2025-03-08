package ru.yandex.practicum.filmorate.dal;

import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcOperations;
import org.springframework.stereotype.Repository;
import ru.yandex.practicum.filmorate.model.Film;
import ru.yandex.practicum.filmorate.model.User;

import java.util.List;


@Repository
public class LikeRepository {
    private static final String INSERT_QUERY = "INSERT INTO likes (film_id, users_id)" +
            " VALUES (:film_id, :users_id)";
    private static final String DELETE_QUERY = "DELETE FROM likes where (film_id=:film_id and users_id=:users_id)";
    private static final String TOP_QUERY = """
            SELECT *
            FROM (SELECT *
            FROM (SELECT FILM_ID,
            count(FILM_ID) AS top
            FROM LIKES
            GROUP BY FILM_ID
            ORDER BY TOP DESC
            LIMIT :toplimit) AS lk
            JOIN FILM ON lk.FILM_ID =film.ID)AS topfilm
            join FILMGENRE ON topfilm.id=FILMGENRE.FILM_ID
            JOIN genre ON FILMGENRE.GENRE_ID = genre.ID
            """;
    protected final RowMapper<Film> mapper;
    private final NamedParameterJdbcOperations jdbc;

    public LikeRepository(RowMapper<Film> mapper, NamedParameterJdbcOperations jdbc) {
        this.mapper = mapper;
        this.jdbc = jdbc;
    }

    public void addLike(Film film, User user) {
        MapSqlParameterSource map = new MapSqlParameterSource();
        map.addValue("film_id", film.getId());
        map.addValue("users_id", user.getId());
        jdbc.update(INSERT_QUERY, map);
    }

    public void deleteLike(Film film, User user) {
        MapSqlParameterSource map = new MapSqlParameterSource();
        map.addValue("film_id", film.getId());
        map.addValue("users_id", user.getId());
        jdbc.update(DELETE_QUERY, map);
    }

    public List<Film> getTopFilm(int count) {
        MapSqlParameterSource map = new MapSqlParameterSource();
        map.addValue("toplimit", count);
        return jdbc.query(TOP_QUERY, map, mapper);
    }
}
