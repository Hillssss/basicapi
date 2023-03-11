package tp6.basicapi;

import net.sf.jasperreports.engine.JRException;
import tp6.basicapi.service.ExportService;
import tp6.basicapi.service.ItemmServicee;

import javax.inject.Inject;
import javax.transaction.Transactional;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.Map;

@Path("/item")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)


public class Item {
    @Inject
    ItemmServicee itemmServicee;
    @Inject
    ExportService exportService;

    @GET
    public Response get() {
        return itemmServicee.get();
    }
    @GET
    @Path("/export")
    @Produces("application/pdf")
    public Response export() throws JRException {
        return exportService.exportItem();
    }
    @POST
    @Transactional
    public Response post(Map<String,Object> request){
        return itemmServicee.post(request);
    }
    @PUT
    @Path("/{id}")
    @Transactional
    public Response put (@PathParam("id") Long id, Map<String,Object>
            request){
        return itemmServicee.put(id, request);
    }
    @DELETE
    @Path("/{id}")
    @Transactional
    public Response delete(@PathParam("id") Long id){
        return itemmServicee.delete(id);
    }

}
