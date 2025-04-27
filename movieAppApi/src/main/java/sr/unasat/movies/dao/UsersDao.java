package sr.unasat.movies.dao;

import jakarta.persistence.EntityManager;
import jakarta.persistence.Query;
import sr.unasat.movies.DaoInterface;
import sr.unasat.movies.config.JPAconfig;
import sr.unasat.movies.dto.usersDto;
import sr.unasat.movies.entities.Users;
import java.util.ArrayList;
import java.util.List;

public class UsersDao implements DaoInterface<usersDto, Integer> {
    private final EntityManager entityManager;
    private static final String SELECT_ALL = "SELECT u FROM Users u";
    private static final String SELECT_BY_ID = "SELECT u FROM Users u WHERE u.id = :id";
    private static final String UPDATE_USER = "UPDATE Users u SET u.username = :username, u.email = :email, u.password = :password WHERE u.id = :id";
    private static final String DELETE_USER = "DELETE FROM Users u WHERE u.id = :id";

    public UsersDao() {
        this.entityManager = JPAconfig.getEntityManger();
    }

    @Override
    public List<usersDto> getAll() {
        List<usersDto> usersList = new ArrayList<>();
        try {
            entityManager.getTransaction().begin();
            List<Users> users = entityManager.createQuery(SELECT_ALL, Users.class).getResultList();
            for (Users user : users) {
                usersList.add(new usersDto(user.getId(), user.getUsername(), user.getEmail(), user.getPassword()));
            }
            entityManager.getTransaction().commit();
        } catch (Exception e) {
            if (entityManager.getTransaction().isActive()) {
                entityManager.getTransaction().rollback();
            }
            e.printStackTrace();
        }
        return usersList;
    }

    @Override
    public usersDto getById(Integer id) {
        usersDto userDto = null;
        try {
            entityManager.getTransaction().begin();
            Query query = entityManager.createQuery(SELECT_BY_ID);
            query.setParameter("id", id);
            Users user = (Users) query.getSingleResult();
            if (user != null) {
                userDto = new usersDto(user.getId(), user.getUsername(), user.getEmail(), user.getPassword());
            }
            entityManager.getTransaction().commit();
        } catch (Exception e) {
            if (entityManager.getTransaction().isActive()) {
                entityManager.getTransaction().rollback();
            }
            e.printStackTrace();
        }
        return userDto;
    }

    @Override
    public void insert(usersDto userDto) {
        try {
            entityManager.getTransaction().begin();
            Users user = new Users(userDto.getId(), userDto.getUsername(), userDto.getEmail(), userDto.getPassword());
            entityManager.persist(user);
            entityManager.getTransaction().commit();
        } catch (Exception e) {
            if (entityManager.getTransaction().isActive()) {
                entityManager.getTransaction().rollback();
            }
            e.printStackTrace();
        }
    }

    @Override
    public void update(usersDto userDto) {
        try {
            entityManager.getTransaction().begin();
            Query query = entityManager.createQuery(UPDATE_USER);
            query.setParameter("username", userDto.getUsername());
            query.setParameter("email", userDto.getEmail());
            query.setParameter("password", userDto.getPassword());
            query.setParameter("id", userDto.getId());
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
            Query query = entityManager.createQuery(DELETE_USER);
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