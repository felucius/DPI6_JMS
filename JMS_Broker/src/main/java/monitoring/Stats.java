/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package monitoring;

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;

/**
 *
 * @author M
 */
public class Stats {
    @JacksonXmlProperty(localName = "size")
    private String size;
    @JacksonXmlProperty(localName = "consumerCount")
    private String consumerCount;
    @JacksonXmlProperty(localName = "enqueueCount")
    private String enqueueCount;
    @JacksonXmlProperty(localName = "dequeueCount")
    private String dequeueCount;
    
    public Stats() {
        
    }
    
    public Stats(String size, String consumerCount, String enqueueCount, String dequeueCount) {
        this.size = size;
        this.consumerCount = consumerCount;
        this.enqueueCount = enqueueCount;
        this.dequeueCount = dequeueCount;
    }

    public String getSize() {
        return size;
    }

    public void setSize(String size) {
        this.size = size;
    }

    public String getConsumerCount() {
        return consumerCount;
    }

    public void setConsumerCount(String consumerCount) {
        this.consumerCount = consumerCount;
    }

    public String getEnqueueCount() {
        return enqueueCount;
    }

    public void setEnqueueCount(String enqueueCount) {
        this.enqueueCount = enqueueCount;
    }

    public String getDequeueCount() {
        return dequeueCount;
    }

    public void setDequeueCount(String dequeueCount) {
        this.dequeueCount = dequeueCount;
    }

    @Override
    public String toString() {
        return "Stats{" + 
                "size='" + size + '\'' +
                ", consumerCount='" + consumerCount + '\'' +
                ", enqueueCount='" + enqueueCount + '\'' +
                ", dequeueCount='" + dequeueCount + '\'' +
                '}';
    }
    
}
