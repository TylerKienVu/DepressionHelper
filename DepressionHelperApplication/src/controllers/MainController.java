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
    private AnchorPane contentAnchorPane;
    
    public void loadDistortionList(ActionEvent event) throws IOException{
        AnchorPane newLoadedPane = FXMLLoader.load(getClass().getResource("/views/Distortions.fxml"));
        contentAnchorPane.getChildren().add(newLoadedPane);
    }
}
