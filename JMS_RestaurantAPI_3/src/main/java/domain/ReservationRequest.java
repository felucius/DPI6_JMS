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
    @NamedQuery(name = "Reservation.getReservations", query = "SELECT r from ReservationRequest r"),
    @NamedQuery(name = "Reservation.findReservation", query = "SELECT r from ReservationRequest r WHERE r.id = :id")
})
public class ReservationRequest implements Serializable{
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String name;
    private Integer time;
    private Integer amountOfSeats;
    
    private String restaurantName;
    
    private List<ReservationReply> reservationReplies;
    
    public ReservationRequest(){
        
    }
    
    public ReservationRequest(String name, Integer time, Integer amountOfSeats) {
        this.name = name;
        this.time = time;
        this.amountOfSeats = amountOfSeats;
        this.reservationReplies = new ArrayList();
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getTime() {
        return time;
    }

    public void setTime(Integer time) {
        this.time = time;
    }

    public Integer getAmountOfSeats() {
        return amountOfSeats;
    }

    public void setAmountOfSeats(Integer amountOfSeats) {
        this.amountOfSeats = amountOfSeats;
    }

    public String getRestaurantName() {
        return restaurantName;
    }

    public void setRestaurantName(String restaurantName) {
        this.restaurantName = restaurantName;
    }

    public List<ReservationReply> getReservationReplies() {
        return reservationReplies;
    }

    public void setReservationReplies(List<ReservationReply> reservationReplies) {
        this.reservationReplies = reservationReplies;
    }
    
    
}
