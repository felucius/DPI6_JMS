/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package service;

import dao.RestaurantDAO;
import domain.Restaurant;
import java.io.Serializable;
import java.util.List;
import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.persistence.PersistenceException;

/**
 *
 * @author M
 */
@Stateless
public class RestaurantService implements Serializable {

    @Inject
    RestaurantDAO restaurantDAO;

    public List<Restaurant> getRestaurants() throws PersistenceException {
        return restaurantDAO.getRestaurants();
    }

    public Restaurant getRestaurant(Integer id) throws PersistenceException {
        return restaurantDAO.getRestaurant(id);
    }

    public Restaurant getRestaurantByName(String name) throws PersistenceException {
        return restaurantDAO.getRestaurantByName(name);
    }

    public Boolean insertRestaurant(Restaurant restaurant) throws PersistenceException {
        return restaurantDAO.insertRestaurant(restaurant);
    }

    public Boolean updateRestaurant(Restaurant restaurant) throws PersistenceException {
        return restaurantDAO.updateRestaurant(restaurant);
    }

    public Boolean removeRestaurant(Restaurant restaurant) throws PersistenceException {
        return restaurantDAO.removeRestaurant(restaurant);
    }
}
