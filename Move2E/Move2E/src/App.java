import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class App extends Application {

    public Stage stage;
    @Override
    public void start(Stage primaryStage) throws Exception{
        stage= primaryStage;
        // Load the FXML file
        FXMLLoader loader = new FXMLLoader(getClass().getResource("HomeScene.fxml"));
        Parent root = loader.load();

        
        // Get the controller instance
        HomeController controller = loader.getController();                         


        // Set up the primary stage
        primaryStage.setTitle("Move2E");
        primaryStage.setScene(new Scene(root, 1400, 800));


        primaryStage.setResizable(false);
        // Show the stage
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
