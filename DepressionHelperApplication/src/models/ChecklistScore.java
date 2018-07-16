package models;

import java.util.Date;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
/**
 *
 * @author tyler
 */
@XmlRootElement(name = "ChecklistScore")
public class ChecklistScore {
    private Date date;
    private int score;
    
    public Date getDate(){
        return this.date;
    }
    
    @XmlAttribute
    public void setDate(Date date) {
        this.date = date;
    }
    
    public int getScore() {
        return this.score;
    }
    
    @XmlElement
    public void setScore(int score) {
        this.score = score;
    }
}
