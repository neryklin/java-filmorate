//package ru.yandex.practicum.filmorate.dal;
//
//
//import org.springframework.jdbc.core.RowMapper;
//import org.springframework.stereotype.Component;
//import ru.yandex.practicum.filmorate.model.Director;
//import ru.yandex.practicum.filmorate.model.Genre;
//
//import java.sql.ResultSet;
//import java.sql.SQLException;
//import java.util.HashSet;
//
//
//@Component
//public class HashSetDirectorRowMapper implements RowMapper<HashSet<Director>> {
//    public HashSet<Director> hSD = new HashSet<>();
//
//    @Override
//    public HashSet<Director> mapRow(ResultSet resultSet, int rowNum) throws SQLException {
//        Director director = new Director(resultSet.getLong("id"),
//                resultSet.getString("name")
//        );
//        hSD.add(director);
//        return hSD;
//    }
//}
