package business.controller;

import business.configuration.Mapper;
import business.model.Transport;
import business.service.TransportService;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;

@Path("transport/")
public class TransportController {

    private TransportService service = new TransportService();

    @GET
    @Path("")
    @Produces(MediaType.APPLICATION_JSON)
    public List<Transport> getAll() {
        try{
            List<data.model.Transport> list = service.getAll();
            if (list == null) return null;
            List<Transport> result = (List<Transport>) Mapper.mapCollection(list, Transport.class);
            return result;
        }catch (Exception e){
            System.out.println("TransportController: error getting all");
        }
        return null;
    }

    @GET
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Transport get(@PathParam("id") long id) {
        try{
            data.model.Transport entity = service.get(id);
            if (entity == null) return null;
            Transport result = Mapper.map(entity, Transport.class);
            return result;
        }catch (Exception e){
            System.out.println("TransportController: error getting transport by Id");
        }
        return null;
    }

    @POST
    @Path("/")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response add(Transport transport) {

        if (transport == null) return Response.status(400).build();

        try{
            data.model.Transport entity = Mapper.map(transport, data.model.Transport.class);
            if(service.add(entity)) return Response.status(201).build();
            return Response.status(400).build();
        }catch (Exception e){
            System.out.println("TransportController: error adding transport");
        }
        return Response.status(400).build();
    }

    @DELETE
    @Path("/{id}")
    @Consumes(MediaType.APPLICATION_JSON)
    public Transport delete(@PathParam("id") long id) {

        if (id <= 0) return null;

        try{
            data.model.Transport entity = service.delete(id);
            if(entity == null) return null;
            Transport result = Mapper.map(entity, Transport.class);
            return result;
        }catch (Exception e){
            System.out.println("TransportController: error deleting transport with id " + id);
        }
        return null;
    }

    @PUT
    @Path("/")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response update(Transport transport) {

        if (transport == null) Response.status(400).build();

        try{
            data.model.Transport entity = Mapper.map(transport, data.model.Transport.class);
            if(entity == null) return Response.status(400).build();
            if(service.update(entity)) return Response.status(200).build();
            return Response.status(400).build();
        }catch (Exception e){
            System.out.println("TransportController: error updating transport with id " + transport.getId());
        }
        return Response.status(400).build();
    }
}
