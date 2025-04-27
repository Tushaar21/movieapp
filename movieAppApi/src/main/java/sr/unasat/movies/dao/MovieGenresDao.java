package sr.unasat.movies.dao;

import jakarta.persistence.EntityManager;
import jakarta.persistence.Query;
import sr.unasat.movies.DaoInterface;
import sr.unasat.movies.config.JPAconfig;
import sr.unasat.movies.dto.moviegenresDto;
import sr.unasat.movies.entities.Genres;
import sr.unasat.movies.entities.MovieDetails;
import sr.unasat.movies.entities.MovieGenresId;
import sr.unasat.movies.entities.Moviegenres;
import java.util.ArrayList;
import java.util.List;

public class MovieGenresDao implements DaoInterface<moviegenresDto, MovieGenresId> {
    private final EntityManager entityManager;
    private static final String SELECT_ALL = "SELECT mg FROM Moviegenres mg";
    private static final String SELECT_BY_ID = "SELECT mg FROM Moviegenres mg WHERE mg.id.detail_id = :detailId AND mg.id.genre_id = :genreId";
    private static final String UPDATE_MOVIE_GENRE = "UPDATE Moviegenres mg SET mg.movieDetails = :movieDetails, mg.genre = :genre WHERE mg.id.detail_id = :detailId AND mg.id.genre_id = :genreId";
    private static final String DELETE_MOVIE_GENRE = "DELETE FROM Moviegenres mg WHERE mg.id.detail_id = :detailId AND mg.id.genre_id = :genreId";

    public MovieGenresDao() {
        this.entityManager = JPAconfig.getEntityManger();
    }

    @Override
    public List<moviegenresDto> getAll() {
        List<moviegenresDto> movieGenresList = new ArrayList<>();
        try {
            entityManager.getTransaction().begin();
            List<Moviegenres> moviegenres = entityManager.createQuery(SELECT_ALL, Moviegenres.class).getResultList();
            for (Moviegenres mg : moviegenres) {
                moviegenresDto dto = new moviegenresDto(
                    mg.getId().getDetail_id(),
                    mg.getId().getGenre_id()
                );
                movieGenresList.add(dto);
            }
            entityManager.getTransaction().commit();
        } catch (Exception e) {
            if (entityManager.getTransaction().isActive()) {
                entityManager.getTransaction().rollback();
            }
            e.printStackTrace();
        }
        return movieGenresList;
    }

    @Override
    public moviegenresDto getById(MovieGenresId id) {
        moviegenresDto dto = null;
        try {
            entityManager.getTransaction().begin();
            Query query = entityManager.createQuery(SELECT_BY_ID);
            query.setParameter("detailId", id.getDetail_id());
            query.setParameter("genreId", id.getGenre_id());
            Moviegenres mg = (Moviegenres) query.getSingleResult();
            if (mg != null) {
                dto = new moviegenresDto(
                    mg.getId().getDetail_id(),
                    mg.getId().getGenre_id()
                );
            }
            entityManager.getTransaction().commit();
        } catch (Exception e) {
            if (entityManager.getTransaction().isActive()) {
                entityManager.getTransaction().rollback();
            }
            e.printStackTrace();
        }
        return dto;
    }

    @Override
    public void insert(moviegenresDto dto) {
        try {
            entityManager.getTransaction().begin();
            MovieGenresId id = new MovieGenresId(dto.getDetail_id(), dto.getGenre_id());
            Moviegenres mg = new Moviegenres();

            MovieDetails movieDetails = entityManager.find(MovieDetails.class, dto.getDetail_id());
            Genres genre = entityManager.find(Genres.class, dto.getGenre_id());

            mg.setId(id);
            mg.setMovieDetails(movieDetails);
            mg.setGenre(genre);

            entityManager.persist(mg);
            entityManager.getTransaction().commit();
        } catch (Exception e) {
            if (entityManager.getTransaction().isActive()) {
                entityManager.getTransaction().rollback();
            }
            e.printStackTrace();
        }
    }

    @Override
    public void update(moviegenresDto dto) {
        try {
            entityManager.getTransaction().begin();
            Query query = entityManager.createQuery(UPDATE_MOVIE_GENRE);
            query.setParameter("detailId", dto.getDetail_id());
            query.setParameter("genreId", dto.getGenre_id());
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
    public void delete(MovieGenresId id) {
        try {
            entityManager.getTransaction().begin();
            Query query = entityManager.createQuery(DELETE_MOVIE_GENRE);
            query.setParameter("detailId", id.getDetail_id());
            query.setParameter("genreId", id.getGenre_id());
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