/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import domain.ReservationReply;
import java.util.List;
import javax.persistence.PersistenceException;

/**
 *
 * @author M
 */
public interface ReservationReplyDAO {

    List<ReservationReply> getReservationReplies() throws PersistenceException;

    ReservationReply getReservationReply(Integer id) throws PersistenceException;

    Boolean insertReservationReply(ReservationReply reservationReply) throws PersistenceException;

    Boolean updateReservationReply(ReservationReply reservationReply) throws PersistenceException;

    Boolean removeReservationReply(ReservationReply reservationReply) throws PersistenceException;
}
