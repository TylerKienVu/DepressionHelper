/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;

/**
 *
 * @author tyler
 */
public class DistortionAnalysisController {
    
    @FXML 
    private Button saveBtn;
    @FXML
    private TextArea automaticText;
    @FXML
    private TextArea distortionsText;
    @FXML
    private TextArea rationalText;
    
    public void saveEntry() {
        automaticText.clear();
        distortionsText.clear();
        rationalText.clear();
    }
}
