package data.interfaces;

import data.model.Transport;

import java.util.List;

public interface TransportRepository<E> extends Repository<E> {
    List<E> getAll();
}
