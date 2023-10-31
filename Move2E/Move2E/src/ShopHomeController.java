import java.io.IOException;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.stage.Stage;

public class ShopHomeController {

    private Stage stage;
    private Scene scene;
    private Parent root;

    @FXML
    private Button addProd;

    @FXML
    private Button Signout;

    @FXML
    private Button existProd;

    @FXML
    private Label shopN;
    private int id;
    private String uName;

    @FXML
    void addProucts(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("ShopStoreProdScene.fxml"));
        Parent root = loader.load();
        
        // Access the controller of the next scene
        ShopStoreProdController shopStoreProdController = loader.getController();
        
        // Pass the shop_info ID to the ShopStoreProdController
        shopStoreProdController.setShopInfoId1(id,uName);
    
        Scene scene = new Scene(root);
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setScene(scene);
        stage.show();
    }

    public void setShopInfo(int shopInfoId,String uName) {
        this.id = shopInfoId;
        this.uName = uName;
        shopN.setText(uName);
        
    }

    @FXML
    void existProducts(ActionEvent event) {

    }
    @FXML
    void signouthome(ActionEvent event) throws IOException {
        root = FXMLLoader.load(getClass().getResource("ShopsLoginScene.fxml"));
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();

        // Create a new scene using the loaded root
        scene = new Scene(root);

        // Set the new scene on the stage
        stage.setScene(scene);

        // Show the new scene
        stage.show();
    }

}
