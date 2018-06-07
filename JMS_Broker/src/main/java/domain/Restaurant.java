/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package domain;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;

/**
 *
 * @author M
 */
@Stateless
@Entity
@NamedQueries({
    @NamedQuery(name = "Restaurant.getRestaurants", query = "SELECT r FROM Restaurant r")
    ,
    @NamedQuery(name = "Restaurant.findRestaurant", query = "SELECT r FROM Restaurant r WHERE r.id = :id")
    ,
    @NamedQuery(name = "Restaurant.findRestaurantByName", query = "SELECT r FROM Restaurant r WHERE r.name = :name")
})
public class Restaurant implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String name;
    private String location;
    private Integer amountOfSeats;
    
    // Check if a restaurant already exists
    private List<Restaurant> restaurants = new ArrayList();

    private List<ReservationRequest> reservationRequests;

    public Restaurant() {

    }

    public Restaurant(String name, String location, Integer amountOfSeats) {
        this.name = name;
        this.location = location;
        this.amountOfSeats = amountOfSeats;
        this.reservationRequests = new ArrayList();
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public List<Restaurant> getRestaurants() {
        return restaurants;
    }

    public void setRestaurants(List<Restaurant> restaurants) {
        this.restaurants = restaurants;
    }
    
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public Integer getAmountOfSeats() {
        return amountOfSeats;
    }

    public void setAmountOfSeats(Integer amountOfSeats) {
        this.amountOfSeats = amountOfSeats;
    }

    /*
    public List<ReservationRequest> getReservationRequest() {
        return reservationRequests;
    }

    public void setReservationRequest(List<ReservationRequest> reservationRequest) {
        this.reservationRequests = reservationRequest;
    }*/

    public void addRestaurant(Restaurant restaurant){
        this.restaurants.add(restaurant);
    }
    
    public Integer countRestaurants(){
        return this.restaurants.size();
    }
    
    /*
    @Override
    public String toString() {
        return "Restaurant name: " + this.name
                + " - Location: " + this.location
                + " - Amount of seats: " + this.amountOfSeats;
    }*/
}
