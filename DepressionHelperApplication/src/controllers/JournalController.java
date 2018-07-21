package controllers;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ListView;
import javax.xml.bind.JAXBException;
import models.JournalEntry;
import utility.ProfileUtility;

/**
 *
 * @author tyler
 */
public class JournalController implements Initializable{

    @FXML
    private ListView entriesListView;
    
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        try {
            loadJournalEntries();
        } catch (JAXBException e) {
            e.printStackTrace();
        }
    }
    
    private void loadJournalEntries() throws JAXBException {
        ObservableList<String> entriesToImport = FXCollections.observableArrayList();
        List<JournalEntry> entriesList = ProfileUtility.getJournalEntries();
        entriesListView.setItems(entriesToImport);
        for(JournalEntry entry : entriesList){
            entriesToImport.add(entry.getDate().toString());
        }
    }
    
}
