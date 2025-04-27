package sr.unasat.movies.dao;

import jakarta.persistence.EntityManager;
import jakarta.persistence.Query;
import sr.unasat.movies.DaoInterface;
import sr.unasat.movies.config.JPAconfig;
import sr.unasat.movies.dto.movieDetailsDto;
import sr.unasat.movies.entities.MovieDetails;
import sr.unasat.movies.entities.Movies;
import java.util.ArrayList;
import java.util.List;

public class MovieDetailsDao implements DaoInterface<movieDetailsDto, Integer> {
    private final EntityManager entityManager;
    private static final String SELECT_ALL = "SELECT md FROM MovieDetails md";
    private static final String SELECT_BY_ID = "SELECT md FROM MovieDetails md WHERE md.detail_id = :id";
    private static final String UPDATE_MOVIE_DETAIL = "UPDATE MovieDetails md SET md.budget = :budget, md.box_office = :boxOffice, md.duration_minutes = :duration WHERE md.detail_id = :id";
    private static final String DELETE_MOVIE_DETAIL = "DELETE FROM MovieDetails md WHERE md.detail_id = :id";

    public MovieDetailsDao() {
        this.entityManager = JPAconfig.getEntityManger();
    }

    @Override
    public List<movieDetailsDto> getAll() {
        List<movieDetailsDto> movieDetailsList = new ArrayList<>();
        try {
            entityManager.getTransaction().begin();
            List<MovieDetails> details = entityManager.createQuery(SELECT_ALL, MovieDetails.class).getResultList();
            for (MovieDetails detail : details) {
                movieDetailsDto detailDto = new movieDetailsDto(
                    detail.getDetail_id(),
                    String.valueOf(detail.getMovie().getMovie_id()),
                    String.valueOf(detail.getBudget()),
                    String.valueOf(detail.getBox_office()),
                    detail.getDuration_minutes()
                );
                movieDetailsList.add(detailDto);
            }
            entityManager.getTransaction().commit();
        } catch (Exception e) {
            if (entityManager.getTransaction().isActive()) {
                entityManager.getTransaction().rollback();
            }
            e.printStackTrace();
        }
        return movieDetailsList;
    }

    @Override
    public movieDetailsDto getById(Integer id) {
        movieDetailsDto detailDto = null;
        try {
            entityManager.getTransaction().begin();
            Query query = entityManager.createQuery(SELECT_BY_ID);
            query.setParameter("id", id);
            MovieDetails detail = (MovieDetails) query.getSingleResult();
            if (detail != null) {
                detailDto = new movieDetailsDto(
                    detail.getDetail_id(),
                    String.valueOf(detail.getMovie().getMovie_id()),
                    String.valueOf(detail.getBudget()),
                    String.valueOf(detail.getBox_office()),
                    detail.getDuration_minutes()
                );
            }
            entityManager.getTransaction().commit();
        } catch (Exception e) {
            if (entityManager.getTransaction().isActive()) {
                entityManager.getTransaction().rollback();
            }
            e.printStackTrace();
        }
        return detailDto;
    }

    @Override
    public void insert(movieDetailsDto detailDto) {
        try {
            entityManager.getTransaction().begin();
            MovieDetails detail = new MovieDetails();
            Movies movie = entityManager.find(Movies.class, Integer.parseInt(detailDto.getMovie_id()));

            detail.setMovie(movie);
            detail.setBudget(Integer.parseInt(detailDto.getBudget()));
            detail.setBox_office(Integer.parseInt(detailDto.getBox_office()));
            detail.setDuration_minutes(detailDto.getDuration_minutes());

            entityManager.persist(detail);
            entityManager.getTransaction().commit();
        } catch (Exception e) {
            if (entityManager.getTransaction().isActive()) {
                entityManager.getTransaction().rollback();
            }
            e.printStackTrace();
        }
    }

    @Override
    public void update(movieDetailsDto detailDto) {
        try {
            entityManager.getTransaction().begin();
            Query query = entityManager.createQuery(UPDATE_MOVIE_DETAIL);
            query.setParameter("budget", Integer.parseInt(detailDto.getBudget()));
            query.setParameter("boxOffice", Integer.parseInt(detailDto.getBox_office()));
            query.setParameter("duration", detailDto.getDuration_minutes());
            query.setParameter("id", detailDto.getDetail_id());
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
            Query query = entityManager.createQuery(DELETE_MOVIE_DETAIL);
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