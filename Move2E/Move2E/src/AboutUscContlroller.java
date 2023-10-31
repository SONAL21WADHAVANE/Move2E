import java.io.IOException;
import javafx.scene.Node;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;

public class AboutUscContlroller {

    @FXML
    
    private Button btn;
    private Stage stage;
    private Scene scene;
    private Parent root;

    @FXML
    void back(ActionEvent event)  throws IOException{
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
