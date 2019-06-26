package data.interfaces;

import java.util.List;

public interface ITransportRepository<E> extends IRepository<E> {

    E getByOrderId(long id);

}
