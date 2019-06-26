package business.controller;

import business.model.Order;
import business.service.OrderService;

import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import java.awt.*;
import java.util.List;

@Path("order/")
public class OrderController {

    OrderService service = new OrderService();

    @Path("")
    @Produces(MediaType.APPLICATION_JSON)
    public List<Order> getAll() {

        List<data.model.Order> result = service.getAll();
        return null;
    }

}
