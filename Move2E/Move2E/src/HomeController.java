import java.io.IOException;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.stage.Stage;
import javafx.scene.Node;


public class HomeController {

    private Stage stage;
    private Scene scene;
    private Parent root;

    @FXML
    private Button generalStore;

    @FXML
    private Button login;

    @FXML
    private Button Aboutus;

    @FXML
    private Button contact;

    @FXML
    private Label userN;

    @FXML
    private Button medical1;

    @FXML
    private Button stationayShop;

    @FXML
    void Login1(ActionEvent event) throws IOException{

        root = FXMLLoader.load(getClass().getResource("OptionScene.fxml"));
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        
        // Create a new scene using the loaded root
        scene = new Scene(root);
        
        // Set the new scene on the stage
        stage.setScene(scene);
        
        // Show the new scene
        stage.show();
        
    }

    @FXML
    void Aboutas(ActionEvent event) throws IOException {
        
        root = FXMLLoader.load(getClass().getResource("NewAboutUs.fxml"));
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        
        // Create a new scene using the loaded root
        scene = new Scene(root);
        
        // Set the new scene on the stage
        stage.setScene(scene);
        
        // Show the new scene
        stage.show();
    }

    @FXML
    void ContInfo(ActionEvent event) throws IOException {

        root = FXMLLoader.load(getClass().getResource("thankYouScene.fxml"));
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        
        // Create a new scene using the loaded root
        scene = new Scene(root);
        
        // Set the new scene on the stage
        stage.setScene(scene);
        
        // Show the new scene
        stage.show();

    }

    @FXML
    void RajaStore(ActionEvent event) {

    }

    @FXML
    void ShreeStore(ActionEvent event) {

    }

    @FXML
    void mahavirMedi(ActionEvent event) {

    }

}
