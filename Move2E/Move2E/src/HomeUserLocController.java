/*import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;

import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class HomeUserLocController implements Initializable {

    @FXML
    private Button Aboutus;

    @FXML
    private Button contact;

    @FXML
    private Button findShop;

    @FXML
    private Label locN;

    @FXML
    private ScrollPane scrollV;

    @FXML
    private Button signout;

    @FXML
    private Label userN;

    @FXML
    private TextField locationInput; // Text field for entering location

    private String userLocation;
    private VBox shopContainer;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        shopContainer = new VBox(10);
        scrollV.setContent(shopContainer);
    }

    @FXML
    void Aboutas(ActionEvent event) {
        // Handle the About Us button click
    }

    @FXML
    void ContInfo(ActionEvent event) {
        // Handle the Contact Info button click
    }

    @FXML
    void Signout(ActionEvent event) {
        // Handle the Sign Out button click
    }

    @FXML
    void findShop(ActionEvent event) {
        // Get the location entered by the user
        userLocation = locationInput.getText();

        // Retrieve shops based on the user's location
        ArrayList<Shop> shopList = ShopDAO.getShopsByLocation(userLocation);

        shopContainer.getChildren().clear(); // Clear existing shop list

        for (Shop shop : shopList) {
            Pane shopPane = createShopPane(shop);
            shopContainer.getChildren().add(shopPane);
        }
    }

    private Pane createShopPane(Shop shop) {
        Pane shopPane = new Pane();

        // Add shop information to the Pane
        ImageView shopImage = new ImageView(new Image(shop.getImageUrl()));
        Label shopNameLabel = new Label(shop.getName());

        // Customize the layout and styling of shopPane
        shopImage.setLayoutX(10);
        shopImage.setLayoutY(10);

        shopNameLabel.setLayoutX(10);
        shopNameLabel.setLayoutY(160); // Adjust the Y position as needed

        shopPane.getChildren().addAll(shopImage, shopNameLabel);

        return shopPane;
    }

    public void setLocation(String location, String uName) {
        userN.setText(uName);
    }
}


class ShopDAO {
    public static ArrayList<Shop> getShopsByLocation(String location) {
        ArrayList<Shop> shops = new ArrayList<>();
        String query = "SELECT id, name, shop_img FROM shop_info WHERE location = ?";

            try (Connection connection = ConnectionProvider.getConnection();
        PreparedStatement statement = connection.prepareStatement(query)) {
        statement.setString(1, location);

        try (ResultSet resultSet = statement.executeQuery()) {
            while (resultSet.next()) {
                int id = resultSet.getInt("id");
                String name = resultSet.getString("name");
                String imageUrl = resultSet.getString("shop_img");
                Shop shop = new Shop(id, name, imageUrl);
                shops.add(shop);
            }
        }
    } catch (SQLException e) {
        e.printStackTrace();
    }


        return shops;
    }
}

class Shop {
    private int id;
    private String name;
    private String imageUrl;

    public Shop(int id, String name, String imageUrl) {
        this.id = id;
        this.name = name;
        this.imageUrl = imageUrl;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getImageUrl() {
        return imageUrl;
    }
}
*/

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.io.ByteArrayInputStream;
import java.io.IOException;

public class HomeUserLocController {

    private Stage stage;
    private Scene scene;
    private Parent root;
    
    @FXML
    private Button Aboutus;

    @FXML
    private Button btn1;

    @FXML
    private Button btn2;

    @FXML
    private Button btn3;

    @FXML
    private Button btn4;

    @FXML
    private Button btn5;

    @FXML
    private Button btn6;

    @FXML
    private Button contact;

    @FXML
    private ImageView img1;

    @FXML
    private ImageView img2;

    @FXML
    private ImageView img3;

    @FXML
    private ImageView img4;

    @FXML
    private ImageView img5;

    @FXML
    private ImageView img6;

    @FXML
    private Label shopN1;

    @FXML
    private Label shopN2;

    @FXML
    private Label shopN3;

    @FXML
    private Label shopN4;

    @FXML
    private Label shopN5;

    @FXML
    private Label shopN6;

    @FXML
    private Button signout;

    @FXML
    private Label userN;

    @FXML
    void Aboutas(ActionEvent event) {

    }

    @FXML
    void ContInfo(ActionEvent event) {

    }

    @FXML
    void Signout(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("HomeUserScene.fxml"));
        root = loader.load();
        
        // Access the controller of the next scene
        HomeUserController homeUserController = loader.getController();
        
        // Pass the shop_info ID to the ShopStoreProdController
        homeUserController.setUName(userN.getText());
    
        Scene scene = new Scene(root);
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setScene(scene);
        stage.show();
    }

    public void setLocation(String location, String uName) {
        userN.setText(uName);
    
        ResultSet resultSet = getShopInfoByLocation(location);
        System.out.println(resultSet);
        int index = 1; // Start from 1
    
        try {
            while (index <= 6 && resultSet.next()) { // Move the cursor to the first row and check if there are more rows
                String shopName = resultSet.getString("name");
                byte[] shopImageBytes = resultSet.getBytes("shop_img");
                System.out.println(shopName);
                // Update shop name labels and image views
                Label shopNameLabel = getShopNameLabel(index);
                ImageView imageView = getImageView(index);
    
                if (shopNameLabel != null && imageView != null) {
                    shopNameLabel.setText(shopName);
    
                    // Display shop image
                    if (shopImageBytes != null) {
                        Image shopImage = new Image(new ByteArrayInputStream(shopImageBytes));
                        imageView.setImage(shopImage);
                    }
                }
    
                index++;
            }
            System.out.println(index);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private Label getShopNameLabel(int index) {
        switch (index) {
            case 1:
                return shopN1;
            case 2:
                return shopN2;
            case 3:
                return shopN3;
            case 4:
                return shopN4;
            case 5:
                return shopN5;
            case 6:
                return shopN6;
            default:
                return null;
        }
    }

    private ImageView getImageView(int index) {
        switch (index) {
            case 1:
                return img1;
            case 2:
                return img2;
            case 3:
                return img3;
            case 4:
                return img4;
            case 5:
                return img5;
            case 6:
                return img6;
            default:
                return null;
        }
    }

    public ResultSet getShopInfoByLocation(String location) {
        Connection connection = ConnectionProvider.getConnection();
        ResultSet resultSet = null;
    
        try {
            if (connection != null) {
                System.out.println("Database connection is not null."); // Debug statement
                String query = "SELECT name, shop_img FROM shop_info WHERE location = ?";
                PreparedStatement preparedStatement = connection.prepareStatement(query);
                preparedStatement.setString(1, location);
                resultSet = preparedStatement.executeQuery();
            } else {
                System.out.println("Database connection is null."); // Debug statement
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    
        return resultSet;
    }
}


