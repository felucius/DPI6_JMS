/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package service;

import domain.Restaurant;
import javax.annotation.PostConstruct;
import javax.ejb.Singleton;
import javax.ejb.Startup;
import javax.inject.Inject;
import messaging.MessageReceiver;

/**
 *
 * @author M
 */
@Singleton
@Startup
public class init {

    @Inject
    RestaurantService restaurantService;

    @Inject
    ReservationRequestService reservationRequestService;

    @Inject
    ReservationReplyService reservationReplyService;

    @PostConstruct
    public void init() {
        //Restaurant restaurant = new Restaurant("Alesandro Pizza & Pasta", "Geldrop", 50);
        //ReservationRequest reservationRequest = new ReservationRequest("Maxime", 11, 2);
        //ReservationReply reservationReply = new ReservationReply("yes", 11);

        //restaurantService.insertRestaurant(restaurant);
        //reservationRequestService.insertReservation(reservationRequest);
        //reservationReplyService.insertReservationReply(reservationReply);

        connectToBroker();
    }

    public void connectToBroker() {
        MessageReceiver receiver = new MessageReceiver();
    }
}
