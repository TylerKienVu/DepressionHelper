package application;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import utility.ProfileUtility;

/**
 *
 * @author tyler
 */
public class Main extends Application {
    
    @Override
    public void start(Stage primaryStage) throws Exception {
        //Setup save path for application
        ProfileUtility.createSavePath();
        
        //Load root window
        Parent root = FXMLLoader.load(getClass().getResource("/views/Main.fxml"));
        Scene scene = new Scene(root);
        scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
        
        //Gives focus to root window
        root.requestFocus();
        
        //Setup Stage
        primaryStage.setTitle("DepressionHelperApplication");
        primaryStage.setResizable(false);
        primaryStage.setScene(scene);
        primaryStage.show();
        
    }

    public static void main(String[] args) {
        launch(args);
    }
    
}
