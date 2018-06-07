/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import java.util.List;
import javax.persistence.PersistenceException;
import messaging.ReservationRequestReply;

/**
 *
 * @author M
 */
public interface ReservationRequestReplyDAO {

    List<ReservationRequestReply> getReservationValuePairs() throws PersistenceException;

    ReservationRequestReply findReservationValuePair(Integer id) throws PersistenceException;

    Boolean insertReservationValuePair(ReservationRequestReply reservationValuePair) throws PersistenceException;

    Boolean updateReservationValuePair(ReservationRequestReply reservationValuePair) throws PersistenceException;

    Boolean removeReservationValuePair(ReservationRequestReply reservationValuePair) throws PersistenceException;
}
