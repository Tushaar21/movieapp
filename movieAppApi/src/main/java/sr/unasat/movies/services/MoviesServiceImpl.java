package sr.unasat.movies.services;

import sr.unasat.movies.BaseService;
import sr.unasat.movies.dao.MoviesDao;
import sr.unasat.movies.dto.moviesDto;

import java.time.Year;
import java.util.List;
import java.util.Optional;

public class MoviesServiceImpl implements BaseService<moviesDto, Integer> {
    private final MoviesDao moviesDao;

    public MoviesServiceImpl(MoviesDao moviesDao) {
        this.moviesDao = moviesDao;
    }

    @Override
    public List<moviesDto> findAll() {
        return moviesDao.getAll();
    }

    @Override
    public Optional<moviesDto> findById(Integer id) {
        validateId(id);
        return Optional.ofNullable(moviesDao.getById(id));
    }

    @Override
    public moviesDto save(moviesDto movie) {
        validateMovie(movie);
        moviesDao.insert(movie);
        return movie;
    }

    @Override
    public moviesDto update(moviesDto movie) {
        validateMovie(movie);
        validateId(movie.getMovie_id());
        moviesDao.update(movie);
        return movie;
    }

    @Override
    public void deleteById(Integer id) {
        validateId(id);
        moviesDao.delete(id);
    }

    private void validateMovie(moviesDto movie) {
        if (movie == null) {
            throw new IllegalArgumentException("Movie cannot be null");
        }
        if (movie.getTitle() == null || movie.getTitle().trim().isEmpty()) {
            throw new IllegalArgumentException("Movie title cannot be empty");
        }
        if (movie.getDescription() == null || movie.getDescription().trim().isEmpty()) {
            throw new IllegalArgumentException("Movie description cannot be empty");
        }
        if (movie.getRelease_year() < 1888 || movie.getRelease_year() > Year.now().getValue() + 5) {
            throw new IllegalArgumentException("Invalid release year");
        }
    }

    private void validateId(Integer id) {
        if (id == null || id <= 0) {
            throw new IllegalArgumentException("Invalid ID");
        }
    }
}
