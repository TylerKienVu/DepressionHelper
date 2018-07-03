package controllers;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.RadioButton;
import javafx.scene.control.ToggleGroup;

/**
 *
 * @author tyler
 */
public class ChecklistController {
    
    @FXML
    private Button submitBtn;
    @FXML
    private ToggleGroup radio1;
    @FXML
    private ToggleGroup radio2;
    @FXML
    private ToggleGroup radio3;
    @FXML
    private ToggleGroup radio4;
    @FXML
    private ToggleGroup radio5;
    @FXML
    private ToggleGroup radio6;
    @FXML
    private ToggleGroup radio7;
    @FXML
    private ToggleGroup radio8;
    @FXML
    private ToggleGroup radio9;
    @FXML
    private ToggleGroup radio10;
    @FXML
    private ToggleGroup radio11;
    @FXML
    private ToggleGroup radio12;
    @FXML
    private ToggleGroup radio13;
    @FXML
    private ToggleGroup radio14;
    @FXML
    private ToggleGroup radio15;
    @FXML
    private ToggleGroup radio16;
    @FXML
    private ToggleGroup radio17;
    @FXML
    private ToggleGroup radio18;
    @FXML
    private ToggleGroup radio19;
    @FXML
    private ToggleGroup radio20;
    @FXML
    private ToggleGroup radio21;
    @FXML
    private ToggleGroup radio22;
    @FXML
    private ToggleGroup radio23;
    @FXML
    private ToggleGroup radio24;
    @FXML
    private ToggleGroup radio25;
    
    public void loadResults(ActionEvent event) throws IOException{
        ArrayList<ToggleGroup> radioButtons = new ArrayList<>(25);
        ToggleGroup[] radioList = new ToggleGroup[]{radio1,radio2,radio3,radio4,radio5,radio6,radio7,
        radio8,radio9,radio10,radio11,radio12,radio13,radio14,radio15,radio16,radio17,radio18,radio19,radio20,
        radio21,radio22,radio23,radio24,radio25};
        radioButtons.addAll(Arrays.asList(radioList));
        
        int totalScore = 0;
        for(ToggleGroup radio : radioButtons){
            totalScore += getRadioButtonValue(radio.getSelectedToggle().toString());
        }
        
        System.out.println(totalScore);
    }
    
    private int getRadioButtonValue(String radioString){
        int indexOfSelectedValue = radioString.indexOf("'")+1;
        int selectedValue = Integer.parseInt(String.valueOf(radioString.charAt(indexOfSelectedValue)));
        return selectedValue;
    }
}
