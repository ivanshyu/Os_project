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
import javafx.concurrent.Task;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;
import java.util.Timer;
import java.util.TimerTask;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.PathTransition;
import javafx.animation.Timeline;
import javafx.application.Platform;
import javafx.concurrent.Task;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.scene.shape.Line;
import javafx.scene.shape.Polyline;
import javafx.util.Duration;
import javax.swing.SwingUtilities;
import java.util.concurrent.TimeUnit;
import java.awt.Robot;
import java.util.Random;




public class FXMLDocumentController extends Thread implements Initializable {

    int w=0,e=0;
    
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
        east.setText(Integer.toString(b));
    }
    
    @FXML
    public void Come_west(ActionEvent event){
        w+=1;
        west_num(w);
    }

    @FXML
    public void Come_east(ActionEvent event){
        e+=1;
        east_num(e);
    }
    
    @FXML
    public void Run_west(ActionEvent event) {
        /*
        Image image = new Image(OS_Project.class.getResourceAsStream("west_car.jpg"));
        imageview.setImage(image);        
        */
        if(w!=0){
            w-=1;
            west_num(w);

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
    }
    
    @FXML
    public void Run_east(ActionEvent event) {
        /*
        Image image = new Image(OS_Project.class.getResourceAsStream("west_car.jpg"));
        imageview.setImage(image);        
        */
        if(e!=0)
        {
            e-=1;
            east_num(e);

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
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    
}