package configuration;

import business.configuration.Mapper;
import data.model.Order;
import org.junit.Assert;
import org.junit.Test;
import org.modelmapper.ModelMapper;

import java.util.ArrayList;
import java.util.List;

public class ModelMapperTester {

    @Test
    public void testMappingFromBusinessToData() {

        Order do1 = new Order();
        do1.setId(1);
        do1.setAddress("Stockholm");
        do1.setFirstName("O2");
        do1.setLastName("01");
        do1.setPostalCode(020202);
        do1.setTelephone("0700000000");
        do1.setCity("O2 city");

        business.model.Order bo1 = Mapper.map(do1, business.model.Order.class);
        checkOrders(bo1,do1);
    }

    @Test
    public void testMappingFromDataToBusiness() {

        business.model.Order bo1 = new business.model.Order();
        bo1.setId(1);
        bo1.setAddress("Stockholm");
        bo1.setFirstName("O2");
        bo1.setLastName("01");
        bo1.setPostalCode(020202);
        bo1.setTelephone("0700000000");
        bo1.setCity("O2 city");

        Order do1 = Mapper.map(bo1, Order.class);
        checkOrders(bo1,do1);
    }

    @Test
    public void testMappingFromDataToBusinessList() {

        business.model.Order bo1 = new business.model.Order();
        bo1.setId(1);
        bo1.setAddress("Stockholm");
        bo1.setFirstName("O2");
        bo1.setLastName("01");
        bo1.setPostalCode(020202);
        bo1.setTelephone("0700000000");
        bo1.setCity("O2 city");

        business.model.Order bo2 = new business.model.Order();
        bo2.setId(1);
        bo2.setAddress("Stockholm");
        bo2.setFirstName("O2");
        bo2.setLastName("01");
        bo2.setPostalCode(020202);
        bo2.setTelephone("0700000000");
        bo2.setCity("O2 city");

        List<business.model.Order> lbo = new ArrayList<>();
        lbo.add(bo1);
        lbo.add(bo2);

        List<Order> ldo = (List<Order>) Mapper.mapCollection(lbo,Order.class);
        checkListOrders(lbo,ldo);
    }

    @Test
    public void testMappingFromBusinessToDataList() {

        Order do1 = new Order();
        do1.setId(1);
        do1.setAddress("Stockholm");
        do1.setFirstName("O2");
        do1.setLastName("01");
        do1.setPostalCode(020202);
        do1.setTelephone("0700000000");
        do1.setCity("O2 city");

        Order do2 = new Order();
        do2.setId(1);
        do2.setAddress("Stockholm");
        do2.setFirstName("O2");
        do2.setLastName("01");
        do2.setPostalCode(020202);
        do2.setTelephone("0700000000");
        do2.setCity("O2 city");

        List<Order> ldo = new ArrayList<>();
        ldo.add(do2);
        ldo.add(do2);

        List<business.model.Order> lbo = (List<business.model.Order>) Mapper.mapCollection(ldo,business.model.Order.class);
        checkListOrders(lbo,ldo);
    }

    private void checkOrders(business.model.Order o1, Order o2) {
        Assert.assertTrue(o1.getId() == o2.getId());
        Assert.assertTrue(o1.getAddress().equals(o2.getAddress()));
        Assert.assertTrue(o1.getFirstName().equals(o2.getFirstName()));
        Assert.assertTrue(o1.getLastName().equals(o2.getLastName()));
        Assert.assertTrue(o1.getPostalCode() == o2.getPostalCode());
        Assert.assertTrue(o1.getCity().equals(o2.getCity()));
        Assert.assertTrue(o1.getTelephone().equals(o2.getTelephone()));
    }

    private void checkListOrders(List<business.model.Order> lo1, List<Order> lo2) {
        business.model.Order o1;
        Order o2;
        for(int i = 0; i < lo1.size(); i++) {
            o1 = lo1.get(i); o2 = lo2.get(i);
            Assert.assertTrue(o1.getId() == o2.getId());
            Assert.assertTrue(o1.getAddress().equals(o2.getAddress()));
            Assert.assertTrue(o1.getFirstName().equals(o2.getFirstName()));
            Assert.assertTrue(o1.getLastName().equals(o2.getLastName()));
            Assert.assertTrue(o1.getPostalCode() == o2.getPostalCode());
            Assert.assertTrue(o1.getCity().equals(o2.getCity()));
            Assert.assertTrue(o1.getTelephone().equals(o2.getTelephone()));
        }
    }

}
