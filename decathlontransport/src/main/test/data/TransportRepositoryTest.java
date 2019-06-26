package data;

import data.model.Order;
import data.model.Status;
import data.model.Transport;
import data.repository.TransportRepository;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.Calendar;
import java.util.List;

public class TransportRepositoryTest {

    private Transport addTransport, deleteTransport;
    private Order addTransportOrder;
    private TransportRepository repo;

    @Before
    public void setUp() {
        repo = new TransportRepository("test-unit");

        Status s1 = new Status();
        s1.setId(1);

        addTransportOrder = new Order();
        addTransportOrder.setAddress("T1 address");
        addTransportOrder.setFirstName("T1");
        addTransportOrder.setLastName("T1");
        addTransportOrder.setPostalCode(11111);
        addTransportOrder.setTelephone("0700000000");
        addTransportOrder.setCity("T1 city");

        Order o2 = new Order();
        o2.setAddress("T2 address");
        o2.setFirstName("T2");
        o2.setLastName("T2");
        o2.setPostalCode(11111);
        o2.setTelephone("0700000000");
        o2.setCity("T2 city");

        addTransport = new Transport();
        addTransport.setOrder(addTransportOrder);
        addTransport.setIssueDate(Calendar.getInstance().getTime());
        addTransport.setDeliveryDate(Calendar.getInstance().getTime());
        addTransport.setStatus(s1);

        deleteTransport = new Transport();
        deleteTransport.setOrder(o2);
        deleteTransport.setIssueDate(Calendar.getInstance().getTime());
        deleteTransport.setDeliveryDate(Calendar.getInstance().getTime());
        deleteTransport.setStatus(s1);

        Assert.assertTrue(repo.add(addTransport));
        Assert.assertTrue(repo.add(deleteTransport));
    }

    @Test
    public void testAddTransport() {

        Status s1 = new Status();
        s1.setId(1);

        Order o1 = new Order();
        o1.setAddress("T3 address");
        o1.setFirstName("T3");
        o1.setLastName("T3");
        o1.setPostalCode(11111);
        o1.setTelephone("0700000000");
        o1.setCity("T3 city");

        Order o2 = new Order();
        o2.setAddress("T4 address");
        o2.setFirstName("T4");
        o2.setLastName("T4");
        o2.setPostalCode(11111);
        o2.setTelephone("0700000000");
        o2.setCity("T4 city");

        Transport t1 = new Transport();
        t1.setOrder(o1);
        t1.setIssueDate(Calendar.getInstance().getTime());
        t1.setDeliveryDate(Calendar.getInstance().getTime());
        t1.setStatus(s1);

        Transport t2 = new Transport();
        t2.setOrder(o2);
        t2.setIssueDate(Calendar.getInstance().getTime());
        t2.setDeliveryDate(Calendar.getInstance().getTime());
        t2.setStatus(s1);

        Assert.assertTrue(repo.add(t1));
        Assert.assertTrue(repo.add(t2));
    }

    @Test
    public void testGetTransport() {

        System.out.println("addTransport id: "+addTransport.getId());
        Transport t1 = repo.get(addTransport.getId());
        Assert.assertNotNull("Transport object 1 null",t1);
        Assert.assertNotNull(t1.getStatus());
        Assert.assertNotNull(t1.getIssueDate());
        Assert.assertNotNull(t1.getDeliveryDate());
        Assert.assertNotNull(t1.getOrder());
        Assert.assertTrue(t1.getId() > 0);
        Assert.assertTrue(t1.getId() == addTransport.getId());
        Assert.assertTrue(addTransport.getDeliveryDate().equals(t1.getDeliveryDate()));
        Assert.assertTrue(addTransport.getIssueDate().equals(t1.getIssueDate()));
        testTransportOrder(addTransport.getOrder(),t1.getOrder());
        List<Transport> list = repo.getAll();
        Assert.assertNotNull("List is nulled",list);
        Assert.assertTrue("List is empty", list.size() > 0);
        testTransportObject(list);
    }

    @Test
    public void testGetByOrderId() {
        Transport t1 = repo.getByOrderId(addTransportOrder.getId());
        Assert.assertNotNull(t1);
        testTransportOrder(addTransport.getOrder(), addTransportOrder);
    }

    @Test
    public void testUpdateOrder() {
        Transport t1 = addTransport;
        Status status = new Status();
        status.setId(2);
        t1.setStatus(status);
        Assert.assertTrue(repo.update(t1));
    }

    private void testTransportObject(List<Transport> list) {
        for(Transport t: list){
            Assert.assertNotNull(t.getOrder());
            Assert.assertNotNull(t.getDeliveryDate());
            Assert.assertNotNull(t.getIssueDate());
            Assert.assertNotNull(t.getStatus());
        }
    }

    private void testTransportOrder(Order o1, Order o2) {
        Assert.assertTrue(o1.getAddress().equals(o2.getAddress()));
        Assert.assertTrue(o1.getFirstName().equals(o2.getFirstName()));
        Assert.assertTrue(o1.getLastName().equals(o2.getLastName()));
        Assert.assertTrue(o1.getPostalCode() == o2.getPostalCode());
        Assert.assertTrue(o1.getCity().equals(o2.getCity()));
        Assert.assertTrue(o1.getTelephone().equals(o2.getTelephone()));
    }
}
