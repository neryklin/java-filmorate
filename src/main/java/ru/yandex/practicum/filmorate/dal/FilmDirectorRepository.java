//package ru.yandex.practicum.filmorate.dal;
//
//import org.springframework.context.annotation.ComponentScan;
//import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
//import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
//import org.springframework.stereotype.Repository;
//import ru.yandex.practicum.filmorate.model.Director;
//import ru.yandex.practicum.filmorate.model.Film;
//import ru.yandex.practicum.filmorate.model.Genre;
//
//import java.util.*;
//
//@Repository
//@ComponentScan
//public class FilmDirectorRepository {
//    //  private final JdbcTemplate jdbc2;
//    private static final String FIND_ALL_QUERY = "SELECT FILM_ID, director.id ,director.name FROM FILMDIRECTOR " +
//            "JOIN director ON director_ID =director.ID ";
//    private static final String FIND_BY_ID_QUERY = """
//            SELECT director.ID,
//            director.NAME
//            FROM (
//            SELECT * FROM filmdirector WHERE film_id = :film_id
//            )
//            JOIN director ON director_ID =director.ID
//            ORDER BY director.ID""";
//
//    private static final String INSERT_QUERY = "INSERT INTO filmdirector(film_id, director_id)" +
//            " VALUES (:film_id, :director_id)";
//    private static final String DELETE_QUERY = "INSERT INTO filmdirector(film_id, director_id)" +
//            " VALUES (?, ?)";
//    private final NamedParameterJdbcTemplate jdbc;
//
//    public FilmDirectorRepository(NamedParameterJdbcTemplate jdbc) {
//        this.jdbc = jdbc;
//
//    }
//
//    public Optional<List<HashSet<Director>>> getDirectorByFilmId(Long filmid) {
//        MapSqlParameterSource map = new MapSqlParameterSource();
//        map.addValue("film_id", filmid);
//        HashSetDirectorRowMapper hashSetDirectorRowMapper = new HashSetDirectorRowMapper();
//        return Optional.ofNullable(jdbc.query(FIND_BY_ID_QUERY, map, hashSetDirectorRowMapper));
//    }
//
//    public Long save(Film film) {
//
//        Map<String, Object>[] batchOfInputs = new HashMap[film.getDirector().size()];
//        int count = 0;
//        for (Director director : film.getDirector()) {
//            Map<String, Object> map = new HashMap();
//            map.put("film_id", film.getId());
//            map.put("director_id", director.getId());
//            batchOfInputs[count++] = map;
//
//        }
//        jdbc.batchUpdate(INSERT_QUERY, batchOfInputs);
//        return film.getId();
//
//    }
//
//
//}
