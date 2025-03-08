package ru.yandex.practicum.filmorate.dal;


import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;
import ru.yandex.practicum.filmorate.model.Genre;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashSet;


@Component
public class HashSetGenreRowMapper implements RowMapper<HashSet<Genre>> {
    public HashSet<Genre> hSG = new HashSet<>();

    @Override
    public HashSet<Genre> mapRow(ResultSet resultSet, int rowNum) throws SQLException {
        Genre genre = new Genre(resultSet.getLong("id"),
                resultSet.getString("name")
        );
        hSG.add(genre);
        return hSG;
    }
}
