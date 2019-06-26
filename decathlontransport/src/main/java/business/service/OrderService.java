package business.service;

import data.interfaces.IOrderRepository;
import data.model.Order;
import data.repository.OrderRepository;

import java.util.List;

public class OrderService implements IOrderRepository<Order> {

    private OrderRepository repo;

    public OrderService(){
        repo = new OrderRepository();
    }

    public OrderService(String unitName){
        repo = new OrderRepository(unitName);
    }


    @Override
    public Order get(long id) {
        return repo.get(id);
    }

    @Override
    public List<Order> getAll(String name) {
        return repo.getAll(name);
    }

    @Override
    public List<Order> getAll() {
        return repo.getAll();
    }

    @Override
    public boolean add(Order entity) {
        return repo.add(entity);
    }

    @Override
    public boolean delete(Order entity) {
        return repo.delete(entity);
    }

    @Override
    public boolean update(Order entity) {
        return repo.update(entity);
    }

}
