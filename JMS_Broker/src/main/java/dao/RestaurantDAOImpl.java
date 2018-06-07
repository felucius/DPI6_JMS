/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import domain.Restaurant;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.PersistenceException;

/**
 *
 * @author M
 */
@Stateless
public class RestaurantDAOImpl implements RestaurantDAO {

    @PersistenceContext(name = "JMS_Broker_PU")
    EntityManager em;

    @Override
    public List<Restaurant> getRestaurants() throws PersistenceException {
        return em.createNamedQuery("Restaurant.getRestaurants").getResultList();
    }

    @Override
    public Restaurant getRestaurant(Integer id) throws PersistenceException {
        return (Restaurant) em.createNamedQuery("Restaurant.findRestaurant").setParameter("id", id).getSingleResult();
    }

    @Override
    public Boolean insertRestaurant(Restaurant restaurant) throws PersistenceException {
        em.persist(restaurant);
        return true;
    }

    @Override
    public Boolean updateRestaurant(Restaurant restaurant) throws PersistenceException {
        em.merge(restaurant);
        return true;
    }

    @Override
    public Boolean removeRestaurant(Restaurant restaurant) throws PersistenceException {
        em.remove(restaurant);
        return true;
    }
}
