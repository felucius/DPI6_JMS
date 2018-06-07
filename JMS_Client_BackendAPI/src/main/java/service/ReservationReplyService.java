/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package service;

import dao.ReservationReplyDAO;
import domain.ReservationReply;
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
public class ReservationReplyService implements Serializable {

    @Inject
    ReservationReplyDAO reservationReplyDAO;

    public List<ReservationReply> getReservationReplies() throws PersistenceException {
        return reservationReplyDAO.getReservationReplies();
    }

    public ReservationReply getReservationReply(Integer id) throws PersistenceException {
        return reservationReplyDAO.getReservationReply(id);
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
