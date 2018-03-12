/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package boundary.rest;

import domain.Kwet;
import java.util.List;
import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import service.KwetService;

/**
 *
 * @author Jeroen
 */
@Path("kwets")
@Stateless
public class KwetResource {

    @Inject
    private KwetService service;

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<Kwet> getAll() {
        return service.getAll();
    }

    @GET
    @Path("{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Kwet getStudent(@PathParam("id") long id) {
        Kwet kwet = service.getById(id);
        return kwet;
    }
}
