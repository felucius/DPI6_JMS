/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package messaging;

import com.google.gson.Gson;
import domain.Restaurant;
import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import javax.jms.Message;
import javax.jms.MessageListener;
import javax.jms.TextMessage;
import jms.MessageSenderGateway;
import domain.ReservationReply;

/**
 *
 * @author M
 */
public class RestaurantMessageListener implements MessageListener {

    private List<Restaurant> restaurants;

    public RestaurantMessageListener() {
        restaurants = new ArrayList();
    }

    @Override
    public void onMessage(Message msg) {
        try {

            TextMessage message = (TextMessage) msg;
            Restaurant restaurantInfo = new Gson().fromJson(message.getText(), Restaurant.class);
            //ReservationReply reservationReply = new Gson().fromJson(message.getText(), ReservationReply.class);

            ReservationValuePair reservationValuePair = new Gson().fromJson(message.getText(), ReservationValuePair.class);
            MessageSenderGateway messageSenderGateway = new MessageSenderGateway("FromBrokerToReservationClient");


            if (reservationValuePair.getReply() != null) {

                //addReservationReply(reply);
                addReservationValuePairToDB(reservationValuePair);

                messageSenderGateway.sendToClient(reservationValuePair);//, message.getJMSCorrelationID());
                System.out.println("Broker: Reply from restaurant: " + reservationValuePair.getReply().getAnswer()
                        + " time:" + reservationValuePair.getReply().getTime());
            } else {
                Restaurant restaurant = new Restaurant(restaurantInfo.getName(),
                        restaurantInfo.getLocation(),
                        restaurantInfo.getAmountOfSeats());

                if (restaurants.size() < 1) {
                    restaurants.add(restaurant);
                    messageSenderGateway.sendRestaurantToClient(restaurant);
                    addRestaurant(restaurant);

                    System.out.println("Broker: Restaurant info received: " + restaurant.getName()
                            + " location: " + restaurant.getLocation()
                            + " seats: " + restaurant.getAmountOfSeats());
                    System.out.println("Broker: Sending restaurant info to connected client application.");
                } else {
                    System.out.println("Broker: Restaurant already exists " + restaurant.getName());
                }
            }

            //BrokerToClient sender = new BrokerToClient(frame);
            //LoanReply reply = new LoanReply(bankInterestReply.getInterest(), bankInterestReply.getQuoteId());
            //sender.sendToClient(reply, message.getJMSCorrelationID());
            //frame.add(request, bankInterestReply);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    public void addRestaurant(Restaurant restaurant) {
        try {

            String url = "http://localhost:22603/JMS_Broker/api/restaurants";
            URL obj = new URL(url);
            HttpURLConnection con = (HttpURLConnection) obj.openConnection();

            //add reuqest header
            con.setRequestMethod("POST");
            con.setRequestProperty("Content-type", "application/json");

            String urlParameters = "{\"name\":\"" + restaurant.getName()
                    + "\",\"location\":\"" + restaurant.getLocation() + ""
                    + "\",\"amountOfSeats\":\"" + restaurant.getAmountOfSeats() + "\"}";

            // Send post request
            con.setDoOutput(true);
            DataOutputStream wr = new DataOutputStream(con.getOutputStream());
            wr.writeBytes(urlParameters);
            wr.flush();
            wr.close();

            int responseCode = con.getResponseCode();
            //System.out.println("\nSending 'POST' request to URL : " + url);
            //System.out.println("Post parameters : " + urlParameters);
            //System.out.println("Response Code : " + responseCode);
            System.out.println("Broker: Adding RESTAURANT to DB");

            BufferedReader in = new BufferedReader(
                    new InputStreamReader(con.getInputStream()));
            String inputLine;
            StringBuffer response = new StringBuffer();

            while ((inputLine = in.readLine()) != null) {
                response.append(inputLine);
            }
            in.close();

            //print result
            //System.out.println(response.toString());
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
    }

    public void addReservationReply(ReservationReply reply) {
        try {

            String url = "http://localhost:22603/JMS_Broker/api/reservationreplies";
            URL obj = new URL(url);
            HttpURLConnection con = (HttpURLConnection) obj.openConnection();

            //add reuqest header
            con.setRequestMethod("POST");
            con.setRequestProperty("Content-type", "application/json");

            String urlParameters = "{\"answer\":\"" + reply.getAnswer() + ""
                    + "\",\"time\":\"" + reply.getTime() + "\"}";

            // Send post request
            con.setDoOutput(true);
            DataOutputStream wr = new DataOutputStream(con.getOutputStream());
            wr.writeBytes(urlParameters);
            wr.flush();
            wr.close();

            int responseCode = con.getResponseCode();
            //System.out.println("\nSending 'POST' request to URL : " + url);
            //System.out.println("Post parameters : " + urlParameters);
            //System.out.println("Response Code : " + responseCode);
            System.out.println("Broker: Adding RESERVATION_REPLY to DB");

            BufferedReader in = new BufferedReader(
                    new InputStreamReader(con.getInputStream()));
            String inputLine;
            StringBuffer response = new StringBuffer();

            while ((inputLine = in.readLine()) != null) {
                response.append(inputLine);
            }
            in.close();

            //print result
            //System.out.println(response.toString());
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
    }

    public void addReservationValuePairToDB(ReservationValuePair reservationValuePair) {
        try {

            String url = "http://localhost:22603/JMS_Broker/api/reservationvaluepairs";
            URL obj = new URL(url);
            HttpURLConnection con = (HttpURLConnection) obj.openConnection();

            //add reuqest header
            con.setRequestMethod("POST");
            con.setRequestProperty("Content-type", "application/json");

            String urlParameters = "{\"amountOfSeats\":\"" + reservationValuePair.getRequest().getAmountOfSeats()
                    + "\",\"name\":\"" + reservationValuePair.getRequest().getName() + ""
                    + "\",\"restaurantName\":\"" + reservationValuePair.getRequest().getRestaurantName() + ""
                    + "\",\"time\":\"" + reservationValuePair.getRequest().getTime() + ""
                    + "\",\"answer\":\"" + reservationValuePair.getReply().getAnswer() + ""
                    + "\",\"replyTime\":\"" + reservationValuePair.getReply().getTime() + "\"}";
            
            /*
            String urlParameters = "{\"answer\":\"" + reservationValuePair.getRequest()+ ""
                    + "\",\"time\":\"" + reservationValuePair.getReply()+ "\"}";
            */

            // Send post request
            con.setDoOutput(true);
            DataOutputStream wr = new DataOutputStream(con.getOutputStream());
            wr.writeBytes(urlParameters);
            wr.flush();
            wr.close();

            int responseCode = con.getResponseCode();
            //System.out.println("\nSending 'POST' request to URL : " + url);
            //System.out.println("Post parameters : " + urlParameters);
            //System.out.println("Response Code : " + responseCode);
            System.out.println("Broker: Adding RESERVATION_REPLY to DB");

            BufferedReader in = new BufferedReader(
                    new InputStreamReader(con.getInputStream()));
            String inputLine;
            StringBuffer response = new StringBuffer();

            while ((inputLine = in.readLine()) != null) {
                response.append(inputLine);
            }
            in.close();

            //print result
            //System.out.println(response.toString());
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
    }
}
