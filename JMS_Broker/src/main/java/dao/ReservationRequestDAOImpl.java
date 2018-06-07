/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import domain.ReservationRequest;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.PersistenceException;

/**
 *
 * @author M
 */
@Stateless
public class ReservationRequestDAOImpl implements ReservationRequestDAO {

    @PersistenceContext(name = "RestaurantAPI")
    EntityManager em;

    @Override
    public List<ReservationRequest> getReservations() throws PersistenceException {
        return em.createNamedQuery("Reservation.getReservations").getResultList();
    }

    @Override
    public ReservationRequest getReservation(Integer id) throws PersistenceException {
        return (ReservationRequest) em.createNamedQuery("Reservation.findReservation").setParameter("id", id).getSingleResult();
    }

    @Override
    public Boolean insertReservation(ReservationRequest reservationRequest) throws PersistenceException {
        em.persist(reservationRequest);
        return true;
    }

    @Override
    public Boolean updateReservation(ReservationRequest reservationRequest) throws PersistenceException {
        em.merge(reservationRequest);
        return true;
    }

    @Override
    public Boolean removeReservation(ReservationRequest reservationRequest) throws PersistenceException {
        em.remove(reservationRequest);
        return true;
    }

}
