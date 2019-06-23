package data.interfaces;

import java.util.List;

public interface Repository<E> {

    public E get(long id);
    public List<E> getAll();
    public boolean add(E entity);
    public boolean delete(E entity);
    public boolean update(E entity);

}
