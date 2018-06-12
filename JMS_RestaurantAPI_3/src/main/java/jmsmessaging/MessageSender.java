package jmsmessaging;

import domain.ReservationValuePair;
import com.google.gson.Gson;
import domain.ReservationReply;
import domain.Restaurant;
import java.util.Properties;
import javax.jms.Connection;
import javax.jms.ConnectionFactory;
import javax.jms.Destination;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageProducer;
import javax.jms.Session;
import javax.naming.Context;
import javax.naming.InitialContext;

import javax.naming.NamingException;

public class MessageSender {

    Connection connection = null;
    Session session = null;
    Destination destination = null;
    MessageProducer producer = null;

    public MessageSender() {
        try {
            Properties properties = new Properties();
            properties.setProperty(Context.INITIAL_CONTEXT_FACTORY, "org.apache.activemq.jndi.ActiveMQInitialContextFactory");
            properties.setProperty(Context.PROVIDER_URL, "tcp://localhost:61616");

            properties.put(("queue.FromRestaurantToBrokerAPI3"), "FromRestaurantToBrokerAPI3");

            /*
            Creating a connection factory.
             */
            Context jndiContext = new InitialContext(properties);
            ConnectionFactory connectionFactory = (ConnectionFactory) jndiContext.lookup("ConnectionFactory");;

            /*
            Connect with the created JNDI connection factory.
             */
            connection = connectionFactory.createConnection();
            session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);

            /*
            Setting the destination to send the message.
             */
            destination = (Destination) jndiContext.lookup("FromRestaurantToBrokerAPI3");
            producer = session.createProducer(destination);

        } catch (NamingException | JMSException ex) {
            ex.printStackTrace();
        }
    }
    public void sendRestaurantReply(ReservationReply reply) {//, String correlationId) {
        try {
            Message message = session.createTextMessage(new Gson().toJson(reply));
            producer.send(message);
        } catch (JMSException ex) {
            ex.printStackTrace();
        }
    }

    public void sendRestaurant(Restaurant restaurant) {
        try {
            Message message = session.createTextMessage(new Gson().toJson(restaurant));
            producer.send(message);
        } catch (JMSException ex) {
            ex.printStackTrace();
        }
    }

    public void sendReservationValuePairToBroker(ReservationValuePair reservationValuePair) {
        try {
            Message message = session.createTextMessage(new Gson().toJson(reservationValuePair));
            producer.send(message);
        } catch (JMSException ex) {
            ex.printStackTrace();
        }
    }
}
