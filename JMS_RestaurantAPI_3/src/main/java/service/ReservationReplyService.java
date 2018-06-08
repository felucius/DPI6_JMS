/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package service;

import dao.ReservationReplyDAO;
import domain.ReservationReply;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.persistence.PersistenceException;

/**
 *
 * @author M
 */
@Stateless
public class ReservationReplyService implements Serializable {

    @Inject
    ReservationReplyDAO reservationReplyDAO;

    public List<ReservationReply> getReservationReplies() throws PersistenceException {
        return reservationReplyDAO.getReservationReplies();
    }

    public ReservationReply getReservationReply(/*Integer id*/) throws PersistenceException {
        //return reservationReplyDAO.getReservationReply(id);       
        List<ReservationReply> reservationReplies = new ArrayList();
        Random random = new Random();
        
        reservationReplies.add(new ReservationReply("OK", 11));
        reservationReplies.add(new ReservationReply("NO", 12));
        
        int item = random.nextInt(reservationReplies.size());
        ReservationReply reply = reservationReplies.get(item);
        return reply;
    }

    public Boolean insertReservationReply(ReservationReply reservationReply) throws PersistenceException {
        return reservationReplyDAO.insertReservationReply(reservationReply);
    }

    public Boolean updateReservationReply(ReservationReply reservationReply) throws PersistenceException {
        return reservationReplyDAO.updateReservationReply(reservationReply);
    }

    public Boolean removeReservationReply(ReservationReply reservationReply) throws PersistenceException {
        return reservationReplyDAO.removeReservationReply(reservationReply);
    }
}
