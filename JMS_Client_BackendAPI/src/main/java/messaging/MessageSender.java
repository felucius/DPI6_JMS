package messaging;

import com.google.gson.Gson;
import java.util.HashMap;
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
import domain.ReservationRequest;

public class MessageSender {

    private Connection connection = null;
    private Session session = null;
    private Destination destination = null;
    private MessageProducer producer = null;
    
    private HashMap<String, ReservationRequest> correlations = new HashMap();

    public MessageSender(){
        this.correlations = correlations;
        try {
            Properties properties = new Properties();
            properties.setProperty(Context.INITIAL_CONTEXT_FACTORY, "org.apache.activemq.jndi.ActiveMQInitialContextFactory");
            properties.setProperty(Context.PROVIDER_URL, "tcp://localhost:61616");

            properties.put(("queue.FromReservationClientToBroker"), "FromReservationClientToBroker");

            /*
            Creating a connection factory.
            */
            Context jndiContext = new InitialContext(properties);
            ConnectionFactory connectionFactory = (ConnectionFactory) jndiContext.lookup("ConnectionFactory");;

            /*
            Connect with the JNDI connection factory.
            */
            connection = connectionFactory.createConnection();
            session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);

            /*
            Setting the destination to send the message. In our case the Broker
            */
            destination = (Destination) jndiContext.lookup("FromReservationClientToBroker");
            producer = session.createProducer(destination);

        } catch (NamingException | JMSException ex) {
            ex.printStackTrace();
        }
    }

    public void sendReservationRequest(ReservationRequest reservationRequest) {
        try {
            Message message = session.createTextMessage(new Gson().toJson(reservationRequest));
            producer.send(message);
            correlations.put(message.getJMSMessageID(), reservationRequest);

            session.close();
            connection.close();
        } catch (JMSException ex) {
            ex.printStackTrace();
        }
    }
}
