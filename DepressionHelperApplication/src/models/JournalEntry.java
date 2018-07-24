package models;

import java.util.Date;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author tyler
 */
@XmlRootElement(name = "JournalEntry")
public class JournalEntry {
    private Date date;
    private String name;
    private String autoEntry;
    private String distortionEntry;
    private String rationalEntry;
    
    public Date getDate() {
        return this.date;
    }
    
    @XmlAttribute
    public void setDate(Date date) {
        this.date = date;
    }

    public String getName() {
    	return this.name;
    }
    
    @XmlAttribute
    public void setName(String name) {
    	this.name = name;
    }
    
    public String getAutoEntry() {
        return this.autoEntry;
    }
    
    @XmlElement
    public void setAutoEntry(String autoEntry) {
        this.autoEntry = autoEntry;
    }
    
    public String getDistortionEntry(){
        return this.distortionEntry;
    }
    
    @XmlElement
    public void setDistortionEntry(String distortionEntry) {
        this.distortionEntry = distortionEntry;
    }
    
    public String getRationalEntry() {
        return this.rationalEntry;
    }
    
    @XmlElement
    public void setRationalEntry(String rationalEntry) {
        this.rationalEntry = rationalEntry;
    }
}
