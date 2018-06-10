/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rest;

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
import domain.ReservationRequestReply;
import service.ReservationRequestReplyService;

/**
 *
 * @author M
 */
@Stateless
@Path("reservationvaluepairs")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class ReservationRequestReplyRest implements Serializable {

    @Inject
    ReservationRequestReplyService reservationValuePairService;

    @GET
    public List<ReservationRequestReply> getReservationValuePairs() throws PersistenceException {
        return reservationValuePairService.getReservationValuePairs();
    }

    @GET
    @Path("{id}")
    public ReservationRequestReply findReservationValuePair(@PathParam("id") Integer id) throws PersistenceException {
        return reservationValuePairService.findReservationValuePair(id);
    }

    @POST
    public Boolean insertReservationValuePair(ReservationRequestReply reservationValuePair) throws PersistenceException {
        return reservationValuePairService.insertReservationValuePair(reservationValuePair);
    }

    @PUT
    public Boolean updateReservationValuePair(ReservationRequestReply reservationValuePair) throws PersistenceException {
        return reservationValuePairService.updateReservationValuePair(reservationValuePair);
    }

    @DELETE
    public Boolean removeReservationValuePair(ReservationRequestReply reservationValuePair) throws PersistenceException {
        return reservationValuePairService.removeReservationValuePair(reservationValuePair);
    }
}
