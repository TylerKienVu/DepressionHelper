package controllers;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.MultipleSelectionModel;
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
            loadJournalEntries();
        } catch (JAXBException e) {
            e.printStackTrace();
        }
    }
    
    public void loadSelectedJournal() {
    	try {
    		JournalEntry entry = getSelectedJournal();
    		loadDistortionAnalysisEditView(entry);
    	}
    	catch (Exception e) {
    		e.printStackTrace();
    	}    	
    }
    
    public void deleteSelectedJournal() {
    	try {
			JournalEntry entry = getSelectedJournal();
			File saveFile = ProfileUtility.getSaveFile();
			ProfileUtility.deleteJournalEntry(entry,saveFile);
			loadJournalEntries();
		} 
    	catch (Exception e) {
			e.printStackTrace();
		}
    }
    
    public void loadNewJournalEntry() {
        journalsAnchorPane.getChildren().clear();
        
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/views/DistortionAnalysis.fxml"));
        AnchorPane entryPane;
		
        try {
			entryPane = (AnchorPane) loader.load();
			journalsAnchorPane.getChildren().add(entryPane);
		} catch (IOException e) {
			e.printStackTrace();
		}
    }
    
    private void loadJournalEntries() throws JAXBException {
        ObservableList<String> entriesToImport = FXCollections.observableArrayList();
        entriesList = ProfileUtility.getJournalEntries();
        entriesListView.setItems(entriesToImport);
        
        if (entriesList != null) {
        	for(JournalEntry entry : entriesList){
                entriesToImport.add(entry.getDate().toString());
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
    }
    
    private JournalEntry getSelectedJournal() throws Exception {
    	MultipleSelectionModel<String> listModel = entriesListView.getSelectionModel();
    	String selectedJournalDate = listModel.getSelectedItem();
    	for(JournalEntry entry : entriesList) {
    		if(entry.getDate().toString().equals(selectedJournalDate)) {
    			return entry;
    		}
    	}
    	throw new Exception("Selected journal not found");
    }
}
