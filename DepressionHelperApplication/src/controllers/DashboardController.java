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
    private ScatterChart scatterChart;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        try {
            loadDepressionScores();
        } catch (JAXBException e) {
            e.printStackTrace();
        }
    }
    
    private void loadDepressionScores() throws JAXBException{
        //testing
//        XYChart.Series testSeries = new XYChart.Series();
//        testSeries.setName("Test");
//        
//        testSeries.getData().add(new XYChart.Data("12-2", 23));
//        testSeries.getData().add(new XYChart.Data("12-2", 14));
//        testSeries.getData().add(new XYChart.Data("12-4", 15));
//        testSeries.getData().add(new XYChart.Data("12-8", 24));
//        testSeries.getData().add(new XYChart.Data("12-9", 34));
//        testSeries.getData().add(new XYChart.Data("12-9", 36));
//        testSeries.getData().add(new XYChart.Data("12-10", 22));
//        testSeries.getData().add(new XYChart.Data("12-11", 45));
//        testSeries.getData().add(new XYChart.Data("1-2", 43));
//        testSeries.getData().add(new XYChart.Data("1-3", 17));
//        testSeries.getData().add(new XYChart.Data("1-7", 29));
//        testSeries.getData().add(new XYChart.Data("1-7", 25));
//        
//        scatterChart.getData().add(testSeries);
        
        List<ChecklistScore> scoreList = ProfileUtility.getChecklistScores();
        XYChart.Series scoreSeries = new XYChart.Series();
        scoreSeries.setName("Depression Scores");
        
        Calendar calendar = Calendar.getInstance();
        for(ChecklistScore score : scoreList) {
            Date date = score.getDate();
            calendar.setTime(date);
            String month = Integer.toString(calendar.get(Calendar.MONTH));
            String day = Integer.toString(calendar.get(Calendar.DAY_OF_MONTH));
            String xAxis = month + "-" + day;
            scoreSeries.getData().add(new XYChart.Data(xAxis,score.getScore()));
        }
        scatterChart.getData().add(scoreSeries);

    }
    
}
