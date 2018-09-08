package models;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author tyler
 */
@XmlRootElement(name = "PleasurePrediction")
public class PleasurePrediction {
    private String activtyName;
    private int predictionPercentage;
    private int actualPercentage;
    
    public String getActivityName() {
        return this.activtyName;
    }
    
    @XmlAttribute
    public void setActivityName(String activityName) {
        this.activtyName = activityName;
    }
    
    public int getPredictionPercentage() {
        return this.predictionPercentage;
    }
    
    @XmlElement
    public void setPredictionPercentage(int predictionPercentage) {
        this.predictionPercentage = predictionPercentage;
    }
    
    public int getActualPercentage() {
        return this.actualPercentage;
    }
    
    @XmlElement
    public void setActualPercentage(int actualPercentage) {
        this.actualPercentage = actualPercentage;
    }
    
}
