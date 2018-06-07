/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rest;

import domain.ReservationReply;
import java.io.Serializable;
import java.util.List;
import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.persistence.PersistenceException;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import service.ReservationReplyService;

/**
 *
 * @author M
 */
@Stateless
@Path("reservationreplies")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class ReservationReplyRest implements Serializable {

    @Inject
    ReservationReplyService reservationReplyService;

    @GET
    public List<ReservationReply> getReservationReplies() throws PersistenceException {
        return reservationReplyService.getReservationReplies();
    }

    @GET
    @Path("reply")
    public ReservationReply getReservationReply(/*@PathParam("id") Integer id*/) throws PersistenceException {
        return reservationReplyService.getReservationReply();
    }

    @POST
    public Boolean insertReservationReply(ReservationReply reservationReply) throws PersistenceException {
        return reservationReplyService.insertReservationReply(reservationReply);
    }

    @PUT
    public Boolean updateReservationReply(ReservationReply reservationReply) throws PersistenceException {
        return reservationReplyService.updateReservationReply(reservationReply);
    }

    @DELETE
    public Boolean removeReservationReply(ReservationReply reservationReply) throws PersistenceException {
        return reservationReplyService.removeReservationReply(reservationReply);
    }
}
