/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package monitoring;

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlElementWrapper;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlRootElement;
import java.util.Arrays;

/**
 *
 * @author M
 */
@JacksonXmlRootElement(localName = "queues")
public class Queues {
    @JacksonXmlElementWrapper(localName = "queue", useWrapping = false)
    private Queue[] queue;
    
    public Queues() {
        
    }
    
    public Queues(Queue[] queue){
        this.queue = queue;
    }
    
    public void setQueues(Queue[] queue) {
        this.queue = queue;
    }
    
    @Override
    public String toString() {
        return "Queues{" + "queues=" + Arrays.toString(queue) + "}";
    }
}
