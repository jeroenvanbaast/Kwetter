/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package helper;

import java.net.URI;
import javax.ws.rs.core.UriInfo;

/**
 *
 * @author Jeroen
 */
public class ResourceUriBuilder {

    public URI createResourceUri(Class<?> resourcesClass, UriInfo uriInfo) {
        return uriInfo.getBaseUriBuilder().path(resourcesClass).build();
    }

    public URI createResourceUri(Class<?> resourcesClass, String method, long id, UriInfo uriInfo) {
        return uriInfo.getBaseUriBuilder().path(resourcesClass).path(resourcesClass, method).build(id);
    }
}
