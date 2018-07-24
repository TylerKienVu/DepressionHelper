/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers;

import javafx.application.Platform;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
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
    private TextField nameText;
    @FXML
    private AnchorPane distortionAnalysisAnchorPane;
    
    private boolean editMode;
    private Date saveDate;
    private static final int NameLimit = 50;
    
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		editMode = false;
		addListeners();
		
		//set focus on text field when scene is ready
		Platform.runLater(new Runnable() {
			@Override
			public void run() {			
				nameText.requestFocus();
			}			
		});
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
    	displayImportedJournal(entry);
    	enableEditingMode(entry.getDate());
    }
    
    public void enableBackButton() {
    	Button backBtn = new Button();
    	backBtn.setText("Back");
    	backBtn.setLayoutX(650);
    	backBtn.setLayoutY(693);
    	backBtn.setOnMouseClicked(new EventHandler<Event>() {
			@Override
			public void handle(Event event) {			
				try {
					loadJournalsView();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		});
    	
    	distortionAnalysisAnchorPane.getChildren().add(backBtn);
    }
    
    private void displayImportedJournal(JournalEntry entry) {
    	nameText.setText(entry.getName());
    	automaticText.setText(entry.getAutoEntry());
    	distortionsText.setText(entry.getDistortionEntry());
    	rationalText.setText(entry.getRationalEntry());
    }
    
    private void enableEditingMode(Date entryDate) {
    	editMode = true;
    	saveDate = entryDate;
    	enableBackButton();    	
    }
    
    private JournalEntry createEntry() {
        JournalEntry entry = new JournalEntry();
        if(editMode) {
        	entry.setDate(saveDate);
        }
        else {
        	entry.setDate(new Date());
        }
        entry.setName(nameText.getText());
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
    	nameText.clear();
        automaticText.clear();
        distortionsText.clear();
        rationalText.clear();
    }
    
    private void loadJournalsView() throws IOException {
        distortionAnalysisAnchorPane.getChildren().clear();
        
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/views/Journals.fxml"));
        AnchorPane jounralPane = (AnchorPane) loader.load();
        
        distortionAnalysisAnchorPane.getChildren().add(jounralPane);
        distortionAnalysisAnchorPane.requestFocus();
    }
    
    private void addListeners() {
		addNameLimitListener();
		addTabNavigationListeners();
		
    }
    
    private void addNameLimitListener() {
		nameText.lengthProperty().addListener(new ChangeListener<Number>() {

			@Override
			public void changed(ObservableValue<? extends Number> observable, Number oldValue, Number newValue) {
				if (newValue.intValue() > oldValue.intValue()) {
					if (nameText.getText().length() >= NameLimit) {
						nameText.setText(nameText.getText().substring(0,NameLimit));
					}
				}
			}
			
		});
    }
    
    private void addTabNavigationListeners() {
    	automaticText.addEventFilter(KeyEvent.KEY_PRESSED, new EventHandler<KeyEvent>() {
			@Override
			public void handle(KeyEvent event) {
				if (event.getCode() == KeyCode.TAB) {
					distortionsText.requestFocus();
				}
			}
    	});
    	
    	distortionsText.addEventFilter(KeyEvent.KEY_PRESSED, new EventHandler<KeyEvent>() {
			@Override
			public void handle(KeyEvent event) {
				if (event.getCode() == KeyCode.TAB) {
					rationalText.requestFocus();
				}
			}
    	});
    }
}
