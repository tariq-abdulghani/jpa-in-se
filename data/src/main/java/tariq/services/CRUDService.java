package tariq.services;

import java.util.List;

public interface CRUDService<T> {

    T create(T e);
    List<T> getAll();
    T getById(String id);
    T getById(Long id);
    T update(T newE);
    T delete(T e);

}
