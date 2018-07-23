package controllers;

import java.net.URL;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.chart.ScatterChart;
import javafx.scene.chart.XYChart;
import javax.xml.bind.JAXBException;
import models.ChecklistScore;
import utility.ProfileUtility;

/**
 *
 * @author tyler
 */
public class DashboardController implements Initializable{
    
    @FXML
    private ScatterChart<String,Integer> scatterChart;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        try {
            loadDepressionScores();
        } catch (JAXBException e) {
            e.printStackTrace();
        }
    }
    
    private void loadDepressionScores() throws JAXBException{
        
        List<ChecklistScore> scoreList = ProfileUtility.getChecklistScores();
        XYChart.Series<String,Integer> scoreSeries = new XYChart.Series<>();
        scoreSeries.setName("Depression Scores");
        
        Calendar calendar = Calendar.getInstance();
        
        //make sure that the user has checklist score history
        if(scoreList != null) {
        	for(ChecklistScore score : scoreList) {
                Date date = score.getDate();
                calendar.setTime(date);
                String month = Integer.toString(calendar.get(Calendar.MONTH));
                String day = Integer.toString(calendar.get(Calendar.DAY_OF_MONTH));
                String xAxis = month + "-" + day;
                scoreSeries.getData().add(new XYChart.Data<>(xAxis,score.getScore()));
            }
        }
        
        scatterChart.getData().add(scoreSeries);
    }
    
}
