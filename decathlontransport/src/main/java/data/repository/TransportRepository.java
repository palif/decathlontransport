package data.repository;

import data.interfaces.Repository;
import data.model.Transport;
import data.util.JPAUtil;

import javax.persistence.EntityManager;
import java.util.List;

public class TransportRepository extends AbstractRepository<Transport> implements data.interfaces.TransportRepository<Transport> {

    public TransportRepository() {
        super(Transport.class);
    }

    public TransportRepository(String unitName) {
        super(unitName, Transport.class);
    }

    @Override
    public Transport get(long id) {
        return super.get(id);
    }

    @Override
    public boolean add(Transport entity) {
        return super.add(entity);
    }

    @Override
    public boolean delete(Transport entity) {
        return super.delete(entity);
    }

    @Override
    public boolean update(Transport entity) {
        if (entity == null) return false;
        Transport oldEntity = get(entity.getId());
        if (oldEntity == null) return false;
        if (isUpdateValid(oldEntity, entity) == false) return false;
        return super.update(entity);
    }

    @Override
    public List<Transport> getAll() {
        return super.getAll();
    }

    private boolean isUpdateValid(Transport oe, Transport ne) {
        boolean validTransport = oe.getId() == ne.getId()
                && oe.getDeliveryDate().getTime() == ne.getDeliveryDate().getTime()
                && oe.getIssueDate().getTime() == ne.getIssueDate().getTime();
        boolean validOrder = oe.getOrder().getId() == ne.getOrder().getId()
                && oe.getOrder().getPostalCode() == ne.getOrder().getPostalCode()
                && oe.getOrder().getFirstName().equals(ne.getOrder().getFirstName())
                && oe.getOrder().getLastName().equals(ne.getOrder().getLastName())
                && oe.getOrder().getAddress().equals(ne.getOrder().getAddress())
                && oe.getOrder().getCity().equals(ne.getOrder().getCity())
                && oe.getOrder().getTelephone().equals(ne.getOrder().getTelephone());
        if (validOrder && validTransport) return true;
        return false;
    }

}
