/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package monitoring;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import javax.jms.Message;
import javax.jms.MessageListener;
import org.apache.commons.lang3.StringUtils;

/**
 *
 * @author M
 */
public class Parser implements MessageListener {

    private ObjectMapper objectMapper = new XmlMapper();

    public Parser() {
        parseXMLqueues();
    }

    @Override
    public void onMessage(Message msg) {
        parseXMLqueues();
    }

    public void parseXMLqueues() {
        try {
            Queues queues = objectMapper.readValue(
                    StringUtils.toEncodedString(Files.readAllBytes(Paths.get("/http://localhost:8161/admin/xml/queues.jsp")), StandardCharsets.UTF_8),
                    Queues.class);
            System.out.println("MONITORING_ACTIVEMQ_BROKER: " + queues);
        } catch (IOException ioe) {
            ioe.getMessage();
        }
    }
}
