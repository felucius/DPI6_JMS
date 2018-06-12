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
import jmsmessaging.BrokerMessageListener;
import jmsmessaging.MessageReceiverGateway;
import jmsmessaging.MessageSender;

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
        Restaurant restaurant = new Restaurant("Greek specialities", "Weert", 45);

        restaurantService.insertRestaurant(restaurant);

        sendRestaurantInformation();
        connectToBroker();
    }

    public void sendRestaurantInformation() {
        Restaurant restaurant = new Restaurant("Greek specialities", "Weert", 45);

        MessageSender sender = new MessageSender();
        sender.sendRestaurant(restaurant);
    }

    public void connectToBroker() {
        MessageReceiverGateway brokerToRestaurantReceiver = new MessageReceiverGateway("FromBrokerToRestaurantAPI3");
        brokerToRestaurantReceiver.setListener(new BrokerMessageListener());
    }
}
