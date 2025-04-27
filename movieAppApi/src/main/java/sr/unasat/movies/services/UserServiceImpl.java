package sr.unasat.movies.services;


import sr.unasat.movies.BaseService;
import sr.unasat.movies.dao.UsersDao;
import sr.unasat.movies.dto.usersDto;

import java.util.List;
import java.util.Optional;
import java.util.regex.Pattern;

public class UserServiceImpl implements BaseService<usersDto, Integer> {
    private final UsersDao usersDao;
    private static final Pattern EMAIL_PATTERN = Pattern.compile("^[A-Z0-9._%+-]+@[A-Z0-9.-]+\\.[A-Z]{2,6}$", Pattern.CASE_INSENSITIVE);
    private static int MIN_PASSWORD_LENGTH = 8;

    public UserServiceImpl(UsersDao usersDao) {
        this.usersDao = usersDao;
    }

    @Override
    public List<usersDto> findAll() {
        return usersDao.getAll();
    }

    @Override
    public Optional<usersDto> findById(Integer id) {
        validateId(id);
        return Optional.ofNullable(usersDao.getById(id));
    }

    @Override
    public usersDto save(usersDto user) {
        validateUser(user);
        usersDao.insert(user);
        return user;
    }

    @Override
    public usersDto update(usersDto user) {
        validateUser(user);
        validateId(user.getId());
        usersDao.update(user);
        return user;
    }

    @Override
    public void deleteById(Integer id) {
        validateId(id);
        usersDao.delete(id);
    }

    private void validateUser(usersDto user) {
        if (user == null) {
            throw new IllegalArgumentException("User cannot be null");
        }
        if (user.getUsername() == null || user.getUsername().trim().isEmpty()) {
            throw new IllegalArgumentException("Username cannot be empty");
        }
        if (user.getEmail() == null || !EMAIL_PATTERN.matcher(user.getEmail()).matches()) {
            throw new IllegalArgumentException("Invalid email format");
        }
        if (user.getPassword() == null || user.getPassword().length() < MIN_PASSWORD_LENGTH) {
            throw new IllegalArgumentException("Password must be at least " + MIN_PASSWORD_LENGTH + " characters long");
        }
    }

    private void validateId(Integer id) {
        if (id == null || id <= 0) {
            throw new IllegalArgumentException("Invalid ID");
        }
    }

}
