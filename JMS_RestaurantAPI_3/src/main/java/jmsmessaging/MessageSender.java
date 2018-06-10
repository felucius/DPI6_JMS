package jmsmessaging;

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

/*
This class sends a message to the Broker application that is needed for
the Client application.
 */
public class MessageSender {

    Connection connection = null;
    Session session = null;
    Destination destination = null;
    MessageProducer producer = null;

    public MessageSender() {
        try {
            /*
            Creating properties to connect with ActiveMQ and with correct URL.
             */
            Properties properties = new Properties();
            properties.setProperty(Context.INITIAL_CONTEXT_FACTORY, "org.apache.activemq.jndi.ActiveMQInitialContextFactory");
            properties.setProperty(Context.PROVIDER_URL, "tcp://localhost:61616");

            /*
            Sends message to the queue BankToBroker.
             */
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
            Setting the destination to send the message. In our case the Broker
            or BankToBRoker.
             */
            destination = (Destination) jndiContext.lookup("FromRestaurantToBrokerAPI3");
            producer = session.createProducer(destination);

        } catch (NamingException | JMSException ex) {
            ex.printStackTrace();
        }
    }

    /**
     * This method sends the message to ActiveMQ with a reply from the bank and
     * a correlationID of the message.
     *
     * @param reply is the reply from the bank.
     * @param correlationId is the id that matches the request and reply.
     */
    public void sendRestaurantReply(ReservationReply reply) {//, String correlationId) {
        try {
            Message message = session.createTextMessage(new Gson().toJson(reply));
            //message.setJMSCorrelationID(correlationId);
            producer.send(message);

            //session.close();
            //connection.close();
        } catch (JMSException ex) {
            ex.printStackTrace();
        }
    }

    public void sendRestaurant(Restaurant restaurant) {
        try {
            Message message = session.createTextMessage(new Gson().toJson(restaurant));
            producer.send(message);

            //session.close();
            //connection.close();
        } catch (JMSException ex) {
            ex.printStackTrace();
        }
    }

    public void sendReservationValuePairToBroker(ReservationValuePair reservationValuePair) {
        try {
            Message message = session.createTextMessage(new Gson().toJson(reservationValuePair));
            producer.send(message);

            //session.close();
            //connection.close();
        } catch (JMSException ex) {
            ex.printStackTrace();
        }
    }
}
