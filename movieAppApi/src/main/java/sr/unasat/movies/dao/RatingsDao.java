package sr.unasat.movies.dao;

import jakarta.persistence.EntityManager;
import jakarta.persistence.Query;
import sr.unasat.movies.DaoInterface;
import sr.unasat.movies.config.JPAconfig;
import sr.unasat.movies.dto.ratingsDto;
import sr.unasat.movies.entities.Movies;
import sr.unasat.movies.entities.Ratings;
import sr.unasat.movies.entities.Users;
import java.util.ArrayList;
import java.util.List;

public class RatingsDao implements DaoInterface<ratingsDto, Integer> {
    private final EntityManager entityManager;
    private static final String SELECT_ALL = "SELECT r FROM Ratings r";
    private static final String SELECT_BY_ID = "SELECT r FROM Ratings r WHERE r.id = :id";
    private static final String UPDATE_RATING = "UPDATE Ratings r SET r.score = :score, r.upvotes = :upvotes, r.downvotes = :downvotes WHERE r.id = :id";
    private static final String DELETE_RATING = "DELETE FROM Ratings r WHERE r.id = :id";

    public RatingsDao() {
        this.entityManager = JPAconfig.getEntityManger();
    }

    @Override
    public List<ratingsDto> getAll() {
        List<ratingsDto> ratingsList = new ArrayList<>();
        try {
            entityManager.getTransaction().begin();
            List<Ratings> ratings = entityManager.createQuery(SELECT_ALL, Ratings.class).getResultList();
            for (Ratings rating : ratings) {
                ratingsDto ratingDto = new ratingsDto(
                    rating.getId(),
                    rating.getUser_id().getId(),
                    rating.getMovie().getMovie_id(),
                    rating.getScore(),
                    rating.getUpvotes(),
                    rating.getDownvotes()
                );
                ratingsList.add(ratingDto);
            }
            entityManager.getTransaction().commit();
        } catch (Exception e) {
            if (entityManager.getTransaction().isActive()) {
                entityManager.getTransaction().rollback();
            }
            e.printStackTrace();
        }
        return ratingsList;
    }

    public double getAverageRating(int movieId) {
        double averageRating = 0.0;
        try {
            entityManager.getTransaction().begin();
            Query query = entityManager.createQuery("SELECT AVG(r.score) FROM Ratings r WHERE r.movie.movie_id = :movieId");
            query.setParameter("movieId", movieId);
            averageRating = (double) query.getSingleResult();
            entityManager.getTransaction().commit();
        } catch (Exception e) {
            if (entityManager.getTransaction().isActive()) {
                entityManager.getTransaction().rollback();
            }
            e.printStackTrace();
        }
        return averageRating;
    }

    @Override
    public ratingsDto getById(Integer id) {
        ratingsDto ratingDto = null;
        try {
            entityManager.getTransaction().begin();
            Query query = entityManager.createQuery(SELECT_BY_ID);
            query.setParameter("id", id);
            Ratings rating = (Ratings) query.getSingleResult();
            if (rating != null) {
                ratingDto = new ratingsDto(
                    rating.getId(),
                    rating.getUser_id().getId(),
                    rating.getMovie().getMovie_id(),
                    rating.getScore(),
                    rating.getUpvotes(),
                    rating.getDownvotes()
                );
            }
            entityManager.getTransaction().commit();
        } catch (Exception e) {
            if (entityManager.getTransaction().isActive()) {
                entityManager.getTransaction().rollback();
            }
            e.printStackTrace();
        }
        return ratingDto;
    }

    public ratingsDto findByUserIdAndMovieId(int userId, int movieId) {
        ratingsDto ratingDto = null;
        try {
            entityManager.getTransaction().begin();
            Query query = entityManager.createQuery("SELECT r FROM Ratings r WHERE r.user_id.id = :userId AND r.movie.movie_id = :movieId");
            query.setParameter("userId", userId);
            query.setParameter("movieId", movieId);
            Ratings rating = (Ratings) query.getSingleResult();
            if (rating != null) {
                ratingDto = new ratingsDto(
                        rating.getId(),
                        rating.getUser_id().getId(),
                        rating.getMovie().getMovie_id(),
                        rating.getScore(),
                        rating.getUpvotes(),
                        rating.getDownvotes()
                );
            }
            entityManager.getTransaction().commit();
        } catch (jakarta.persistence.NoResultException e) {
            entityManager.getTransaction().rollback();
            System.out.println("No rating found for userId: " + userId + " and movieId: " + movieId);
        } catch (Exception e) {
            if (entityManager.getTransaction().isActive()) {
                entityManager.getTransaction().rollback();
            }
            e.printStackTrace();
        }
        return ratingDto;
    }

    @Override
    public void insert(ratingsDto ratingDto) {
        try {
            entityManager.getTransaction().begin();
            Ratings rating = new Ratings();

            Users user = entityManager.find(Users.class, ratingDto.getUser_id());
            Movies movie = entityManager.find(Movies.class, ratingDto.getMovie_id());

            rating.setUser_id(user);
            rating.setMovie(movie);
            rating.setScore(ratingDto.getScore());
            rating.setUpvotes(ratingDto.getUpvotes());
            rating.setDownvotes(ratingDto.getDownvotes());

            entityManager.persist(rating);
            entityManager.getTransaction().commit();
        } catch (Exception e) {
            if (entityManager.getTransaction().isActive()) {
                entityManager.getTransaction().rollback();
            }
            e.printStackTrace();
        }
    }

    @Override
    public void update(ratingsDto ratingDto) {
        try {
            entityManager.getTransaction().begin();
            Query query = entityManager.createQuery(UPDATE_RATING);
            query.setParameter("score", ratingDto.getScore());
            query.setParameter("upvotes", ratingDto.getUpvotes());
            query.setParameter("downvotes", ratingDto.getDownvotes());
            query.setParameter("id", ratingDto.getId());
            query.executeUpdate();
            entityManager.getTransaction().commit();
        } catch (Exception e) {
            if (entityManager.getTransaction().isActive()) {
                entityManager.getTransaction().rollback();
            }
            e.printStackTrace();
        }
    }

    @Override
    public void delete(Integer id) {
        try {
            entityManager.getTransaction().begin();
            Query query = entityManager.createQuery(DELETE_RATING);
            query.setParameter("id", id);
            query.executeUpdate();
            entityManager.getTransaction().commit();
        } catch (Exception e) {
            if (entityManager.getTransaction().isActive()) {
                entityManager.getTransaction().rollback();
            }
            e.printStackTrace();
        }
    }
}