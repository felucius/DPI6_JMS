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

/**
 *
 * @author M
 */
@Stateless
public class ReservationReplyDAOImpl implements ReservationReplyDAO {

    @PersistenceContext(name = "JMS_Broker_PU")
    EntityManager em;
    
    @Override
    public List<ReservationReply> getReservationReplies() throws PersistenceException {
        return em.createNamedQuery("ReservationReply.getReservationReplies").getResultList();
    }

    @Override
    public ReservationReply getReservationReply(Integer id) throws PersistenceException {
        return (ReservationReply) em.createNamedQuery("ReservationReply.findReservationReply").setParameter("id", id).getSingleResult();
    }

    @Override
    public Boolean insertReservationReply(ReservationReply reservationReply) throws PersistenceException {
        em.persist(reservationReply);
        return true;
    }

    @Override
    public Boolean updateReservationReply(ReservationReply reservationReply) throws PersistenceException {
        em.merge(reservationReply);
        return true;
    }

    @Override
    public Boolean removeReservationReply(ReservationReply reservationReply) throws PersistenceException {
        em.remove(reservationReply);
        return true;
    }
    
}
