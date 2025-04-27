package sr.unasat.movies.services;

import sr.unasat.movies.BaseService;
import sr.unasat.movies.dao.CommentsDao;
import sr.unasat.movies.dto.commentsDto;

import java.util.List;
import java.util.Optional;

public class CommentsServiceImpl implements BaseService<commentsDto, Integer> {
    private final CommentsDao commentsDao;

    public CommentsServiceImpl(CommentsDao commentsDao) {
        this.commentsDao = commentsDao;
    }

    @Override
    public List<commentsDto> findAll(){
        return commentsDao.getAll();
    }
    @Override
    public Optional<commentsDto> findById(Integer id){
        validateId(id);
        return Optional.ofNullable(commentsDao.getById(id));
    }

    public List<commentsDto> findByMovieId(Integer movieId) {
        if (movieId == null || movieId < 1) {
            throw new IllegalArgumentException("Invalid movie ID");
        }
        return commentsDao.getByMovieId(movieId);
    }


    @Override
    public commentsDto save(commentsDto comment){
        validateComment(comment);
        commentsDao.insert(comment);
        return comment;
    }

    @Override
    public commentsDto update(commentsDto comment){
        validateComment(comment);
        validateId(comment.getComment_id());
        commentsDao.update(comment);
        return comment;
    }

    @Override
    public void deleteById(Integer id){
        validateId(id);
        commentsDao.delete(id);
    }

    private void validateComment(commentsDto comment){
        if (comment == null){
            throw new IllegalArgumentException("Comment cannot be null");
        }
        if (comment.getContent() == null || comment.getContent().trim().isEmpty()){
            throw new IllegalArgumentException("Comment cannot be empty");
        }
    }

    private void validateId(Integer id){
        if (id == null || id < 1){
            throw new IllegalArgumentException("Invalid id");
        }
    }
}
