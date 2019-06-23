package data.interfaces;

import java.util.List;

public interface OrderRepository<E> extends Repository<E> {

    List<E> getAll(String name);
    List<E> getAll();

}
