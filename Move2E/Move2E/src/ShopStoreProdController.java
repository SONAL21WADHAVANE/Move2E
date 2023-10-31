/*import java.io.File;
import java.io.FileInputStream;
import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.FileChooser;
import javafx.stage.FileChooser.ExtensionFilter;
import javafx.stage.Stage;

public class ShopStoreProdController {

    @FXML
    private Button addP;
    int shopId;
    @FXML
    private Button addProd;

    @FXML
    private Button existProd;

    @FXML
    private Button imgbtn;

    @FXML
    private TextField prodName;

    @FXML
    private TextField prodPrice;

    @FXML
    private Label shopN;

    @FXML
    void addProucts(ActionEvent event) {

    }

    @FXML
    void existProducts(ActionEvent event) {

    }

    @FXML
    void pickImage(ActionEvent event) {
        FileChooser fileChooser = new FileChooser();
        File selectedFile = fileChooser.showOpenDialog(new Stage());

        if (selectedFile != null) {
            // Display the selected file path in a label or perform other actions as needed
            String imagePath = selectedFile.getAbsolutePath();
            // You can display the path in a label, e.g., imageLabel.setText(imagePath);
        } else {
            // Handle the case when the user cancels the file dialog
        }
    }



    @FXML
    void storeProd(ActionEvent event) {
        String productName = prodName.getText();
        String productPriceText = prodPrice.getText();

        if (productName.isEmpty() || productPriceText.isEmpty()) {
            // Show an alert to inform the user
            Alert alert = new Alert(AlertType.ERROR);
            alert.setTitle("Validation Error");
            alert.setHeaderText(null);
            alert.setContentText("Product name and price are required.");
            alert.showAndWait();
            return;
        }

        try {
            BigDecimal productPrice = new BigDecimal(productPriceText);

    

            Connection connection = ConnectionProvider.getConnection();

            // Read the selected image file into a byte array
            FileChooser fileChooser = new FileChooser();
            fileChooser.getExtensionFilters().addAll(
                new ExtensionFilter("Image Files", "*.png", "*.jpg", "*.jpeg", "*.gif", "*.bmp")
            );
            File imageFile = fileChooser.showOpenDialog(new Stage());

            if (imageFile != null) {
                FileInputStream fis = new FileInputStream(imageFile);
                byte[] imageData = new byte[(int) imageFile.length()];
                fis.read(imageData);
                fis.close();

                // Prepare SQL statement to insert the product
                String insertQuery = "INSERT INTO products (product_name, product_price, product_image, id) VALUES (?, ?, ?, ?)";
                PreparedStatement preparedStatement = connection.prepareStatement(insertQuery);
                preparedStatement.setString(1, productName);
                preparedStatement.setBigDecimal(2, productPrice);
                preparedStatement.setBytes(3, imageData);
                
                preparedStatement.setInt(4, shopId);

                // Execute the SQL statement to insert the product
                preparedStatement.executeUpdate();

                // Close resources
                preparedStatement.close();
                connection.close();

                // Provide user feedback or perform other actions as needed
                Alert successAlert = new Alert(AlertType.INFORMATION);
                successAlert.setTitle("Success");
                successAlert.setHeaderText(null);
                successAlert.setContentText("Product stored in the database successfully.");
                successAlert.showAndWait();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}*/

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
import javafx.scene.control.TextField;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javafx.stage.FileChooser.ExtensionFilter;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;

public class ShopStoreProdController {

    private Stage stage;
    private Scene scene;
    private Parent root;
    
    @FXML
    private Button addP;

    @FXML
    private Button back;

    @FXML
    private Button addProd;

    @FXML
    private Button existProd;

    @FXML
    private Label imageLabel;

    @FXML
    private Button imgbtn;

    @FXML
    private TextField prodName;

    @FXML
    private TextField prodPrice;

    @FXML
    private Label shopN;

    private int id;
    private String uName;

    private File selectedImageFile; // Store the selected image file

    @FXML
    void addProucts(ActionEvent event) {
        // Add your code for adding products here
    }

    public void setShopInfoId1(int shopInfoId,String uName) {
        this.id = shopInfoId;
        this.uName = uName;
        shopN.setText(uName);
        
    }

    @FXML
    void existProducts(ActionEvent event) {
        // Add your code for existing products here
    }

    @FXML
    void pickImage(ActionEvent event) {
        FileChooser fileChooser = new FileChooser();
        fileChooser.getExtensionFilters().addAll(
            new ExtensionFilter("Image Files", "*.png", "*.jpg", "*.jpeg", "*.gif", "*.bmp")
        );
        selectedImageFile = fileChooser.showOpenDialog(new Stage());

        if (selectedImageFile != null) {
            // Display the selected file path in a label or perform other actions as needed
            String imagePath = selectedImageFile.getAbsolutePath();
            imageLabel.setText(imagePath);
        } else {
            // Handle the case when the user cancels the file dialog
        }
    }

    @FXML
    void redirect(ActionEvent event) throws IOException {
         root = FXMLLoader.load(getClass().getResource("ShopHomeScene.fxml"));
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        
        // Create a new scene using the loaded root
        scene = new Scene(root);
        
        // Set the new scene on the stage
        stage.setScene(scene);
        
        // Show the new scene
        stage.show();
    }

    @FXML
    void storeProd(ActionEvent event) {
        String productName = prodName.getText();
        String productPriceText = prodPrice.getText();

        if (productName.isEmpty() || productPriceText.isEmpty() || selectedImageFile == null) {
            // Show an alert to inform the user
            Alert alert = new Alert(AlertType.ERROR);
            alert.setTitle("Validation Error");
            alert.setHeaderText(null);
            alert.setContentText("Product name, price, and image are required.");
            alert.showAndWait();
            return;
        }

        try {
            BigDecimal productPrice = new BigDecimal(productPriceText);

           

            Connection connection = ConnectionProvider.getConnection();
            // Read the selected image file into a byte array
            FileInputStream fis = new FileInputStream(selectedImageFile);
            byte[] imageData = new byte[(int) selectedImageFile.length()];
            fis.read(imageData);
            fis.close();

            // Prepare SQL statement to insert the product
            String insertQuery = "INSERT INTO products (product_name, product_price, product_image, id) VALUES (?, ?, ?, ?)";
            PreparedStatement preparedStatement = connection.prepareStatement(insertQuery);
            preparedStatement.setString(1, productName);
            preparedStatement.setBigDecimal(2, productPrice);
            preparedStatement.setBytes(3, imageData);
            preparedStatement.setInt(4, id);

            // Execute the SQL statement to insert the product
            preparedStatement.executeUpdate();

        

            // Provide user feedback or perform other actions as needed
            Alert successAlert = new Alert(AlertType.INFORMATION);
            successAlert.setTitle("Success");
            successAlert.setHeaderText(null);
            successAlert.setContentText("Product stored in the database successfully.");
            successAlert.showAndWait();

            prodName.clear();
            prodPrice.clear();
            imageLabel.setText(""); 

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

