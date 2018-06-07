/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package service;

import dao.ReservationRequestReplyDAO;
import java.io.Serializable;
import java.util.List;
import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.persistence.PersistenceException;
import messaging.ReservationRequestReply;

/**
 *
 * @author M
 */
@Stateless
public class ReservationRequestReplyService implements Serializable {

    @Inject
    ReservationRequestReplyDAO reservationValuePairDAO;

    public List<ReservationRequestReply> getReservationValuePairs() throws PersistenceException {
        return reservationValuePairDAO.getReservationValuePairs();
    }

    public ReservationRequestReply findReservationValuePair(Integer id) throws PersistenceException {
        return reservationValuePairDAO.findReservationValuePair(id);
    }

    public Boolean insertReservationValuePair(ReservationRequestReply reservationValuePair) throws PersistenceException {
        return reservationValuePairDAO.insertReservationValuePair(reservationValuePair);
    }

    public Boolean updateReservationValuePair(ReservationRequestReply reservationValuePair) throws PersistenceException {
        return reservationValuePairDAO.updateReservationValuePair(reservationValuePair);
    }

    public Boolean removeReservationValuePair(ReservationRequestReply reservationValuePair) throws PersistenceException {
        return reservationValuePairDAO.removeReservationValuePair(reservationValuePair);
    }
}
