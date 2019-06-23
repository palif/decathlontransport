package data;

import data.model.Order;
import data.repository.OrderRepository;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.List;

/**
 * This test works if there are two data
 */
public class OrderRepositoryTest {

    private OrderRepository repo;
    private Order addedOrder, testOrder, deleteOrder;

    @Before
    public void setUp(){

        repo = new OrderRepository("test-unit");

        addedOrder = new Order();
        addedOrder.setAddress("addedOrder addr");
        addedOrder.setFirstName("addedOrder");
        addedOrder.setLastName("addedOrder");
        addedOrder.setPostalCode(12323);
        addedOrder.setTelephone("0700000000");
        addedOrder.setCity("Stockholm");

        deleteOrder = new Order();
        deleteOrder.setAddress("deleteOrder addr");
        deleteOrder.setFirstName("deleteOrder");
        deleteOrder.setLastName("deleteOrder");
        deleteOrder.setPostalCode(12323);
        deleteOrder.setTelephone("0700000000");
        deleteOrder.setCity("Stockholm");

        testOrder = new Order();
        testOrder.setAddress("testOrder addr");
        testOrder.setFirstName("testOrder");
        testOrder.setLastName("testOrder");
        testOrder.setPostalCode(12323);
        testOrder.setTelephone("0700000000");
        testOrder.setCity("Stockholm");

        repo.add(addedOrder);
        repo.add(testOrder);
        repo.add(deleteOrder);

    }

    @Test
    public void testAddOrder() {

        Order o1 = new Order();
        o1.setAddress("temp");
        o1.setFirstName("temp");
        o1.setLastName("temp");
        o1.setPostalCode(12323);
        o1.setTelephone("070000000");
        o1.setCity("temp city");

        Assert.assertTrue(repo.add(o1));

    }

    @Test
    public void testGetOrder() {
        System.out.println("addedOrder id: " + addedOrder.getId());
        System.out.println("testOrder id: " + testOrder.getId());

        List<Order> list = repo.getAll();
        Order o1 = repo.get(addedOrder.getId());
        Order o2 = repo.get(testOrder.getId());
        print(o1);
        print(o2);
        print(addedOrder);
        print(testOrder);
        System.out.println(addedOrder.hashCode() + " " + o1.hashCode());
        Assert.assertNotNull("o1 == null",o1);
        Assert.assertTrue(o1.getId() == addedOrder.getId());
        Assert.assertTrue(o2.getId() == testOrder.getId());
        Assert.assertNotNull("list == null",list);
        Assert.assertTrue("list.empty == true", list.size() > 0);
        testOrderObject(list);
    }

    private void print(Order o){
        System.out.println(o.getId() + " " + o.getFirstName() + ", " + o.getLastName() + ", " + o.getAddress());
    }

    @Test
    public void testUpdateOrder() {
        System.out.println("Testorder in update? " + testOrder.getId());
        print(testOrder);
        System.out.println();
        Order o1 = repo.get(testOrder.getId());
        int pc = o1.getPostalCode()+1;
        Assert.assertTrue(testOrder.getPostalCode() == o1.getPostalCode());
        o1.setPostalCode(pc);
        boolean success = repo.update(o1);
        Assert.assertTrue("update o1 == false", success);
        Assert.assertTrue(testOrder.getPostalCode() != o1.getPostalCode());
    }

    @Test
    public void testGetOrderByName() {

        Order o1 = repo.get(addedOrder.getId());
        Order o2 = repo.get(testOrder.getId());
        List<Order> o3 = repo.getAll("added");
        List<Order> o4 = repo.getAll("test");
        List<Order> o5 = repo.getAll("rder");
        List<Order> oNull = repo.getAll("si");

        Assert.assertNotNull("Object 1 null",o1);
        Assert.assertNotNull("Object 2 null",o2);
        Assert.assertNotNull("Object 3 null",o3);
        Assert.assertNotNull("Object 4 null",o4);
        Assert.assertNotNull("Object 5 null",o5);
        Assert.assertNull("Object Null not null",oNull);

        Assert.assertTrue("o5 list is less than 2", o5.size() >= 2);

        Assert.assertTrue("o1 != o3.get(0)", o1.getId() == o3.get(0).getId());
        Assert.assertTrue("o2 != o4.get(0)", o2.getId() == o4.get(0).getId());
        Assert.assertTrue("o1 != o5.get(0)", o1.getId() == o5.get(0).getId());
        Assert.assertTrue("o2 != o5.get(1)", o2.getId() == o5.get(1).getId());

        testOrderObject(o5);

    }

    private void testOrderObject(List<Order> list) {
        for(Order o: list){
            Assert.assertTrue( o.getTelephone().length() == 10);
            Assert.assertTrue(o.getFirstName().length() > 0);
            Assert.assertTrue(o.getLastName().length() > 0);
            Assert.assertTrue(o.getCity().length() > 0);
            Assert.assertTrue(o.getId() > 0);
            Assert.assertTrue(String.valueOf(o.getPostalCode()).length() == 5);
        }
    }


}
