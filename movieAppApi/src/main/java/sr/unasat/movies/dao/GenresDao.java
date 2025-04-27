package sr.unasat.movies.dao;

import jakarta.persistence.EntityManager;
import jakarta.persistence.Query;
import sr.unasat.movies.DaoInterface;
import sr.unasat.movies.config.JPAconfig;
import sr.unasat.movies.dto.genresDto;
import sr.unasat.movies.entities.Genres;
import java.util.ArrayList;
import java.util.List;

public class GenresDao implements DaoInterface<genresDto, Integer> {
    private final EntityManager entityManager;
    private static final String SELECT_ALL = "SELECT g FROM Genres g";
    private static final String SELECT_BY_ID = "SELECT g FROM Genres g WHERE g.genre_id = :id";
    private static final String UPDATE_GENRE = "UPDATE Genres g SET g.name = :name WHERE g.genre_id = :id";
    private static final String DELETE_GENRE = "DELETE FROM Genres g WHERE g.genre_id = :id";

    public GenresDao() {
        this.entityManager = JPAconfig.getEntityManger();
    }

    @Override
    public List<genresDto> getAll() {
        List<genresDto> genresList = new ArrayList<>();
        try {
            entityManager.getTransaction().begin();
            List<Genres> genres = entityManager.createQuery(SELECT_ALL, Genres.class).getResultList();
            for (Genres genre : genres) {
                genresDto genreDto = new genresDto(genre.getGenre_id(), genre.getName());
                genresList.add(genreDto);
            }
            entityManager.getTransaction().commit();
        } catch (Exception e) {
            if (entityManager.getTransaction().isActive()) {
                entityManager.getTransaction().rollback();
            }
            e.printStackTrace();
        }
        return genresList;
    }

    @Override
    public genresDto getById(Integer id) {
        genresDto genreDto = null;
        try {
            entityManager.getTransaction().begin();
            Query query = entityManager.createQuery(SELECT_BY_ID);
            query.setParameter("id", id);
            Genres genre = (Genres) query.getSingleResult();
            if (genre != null) {
                genreDto = new genresDto(genre.getGenre_id(), genre.getName());
            }
            entityManager.getTransaction().commit();
        } catch (Exception e) {
            if (entityManager.getTransaction().isActive()) {
                entityManager.getTransaction().rollback();
            }
            e.printStackTrace();
        }
        return genreDto;
    }

    @Override
    public void insert(genresDto genreDto) {
        try {
            entityManager.getTransaction().begin();
            Genres genre = new Genres();
            genre.setName(genreDto.getName());
            entityManager.persist(genre);
            entityManager.getTransaction().commit();
        } catch (Exception e) {
            if (entityManager.getTransaction().isActive()) {
                entityManager.getTransaction().rollback();
            }
            e.printStackTrace();
        }
    }

    @Override
    public void update(genresDto genreDto) {
        try {
            entityManager.getTransaction().begin();
            Query query = entityManager.createQuery(UPDATE_GENRE);
            query.setParameter("name", genreDto.getName());
            query.setParameter("id", genreDto.getGenre_id());
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
            Query query = entityManager.createQuery(DELETE_GENRE);
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