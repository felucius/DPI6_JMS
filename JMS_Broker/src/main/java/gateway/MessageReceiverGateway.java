/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gateway;

import java.util.Properties;
import javax.jms.Connection;
import javax.jms.ConnectionFactory;
import javax.jms.Destination;
import javax.jms.JMSException;
import javax.jms.MessageConsumer;
import javax.jms.MessageListener;
import javax.jms.Session;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;

/**
 *
 * @author M
 */
public class MessageReceiverGateway {

    Connection connection = null;
    Session session = null;
    Destination destination = null;
    MessageConsumer consumer = null;

    public MessageReceiverGateway(String channelName) {
        try {
            Properties properties = new Properties();
            properties.setProperty(Context.INITIAL_CONTEXT_FACTORY, "org.apache.activemq.jndi.ActiveMQInitialContextFactory");
            properties.setProperty(Context.PROVIDER_URL, "tcp://localhost:61616");

            properties.put(("queue." + channelName), channelName);

            /*
            Looking up the existing JNDI context to create a connection later on.
             */
            Context jndiContext = new InitialContext(properties);
            ConnectionFactory connectionFactory = (ConnectionFactory) jndiContext.lookup("ConnectionFactory");;

            /*
            Creating a connection with a destination to listen to.
             */
            connection = connectionFactory.createConnection();
            session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);

            destination = (Destination) jndiContext.lookup(channelName);

            /*
            Consuming the messages from the given destination (Broker)
             */
            consumer = session.createConsumer(destination);

            connection.start();
        } catch (NamingException | JMSException ex) {
            ex.printStackTrace();
        }
    }

    public void setListener(MessageListener ml) {
        try {
            consumer.setMessageListener(ml);
        } catch (Exception ex) {
            ex.printStackTrace();
        }

    }
}
