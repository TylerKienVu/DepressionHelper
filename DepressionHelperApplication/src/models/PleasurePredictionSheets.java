package models;

import java.util.ArrayList;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author tyler
 */
@XmlRootElement(name = "PleasurePredictionSheets")
public class PleasurePredictionSheets {
  public ArrayList<PleasurePredictionSheet> sheetsList;  
    
  public ArrayList<PleasurePredictionSheet> getPleasurePredictionSheetsList() {
      return this.sheetsList;
  }
  
  @XmlElement
  public void setPleasurePredictionSheetsList(ArrayList<PleasurePredictionSheet> pleasurePredictionSheetsList) {
      this.sheetsList = sheetsList;
  }  
}
