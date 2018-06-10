/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import domain.ReservationReply;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.PersistenceException;
import domain.ReservationRequestReply;

/**
 *
 * @author M
 */
@Stateless
public class ReservationRequestReplyImpl implements ReservationRequestReplyDAO {

    @PersistenceContext(name = "RestaurantAPI")
    EntityManager em;

    @Override
    public List<ReservationRequestReply> getReservationValuePairs() throws PersistenceException {
        return em.createNamedQuery("Reservation.getReservationValuePair").getResultList();
    }

    @Override
    public ReservationRequestReply findReservationValuePair(Integer id) throws PersistenceException {
        return (ReservationRequestReply) em.createNamedQuery("Reservation.findReservationValuePair").setParameter("id", id).getSingleResult();
    }

    @Override
    public Boolean insertReservationValuePair(ReservationRequestReply reservationValuePair) throws PersistenceException {
        em.persist(reservationValuePair);
        return true;
    }

    @Override
    public Boolean updateReservationValuePair(ReservationRequestReply reservationValuePair) throws PersistenceException {
        em.merge(reservationValuePair);
        return true;
    }

    @Override
    public Boolean removeReservationValuePair(ReservationRequestReply reservationValuePair) throws PersistenceException {
        em.remove(reservationValuePair);
        return true;
    }

}
