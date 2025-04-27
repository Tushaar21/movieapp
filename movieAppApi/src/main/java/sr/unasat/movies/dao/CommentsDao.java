package sr.unasat.movies.dao;

import jakarta.persistence.EntityManager;
import jakarta.persistence.Query;
import sr.unasat.movies.DaoInterface;
import sr.unasat.movies.config.JPAconfig;
import sr.unasat.movies.dto.commentsDto;
import sr.unasat.movies.entities.Comments;
import sr.unasat.movies.entities.Movies;
import sr.unasat.movies.entities.Users;
import java.util.ArrayList;
import java.util.List;

public class CommentsDao implements DaoInterface<commentsDto, Integer> {
    private final EntityManager entityManager;
    private static final String SELECT_ALL = "SELECT c FROM Comments c";
    private static final String SELECT_BY_ID = "SELECT c FROM Comments c WHERE c.comment_id = :id";
    private static final String UPDATE_COMMENT = "UPDATE Comments c SET c.content = :content WHERE c.comment_id = :id";
    private static final String DELETE_COMMENT = "DELETE FROM Comments c WHERE c.comment_id = :id";

    public CommentsDao() {
        this.entityManager = JPAconfig.getEntityManger();
    }

    @Override
    public List<commentsDto> getAll() {
        List<commentsDto> commentsList = new ArrayList<>();
        try {
            entityManager.getTransaction().begin();
            List<Comments> comments = entityManager.createQuery(SELECT_ALL, Comments.class).getResultList();
            for (Comments comment : comments) {
                commentsDto commentDto = new commentsDto(
                    comment.getComment_id(),
                    comment.getUser().getId(),
                    comment.getMovie().getMovie_id(),
                    comment.getContent()
                );
                commentsList.add(commentDto);
            }
            entityManager.getTransaction().commit();
        } catch (Exception e) {
            if (entityManager.getTransaction().isActive()) {
                entityManager.getTransaction().rollback();
            }
            e.printStackTrace();
        }
        return commentsList;
    }

    @Override
    public commentsDto getById(Integer id) {
        commentsDto commentDto = null;
        try {
            entityManager.getTransaction().begin();
            Query query = entityManager.createQuery(SELECT_BY_ID);
            query.setParameter("id", id);
            Comments comment = (Comments) query.getSingleResult();
            if (comment != null) {
                commentDto = new commentsDto(
                    comment.getComment_id(),
                    comment.getUser().getId(),
                    comment.getMovie().getMovie_id(),
                    comment.getContent()
                );
            }
            entityManager.getTransaction().commit();
        } catch (Exception e) {
            if (entityManager.getTransaction().isActive()) {
                entityManager.getTransaction().rollback();
            }
            e.printStackTrace();
        }
        return commentDto;
    }

    public List<commentsDto> getByMovieId(Integer movieId) {
        List<commentsDto> commentsList = new ArrayList<>();
        try {
            entityManager.getTransaction().begin();
            Query query = entityManager.createQuery("SELECT c FROM Comments c WHERE c.movie.movie_id = :id");
            query.setParameter("id", movieId);
            List<Comments> comments = query.getResultList();
            for (Comments comment : comments) {
                commentsDto commentDto = new commentsDto(
                    comment.getComment_id(),
                    comment.getUser().getId(),
                    comment.getMovie().getMovie_id(),
                    comment.getContent()
                );
                commentsList.add(commentDto);
            }
            entityManager.getTransaction().commit();
        } catch (Exception e) {
            if (entityManager.getTransaction().isActive()) {
                entityManager.getTransaction().rollback();
            }
            e.printStackTrace();
        }
        return commentsList;
    }

    @Override
    public void insert(commentsDto commentDto) {
        try {
            entityManager.getTransaction().begin();
            Comments comment = new Comments();
            Users user = entityManager.find(Users.class, commentDto.getUser_id());
            Movies movie = entityManager.find(Movies.class, commentDto.getMovie_id());

            comment.setUser(user);
            comment.setMovie(movie);
            comment.setContent(commentDto.getContent());

            entityManager.persist(comment);
            entityManager.getTransaction().commit();
        } catch (Exception e) {
            if (entityManager.getTransaction().isActive()) {
                entityManager.getTransaction().rollback();
            }
            e.printStackTrace();
        }
    }

    @Override
    public void update(commentsDto commentDto) {
        try {
            entityManager.getTransaction().begin();
            entityManager.getTransaction().begin();
            Query query = entityManager.createQuery(UPDATE_COMMENT);
            query.setParameter("content", commentDto.getContent());
            query.setParameter("id", commentDto.getComment_id());
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
            Query query = entityManager.createQuery(DELETE_COMMENT);
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