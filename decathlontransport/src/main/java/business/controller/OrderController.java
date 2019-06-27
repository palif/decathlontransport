package business.controller;

import business.configuration.Mapper;
import business.model.Order;
import business.service.OrderService;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.awt.*;
import java.util.List;

@Path("order/")
public class OrderController {

    private OrderService service = new OrderService();

    @Path("")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<Order> getAll() {

        List<data.model.Order> result = service.getAll();
        if (result == null) return null;
        return (List<Order>) Mapper.mapCollection(result, Order.class);

    }

    @Path("/{name}")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<Order> getByName(@PathParam("name") String name) {

        if(name.isEmpty()) return null;
        try {
            List<data.model.Order> list = service.getAll(name);
            if(list == null) return null;
            List<Order> result = (List<Order>) Mapper.mapCollection(list,Order.class);
            return result;
        } catch (Exception e){
            System.out.println("OrderController: adding order exception \n" + e.getMessage());
        }

        return null;
    }

    @Path("")
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    public Response add(Order order) {

        if(order == null) return Response.status(400).build();
        try {
            data.model.Order entity = Mapper.map(order,data.model.Order.class);
            if(service.add(entity)) return Response.status(201).build();
            return Response.status(400).build();
        } catch (Exception e){
            System.out.println("OrderController: adding order exception \n" + e.getMessage());
        }

        return Response.status(400).build();
    }

    @Path("/{id}")
    @DELETE
    @Produces(MediaType.APPLICATION_JSON)
    public Order delete(@PathParam("id") long id) {

        if (id <= 0) return null;

        try {
            data.model.Order entity = service.delete(id);
            if (entity == null) return null;
            Order result = Mapper.map(entity, Order.class);
            return result;
        } catch (Exception e) {
            System.out.println("OrderController: deleting entity failed with id " + id);
        }

        return null;

    }



}
