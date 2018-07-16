package models;


import java.util.List;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
/**
 *
 * @author tyler
 */
@XmlRootElement(name = "ChecklistScores")
public class ChecklistScores {
    private List<ChecklistScore> scores;
    
    public List<ChecklistScore> getScores() {
        return this.scores;
    }
    
    @XmlElement(name="ChecklistScore")
    public void setScores(List<ChecklistScore> scores) {
        this.scores = scores;
    }
}
