/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package boundary.rest;

import domain.Kwet;
import domain.Profile;
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
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import service.KwetService;
import service.ProfileService;
import service.UserService;

/**
 *
 * @author Jeroen
 */
@Path("profiles")
@Stateless
public class ProfileResource {

    @Inject
    private ProfileService service;
    @Inject
    private KwetService kwetService;
    @Inject
    private UserService userService;

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<Profile> getAll() {
        return service.getAll();
    }

    @GET
    @Path("{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Profile getProfile(@PathParam("id") long id) {
        Profile profile = service.getById(id);
        return profile;
    }

    @GET
    @Path("byuser/{username}")
    @Produces(MediaType.APPLICATION_JSON)
    public Profile getProfileByUsername(@PathParam("username") String username) {
        User user = this.userService.findByName(username);
        if (user != null) {
            return user.getProfile();
        }
        return null;
    }

    @GET
    @Path("byname/{name}")
    @Produces(MediaType.APPLICATION_JSON)
    public Profile getByProfileName(@PathParam("name") String name) {
        return service.getByName(name);
    }

    @PUT
    public void putProfile(@QueryParam("name") String name, @QueryParam("bio") String bio) {
        Profile profile = new Profile(name, bio);
        service.create(profile);
    }

    @POST
    @Path("{id}")
    public void updateProfile(@PathParam("id") long id, @QueryParam("name") String name, @QueryParam("bio") String bio, @QueryParam("locatie") String locatie, @QueryParam("website") String website) {
        Profile profile = service.getById(id);
        profile.setName(name);
        profile.setBio(bio);
        profile.setLocatie(locatie);
        profile.setWebsite(website);
        service.update(profile);
    }

    @DELETE
    @Path("{id}")
    public void deleteProfile(@PathParam("id") long id) {
        service.remove(service.getById(id));
    }

    @POST
    @Path("{id}/follow")
    public void follow(@PathParam("id") int id, @QueryParam("followerId") int followerId) {
        Profile profile = service.getById(id);
        Profile follower = service.getById(followerId);
        profile.getFollowing().add(follower);
        service.update(profile);
    }

    @DELETE
    @Path("{id}/follow")
    public void unFollow(@PathParam("id") int id, @QueryParam("followerId") int followerId) {
        Profile profile = service.getById(id);
        Profile follower = service.getById(followerId);
        profile.getFollowing().remove(follower);
        service.update(profile);
    }

    @POST
    @Path("{id}/heart")
    public void heart(@PathParam("id") int id, @QueryParam("kwetId") int kwetId) {
        Kwet kwet = kwetService.getById(kwetId);
        Profile profile = service.getById(id);
        profile.getHeartedKwets().add(kwet);
        service.update(profile);
    }

    @DELETE
    @Path("{id}/heart")
    public void unHeart(@PathParam("id") int id, @QueryParam("kwetId") int kwetId) {
        Kwet kwet = kwetService.getById(kwetId);
        Profile profile = service.getById(id);
        profile.getHeartedKwets().remove(kwet);
        service.update(profile);
    }
}
