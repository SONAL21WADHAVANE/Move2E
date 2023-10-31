import java.io.IOException;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;

public class HomeUserController {

    private Stage stage;
    private Scene scene;
    private Parent root;

    @FXML
    private Button Aboutus;

    @FXML
    private Button contact;

    @FXML
    private Button locBtn;

    @FXML
    private Button generalStore;

    @FXML
    private ImageView img1;

    @FXML
    private ImageView img2;

    @FXML
    private ImageView img3;

    @FXML
    private TextField loc;

    @FXML
    private Button loginout;

    @FXML
    private Button medical1;

    @FXML
    private Label sh1;

    @FXML
    private Label sh2;

    @FXML
    private Label sh3;

    @FXML
    private Button stationayShop;

    @FXML
    private Label userN;

    private String uName;

    public void setUName(String uName) {
       
        this.uName = uName; 
        userN.setText(uName);   
    }

    @FXML
    void findShop(ActionEvent event) throws IOException {

        String Location = loc.getText();
        System.out.println(Location);
            FXMLLoader loader = new FXMLLoader(getClass().getResource("HomeUserLocScene.fxml"));
            root = loader.load();
            
            // Access the controller of the next scene
            HomeUserLocController homeUserLoc = loader.getController();
            
            // Pass the shop_info ID to the ShopStoreProdController
            homeUserLoc.setLocation(Location,uName);
        
            Scene scene = new Scene(root);
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            stage.setScene(scene);
            stage.show();
        }


    @FXML
    void Aboutas(ActionEvent event) {

    }

    @FXML
    void ContInfo(ActionEvent event) {

    }

    @FXML
    void RajaStore(ActionEvent event) {

    }

    @FXML
    void ShreeStore(ActionEvent event) {

    }

    @FXML
    void Signout(ActionEvent event) throws IOException {

        root = FXMLLoader.load(getClass().getResource("HomeScene.fxml"));
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        
        // Create a new scene using the loaded root
        scene = new Scene(root);
        
        // Set the new scene on the stage
        stage.setScene(scene);
        
        // Show the new scene
        stage.show();

    }

    @FXML
    void mahavirMedi(ActionEvent event) {

    }

}
