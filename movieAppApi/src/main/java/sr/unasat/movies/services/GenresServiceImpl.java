package sr.unasat.movies.services;

import sr.unasat.movies.BaseService;
import sr.unasat.movies.dao.GenresDao;
import sr.unasat.movies.dto.genresDto;

import java.util.List;
import java.util.Optional;

public class GenresServiceImpl implements BaseService<genresDto, Integer> {
    private final GenresDao genresDao;

    public GenresServiceImpl(GenresDao genresDao) {
        this.genresDao = genresDao;
    }

    @Override
    public List<genresDto> findAll() {
        return genresDao.getAll();
    }

    @Override
    public Optional<genresDto> findById(Integer id) {
        validateId(id);
        return Optional.ofNullable(genresDao.getById(id));
    }

    @Override
    public genresDto save(genresDto genre) {
        validateGenre(genre);
        genresDao.insert(genre);
        return genre;
    }

    @Override
    public genresDto update(genresDto genre) {
        validateGenre(genre);
        validateId(genre.getGenre_id());
        genresDao.update(genre);
        return genre;
    }

    @Override
    public void deleteById(Integer id) {
        validateId(id);
        genresDao.delete(id);
    }

    private void validateGenre(genresDto genre) {
        if (genre == null) {
            throw new IllegalArgumentException("Genre cannot be null");
        }
        if (genre.getName() == null || genre.getName().trim().isEmpty()) {
            throw new IllegalArgumentException("Genre name cannot be empty");
        }
    }

    private void validateId(Integer id) {
        if (id == null || id < 1) {
            throw new IllegalArgumentException("Invalid id");
        }
    }
}
