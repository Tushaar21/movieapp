package sr.unasat.movies.services;

import sr.unasat.movies.BaseService;
import sr.unasat.movies.dao.RatingsDao;
import sr.unasat.movies.dto.ratingsDto;

import java.util.List;
import java.util.Optional;

public class RatingServiceImpl implements BaseService<ratingsDto, Integer> {

    private final RatingsDao ratingsDao;
    private static final int MIN_SCORE = 1;
    private static final int MAX_SCORE = 10;

    public RatingServiceImpl(RatingsDao ratingsDao) {
        this.ratingsDao = ratingsDao;
    }

    @Override
    public List<ratingsDto> findAll() {
        return ratingsDao.getAll();
    }

    public double getAverageRating(int movieId) {
        return ratingsDao.getAverageRating(movieId);
    }

    @Override
    public Optional<ratingsDto> findById(Integer id) {
        validateId(id);
        return Optional.ofNullable(ratingsDao.getById(id));
    }

    public ratingsDto findByUserIdAndMovieId(int userId, int movieId) {
        return ratingsDao.findByUserIdAndMovieId(userId, movieId);
    }

    @Override
    public ratingsDto save(ratingsDto rating) {
        validateRating(rating);
        ratingsDao.insert(rating);
        return rating;
    }

    @Override
    public ratingsDto update(ratingsDto rating) {
        validateRating(rating);
        validateId(rating.getId());
        ratingsDao.update(rating);
        return rating;
    }

    @Override
    public void deleteById(Integer id) {
        validateId(id);
        ratingsDao.delete(id);
    }

    private void validateRating(ratingsDto rating) {
        if (rating == null) {
            throw new IllegalArgumentException("Rating cannot be null");
        }
        if (rating.getScore() < MIN_SCORE || rating.getScore() > MAX_SCORE) {
            throw new IllegalArgumentException("Score must be between " + MIN_SCORE + " and " + MAX_SCORE);
        }
        if (rating.getUpvotes() < 0) {
            throw new IllegalArgumentException("Upvotes cannot be negative");
        }
        if (rating.getDownvotes() < 0) {
            throw new IllegalArgumentException("Downvotes cannot be negative");
        }
    }

    private void validateId(Integer id) {
        if (id == null || id <= 0) {
            throw new IllegalArgumentException("Invalid ID");
        }
    }
}
