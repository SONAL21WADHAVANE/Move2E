import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import java.util.regex.Pattern;
import java.util.regex.Matcher;

public class UserRegisController {
    
    private Stage stage;
    private Scene scene;
    private Parent root;

    @FXML
    private Button back;

    @FXML
    private PasswordField RUserpass;

    @FXML
    private TextField RUsermob;

    @FXML
    private Label RUsername;

    @FXML
    private TextField RUsernameEmail;

    @FXML
    private PasswordField RUserRepass;


    @FXML
    private RadioButton female;

    @FXML
    private RadioButton male;

    @FXML
    private TextField uName;

    @FXML
    private TextField userLoc;

    @FXML
    private Button userSignup;

    @FXML
    void UserSignup(ActionEvent event) {
        
        String username = uName.getText();
        String email = RUsernameEmail.getText();
        String password = RUserpass.getText();
        String confirmPassword = RUserRepass.getText();
        String mobile = RUsermob.getText();
        String location = userLoc.getText();

        if (username.isEmpty() || email.isEmpty() || password.isEmpty() || confirmPassword.isEmpty() || mobile.isEmpty() || location.isEmpty()) {
            
            showAlert(AlertType.ERROR, "Error", "Missing Information", "Please fill in all fields.");
        } else if (!isValidEmail(email)) {
            
            showAlert(AlertType.ERROR, "Error", "Invalid Email", "Please enter a valid email address.");
        } else if (!isValidMobile(mobile)) {
            
            showAlert(AlertType.ERROR, "Error", "Invalid Mobile Number", "Please enter a valid mobile number.");
        } else if (!isValidPassword(password)) {
            
            showAlert(AlertType.ERROR, "Error", "Invalid Password", "Password should meet the requirements.");
        } else if (!password.equals(confirmPassword)) {
            
            showAlert(AlertType.ERROR, "Error", "Password Mismatch", "Passwords do not match.");
        } else {
            
            
            insertUserIntoDatabase(username,email,password,mobile,location,event);
            
        }
    }

    @FXML
    void rediLogin(ActionEvent event) throws IOException {

        root = FXMLLoader.load(getClass().getResource("UserLoginScene.fxml"));
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        
        // Create a new scene using the loaded root
        scene = new Scene(root);
        
        // Set the new scene on the stage
        stage.setScene(scene);
        
        // Show the new scene
        stage.show();
    }

    private void insertUserIntoDatabase(String username, String email, String password, String mobile, String location,ActionEvent event) {
        Connection connection = null;
        PreparedStatement preparedStatement = null;

        try {
            // Create a database connection
            connection = ConnectionProvider.getConnection();

            // Define your SQL insert statement (modify this as per your table structure)
            String insertQuery = "INSERT INTO users (username, email, password, mobile, location) VALUES (?, ?, ?, ?, ?)";

            // Create a prepared statement
            preparedStatement = connection.prepareStatement(insertQuery);
            preparedStatement.setString(1, username);
            preparedStatement.setString(2, email);
            preparedStatement.setString(3, password);
            preparedStatement.setString(4, mobile);
            preparedStatement.setString(5, location);

            // Execute the insert query
            preparedStatement.executeUpdate();

            // Database operation was successful, you can show a success message
            showAlert(AlertType.INFORMATION, "Success", "User Registration", "User registered successfully!");
            redirectToUL(event);
        } catch (SQLException e) {
            e.printStackTrace();
            showAlert(AlertType.ERROR, "Error", "Database Error", "Failed to register user.");
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } finally {
            // Close the resources
            try {
                if (preparedStatement != null) {
                    preparedStatement.close();
                }
                if (connection != null) {
                    connection.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }


    private void redirectToUL(ActionEvent event) throws IOException {

        root = FXMLLoader.load(getClass().getResource("UserLoginScene.fxml"));
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        
        // Create a new scene using the loaded root
        scene = new Scene(root);
        
        // Set the new scene on the stage
        stage.setScene(scene);
        
        // Show the new scene
        stage.show();
    }

        private void showAlert(AlertType type, String title, String header, String content) {
        Alert alert = new Alert(type);
        alert.setTitle(title);
        alert.setHeaderText(header);
        alert.setContentText(content);
        alert.showAndWait();
    }

    // Validate email using a regular expression
    private boolean isValidEmail(String email) {
        String emailRegex = "^[A-Za-z0-9+_.-]+@(.+)$";
        Pattern pattern = Pattern.compile(emailRegex);
        Matcher matcher = pattern.matcher(email);
        return matcher.matches();
    }

    // Validate mobile number (assuming 10 digits)
    private boolean isValidMobile(String mobile) {
        return mobile.matches("\\d{10}");
    }

    // Validate password (customize the regex based on your requirements)
    private boolean isValidPassword(String password) {
        // For example, require at least 8 characters with at least one digit and one special character
        return password.matches("^(?=.*[0-9])(?=.*[!@#$%^&*])(?=\\S+$).{8,}$");
    }

}
