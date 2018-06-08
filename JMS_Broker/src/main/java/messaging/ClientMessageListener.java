package messaging;

import com.google.gson.Gson;
import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import javax.jms.Message;
import javax.jms.MessageListener;
import javax.jms.TextMessage;
import jms.MessageSenderGateway;
import domain.ReservationRequest;

/*
This class listens to the messages received from the client application.
 */
public class ClientMessageListener implements MessageListener {

    private ReservationRequestReply reservationValuePair;

    public ClientMessageListener() {

    }

    @Override
    public void onMessage(Message msg) {
        try {

            TextMessage message = (TextMessage) msg;
            ReservationRequest reservationRequest = new Gson().fromJson(message.getText(), ReservationRequest.class);
            //frame.add(restaurantRequest);

            MessageSenderGateway messageSenderGatewayAPI1 = new MessageSenderGateway("FromBrokerToRestaurantAPI1");
            MessageSenderGateway messageSenderGatewayAPI2 = new MessageSenderGateway("FromBrokerToRestaurantAPI2");
            MessageSenderGateway messageSenderGatewayAPI3 = new MessageSenderGateway("FromBrokerToRestaurantAPI3");

            if (reservationRequest.getRestaurantName().equals("Alesandro Pizza & Pasta")) {
                messageSenderGatewayAPI1.sendRequestToRestaurant(reservationRequest);
                addReservationRequest(reservationRequest);
            } else if (reservationRequest.getRestaurantName().equals("Tokyo Sushi and Wok")) {
                messageSenderGatewayAPI2.sendRequestToRestaurant(reservationRequest);
                addReservationRequest(reservationRequest);
            } else if (reservationRequest.getRestaurantName().equals("Greek specialities")) {
                messageSenderGatewayAPI3.sendRequestToRestaurant(reservationRequest);
                addReservationRequest(reservationRequest);
            }

            System.out.println("Broker: Client request received: " + reservationRequest.getName()
                    + " amount of seats: " + reservationRequest.getAmountOfSeats()
                    + " time: " + reservationRequest.getTime()
                    + " restaurantName: " + reservationRequest.getRestaurantName());
            System.out.println("Broker: Sending client REQUEST to connected restaurant application.");

        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    public void addReservationRequest(ReservationRequest request) {
        try {

            String url = "http://localhost:22603/JMS_Broker/api/reservationrequests";
            URL obj = new URL(url);
            HttpURLConnection con = (HttpURLConnection) obj.openConnection();

            //add request header
            con.setRequestMethod("POST");
            con.setRequestProperty("Content-type", "application/json");

            String urlParameters = "{\"name\":\"" + request.getName()
                    + "\",\"time\":\"" + request.getTime() + ""
                    + "\",\"amountOfSeats\":\"" + request.getAmountOfSeats() + ""
                    + "\",\"restaurantName\":\"" + request.getRestaurantName() + "\"}";

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
            System.out.println("Broker: Adding reservation REQUEST to DB");

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
