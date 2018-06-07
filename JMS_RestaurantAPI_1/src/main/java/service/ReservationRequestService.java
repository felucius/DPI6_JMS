/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package service;

import dao.ReservationRequestDAO;
import domain.ReservationRequest;
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
public class ReservationRequestService implements Serializable{
    @Inject
    ReservationRequestDAO reservationRequestDAO;
    
    public List<ReservationRequest> getReservations() throws PersistenceException {
        return reservationRequestDAO.getReservations();
    }

    public ReservationRequest getReservation(Integer id) throws PersistenceException {
        return reservationRequestDAO.getReservation(id);
    }

    public Boolean insertReservation(ReservationRequest reservationRequest) throws PersistenceException {
        return reservationRequestDAO.insertReservation(reservationRequest);
    }

    public Boolean updateReservation(ReservationRequest reservationRequest) throws PersistenceException {
        return reservationRequestDAO.updateReservation(reservationRequest);
    }

    public Boolean removeReservation(ReservationRequest reservationRequest) throws PersistenceException {
        return reservationRequestDAO.removeReservation(reservationRequest);
    }
}
