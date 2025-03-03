package ru.yandex.practicum.filmorate.dal;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcOperations;
import org.springframework.stereotype.Repository;
import ru.yandex.practicum.filmorate.model.Film;

@Repository
public class FilmGenreRepository {
    //  private final JdbcTemplate jdbc2;
    private static final String FIND_ALL_QUERY = "SELECT FILM_ID, genre.id ,genre.name FROM FILMGENRE " +
            "JOIN genre ON GENRE_ID =GENRE.ID ";
    private static final String FIND_BY_ID_QUERY = "SELECT * FROM filmgenre WHERE film_id = ?";
    private static final String INSERT_QUERY = "INSERT INTO filmgenre(film_id, genre_id)" +
            " VALUES (:film_id, :genre_id)";
    private static final String DELETE_QUERY = "INSERT INTO filmgenre(film_id, genre_id)" +
            " VALUES (?, ?)";
    private final NamedParameterJdbcOperations jdbc;

    public FilmGenreRepository(NamedParameterJdbcOperations jdbc, JdbcTemplate jdbc2) {
        this.jdbc = jdbc;

    }


    public Long save(Film film) {

//        //SqlParameterSource[] batch = SqlParameterSourceUtils.createBatch(film.getGenres());
////
//////        MapSqlParameterSource map = new MapSqlParameterSource();
////
//////        Map<String,Object> batchValues = new HashMap<>();
//        //List<Object[]> bk = new ArrayList<Object[]>();
//      List<Object[]> batch = new ArrayList<Object[]>();
//        for (Genre genre : film.getGenres()) {
////            Object[] values = new Object[]{film.getId(),genre.getId()};
//          Object[] values = new Object[] {film.getId(),genre.getId()};
////                  actor.getFirstName(), actor.getLastName(), actor.getId()};
//            batch.add(values);
//        }
////
////
//        jdbc.batchUpdate(INSERT_QUERY,batch);
////        //jdbc.update(INSERT_QUERY,map);
////
//////        MapSqlParameterSource map2 = new MapSqlParameterSource();
//////        map.addValue("film_id",film.getId());
//////        map.addValue("genre_id",3);
//////        jdbc.update(INSERT_QUERY,map);
////
        return film.getId();
    }


}
