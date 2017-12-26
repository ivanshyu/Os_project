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
        imageview.setX(-30);
        imageview.setY(135);
        Image image = new Image(OS_Project.class.getResourceAsStream("west_car.png"));
        imageview.setImage(image);
        
        anchorpane.getChildren().add(imageview);
        
        Timeline timeline = new Timeline();
 
        timeline.getKeyFrames().addAll(
            new KeyFrame(new Duration(1000),
            new KeyValue(imageview.translateXProperty(), 79),
            new KeyValue(imageview.translateYProperty(), 0))
            ,
            new KeyFrame(new Duration(2000),
            new KeyValue(imageview.translateXProperty(), 159),
            new KeyValue(imageview.translateYProperty(), 89))
            ,
            new KeyFrame(new Duration(5000),
            new KeyValue(imageview.translateXProperty(), 499),
            new KeyValue(imageview.translateYProperty(), 89))
            ,
            new KeyFrame(new Duration(6000),
            new KeyValue(imageview.translateXProperty(), 599),
            new KeyValue(imageview.translateYProperty(), 0))
            ,
            new KeyFrame(new Duration(7000),
            new KeyValue(imageview.translateXProperty(), 720),
            new KeyValue(imageview.translateYProperty(), 0))
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
        imageview.setX(640);
        imageview.setY(313);
        Image image = new Image(OS_Project.class.getResourceAsStream("east_car.png"));
        imageview.setImage(image);
        
        anchorpane.getChildren().add(imageview);
        
        Timeline timeline = new Timeline();

        timeline.getKeyFrames().addAll(
            new KeyFrame(new Duration(1000),
            new KeyValue(imageview.translateXProperty(), -79),
            new KeyValue(imageview.translateYProperty(), 0))
            ,
            new KeyFrame(new Duration(2000),
            new KeyValue(imageview.translateXProperty(), -159),
            new KeyValue(imageview.translateYProperty(), -89))
            ,
            new KeyFrame(new Duration(5000),
            new KeyValue(imageview.translateXProperty(), -499),
            new KeyValue(imageview.translateYProperty(), -89))
            ,
            new KeyFrame(new Duration(6000),
            new KeyValue(imageview.translateXProperty(), -599),
            new KeyValue(imageview.translateYProperty(), 0))
            ,
            new KeyFrame(new Duration(7000),
            new KeyValue(imageview.translateXProperty(), -720),
            new KeyValue(imageview.translateYProperty(), 0))
        );
        timeline.play();
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    
}