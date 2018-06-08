/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import domain.ReservationRequest;
import java.util.List;
import javax.persistence.PersistenceException;

/**
 *
 * @author M
 */
public interface ReservationRequestDAO {

    List<ReservationRequest> getReservations() throws PersistenceException;

    ReservationRequest getReservation(Integer id) throws PersistenceException;

    Boolean insertReservation(ReservationRequest reservationRequest) throws PersistenceException;

    Boolean updateReservation(ReservationRequest reservationRequest) throws PersistenceException;

    Boolean removeReservation(ReservationRequest reservationRequest) throws PersistenceException;
}
