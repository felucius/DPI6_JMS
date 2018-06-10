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
public class Feed {
    @JacksonXmlProperty(localName = "atom")
    private String atom;
    @JacksonXmlProperty(localName = "rss")
    private String rss;
    
    public Feed() {
        
    }
    
    public Feed(String atom, String rss) {
        this.atom = atom;
        this.rss = rss;
    }

    public String getAtom() {
        return atom;
    }

    public void setAtom(String atom) {
        this.atom = atom;
    }

    public String getRss() {
        return rss;
    }

    public void setRss(String rss) {
        this.rss = rss;
    }

    @Override
    public String toString() {
        return "Feed{" + 
                "atom=" + atom + '\'' +
                ", rss=" + rss + '}';
    }
    
    
}
