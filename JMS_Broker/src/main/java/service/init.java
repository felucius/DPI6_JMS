/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package service;

import javax.annotation.PostConstruct;
import javax.ejb.Singleton;
import javax.ejb.Startup;
import javax.inject.Inject;
import messaging.ClientMessageListener;
import messaging.RestaurantMessageListener;
import gateway.MessageReceiverGateway;
import monitoring.Monitor;

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
        connectToRestaurantAPI1();
        connectToRestaurantAPI2();
        connectToRestaurantAPI3();
        connectToClient();
        connectToMonitor();
    }

    public void connectToRestaurantAPI1() {
        MessageReceiverGateway restaurantToBrokerReceiver = new MessageReceiverGateway("FromRestaurantToBrokerAPI1");
        restaurantToBrokerReceiver.setListener(new RestaurantMessageListener());
    }

    public void connectToRestaurantAPI2() {
        MessageReceiverGateway restaurantToBrokerReceiver = new MessageReceiverGateway("FromRestaurantToBrokerAPI2");
        restaurantToBrokerReceiver.setListener(new RestaurantMessageListener());
    }

    public void connectToRestaurantAPI3() {
        MessageReceiverGateway restaurantToBrokerReceiver = new MessageReceiverGateway("FromRestaurantToBrokerAPI3");
        restaurantToBrokerReceiver.setListener(new RestaurantMessageListener());
    }

    public void connectToClient() {
        MessageReceiverGateway clientToBrokerReceiver = new MessageReceiverGateway("FromReservationClientToBroker");
        clientToBrokerReceiver.setListener(new ClientMessageListener());
    }
    
    public void connectToMonitor() {
        Monitor monitorParser = new Monitor();
    }
}
