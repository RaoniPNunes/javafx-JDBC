

import java.io.IOException;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.ScrollPane;
import javafx.stage.Stage;

/**
 *
 * @author raoni
 */

public class Main extends Application {
    
    @Override
    public void start(Stage primaryStage) {
       try{
           FXMLLoader loader = new FXMLLoader(getClass().getResource("/gui/MainView.fxml"));
           ScrollPane scrollPane = loader.load();
           
           scrollPane.setFitToWidth(true);
           scrollPane.setFitToHeight(true);
           
           Scene mainScene = new Scene(scrollPane);
           primaryStage.setScene(mainScene);
           primaryStage.setTitle("Registrador");
           primaryStage.show();
       }
       catch(IOException e){
           e.printStackTrace();
       }
    }

    
    public static void main(String[] args) {
        launch(args);
    }
    
}
