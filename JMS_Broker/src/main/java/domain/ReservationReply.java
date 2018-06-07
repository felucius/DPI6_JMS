/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package domain;

import java.io.Serializable;
import javax.ejb.Stateless;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;

/**
 *
 * @author M
 */
@Stateless
@Entity
@NamedQueries({
    @NamedQuery(name = "ReservationReply.getReservationReplies", query = "SELECT r FROM ReservationReply r"),
    @NamedQuery(name = "ReservationReply.findReservationReply", query = "SELECT r FROM ReservationReply r WHERE r.id = :id")
})
public class ReservationReply implements Serializable{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String answer;
    private Integer replyTime;
        
    public ReservationReply() {
        
    }
    
    public ReservationReply(String answer, Integer time){
        this.answer = answer;
        this.replyTime = time;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getAnswer() {
        return answer;
    }

    public void setAnswer(String answer) {
        this.answer = answer;
    }

    public Integer getTime() {
        return replyTime;
    }

    public void setTime(Integer time) {
        this.replyTime = time;
    } 
    
}
