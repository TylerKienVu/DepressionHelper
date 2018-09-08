package models;

import java.util.ArrayList;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author tyler
 */
@XmlRootElement(name = "PleasurePredictionSheet")
public class PleasurePredictionSheet {
    private String sheetName;
    private ArrayList<PleasurePrediction> pleasurePredictionsList;
    
    public String getSheetName() {
        return this.sheetName;
    }
    
    @XmlAttribute
    public void setSheetName(String sheetName) {
        this.sheetName = sheetName;
    }
    
    public ArrayList<PleasurePrediction> getPleasurePredictionList() {
        return this.pleasurePredictionsList;
    }
    
    @XmlElement
    public void setPleasurePredictionList(ArrayList<PleasurePrediction> pleasurePredictionList) {
        this.pleasurePredictionsList = pleasurePredictionList;
    }
}
