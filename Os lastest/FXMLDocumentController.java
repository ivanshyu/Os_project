package os_project;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.Group;
import javafx.scene.shape.Circle;
import javafx.scene.paint.Color;
import javafx.scene.shape.StrokeType;
import javafx.scene.effect.BoxBlur;
import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.Timeline;
import javafx.util.Duration;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Text;



public class FXMLDocumentController implements Initializable {
    
    @FXML
    //private ImageView imageview;
    private AnchorPane anchorpane;
    @FXML
    private Text west;
    @FXML
    private Text east;
   
    @FXML
    public void west_num(int a){
        west.setText(Integer.toString(a));
    }
    
    @FXML
    public void east_num(int b){
        west.setText(Integer.toString(b));
    }
    
    @FXML
    public void Run_west(ActionEvent event) {
        /*
        Image image = new Image(OS_Project.class.getResourceAsStream("west_car.jpg"));
        imageview.setImage(image);        
        */
        ImageView imageview = new ImageView();
        imageview.setX(39);
        imageview.setY(135);
        Image image = new Image(OS_Project.class.getResourceAsStream("west_car.jpg"));
        imageview.setImage(image);
        
        anchorpane.getChildren().add(imageview);
        
        Timeline timeline = new Timeline();
 
        timeline.getKeyFrames().addAll(
            new KeyFrame(new Duration(1200),
            new KeyValue(imageview.translateXProperty(), 100),
            new KeyValue(imageview.translateYProperty(), 89))
            ,
            new KeyFrame(new Duration(4200),
            new KeyValue(imageview.translateXProperty(), 440),
            new KeyValue(imageview.translateYProperty(), 89))
            ,
            new KeyFrame(new Duration(5400),
            new KeyValue(imageview.translateXProperty(), 540),
            new KeyValue(imageview.translateYProperty(), 0))
            ,
            new KeyFrame(new Duration(5401),
            new KeyValue(imageview.translateXProperty(), 1000000000),
            new KeyValue(imageview.translateYProperty(), 2000000000))
        );
        timeline.play();
    }
    
    @FXML
    public void Run_east(ActionEvent event) {
        /*
        Image image = new Image(OS_Project.class.getResourceAsStream("west_car.jpg"));
        imageview.setImage(image);        
        */
        ImageView imageview = new ImageView();
        imageview.setX(579);
        imageview.setY(313);
        Image image = new Image(OS_Project.class.getResourceAsStream("east_car.jpg"));
        imageview.setImage(image);
        
        anchorpane.getChildren().add(imageview);
        
        Timeline timeline = new Timeline();

        timeline.getKeyFrames().addAll(
            new KeyFrame(new Duration(1200),
            new KeyValue(imageview.translateXProperty(), -100),
            new KeyValue(imageview.translateYProperty(), -89))
            ,
            new KeyFrame(new Duration(4200),
            new KeyValue(imageview.translateXProperty(), -440),
            new KeyValue(imageview.translateYProperty(), -89))
            ,
            new KeyFrame(new Duration(5400),
            new KeyValue(imageview.translateXProperty(), -540),
            new KeyValue(imageview.translateYProperty(), 0))
            ,
            new KeyFrame(new Duration(5401),
            new KeyValue(imageview.translateXProperty(), -5400000),
            new KeyValue(imageview.translateYProperty(), -100000000))
        );
        timeline.play();
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    
}