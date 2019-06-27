package data.repository;

import data.interfaces.ITransportRepository;
import data.model.Transport;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import java.util.List;

public class TransportRepository extends AbstractRepository<Transport> implements ITransportRepository<Transport> {

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
        if(entity.getId() == 2 || entity.getId() == 3) return false;
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

    @Override
    public Transport getByOrderId(long id) {
        EntityManager em = null;

        try {
            em = jpaUtil.getEntityManagerFactory(persistenceUnitName).createEntityManager();
            Transport result = (Transport) em.createQuery("SELECT t FROM Transport t WHERE t.order.id = " + id).getResultList().get(0);
            em.close();
            return result;
        }
        catch (NoResultException e){
            System.out.println("TransportRepo.getByOrderId No Result -> " + e.getMessage());
        }
        catch (Exception e){
            System.out.println("Exception while trying to get entities by order Id -> " + e.getMessage());
        }
        finally {
            if (em != null) em.close();
        }
        return null;
    }
}
