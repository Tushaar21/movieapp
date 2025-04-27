package sr.unasat.movies.dao;

import jakarta.persistence.EntityManager;
import jakarta.persistence.Query;
import sr.unasat.movies.DaoInterface;
import sr.unasat.movies.config.JPAconfig;
import sr.unasat.movies.dto.moviesDto;
import sr.unasat.movies.entities.Movies;
import java.util.ArrayList;
import java.util.List;

public class MoviesDao implements DaoInterface<moviesDto, Integer> {
    private final EntityManager entityManager;
    private static final String SELECT_ALL = "SELECT m FROM Movies m";
    private static final String SELECT_BY_ID = "SELECT m FROM Movies m WHERE m.movie_id = :id";
    private static final String UPDATE_MOVIE = "UPDATE Movies m SET m.title = :title, m.description = :description, m.release_year = :releaseYear WHERE m.movie_id = :id";
    private static final String DELETE_MOVIE = "DELETE FROM Movies m WHERE m.movie_id = :id";

    public MoviesDao() {
        this.entityManager = JPAconfig.getEntityManger();
    }

    @Override
    public List<moviesDto> getAll() {
        List<moviesDto> moviesList = new ArrayList<>();
        try {
            entityManager.getTransaction().begin();
            List<Movies> movies = entityManager.createQuery(SELECT_ALL, Movies.class).getResultList();
            for (Movies movie : movies) {
                moviesDto movieDto = new moviesDto(
                    movie.getMovie_id(),
                    movie.getTitle(),
                    movie.getDescription(),
                    movie.getRelease_year()
                );
                moviesList.add(movieDto);
            }
            entityManager.getTransaction().commit();
        } catch (Exception e) {
            if (entityManager.getTransaction().isActive()) {
                entityManager.getTransaction().rollback();
            }
            e.printStackTrace();
        }
        return moviesList;
    }

    @Override
    public moviesDto getById(Integer id) {
        moviesDto movieDto = null;
        try {
            entityManager.getTransaction().begin();
            Query query = entityManager.createQuery(SELECT_BY_ID);
            query.setParameter("id", id);
            Movies movie = (Movies) query.getSingleResult();
            if (movie != null) {
                movieDto = new moviesDto(
                    movie.getMovie_id(),
                    movie.getTitle(),
                    movie.getDescription(),
                    movie.getRelease_year()
                );
            }
            entityManager.getTransaction().commit();
        } catch (Exception e) {
            if (entityManager.getTransaction().isActive()) {
                entityManager.getTransaction().rollback();
            }
            e.printStackTrace();
        }
        return movieDto;
    }

    @Override
    public void insert(moviesDto movieDto) {
        try {
            entityManager.getTransaction().begin();
            Movies movie = new Movies();
            movie.setTitle(movieDto.getTitle());
            movie.setDescription(movieDto.getDescription());
            movie.setRelease_year(movieDto.getRelease_year());
            entityManager.persist(movie);
            entityManager.getTransaction().commit();
        } catch (Exception e) {
            if (entityManager.getTransaction().isActive()) {
                entityManager.getTransaction().rollback();
            }
            e.printStackTrace();
        }
    }

    @Override
    public void update(moviesDto movieDto) {
        try {
            entityManager.getTransaction().begin();
            Query query = entityManager.createQuery(UPDATE_MOVIE);
            query.setParameter("title", movieDto.getTitle());
            query.setParameter("description", movieDto.getDescription());
            query.setParameter("releaseYear", movieDto.getRelease_year());
            query.setParameter("id", movieDto.getMovie_id());
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
            Query query = entityManager.createQuery(DELETE_MOVIE);
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