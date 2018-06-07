package messaging;

import com.google.gson.Gson;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageListener;
import javax.jms.TextMessage;
import domain.ReservationReply;
//import service.StoreReservationReplyDB;
import domain.Restaurant;
import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class BrokerMessageListener implements MessageListener {

    public BrokerMessageListener() {
    }

    @Override
    public void onMessage(Message msg) {
        try {
            System.out.println("Client: Received a reply from a restaurant");
            TextMessage message = (TextMessage) msg;
            //ReservationReply reply = new Gson().fromJson(message.getText(), ReservationReply.class);
            Restaurant restaurant = new Gson().fromJson(message.getText(), Restaurant.class);
            //ReservationReply reply = new Gson().fromJson(message.getText(), ReservationReply.class);

            ReservationValuePair reservationValuePair = new Gson().fromJson(message.getText(), ReservationValuePair.class);

            if (reservationValuePair.getReply() != null) {
                //addReservationReply(reply);
                addReservationValuePairToDB(reservationValuePair);
                System.out.println("Reply from restaurant: " + reservationValuePair.getReply().getAnswer()
                        + " time: " + reservationValuePair.getReply().getTime());
            } else {
                addRestaurant(restaurant);
                System.out.println("Client: Restaurant received: " + restaurant.getName()
                        + " - location: " + restaurant.getLocation()
                        + " - Amount of seats:" + restaurant.getAmountOfSeats());
            }
        } catch (JMSException ex) {
            ex.printStackTrace();
        }
    }

    public void addRestaurant(Restaurant restaurant) {
        try {

            String url = "http://localhost:25239/JMS_Client_BackendAPI/api/restaurants";
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
            System.out.println("Client: Adding restaurant to DB");

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

            String url = "http://localhost:25239/JMS_Client_BackendAPI/api/reservationreplies";
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
            System.out.println("Client: Adding reservation reply to DB");

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

            String url = "http://localhost:25239/JMS_Client_BackendAPI/api/reservationvaluepairs";
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
