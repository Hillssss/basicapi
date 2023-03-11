package tp6.basicapi.service;

import tp6.basicapi.model.Attribut;

import javax.enterprise.context.ApplicationScoped;
import javax.transaction.Transactional;
import javax.ws.rs.core.Response;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

@ApplicationScoped
public class ItemmServicee {

    public Response get() {
        return
                Response.status(Response.Status.OK).entity(Attribut.findAll().list()).build();
    }

    @Transactional
    public Response post(Map<String,Object> request){
        Attribut attribut = new Attribut();
        attribut.name = request.get("name").toString();
        attribut.count = request.get("count").toString();
        attribut.price = request.get("price").toString();
        attribut.type = request.get("type").toString();
        attribut.description = request.get("description").toString();
        attribut.createdAt =
                LocalDateTime.parse(request.get("createdAt").toString());
        attribut.updatedAt =
                LocalDateTime.parse(request.get("updatedAt").toString());
        attribut.persist();
        return Response.status(Response.Status.CREATED).entity(new
                HashMap<>()).build();
    }


    @Transactional
    public Response put (Long id, Map<String,Object>
            request){
        Attribut attribut = Attribut.findById(id);
        if (attribut == null){
            return Response.status(Response.Status.BAD_REQUEST).build();
        }
        attribut.name = request.get("name").toString();
        attribut.count = request.get("count").toString();
        attribut.price = request.get("price").toString();
        attribut.type = request.get("type").toString();
        attribut.description = request.get("description").toString();
        attribut.createdAt =
                LocalDateTime.parse(request.get("createdAt").toString());
        attribut.updatedAt =
                LocalDateTime.parse(request.get("updatedAt").toString());
        attribut.persist();
        return Response.status(Response.Status.CREATED).entity(new
                HashMap<>()).build();
    }


    @Transactional
    public Response delete(Long id){
        Attribut attribut = Attribut.findById(id);
        if (attribut == null){
            return Response.status(Response.Status.BAD_REQUEST).build();
        }
        attribut.delete();
        return Response.status(Response.Status.OK).entity(new
                HashMap<>()).build();
    }
}
