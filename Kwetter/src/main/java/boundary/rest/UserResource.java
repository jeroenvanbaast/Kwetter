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
import static javax.ws.rs.core.Response.Status.NOT_FOUND;
import static javax.ws.rs.core.Response.Status.UNAUTHORIZED;
import static javax.ws.rs.core.HttpHeaders.AUTHORIZATION;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import service.UserService;

/**
 *
 * @author Jeroen
 */
@Path("users")
@Stateless
public class UserResource {

    @Inject
    private UserService service;

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<User> getAll() {
        return service.getAll();
    }

    @GET
    @Path("{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public User getUser(@PathParam("id") long id) {
        User user = service.getById(id);
        return user;
    }

    @GET
    @Path("/login")
    @Produces(MediaType.APPLICATION_JSON)
    public Response login(@QueryParam("username") String username, @QueryParam("password") String password) {
        if (username != null && password != null) {
            try {
                User user = service.login(username, password);
                if (user != null) {
                    String id = Long.toString(user.getId());
                    String token = service.issueToken(id);
                    user.setToken(token);
                    return Response.ok(user).header(AUTHORIZATION, "Bearer " + token).build();
                }
            } catch (Exception e) {
            }
        }
        return Response.status(UNAUTHORIZED).build();
    }

    @PUT
    public void putUser(@QueryParam("userName") String userName, @QueryParam("passwordHash") String passwordHash) {
        User user = new User(userName, passwordHash);
        service.create(user);
    }

    @POST
    @Path("{id}")
    public void updateUser(@PathParam("id") long id, @QueryParam("userName") String userName, @QueryParam("password") String password) {
        User user = service.getById(id);
        user.setUserName(userName);
        user.setPasswordHash(password);
        service.update(user);
    }

    @javax.ws.rs.DELETE
    @Path("{id}")
    public void deleteUser(@PathParam("id") long id) {
        User user = service.getById(id);
        service.remove(user);
    }

    @GET
    @Path("byname/{name}")
    @Produces(MediaType.APPLICATION_JSON)
    public User getByName(@PathParam("name") String name) {
        return service.findByName(name);
    }

    @GET
    @Path("byprofilename/{name}")
    @Produces(MediaType.APPLICATION_JSON)
    public User getByProfileName(@PathParam("name") String name) {
        return service.findByProfileName(name);
    }
}
