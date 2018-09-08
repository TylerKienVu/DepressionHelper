package controllers;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

/**
 *
 * @author tyler
 */
public class MainController implements Initializable{
    
    @FXML
    private Button listOfDistortionsBtn;
    @FXML
    private Button checklistBtn;
    @FXML
    private Button distortionAnalysisBtn;
    @FXML
    private Button journalBtn;
    @FXML
    private AnchorPane contentAnchorPane;
    
    //Not used anymore
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
    
    public void loadDashboard() throws IOException{
        contentAnchorPane.getChildren().clear();
        AnchorPane newLoadedPane = FXMLLoader.load(getClass().getResource("/views/Dashboard.fxml"));
        contentAnchorPane.getChildren().add(newLoadedPane);
    }
    
    public void loadJournals() throws IOException{
        contentAnchorPane.getChildren().clear();
        AnchorPane newLoadedPane = FXMLLoader.load(getClass().getResource("/views/Journals.fxml"));
        contentAnchorPane.getChildren().add(newLoadedPane);
    }
    
    public void loadPleasurePrediction() throws IOException {
        contentAnchorPane.getChildren().clear();
        AnchorPane newLoadedPane = FXMLLoader.load(getClass().getResource("/views/PleasurePrediction.fxml"));
        contentAnchorPane.getChildren().add(newLoadedPane);
    }
    
    public void openDistortionsWindow() {
        try {
            //Load root window
            Parent root = FXMLLoader.load(getClass().getResource("/views/Distortions.fxml"));
//            Parent root = FXMLLoader.load(getClass().getResource("/views/Test.fxml"));
            Scene scene = new Scene(root);
            scene.getStylesheets().add(getClass().getResource("/application/application.css").toExternalForm());

            //Gives focus to root window
            root.requestFocus();

            //Setup Stage
            Stage distortionsStage = new Stage();
            distortionsStage.setTitle("Distortions");
            distortionsStage.setResizable(false);
            distortionsStage.setScene(scene);
            distortionsStage.sizeToScene();
            distortionsStage.show();
        }
        catch ( IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        try {
            loadDashboard();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
