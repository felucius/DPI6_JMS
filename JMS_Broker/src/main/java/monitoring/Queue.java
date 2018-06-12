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
public class Queue {
    @JacksonXmlProperty(localName = "name", isAttribute = true)
    private String name;
    @JacksonXmlProperty(localName = "stats", isAttribute = true)
    private Stats stats;
    @JacksonXmlProperty(localName = "feed", isAttribute = true)
    private Feed feed;
    
    public Queue() {
        
    }
    
    public Queue(String name, Stats stats, Feed feed) {
        this.name = name;
        this.stats = stats;
        this.feed = feed;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
    
    public Stats getStats() {
        return stats;
    }

    public void setStats(Stats stats) {
        this.stats = stats;
    }

    public Feed getFeed() {
        return feed;
    }

    public void setFeed(Feed feed) {
        this.feed = feed;
    }

    @Override
    public String toString() {
        return "Queue{" + 
                "stats=" + stats + '\'' +
                ", feed=" + feed + '}';
    }
    
}
