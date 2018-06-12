/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package domain;

import domain.ReservationReply;
import domain.ReservationRequest;
import java.io.Serializable;
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
    @NamedQuery(name = "Reservation.getReservationValuePair", query = "SELECT r FROM ReservationRequestReply r")
    ,
    @NamedQuery(name = "Reservation.findReservationValuePair", query = "SELECT r FROM ReservationRequestReply r WHERE r.id = :id")
})
public class ReservationRequestReply implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    
    // Properties for reservation request
    private Integer amountOfSeats;
    private String name;
    private String restaurantName;
    private Integer time;
    
    // Properties for reservation reply
    private String answer;
    private Integer replyTime;

    public ReservationRequestReply() {

    }

    public ReservationRequestReply(ReservationRequest request, ReservationReply reply) {
        request.setAmountOfSeats(amountOfSeats);
        request.setName(name);
        request.setRestaurantName(restaurantName);
        request.setTime(time);
        
        reply.setAnswer(answer);
        reply.setTime(replyTime);
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getAmountOfSeats() {
        return amountOfSeats;
    }

    public void setAmountOfSeats(Integer amountOfSeats) {
        this.amountOfSeats = amountOfSeats;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getRestaurantName() {
        return restaurantName;
    }

    public void setRestaurantName(String restaurantName) {
        this.restaurantName = restaurantName;
    }

    public Integer getTime() {
        return time;
    }

    public void setTime(Integer time) {
        this.time = time;
    }

    public String getAnswer() {
        return answer;
    }

    public void setAnswer(String answer) {
        this.answer = answer;
    }

    public Integer getReplyTime() {
        return replyTime;
    }

    public void setReplyTime(Integer replyTime) {
        this.replyTime = replyTime;
    }

    
}
