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
import javafx.scene.control.Label;
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
    @FXML
    private Label latestDepressionLevelLabel;
    @FXML
    private Label latestScoreLabel;
    @FXML
    private Label previousScoreLabel;
    @FXML
    private Label scoreDifferenceLabel;

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
        
        //Add data to chart
        XYChart.Series<String,Integer> scoreSeries = populateChart(scoreList);                
        scatterChart.getData().add(scoreSeries);
        
        //Initialize data logistics
        initializeScoreAnalytics(scoreList);
    }
    
    private XYChart.Series<String,Integer> populateChart(List<ChecklistScore> scoreList) {
        
        //initialize series
        XYChart.Series<String,Integer> scoreSeries = new XYChart.Series<>();
        scoreSeries.setName("Depression Scores");
        
        //Create calendar to retrieve dates in nice format
        Calendar calendar = Calendar.getInstance();
        
        //make sure that the user has checklist score history
        if(scoreList != null) {
            for(ChecklistScore score : scoreList) {
                Date date = score.getDate();
                calendar.setTime(date);
                String month = Integer.toString(calendar.get(Calendar.MONTH)+1);
                String day = Integer.toString(calendar.get(Calendar.DAY_OF_MONTH));
                String xAxis = month + "-" + day;
                scoreSeries.getData().add(new XYChart.Data<>(xAxis,score.getScore()));
            }
        }        
        return scoreSeries;
    }
    
    private void initializeScoreAnalytics(List<ChecklistScore> scoreList){
        if(scoreList != null) {
            setLatestDepressionLevelLabel(scoreList);  
            setLatestScoreLabel(scoreList);
            setPreviousScoreLabel(scoreList);
            setScoreDifferenceLabel(scoreList);
        }
    }
    
    private void setLatestDepressionLevelLabel(List<ChecklistScore> scoreList) {
        int latestScore = scoreList.get(scoreList.size()-1).getScore();
        String depressionLevel = scoreToDepressionLevel(latestScore);
        
        latestDepressionLevelLabel.setText(depressionLevel);
    }
    
    private void setLatestScoreLabel(List<ChecklistScore> scoreList) {
        int latestScore = scoreList.get(scoreList.size()-1).getScore();
        latestScoreLabel.setText(Integer.toString(latestScore));
    }
    
    private void setPreviousScoreLabel(List<ChecklistScore> scoreList) {
        if(scoreList.size() > 1) {
            int previousScore = scoreList.get(scoreList.size()-2).getScore();
            previousScoreLabel.setText(Integer.toString(previousScore));
        }
        else {
            previousScoreLabel.setText("0");
        }
    }
    
    private void setScoreDifferenceLabel(List<ChecklistScore> scoreList) {
        int latestScore = scoreList.get(scoreList.size()-1).getScore();
        
        if(scoreList.size() > 1) {            
            int previousScore = scoreList.get(scoreList.size()-2).getScore();
            int difference = latestScore - previousScore;
            scoreDifferenceLabel.setText(Integer.toString(difference));
        }
        else {
            scoreDifferenceLabel.setText(Integer.toString(latestScore));
        }
        
    }
    
    private String scoreToDepressionLevel(int score) {
        if(score <= 5)
            return "No depression";
        if(score <= 10)
            return "Normal but unhappy";
        if(score <= 25)
            return "Mild depression";
        if(score <= 50)
            return "Moderate depression";
        if(score <= 75)
            return "Severe depression";
        return "Extreme depression";
    }
}
