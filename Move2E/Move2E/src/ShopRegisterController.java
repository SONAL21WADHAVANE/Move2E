import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.FileChooser;
import javafx.stage.FileChooser.ExtensionFilter;
import javafx.stage.Stage;

public class ShopRegisterController {

    private Stage stage;
    private Scene scene;
    private Parent root;

    @FXML
    private Button back;

    @FXML
    private PasswordField RePass;

    @FXML
    private TextField ShopLocation;

    @FXML
    private TextField ShopOwnerName;

    @FXML
    private Button ShopRegister;

    @FXML
    private TextField email;

    @FXML
    private PasswordField passW;

    @FXML
    private TextField shopId;

    @FXML
    private TextField shopName;

    @FXML
    private Label imgN;

    @FXML
    private TextField shopType;

    private File selectedImageFile;

    @FXML
    void rediLogin(ActionEvent event) throws IOException {

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
    void shopRegister(ActionEvent event) {
        String ShopOwner = ShopOwnerName.getText();
        String username = email.getText();
        String pass = passW.getText();
        String confirmPassword = RePass.getText();
        String location = ShopLocation.getText();
        String Shopid = shopId.getText();
        String Shopname = shopName.getText();
        String type = shopType.getText();

        if (ShopOwner.isEmpty() || username.isEmpty() || pass.isEmpty() || confirmPassword.isEmpty() || location.isEmpty() || Shopid.isEmpty() || Shopname.isEmpty() || type.isEmpty() || selectedImageFile == null) {
            showAlert(AlertType.ERROR, "Error", "Missing Information", "Please fill in all fields.");
        } else if (!isValidPassword(pass)) {
            showAlert(AlertType.ERROR, "Error", "Invalid Password", "Password should meet the requirements.");
        } else if (!pass.equals(confirmPassword)) {
            showAlert(AlertType.ERROR, "Error", "Password Mismatch", "Passwords do not match.");
        } else {
            insertShopData(ShopOwner, username, pass, location, Shopid, Shopname, type,selectedImageFile, event);
        }
    }

    @FXML
    void addIMG(ActionEvent event) {
        FileChooser fileChooser = new FileChooser();
        fileChooser.getExtensionFilters().addAll(
            new ExtensionFilter("Image Files", "*.png", "*.jpg", "*.jpeg", "*.gif", "*.bmp")
        );
        selectedImageFile = fileChooser.showOpenDialog(new Stage());

        if (selectedImageFile != null) {
            // Display the selected file path in a label or perform other actions as needed
            String imagePath = selectedImageFile.getAbsolutePath();
            imgN.setText(imagePath);
        } else {
            // Handle the case when the user cancels the file dialog
        }
    }

    private void showAlert(AlertType type, String title, String header, String content) {
        Alert alert = new Alert(type);
        alert.setTitle(title);
        alert.setHeaderText(header);
        alert.setContentText(content);
        alert.showAndWait();
    }

    private boolean isValidPassword(String pass) {
        // Customize the regex for your password requirements
        return pass.matches("^(?=.*[0-9])(?=.*[!@#$%^&*])(?=\\S+$).{8,}$");
    }

    private void insertShopData(String shopOwner, String username, String password, String location, String id1, String name, String type, File selectedImageFile, ActionEvent event) {

        Connection connection = null;
        PreparedStatement preparedStatement = null;

        try {
            // Establish a connection
            connection = ConnectionProvider.getConnection();

            // Read the selected image file into a byte array
            FileInputStream fis = new FileInputStream(selectedImageFile);
            byte[] imageData = new byte[(int) selectedImageFile.length()];
            fis.read(imageData);
            fis.close();

            // Define your INSERT SQL query
            String insertQuery = "INSERT INTO shop_info (shopOwner, username, pass, location, id1, name, type, shop_img) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";

            // Create a PreparedStatement
            preparedStatement = connection.prepareStatement(insertQuery);
            preparedStatement.setString(1, shopOwner);
            preparedStatement.setString(2, username);
            preparedStatement.setString(3, password);
            preparedStatement.setString(4, location);
            preparedStatement.setString(5, id1);
            preparedStatement.setString(6, name);
            preparedStatement.setString(7, type);
            preparedStatement.setBytes(8, imageData);

            // Execute the insert statement
            int rowsAffected = preparedStatement.executeUpdate();


            // Check if the insertion was successful
            showAlert(AlertType.INFORMATION, "Success", "Shop Registration", "User registered successfully!");
            redirectToSL(event);
        } catch (SQLException e) {
            e.printStackTrace();
            showAlert(AlertType.ERROR, "Error", "Database Error", "Failed to register user.");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void redirectToSL(ActionEvent event) throws IOException {

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
