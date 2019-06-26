package data.interfaces;

import java.util.List;

public interface IRepository<E> {

    E get(long id);
    List<E> getAll();
    boolean add(E entity);
    boolean delete(E entity);
    boolean update(E entity);

}
