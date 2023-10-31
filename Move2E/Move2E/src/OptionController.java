import java.io.IOException;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.stage.Stage;

public class OptionController {

    private Stage stage;
    private Scene scene;
    private Parent root;


    @FXML
    private Button shopK;

    @FXML
    private Button home;

    @FXML
    private Button user;

    @FXML
    void shopKLogin(ActionEvent event) throws IOException {
        root = FXMLLoader.load(getClass().getResource("ShopsLoginScene.fxml"));
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        
        // Create a new scene using the loaded root
        scene = new Scene(root);
        
        // Set the new scene on the stage
        stage.setScene(scene);
        
        // Show the new scene
        stage.show();

    }

    @FXML
    void userLogin(ActionEvent event) throws IOException {

        root = FXMLLoader.load(getClass().getResource("UserLoginScene.fxml"));
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        
        // Create a new scene using the loaded root
        scene = new Scene(root);
        
        // Set the new scene on the stage
        stage.setScene(scene);
        
        // Show the new scene
        stage.show();

    }

    @FXML
    void rediHome(ActionEvent event) throws IOException {
        root = FXMLLoader.load(getClass().getResource("HomeScene.fxml"));
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        
        // Create a new scene using the loaded root
        scene = new Scene(root);
        
        // Set the new scene on the stage
        stage.setScene(scene);
        
        // Show the new scene
        stage.show();
    }

}
