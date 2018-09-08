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
    private ChecklistScores checklistScores;
    private PleasurePredictionSheets pleasurePredictionSheets;
    
    public JournalEntries getJournalEntries() {
        return this.journalEntries;
    }
    
    @XmlElement(name = "JournalEntries")
    public void setJournalEntries(JournalEntries journalEntries) {
        this.journalEntries = journalEntries;
    }
    
    public ChecklistScores getChecklistScores() {
        return this.checklistScores;
    }
    
    @XmlElement(name = "ChecklistScores")
    public void setChecklistScores(ChecklistScores checklistScores) {
        this.checklistScores = checklistScores;
    }
    
    public PleasurePredictionSheets getPleasurePredictionSheets() {
        return this.pleasurePredictionSheets;
    }
    
    @XmlElement(name = "PleasurePredictionSheets")
    public void setPleasurePredictionSheets(PleasurePredictionSheets pleasurePredictionSheets) {
        this.pleasurePredictionSheets = pleasurePredictionSheets;
    }
}
