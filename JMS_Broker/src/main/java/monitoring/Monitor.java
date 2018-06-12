/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package monitoring;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;
import domain.ReservationReply;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import javax.jms.Message;
import javax.jms.MessageListener;

/**
 *
 * @author M
 */
public class Monitor implements MessageListener {

    private ObjectMapper objectMapper = new XmlMapper();

    public Monitor() {
        parseXMLqueues();
    }

    @Override
    public void onMessage(Message msg) {
        parseXMLqueues();
    }

    public void parseXMLqueues() {
        try {
            URL url = new URL("http://admin:admin@localhost:8161/admin/xml/queues.jsp");

            String data = getTestData();
            Queues xmlQueues = objectMapper.readValue(data, Queues.class);
            System.out.println("MONITORING_ACTIVEMQ_BROKER: " + xmlQueues);
            System.out.println("AMOUNT_OF_QUEUES: " + xmlQueues.getQueue().length);

            Queue[] queue = xmlQueues.getQueue();
            List<Stats> stats = new ArrayList();
            Stats stat = null;
            for (int i = 0; i < queue.length; i++) {
                System.out.println("---------------------------------------------------------");
                System.out.println("QUEUE_INFORMATION: -------->");
                System.out.println("QUEUE_NAME: " + queue[i].getName());
                System.out.println("QUEUE_SIZE: " + queue[i].getStats().getSize());
                System.out.println("QUEUE_CONSUME_COUNT: " + queue[i].getStats().getConsumerCount());
                System.out.println("QUEUE_DEQUEUE_COUNT: " + queue[i].getStats().getDequeueCount());
                System.out.println("QUEUE_ENQUEUE_COUNT: " + queue[i].getStats().getEnqueueCount());
                
                System.out.println("QUEUE_FEED: -------->");
                System.out.println("QUEUE_ATOM: " + queue[i].getFeed().getAtom());
                System.out.println("QUEUE_RSS: " + queue[i].getFeed().getRss());
            }
        } catch (IOException ioe) {
            ioe.getMessage();
        }
    }

    public String getTestData() {
        String xmldata = "<queues>\n"
                + "<queue name=\"FromRestaurantToBrokerAPI3\">\n"
                + "<stats size=\"0\" consumerCount=\"3\" enqueueCount=\"0\" dequeueCount=\"0\"/>\n"
                + "<feed>\n"
                + "<atom>\n"
                + "queueBrowse/FromRestaurantToBrokerAPI3?view=rss&amp;feedType=atom_1.0\n"
                + "</atom>\n"
                + "<rss>\n"
                + "queueBrowse/FromRestaurantToBrokerAPI3?view=rss&amp;feedType=rss_2.0\n"
                + "</rss>\n"
                + "</feed>\n"
                + "</queue>\n"
                + "<queue name=\"FromRestaurantToBrokerAPI2\">\n"
                + "<stats size=\"0\" consumerCount=\"3\" enqueueCount=\"0\" dequeueCount=\"0\"/>\n"
                + "<feed>\n"
                + "<atom>\n"
                + "queueBrowse/FromRestaurantToBrokerAPI2?view=rss&amp;feedType=atom_1.0\n"
                + "</atom>\n"
                + "<rss>\n"
                + "queueBrowse/FromRestaurantToBrokerAPI2?view=rss&amp;feedType=rss_2.0\n"
                + "</rss>\n"
                + "</feed>\n"
                + "</queue>\n"
                + "<queue name=\"FromBrokerToRestaurantAPI1\">\n"
                + "<stats size=\"0\" consumerCount=\"0\" enqueueCount=\"0\" dequeueCount=\"0\"/>\n"
                + "<feed>\n"
                + "<atom>\n"
                + "queueBrowse/FromBrokerToRestaurantAPI1?view=rss&amp;feedType=atom_1.0\n"
                + "</atom>\n"
                + "<rss>\n"
                + "queueBrowse/FromBrokerToRestaurantAPI1?view=rss&amp;feedType=rss_2.0\n"
                + "</rss>\n"
                + "</feed>\n"
                + "</queue>\n"
                + "<queue name=\"FromReservationClientToBroker\">\n"
                + "<stats size=\"0\" consumerCount=\"3\" enqueueCount=\"0\" dequeueCount=\"0\"/>\n"
                + "<feed>\n"
                + "<atom>\n"
                + "queueBrowse/FromReservationClientToBroker?view=rss&amp;feedType=atom_1.0\n"
                + "</atom>\n"
                + "<rss>\n"
                + "queueBrowse/FromReservationClientToBroker?view=rss&amp;feedType=rss_2.0\n"
                + "</rss>\n"
                + "</feed>\n"
                + "</queue>\n"
                + "<queue name=\"FromBrokerToReservationClient\">\n"
                + "<stats size=\"0\" consumerCount=\"0\" enqueueCount=\"0\" dequeueCount=\"0\"/>\n"
                + "<feed>\n"
                + "<atom>\n"
                + "queueBrowse/FromBrokerToReservationClient?view=rss&amp;feedType=atom_1.0\n"
                + "</atom>\n"
                + "<rss>\n"
                + "queueBrowse/FromBrokerToReservationClient?view=rss&amp;feedType=rss_2.0\n"
                + "</rss>\n"
                + "</feed>\n"
                + "</queue>\n"
                + "<queue name=\"FromBrokerToRestaurantAPI3\">\n"
                + "<stats size=\"0\" consumerCount=\"0\" enqueueCount=\"0\" dequeueCount=\"0\"/>\n"
                + "<feed>\n"
                + "<atom>\n"
                + "queueBrowse/FromBrokerToRestaurantAPI3?view=rss&amp;feedType=atom_1.0\n"
                + "</atom>\n"
                + "<rss>\n"
                + "queueBrowse/FromBrokerToRestaurantAPI3?view=rss&amp;feedType=rss_2.0\n"
                + "</rss>\n"
                + "</feed>\n"
                + "</queue>\n"
                + "<queue name=\"FromBrokerToRestaurantAPI2\">\n"
                + "<stats size=\"0\" consumerCount=\"0\" enqueueCount=\"0\" dequeueCount=\"0\"/>\n"
                + "<feed>\n"
                + "<atom>\n"
                + "queueBrowse/FromBrokerToRestaurantAPI2?view=rss&amp;feedType=atom_1.0\n"
                + "</atom>\n"
                + "<rss>\n"
                + "queueBrowse/FromBrokerToRestaurantAPI2?view=rss&amp;feedType=rss_2.0\n"
                + "</rss>\n"
                + "</feed>\n"
                + "</queue>\n"
                + "<queue name=\"FromRestaurantToBrokerAPI1\">\n"
                + "<stats size=\"0\" consumerCount=\"3\" enqueueCount=\"0\" dequeueCount=\"0\"/>\n"
                + "<feed>\n"
                + "<atom>\n"
                + "queueBrowse/FromRestaurantToBrokerAPI1?view=rss&amp;feedType=atom_1.0\n"
                + "</atom>\n"
                + "<rss>\n"
                + "queueBrowse/FromRestaurantToBrokerAPI1?view=rss&amp;feedType=rss_2.0\n"
                + "</rss>\n"
                + "</feed>\n"
                + "</queue>\n"
                + "</queues>";

        return xmldata;
    }
}
