package controllers;

import java.io.IOException;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;

/**
 *
 * @author tyler
 */
public class MainController {
    
    @FXML
    private Button listOfDistortionsBtn;
    @FXML
    private Button checklistBtn;
    @FXML
    private AnchorPane contentAnchorPane;
    
    public void loadDistortionList(ActionEvent event) throws IOException{
        AnchorPane newLoadedPane = FXMLLoader.load(getClass().getResource("/views/Distortions.fxml"));
        contentAnchorPane.getChildren().clear();
        contentAnchorPane.getChildren().add(newLoadedPane);
    }
    
    public void loadChecklist(ActionEvent event) throws IOException{
        AnchorPane newLoadedPane = FXMLLoader.load(getClass().getResource("/views/Checklist.fxml"));
        contentAnchorPane.getChildren().clear();
        contentAnchorPane.getChildren().add(newLoadedPane);
    }
    
    public void loadChecklistResults() throws IOException{
        AnchorPane newLoadedPane = FXMLLoader.load(getClass().getResource("/views/ChecklistResults.fxml"));
        contentAnchorPane.getChildren().clear();
        contentAnchorPane.getChildren().add(newLoadedPane);
    }
}
