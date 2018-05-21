/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package boundary.rest;

import domain.Kwet;
import domain.Profile;
import domain.User;
import io.jsonwebtoken.Jwts;
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
import javax.ws.rs.core.Context;
import javax.ws.rs.core.HttpHeaders;
import jwt.JWTTokenNeeded;
import jwt.KeyGenerator;
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
   @Inject
    private KeyGenerator keyGenerator;
   
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<Profile> getAll() {
        return service.getAll();
    }

    @GET
    @Path("{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Profile getById(@PathParam("id") long id) {
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
        Profile p = service.getByName(name);
        return p;
    }

    @PUT
    public void putProfile(@QueryParam("name") String name, @QueryParam("bio") String bio) {
        Profile profile = new Profile(name, bio);
        service.create(profile);
    }

    @POST
    @Path("update")
    @JWTTokenNeeded
    public Profile updateProfile( @QueryParam("name") String name, @QueryParam("bio") String bio, @QueryParam("locatie") String locatie, @QueryParam("website") String website, @QueryParam("picture") String picture, @Context HttpHeaders headers) {
        String token = headers.getHeaderString("AUTHORIZATION").substring("Bearer".length()).trim();
        String idString = Jwts.parser().setSigningKey(this.keyGenerator.generateKey()).parseClaimsJws(token).getBody().getSubject();
        Long id = Long.valueOf(idString);
        Profile profile = service.getById(id);
        profile.setName(name);
        profile.setBio(bio);
        profile.setProfilePicture(picture);
        profile.setLocatie(locatie);
        profile.setWebsite(website);
        service.update(profile);
        return profile;
    }

    @DELETE
    @Path("{id}")
    public void deleteProfile(@PathParam("id") long id) {
        service.remove(service.getById(id));
    }

    @POST
    @Path("{id}/follow")
    public User follow(@PathParam("id") int id, @QueryParam("followerid") int followerId) {
        Profile profile = service.getById(id);
        User user = userService.findByProfile(profile);
        Profile toFollow = service.getById(followerId);
        user.getFollowing().add(toFollow);
        userService.update(user);
        return user;
    }

    @POST
    @Path("{id}/unfollow")
    public User unFollow(@PathParam("id") int id, @QueryParam("followerid") int followerId) {
        Profile profile = service.getById(id);
        User user = userService.findByProfile(profile);
        Profile follower = service.getById(followerId);
        user.getFollowing().remove(follower);
        userService.update(user);
        return user;
    }

    @POST
    @Path("{id}/heart")
    public void heart(@PathParam("id") int id, @QueryParam("kwetid") int kwetId) {
        Kwet kwet = kwetService.getById(kwetId);
        kwet.Like();
        Profile profile = service.getById(id);
        profile.getHeartedKwets().add(kwet);
        kwetService.update(kwet);
        service.update(profile);
    }

    @DELETE
    @Path("{id}/heart")
    public void unHeart(@PathParam("id") int id, @QueryParam("kwetid") int kwetId) {
        Kwet kwet = kwetService.getById(kwetId);
        kwet.UnLike();
        Profile profile = service.getById(id);
        profile.getHeartedKwets().remove(kwet);
        kwetService.update(kwet);
        service.update(profile);
    }

    @GET
    @Path("getfollowers/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public List<Profile> getFollowers(@PathParam("id") int id) {
        Profile profile = service.getById(id);
        return service.getProfilesFromUsers(userService.findFollowers(profile));
    }
}
