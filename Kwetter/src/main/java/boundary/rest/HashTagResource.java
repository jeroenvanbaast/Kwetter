/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package boundary.rest;

import domain.HashTag;
import domain.User;
import java.util.List;
import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import service.HashTagService;
import service.UserService;

/**
 *
 * @author Jeroen
 */
@Path("hashtags")
@Stateless
public class HashTagResource {

    @Inject
    private HashTagService service;

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<HashTag> getAll() {
        return service.getAll();
    }

    @GET
    @Path("{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public HashTag getHashTag(@PathParam("id") long id) {
        HashTag hashTag = service.getById(id);
        return hashTag;
    }

    
    @DELETE
    @Path("{id}")
    public void deleteHashTag(@PathParam("id") long id) {
       service.remove(service.getById(id));
    }
}
