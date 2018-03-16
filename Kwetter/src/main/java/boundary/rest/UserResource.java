/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package boundary.rest;

import domain.User;
import java.util.List;
import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import service.UserService;

/**
 *
 * @author Jeroen
 */
@Path("users")
@Stateless
public class UserResource
{

    @Inject
    private UserService service;

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<User> getAll()
    {
        return service.getAll();
    }

    @GET
    @Path("{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public User getUser(@QueryParam("id") long id)
    {
        User user = service.getById(id);
        return user;
    }

    @PUT
    public void putUser(@QueryParam("userName") String userName, @QueryParam("passwordHash") String passwordHash)
    {
        User user = new User(userName,passwordHash);
        service.create(user);
    }
}
