package ru.yandex.practicum.filmorate.dal;

import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcOperations;
import org.springframework.stereotype.Repository;
import ru.yandex.practicum.filmorate.model.Film;
import ru.yandex.practicum.filmorate.model.User;


@Repository
public class LikeRepository {
    private static final String INSERT_QUERY = "INSERT INTO likes (film_id, users_id)" +
            " VALUES (:film_id, :users_id)";
    private static final String DELETE_QUERY = "DELETE FROM likes where (film_id=:film_id and users_id=:users_id)";
    //  protected final RowMapper<Map<Long,List<Genre>>> mapper;
    private final NamedParameterJdbcOperations jdbc;

    public LikeRepository(NamedParameterJdbcOperations jdbc) {
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
}
