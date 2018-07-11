/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;

import java.io.File;
import java.util.Date;
import javax.xml.bind.JAXBException;
import models.JournalEntry;
import utility.ProfileUtility;

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
        JournalEntry entry = createEntry();
        updateProfile(entry);
        clearEntry();
    }
    
    private JournalEntry createEntry() {
        JournalEntry entry = new JournalEntry();
        entry.setDate(new Date());
        entry.setAutoEntry(automaticText.getText());
        entry.setDistortionEntry(distortionsText.getText());
        entry.setRationalEntry(rationalText.getText());
        
        return entry;
    }
    
    private void updateProfile(JournalEntry newEntry) {
        File saveFile = new File("profile.xml");
        try {
            if(!saveFile.exists()) {
                ProfileUtility.createProfile(saveFile);
            }
            ProfileUtility.addJournalEntry(newEntry, saveFile);
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
}
