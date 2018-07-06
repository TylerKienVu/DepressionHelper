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
    private Button distortionAnalysisBtn;
    @FXML
    private AnchorPane contentAnchorPane;
    
    public void loadDistortionList() throws IOException{
        contentAnchorPane.getChildren().clear();
        AnchorPane newLoadedPane = FXMLLoader.load(getClass().getResource("/views/Distortions.fxml"));
        contentAnchorPane.getChildren().add(newLoadedPane);
    }
    
    public void loadChecklist() throws IOException{
        contentAnchorPane.getChildren().clear();
        AnchorPane newLoadedPane = FXMLLoader.load(getClass().getResource("/views/Checklist.fxml"));
        contentAnchorPane.getChildren().add(newLoadedPane);
    }
    
    public void loadDistortionAnalysis() throws IOException{
        contentAnchorPane.getChildren().clear();
        AnchorPane newLoadedPane = FXMLLoader.load(getClass().getResource("/views/DistortionAnalysis.fxml"));
        contentAnchorPane.getChildren().add(newLoadedPane);
    }
}
