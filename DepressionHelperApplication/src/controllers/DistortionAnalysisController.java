/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.layout.AnchorPane;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.Date;
import java.util.ResourceBundle;

import javax.xml.bind.JAXBException;
import models.JournalEntry;
import utility.ProfileUtility;

/**
 *
 * @author tyler
 */
public class DistortionAnalysisController implements Initializable{
    
    @FXML 
    private Button saveBtn;
    @FXML
    private TextArea automaticText;
    @FXML
    private TextArea distortionsText;
    @FXML
    private TextArea rationalText;
    @FXML
    private AnchorPane distortionAnalysisAnchorPane;
    
    private boolean editMode;
    private Date saveDate;
    
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		editMode = false;
	}
    
    public void saveEntry() {
    	JournalEntry entry = createEntry();
        updateProfile(entry);
        clearEntry();
        try {
			loadJournalsView();
		} catch (IOException e) {
			e.printStackTrace();
		}
    }
    
    public void importJournal(JournalEntry entry) {
    	automaticText.setText(entry.getAutoEntry());
    	distortionsText.setText(entry.getDistortionEntry());
    	rationalText.setText(entry.getRationalEntry());
    	
    	//enable editing mode
    	editMode = true;
    	saveDate = entry.getDate();
    }
    
    private JournalEntry createEntry() {
        JournalEntry entry = new JournalEntry();
        if(editMode) {
        	entry.setDate(saveDate);
        }
        else {
        	entry.setDate(new Date());
        }
        entry.setAutoEntry(automaticText.getText());
        entry.setDistortionEntry(distortionsText.getText());
        entry.setRationalEntry(rationalText.getText());
        
        return entry;
    }
    
    private void updateProfile(JournalEntry entry) {
        try {
        	File saveFile = ProfileUtility.getSaveFile();
        	if(editMode) {
        		ProfileUtility.updateJournalEntry(entry, saveFile);
        	}
        	else {
        		ProfileUtility.addJournalEntry(entry, saveFile);
        	}
        }
        catch (JAXBException e) {
            e.printStackTrace();
        }
    }
    
    private void clearEntry() {
        automaticText.clear();
        distortionsText.clear();
        rationalText.clear();
    }
    
    private void loadJournalsView() throws IOException {
        distortionAnalysisAnchorPane.getChildren().clear();
        
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/views/Journals.fxml"));
        AnchorPane jounralPane = (AnchorPane) loader.load();
        
        distortionAnalysisAnchorPane.getChildren().add(jounralPane);
    }
}
