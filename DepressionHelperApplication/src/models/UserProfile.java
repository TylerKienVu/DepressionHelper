package models;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author tyler
 */
@XmlRootElement(name = "UserProfile")
public class UserProfile {
    private JournalEntries journalEntries;
    
    public JournalEntries getJournalEntries() {
        return this.journalEntries;
    }
    
    @XmlElement(name = "JournalEntries")
    public void setJournalEntries(JournalEntries journalEntries) {
        this.journalEntries = journalEntries;
    }
}
