/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package boundary.rest;

import domain.Kwet;
import domain.Profile;
import helper.ResourceUriBuilder;
import java.util.List;
import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.json.Json;
import javax.json.JsonArray;
import javax.json.JsonArrayBuilder;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;
import javax.ws.rs.core.UriInfo;
import service.KwetService;
import service.ProfileService;

/**
 *
 * @author Jeroen
 */
@Path("kwets")
@Stateless
public class KwetResource {

    @Inject
    ResourceUriBuilder resourceUriBuilder;
    @Inject
    private KwetService service;
    @Inject
    private ProfileService profileService;

    @Context
    UriInfo uriInfo;

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public JsonArray getAll() {
        JsonArrayBuilder list = Json.createArrayBuilder();
        List<Kwet> all = service.getAll();
        all.stream()
                .map(m -> m.toJson(
                resourceUriBuilder.createResourceUri(
                        KwetResource.class,
                        "getById",
                        m.getId(),
                        uriInfo
                )
        )
                )
                .forEach(list::add);
        return list.build();
    }

    @GET
    @Path("{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Kwet getById(@PathParam("id") String id) {
        Kwet kwet = service.getById(Long.valueOf(id));
        return kwet;
    }

    @PUT
    public Kwet putKwet(@QueryParam("message") String message, @QueryParam("profileId") String profileId) {
        Profile profile = profileService.getById(Long.valueOf(profileId));
        Kwet kwet = new Kwet(message, profile);
        profile.getKwets().add(kwet);
        profileService.update(profile);
        service.create(kwet);
        return kwet;
    }

    @POST
    @Path("{id}")
    public void updateKwet(@PathParam("id") long id, @QueryParam("message") String message) {
        Kwet kwet = service.getById(id);
        kwet.setMessage(message);
        service.update(kwet);
    }

    @DELETE
    @Path("{id}")
    public void deleteKwet(@PathParam("id") long id) {
        service.remove(service.getById(id));
    }
}
