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
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;

public class UserLoginController {

    private Stage stage;
    private Scene scene;
    private Parent root;
    
    @FXML
    private Button optionP;

    @FXML
    private Button ULogin;

    @FXML
    private Button createAcc;

    @FXML
    private Button mainForgot;

    @FXML
    private ImageView mainImage;

    @FXML
    private ImageView mainImage1;

    @FXML
    private ImageView mainImage2;

    @FXML
    private PasswordField mainPassword;

    @FXML
    private CheckBox mainRem;

    @FXML
    private TextField mainUsername;

    @FXML
    void UserLogin(ActionEvent event) throws IOException {

        String username = mainUsername.getText();
        String password = mainPassword.getText();

        // Perform the login check by querying your database
        if (isValidLogin(username, password)) {
            // If the login is successful, redirect to the home page

            redirectToHomePage(event,username);
        } else {
            // If the login fails, display an error message
            // You can show an error message to the user or clear the fields, etc.
            showAlert("Login Failed", "Invalid credentials. Please try again.");
        }
    }

    @FXML
    void createAccount(ActionEvent event) throws IOException{

        root = FXMLLoader.load(getClass().getResource("UserRegisScene.fxml"));
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        
        // Create a new scene using the loaded root
        scene = new Scene(root);
        
        // Set the new scene on the stage
        stage.setScene(scene);
        
        // Show the new scene
        stage.show();
    }

    @FXML
    void mainonAction(ActionEvent event) {

    }

    void redirectToHomePage(ActionEvent event,String username) throws IOException{
        
     
        FXMLLoader loader = new FXMLLoader(getClass().getResource("HomeUserScene.fxml"));
        root = loader.load();
        
        // Access the controller of the next scene
        HomeUserController homeUserController = loader.getController();
        
        // Pass the shop_info ID to the ShopStoreProdController
        homeUserController.setUName(username);
    
        Scene scene = new Scene(root);
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setScene(scene);
        stage.show();
    }

    private boolean isValidLogin(String username, String password) {

        try {
            Connection connection = ConnectionProvider.getConnection();
            String query = "SELECT * FROM users WHERE username = ? AND password = ?";
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

    @FXML
    void rediOptionP(ActionEvent event) throws IOException {
        root = FXMLLoader.load(getClass().getResource("OptionScene.fxml"));
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        
        // Create a new scene using the loaded root
        scene = new Scene(root);
        
        // Set the new scene on the stage
        stage.setScene(scene);
        
        // Show the new scene
        stage.show();
    }
}
