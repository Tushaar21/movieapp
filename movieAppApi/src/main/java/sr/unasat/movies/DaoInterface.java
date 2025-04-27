package sr.unasat.movies;

import java.util.List;

public interface DaoInterface<T, ID> {
    List<T> getAll();
    T getById(ID id);
    void insert(T dto);
    void update(T dto);
    void delete(ID id);
}
