package controllers;

import javafx.fxml.FXML;
import javafx.scene.control.Label;

/**
 *
 * @author tyler
 */
public class ChecklistResultsController{
    
    @FXML
    private Label scoreLabel;
    @FXML
    private Label levelLabel;
    
    public void setLabels(int score){
        scoreLabel.setText(Integer.toString(score));
        setLevelLabel(score);
    }
    
    private void setLevelLabel(int score) {
        if(score <= 5) {
            levelLabel.setText("No Depression");
        }
        else if (score <= 10) {
            levelLabel.setText("Normal but unhappy");
        }
        else if (score <= 25) {
            levelLabel.setText("Mild depression");
        }
        else if (score <= 50) {
            levelLabel.setText("Moderate depression");
        }
        else if (score <= 75) {
            levelLabel.setText("Severe depression");
        }
        else if (score <= 100) {
            levelLabel.setText("Extreme depression");
        }
    }
}
