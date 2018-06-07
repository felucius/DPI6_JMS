/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rest;

import domain.ReservationRequest;
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
import service.ReservationRequestService;

/**
 *
 * @author M
 */
@Stateless
@Path("reservationrequests")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class ReservationRequestRest implements Serializable {

    @Inject
    ReservationRequestService reservationRequestService;

    @GET
    public List<ReservationRequest> getReservations() throws PersistenceException {
        return reservationRequestService.getReservations();
    }

    @GET
    @Path("{id}")
    public ReservationRequest getReservation(@PathParam("id") Integer id) throws PersistenceException {
        return reservationRequestService.getReservation(id);
    }

    @POST
    @Path("reservation")
    public Boolean insertReservation(ReservationRequest reservationRequest) throws PersistenceException {
        return reservationRequestService.insertReservation(reservationRequest);
    }

    @PUT
    public Boolean updateReservation(ReservationRequest reservationRequest) throws PersistenceException {
        return reservationRequestService.updateReservation(reservationRequest);
    }

    @DELETE
    public Boolean removeReservation(ReservationRequest reservationRequest) throws PersistenceException {
        return reservationRequestService.removeReservation(reservationRequest);
    }
}
