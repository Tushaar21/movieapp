package sr.unasat.movies.services;

import sr.unasat.movies.BaseService;
import sr.unasat.movies.dao.MovieDetailsDao;
import sr.unasat.movies.dto.movieDetailsDto;

import java.util.List;
import java.util.Optional;

public class MovieDetailsServiceImpl implements BaseService<movieDetailsDto, Integer> {
    private final MovieDetailsDao movieDetailsDao;

    public MovieDetailsServiceImpl(MovieDetailsDao movieDetailsDao) {
        this.movieDetailsDao = movieDetailsDao;
    }

    @Override
    public List<movieDetailsDto> findAll() {
        return movieDetailsDao.getAll();
    }

    @Override
    public Optional<movieDetailsDto> findById(Integer id) {
        validateId(id);
        return Optional.ofNullable(movieDetailsDao.getById(id));
    }

    @Override
    public movieDetailsDto save(movieDetailsDto movieDetails) {
        validateMovieDetails(movieDetails);
        movieDetailsDao.insert(movieDetails);
        return movieDetails;
    }

    @Override
    public movieDetailsDto update(movieDetailsDto movieDetails) {
        validateMovieDetails(movieDetails);
        validateId(movieDetails.getDetail_id());
        movieDetailsDao.update(movieDetails);
        return movieDetails;
    }

    @Override
    public void deleteById(Integer id) {
        validateId(id);
        movieDetailsDao.delete(id);
    }

    private void validateMovieDetails(movieDetailsDto movieDetails) {
        if (movieDetails == null) {
            throw new IllegalArgumentException("Movie details cannot be null");
        }
        if (movieDetails.getMovie_id() == null || movieDetails.getMovie_id().isEmpty()) {
            throw new IllegalArgumentException("Movie ID cannot be empty");
        }
        if (movieDetails.getBudget() == null || movieDetails.getBudget().isEmpty()) {
            throw new IllegalArgumentException("Budget cannot be empty");
        }
        try {
            int budget = Integer.parseInt(movieDetails.getBudget());
            if (budget < 0) {
                throw new IllegalArgumentException("Budget cannot be negative");
            }
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException("Invalid budget format");
        }

        if (movieDetails.getBox_office() == null || movieDetails.getBox_office().isEmpty()) {
            throw new IllegalArgumentException("Box office cannot be empty");
        }
        try {
            int boxOffice = Integer.parseInt(movieDetails.getBox_office());
            if (boxOffice < 0) {
                throw new IllegalArgumentException("Box office cannot be negative");
            }
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException("Invalid box office format");
        }

        if (movieDetails.getDuration_minutes() == null || movieDetails.getDuration_minutes().isEmpty()) {
            throw new IllegalArgumentException("Duration cannot be empty");
        }
        try {
            int duration = Integer.parseInt(movieDetails.getDuration_minutes());
            if (duration <= 0) {
                throw new IllegalArgumentException("Duration must be greater than 0 minutes");
            }
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException("Invalid duration format");
        }
    }

    private void validateId(Integer id) {
        if (id == null || id <= 0) {
            throw new IllegalArgumentException("Invalid ID");
        }
    }
}
