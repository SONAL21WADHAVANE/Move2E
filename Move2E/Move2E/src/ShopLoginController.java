import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class ShopLoginController {

    private Stage stage;
    private Scene scene;
    private Parent root;

    @FXML
    private CheckBox ShopRemaindid;

    @FXML
    private Button optionP;

    @FXML
    private Button regisShop;

    @FXML
    private Button shopForgotid;

    @FXML
    private Button shopLogin;

    @FXML
    private TextField shopPass;

    @FXML
    private TextField shopUName;


    @FXML
    void OnClickCreate(ActionEvent event) {

    }

    @FXML
    void ShopLogin(ActionEvent event) {
        
        String username = shopUName.getText();
        String password = shopPass.getText();

    // Perform the login check by querying your database
    if (isValidLogin(username, password)) {
        // If the login is successful, redirect to the home page
        int id = getId(username);
        String name = getShopName(username);
        try {
            redirectToHomePage(event,id,name);
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    } else {
        // If the login fails, display an error message
        // You can show an error message to the user or clear the fields, etc.
        showAlert("Login Failed", "Invalid credentials. Please try again.");
    }
    }

    @FXML
    void shopRegis(ActionEvent event) throws IOException {
        
        root = FXMLLoader.load(getClass().getResource("ShopRegisScene.fxml"));
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        
        // Create a new scene using the loaded root
        scene = new Scene(root);
        
        // Set the new scene on the stage
        stage.setScene(scene);
        
        // Show the new scene
        stage.show();
    }
    private boolean isValidLogin(String username, String password) {

        try {
            Connection connection = ConnectionProvider.getConnection();
            String query = "SELECT * FROM shop_info WHERE username = ? AND pass = ?";
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, username);
            preparedStatement.setString(2, password);
            ResultSet resultSet = preparedStatement.executeQuery();
            return resultSet.next(); // Returns true if a matching record is found
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    private void showAlert(String title, String content) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(content);
        alert.showAndWait();
    }

    void redirectToHomePage(ActionEvent event, int shopInfoId,String username) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("ShopHomeScene.fxml"));
        Parent root = loader.load();
        
        // Access the controller of the next scene
        ShopHomeController shopHomeController = loader.getController();
        
        // Pass the shop_info ID to the ShopStoreProdController
        shopHomeController.setShopInfo(shopInfoId,username);
    
        Scene scene = new Scene(root);
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setScene(scene);
        stage.show();
    }
 
    static int getId(String username) {
 
        
   
        try {
            Connection connection = ConnectionProvider.getConnection();
            String query = "SELECT id FROM shop_info WHERE username = ?";
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, username);
            ResultSet resultSet = preparedStatement.executeQuery();
    
            if (resultSet.next()) {
                return resultSet.getInt("id");
            } else {
                return -1; // Return a default value (or any other appropriate value) if no matching record is found
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return -1; // Handle the error and return a default value
        }
    }

    static String getShopName(String username) {

        try {
            Connection connection = ConnectionProvider.getConnection();
            String query = "SELECT name FROM shop_info WHERE username = ?";
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, username);
            ResultSet resultSet = preparedStatement.executeQuery();
    
            if (resultSet.next()) {
                return resultSet.getString("name"); // Retrieve the "name" column as a string
            } else {
                return null; // Return null or any other appropriate value if no matching record is found
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return null; // Handle the error and return null or an appropriate value
        }
    }

    @FXML
    void rediOptionP(ActionEvent event) throws IOException {
        root = FXMLLoader.load(getClass().getResource("OptionScene.fxml"));
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        
        scene = new Scene(root);
        
        stage.setScene(scene);
        stage.show();
    }
    
    
    
}
