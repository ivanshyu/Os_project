package os_project;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.paint.Color;

public class OS_Project extends Application {
    
    @Override
    public void start(Stage stage) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("bridge.fxml"));
        stage.setTitle("OS_Project");
        Scene scene = new Scene(root);
        
        /*
        ImageView imageview = new ImageView();
        Image image = new Image(OS_Project.class.getResourceAsStream("west_car.jpg"));
        imageview.setImage(image);
        
        root.getChildren().add(imageview);
        */
        
        stage.setScene(scene);
        stage.show();
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }
    
}
