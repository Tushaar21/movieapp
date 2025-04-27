package sr.unasat.movies.services;

import sr.unasat.movies.BaseService;
import sr.unasat.movies.dao.MovieGenresDao;
import sr.unasat.movies.dto.moviegenresDto;
import sr.unasat.movies.entities.MovieGenresId;

import java.util.List;
import java.util.Optional;

public class MovieGenresServiceImpl implements BaseService<moviegenresDto, MovieGenresId> {
    private final MovieGenresDao movieGenresDao;

    public MovieGenresServiceImpl(MovieGenresDao movieGenresDao) {
        this.movieGenresDao = movieGenresDao;
    }

    @Override
    public List<moviegenresDto> findAll() {
        return movieGenresDao.getAll();
    }

    @Override
    public Optional<moviegenresDto> findById(MovieGenresId id) {
        validateId(id);
        return Optional.ofNullable(movieGenresDao.getById(id));
    }

    @Override
    public moviegenresDto save(moviegenresDto movieGenre) {
        validateMovieGenre(movieGenre);
        movieGenresDao.insert(movieGenre);
        return movieGenre;
    }

    @Override
    public moviegenresDto update(moviegenresDto movieGenre) {
        validateMovieGenre(movieGenre);
        validateId(new MovieGenresId(movieGenre.getDetail_id(), movieGenre.getGenre_id()));
        movieGenresDao.update(movieGenre);
        return movieGenre;
    }

    @Override
    public void deleteById(MovieGenresId id) {
        validateId(id);
        movieGenresDao.delete(id);
    }

    private void validateMovieGenre(moviegenresDto movieGenre) {
        if (movieGenre == null) {
            throw new IllegalArgumentException("Movie genre cannot be null");
        }
        if (movieGenre.getDetail_id() < 1) {
            throw new IllegalArgumentException("Movie ID cannot be empty");
        }
        if (movieGenre.getGenre_id() < 1) {
            throw new IllegalArgumentException("Genre ID cannot be empty");
        }
    }

    private void validateId(MovieGenresId id) {
        if (id == null || id.getDetail_id() < 1 || id.getGenre_id() < 1) {
            throw new IllegalArgumentException("Invalid id");
        }
    }
}
