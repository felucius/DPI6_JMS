package jmsmessaging;

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

    private MessageSender sender;
    
    public BrokerMessageListener() {
        sender = new MessageSender();
    }

    @Override
    public void onMessage(Message msg) {
        try {
            System.out.println("Received a reply from a user");
            TextMessage message = (TextMessage) msg;
            ReservationRequest request = new Gson().fromJson(message.getText(), ReservationRequest.class);

            System.out.println("Restaurant API 3: Restaurant received: " + request.getName()
                    + " - Amount of seats: " + request.getAmountOfSeats()
                    + " - Time:" + request.getTime());

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

            String url = "http://localhost:47405/JMS_RestaurantAPI_3/api/reservationreplies";
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
            System.out.println("Restaurant API 3: Adding RESERVATION_REPLY to DB");

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
            String url = "http://localhost:47405/JMS_RestaurantAPI_3/api/reservationreplies/reply";

            URL obj = new URL(url);
            HttpURLConnection con = (HttpURLConnection) obj.openConnection();

            // optional default is GET
            con.setRequestMethod("GET");

            //add request header
            con.setRequestProperty("User-Agent", "Chrome");

            int responseCode = con.getResponseCode();
            //System.out.println("\nSending 'GET' request to URL : " + url);
            //System.out.println("Response Code : " + responseCode);

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
                //System.out.println("response: " + responses.length);
            }
            in.close();

            //print result
            System.out.println("Restaurant API 3: Answer: " + response.toString());

            return reply;

        } catch (Exception ex) {
            ex.getMessage();
            return null;
        }
    }

    public void sendReplyToBroker(ReservationReply reply) {
        sender.sendRestaurantReply(reply);
    }

    public void sendReservationValuePairToBroker(ReservationValuePair reservationValuePair) {
        sender.sendReservationValuePairToBroker(reservationValuePair);
    }
}
