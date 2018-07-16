package models;

import java.util.List;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author tyler
 */
@XmlRootElement(name = "JournalEntries")
public class JournalEntries {
    private List<JournalEntry> entries;
    
    public List<JournalEntry> getEntries() {
        return this.entries;
    }
    
    @XmlElement(name = "JournalEntry")
    public void setEntries(List<JournalEntry> entries) {
        this.entries = entries;
    }
}
