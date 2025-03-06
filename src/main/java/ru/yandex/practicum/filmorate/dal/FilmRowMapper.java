package ru.yandex.practicum.filmorate.dal;


import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;
import ru.yandex.practicum.filmorate.model.Film;
import ru.yandex.practicum.filmorate.model.Genre;
import ru.yandex.practicum.filmorate.model.Mpa;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashSet;


@Component
public class FilmRowMapper implements RowMapper<Film> {
    @Override
    public Film mapRow(ResultSet resultSet, int rowNum) throws SQLException {
        Film film = new Film(resultSet.getLong("id"),
                resultSet.getString("name"),
                resultSet.getString("description"),
                resultSet.getDate("releasedate").toLocalDate(),
                resultSet.getInt("duration"),
                new Mpa(resultSet.getLong("mpa_id"), ""),
                new HashSet<Genre>()
        );
        return film;
    }
}
