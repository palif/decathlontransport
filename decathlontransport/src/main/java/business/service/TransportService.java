package business.service;

import data.interfaces.ITransportRepository;
import data.model.Transport;
import data.repository.TransportRepository;

import java.util.List;

public class TransportService implements ITransportRepository<Transport> {

    private TransportRepository repo;

    public TransportService(){
        repo = new TransportRepository();
    }

    public TransportService(String unitName){
        repo = new TransportRepository(unitName);
    }


    @Override
    public Transport get(long id) {
        return repo.get(id);
    }

    @Override
    public Transport getByOrderId(long id) { return repo.getByOrderId(id); }

    @Override
    public List<Transport> getAll() {
        return repo.getAll();
    }

    @Override
    public boolean add(Transport entity) {
        return repo.add(entity);
    }

    @Override
    public boolean delete(Transport entity) {
        return repo.delete(entity);
    }

    @Override
    public boolean update(Transport entity) {
        return repo.update(entity);
    }

}
