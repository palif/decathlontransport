package data.repository;
import data.interfaces.IOrderRepository;
import data.model.Order;

import javax.persistence.EntityManager;
import java.util.List;

public class OrderRepository extends AbstractRepository<Order> implements IOrderRepository<Order> {

    public OrderRepository() {
        super(Order.class);
    }

    public OrderRepository(String unitName){
        super(unitName, Order.class);
    }

    @Override
    public List<Order> getAll() {
        EntityManager em = jpaUtil.getEntityManagerFactory(persistenceUnitName).createEntityManager();
        try {

            List<Order> result = em.createQuery("SELECT o FROM " + Order.class.getName() + " o").getResultList();
            em.close();
            return result;

        }catch (Exception e){
            em.close();
        }
        return null;
    }

    @Override
    public List<Order> getAll(String name) {

        EntityManager em = jpaUtil.getEntityManagerFactory(persistenceUnitName).createEntityManager();
        try {
            List<Order> result = em.createQuery("SELECT dbo FROM Order dbo WHERE dbo.firstName LIKE '%" + name + "%' OR " +
                    "dbo.lastName LIKE '%" + name + "%'").getResultList();
            em.close();

            if (result.isEmpty()) return null;
            return result;

        }catch (Exception e){
            em.close();
        }
        return null;
    }

    @Override
    public Order get(long id) {

        EntityManager em = jpaUtil.getEntityManagerFactory(persistenceUnitName).createEntityManager();

        try {
            Order result = em.find(Order.class, id);
            em.close();
            return result;
        } catch (Exception e){
            System.out.println("Exception while trying to get entities -> " + e.getMessage());
            em.close();
        }
        return null;

       //return super.get(id);
    }

    @Override
    public boolean add(Order entity) {
        if (entity == null) return false;
        checkOrder(entity);
        return super.add(entity);
    }

    @Override
    public boolean delete(Order entity) {
        if (entity == null) return false;
        checkOrder(entity);
        return super.delete(entity);
    }

    @Override
    public boolean update(Order entity) {
        if(entity == null) return false;
        checkOrder(entity);
        return super.update(entity);
    }

    private boolean checkOrder(Order order) {

        if (String.valueOf(order.getPostalCode()).length() != 5) return false;
        if (order.getTelephone().length() > 5 && order.getTelephone().length() < 11) return false;
        if (order.getFirstName().length() < 1 && order.getLastName().length() < 1) return false;
        if (order.getAddress().length() < 2 && order.getCity().length() < 2) return false;

        return true;
    }
}
