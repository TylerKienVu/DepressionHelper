package controllers;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.MultipleSelectionModel;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;

import javax.xml.bind.JAXBException;
import models.JournalEntry;
import utility.ProfileUtility;

/**
 *
 * @author tyler
 */
public class JournalController implements Initializable{

    @FXML
    private ListView<String> entriesListView;
    @FXML
    private Button editBtn;
    @FXML
    private AnchorPane journalsAnchorPane;
    
    private List<JournalEntry> entriesList;
    
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        try {
        	setDoubleClickListener();
            loadJournalEntries();
        } catch (JAXBException e) {
            e.printStackTrace();
        }
    }
    
    public void loadSelectedJournal() {
    	try {
    		if(isSelected()) {
    			JournalEntry entry = getSelectedJournal();
    			loadDistortionAnalysisEditView(entry);
    		}
    	}
    	catch (Exception e) {
    		e.printStackTrace();
    	}    	
    }
    
    public void deleteSelectedJournal() {
    	try {
            if(isSelected()) {
                JournalEntry entry = getSelectedJournal();
                File saveFile = ProfileUtility.getSaveFile();
                ProfileUtility.deleteJournalEntry(entry,saveFile);
                loadJournalEntries();
            }			
	} 
    	catch (Exception e) {
            e.printStackTrace();
	}
    }
    
    public void loadNewJournalEntry() {
        journalsAnchorPane.getChildren().clear();        
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/views/DistortionAnalysis.fxml"));
		
        try {
			AnchorPane entryPane = (AnchorPane) loader.load();        
			DistortionAnalysisController entryController = loader.getController();
	        entryController.enableBackButton();
			
			journalsAnchorPane.getChildren().add(entryPane);
			journalsAnchorPane.requestFocus();
		} catch (IOException e) {
			e.printStackTrace();
		}
    }
    
    private boolean isSelected() {
    	MultipleSelectionModel<String> listModel = entriesListView.getSelectionModel();
    	if (listModel.getSelectedItem() != null) {
    		return true;
    	}
    	return false;
    }
    
    private void loadJournalEntries() throws JAXBException {
        ObservableList<String> entriesToImport = FXCollections.observableArrayList();
        entriesList = ProfileUtility.getJournalEntries();
        entriesListView.setItems(entriesToImport);
        
        if (entriesList != null) {
        	for(JournalEntry entry : entriesList){
        		
        		//handle displaying the names of the journal entries
        		String journalEntryName = entry.getName();
        		String journalEntryDate = entry.getDate().toString();
        		String stringToImport;
        		if (journalEntryName == null) {
        			stringToImport = String.format("%s - %s","Untitled", journalEntryDate);
        		}
        		else {
        			stringToImport = String.format("%s - %s", journalEntryName, journalEntryDate);
        		}
                entriesToImport.add(stringToImport);
            }
        }
    }
    
    private void loadDistortionAnalysisEditView(JournalEntry entry) throws IOException {
        journalsAnchorPane.getChildren().clear();
        
        //prepare next scene by getting distortion analysis controller and calling function to import selected journal
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/views/DistortionAnalysis.fxml"));
        AnchorPane entryPane = (AnchorPane) loader.load();
        DistortionAnalysisController entryController = loader.getController();
        entryController.importJournal(entry);
        
        journalsAnchorPane.getChildren().add(entryPane);
        journalsAnchorPane.requestFocus();
    }
    
    private JournalEntry getSelectedJournal() throws Exception {
    	MultipleSelectionModel<String> listModel = entriesListView.getSelectionModel();
    	String selectedJournalDate = stripJournalName(listModel.getSelectedItem());
    	for(JournalEntry entry : entriesList) {
    		if(entry.getDate().toString().equals(selectedJournalDate)) {
    			return entry;
    		}
    	}
    	throw new Exception("Nothing is selected");
    }
    
    private String stripJournalName(String journalEntryName) {
    	int indexOfDash = journalEntryName.indexOf('-');
    	if (indexOfDash == -1) {
    		return journalEntryName;
    	}
    	return journalEntryName.substring(indexOfDash + 2); 
    }
    
    private void setDoubleClickListener() {
    	entriesListView.setOnMouseClicked(new EventHandler<MouseEvent>() {

			@Override
			public void handle(MouseEvent click) {
				if (click.getClickCount() == 2) {
					loadSelectedJournal();
				}
			}
    	});
    }
}
