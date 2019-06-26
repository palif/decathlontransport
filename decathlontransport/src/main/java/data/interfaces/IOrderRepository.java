package data.interfaces;

import java.util.List;

public interface IOrderRepository<E> extends IRepository<E> {

    List<E> getAll(String name);
    List<E> getAll();

}
