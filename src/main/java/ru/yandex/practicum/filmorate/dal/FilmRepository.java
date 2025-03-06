package ru.yandex.practicum.filmorate.dal;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;
import ru.yandex.practicum.filmorate.model.Film;
import ru.yandex.practicum.filmorate.model.Genre;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;

@Repository
public class FilmRepository extends BaseRepository<Film> {
    private static final String FIND_ALL_QUERY = "SELECT * FROM film";
    private static final String FIND_ALL_GENRE_QUERY = """
            SELECT * FROM film
            JOIN FILMGENRE ON film.ID =FILMGENRE.FILM_ID
            JOIN GENRE ON FILMGENRE.GENRE_ID =genre.ID""";
    private static final String FIND_BY_ID_QUERY = "SELECT * FROM film WHERE id = ?";
    private static final String INSERT_QUERY = "INSERT INTO film(name,description, releaseDate, duration, mpa_id)" +
            " VALUES (?, ?, ?, ?,?)";
    private static final String UPDATE_QUERY = "UPDATE film SET name = ?, description = ?, releaseDate = ?, duration = ?, mpa_id=?  WHERE id = ?";
    private final FilmGenreRepository filmGenreRepository;
    private final MpaRepository mpaRepository;

    public FilmRepository(JdbcTemplate jdbc, RowMapper<Film> mapper, FilmGenreRepository filmGenreRepository, MpaRepository mpaRepository) {
        super(jdbc, mapper);
        this.filmGenreRepository = filmGenreRepository;
        this.mpaRepository = mpaRepository;
    }

    public List<Film> findAll() {
        return findMany(FIND_ALL_QUERY);
    }


    public Optional<Film> findById(long filmId) {
        Optional<Film> optionalFilm = findOne(FIND_BY_ID_QUERY, filmId);
        //add ganre
        optionalFilm.get().setGenres(getGenreSet(filmId));
        //add mpa
        optionalFilm.get().setMpa(mpaRepository.findById(optionalFilm.get().getMpa().getId()).get());
        return optionalFilm;
    }

    public HashSet<Genre> getGenreSet(Long filmId) {
        HashSet<Genre> genreHashSet = new HashSet<>();
        List<HashSet<Genre>> hashSetList = filmGenreRepository.getGenreByFilmId(filmId).orElseThrow();
        if (hashSetList.size() > 0) {
            genreHashSet = hashSetList.get(0);
        }
        return genreHashSet;
    }

    public Film save(Film film) {
        Long id = insert(
                INSERT_QUERY,
                film.getName(),
                film.getDescription(),
                LocalDate.from(film.getReleaseDate()),
                film.getDuration(),
                film.getMpa().getId()
        );
        film.setId(id);
        return film;
    }


    public Film update(Film film) {
        update(
                UPDATE_QUERY,
                film.getName(),
                film.getDescription(),
                LocalDate.from(film.getReleaseDate()),
                film.getDuration(),
                film.getMpa().getId(),
                film.getId()
        );
        return film;
    }
}
