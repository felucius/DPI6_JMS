/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rest;

import domain.Restaurant;
import java.io.Serializable;
import java.util.List;
import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.persistence.PersistenceException;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import service.RestaurantService;

/**
 *
 * @author M
 */
@Stateless
@Path("restaurants")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class RestaurantRest implements Serializable {

    @Inject
    RestaurantService restaurantService;

    @GET
    public List<Restaurant> getRestaurants() throws PersistenceException {
        return restaurantService.getRestaurants();
    }

    @GET
    @Path("{id}")
    public Restaurant getRestaurant(@PathParam("id") Integer id) throws PersistenceException {
        return restaurantService.getRestaurant(id);
    }

    @POST
    public Boolean insertRestaurant(Restaurant restaurant) throws PersistenceException {
        return restaurantService.insertRestaurant(restaurant);
    }

    @PUT
    public Boolean updateRestaurant(Restaurant restaurant) throws PersistenceException {
        return restaurantService.updateRestaurant(restaurant);
    }

    @DELETE
    public Boolean removeRestaurant(Restaurant restaurant) throws PersistenceException {
        return restaurantService.removeRestaurant(restaurant);
    }
}
