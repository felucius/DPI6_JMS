package jmsmessaging;

import domain.ReservationValuePair;
import com.google.gson.Gson;
import domain.ReservationReply;
import domain.ReservationRequest;
import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageListener;
import javax.jms.TextMessage;

public class BrokerMessageListener implements MessageListener {
    
    private MessageSender messageSender;
    
    public BrokerMessageListener() {
        this.messageSender = new MessageSender();
    }

    @Override
    public void onMessage(Message msg) {
        try {
            System.out.println("Received a reply from a user");
            TextMessage message = (TextMessage) msg;
            ReservationRequest request = new Gson().fromJson(message.getText(), ReservationRequest.class);

            System.out.println("-------------------------------------------");
            System.out.println("Restaurant API 1: Restaurant received: " + request.getName()
                    + " - Amount of seats: " + request.getAmountOfSeats()
                    + " - Time:" + request.getTime());
            System.out.println("-------------------------------------------");

            ReservationReply reply = getReservationReply();
            addReservationReply(reply);

            ReservationValuePair reservationValuePair = new ReservationValuePair(request, reply);
            
            //sendReplyToBroker(reply);
            sendReservationValuePairToBroker(reservationValuePair);
        } catch (JMSException ex) {
            ex.printStackTrace();
        }
    }

    public void addReservationReply(ReservationReply reply) {
        try {

            String url = "http://localhost:29714/JMS_RestaurantAPI_1/api/reservationreplies";
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

            System.out.println("-------------------------------------------");
            System.out.println("Restaurant API 1: Adding RESERVATION_REPLY to DB");
            System.out.println("-------------------------------------------");

            BufferedReader in = new BufferedReader(
                    new InputStreamReader(con.getInputStream()));
            String inputLine;
            StringBuffer response = new StringBuffer();

            while ((inputLine = in.readLine()) != null) {
                response.append(inputLine);
            }
            in.close();

            //print result
            System.out.println(response.toString());
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
    }

    public ReservationReply getReservationReply() {
        try {
            String url = "http://localhost:29714/JMS_RestaurantAPI_1/api/reservationreplies/reply";

            URL obj = new URL(url);
            HttpURLConnection con = (HttpURLConnection) obj.openConnection();

            // optional default is GET
            con.setRequestMethod("GET");

            //add request header
            con.setRequestProperty("User-Agent", "Chrome");

            BufferedReader in = new BufferedReader(
                    new InputStreamReader(con.getInputStream()));
            String inputLine;
            StringBuffer response = new StringBuffer();

            String[] responses;
            ReservationReply reply = null;
            while ((inputLine = in.readLine()) != null) {
                responses = inputLine.split(":");
                String answer = responses[1].substring(1, 3);
                String time = responses[2].substring(0, 2);

                reply = new ReservationReply(answer, Integer.valueOf(time));
                response.append(inputLine);
            }
            in.close();

            //print result
            System.out.println("-------------------------------------------");
            System.out.println("Restaurant API 1: Answer: " + response.toString());
            System.out.println("-------------------------------------------");

            return reply;

        } catch (Exception ex) {
            ex.getMessage();
            return null;
        }
    }

    public void sendReplyToBroker(ReservationReply reply) {
        messageSender.sendRestaurantReply(reply);
    }

    public void sendReservationValuePairToBroker(ReservationValuePair reservationValuePair) {
        messageSender.sendReservationValuePairToBroker(reservationValuePair);
    }
}
