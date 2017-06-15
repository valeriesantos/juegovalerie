import javafx.application.Application;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.Group;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.util.Duration;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.animation.Animation;
import java.util.Random;
import javafx.scene.input.KeyCode;
import java.util.Timer;
import java.util.TimerTask;
import java.util.ArrayList;
import javafx.scene.shape.Shape;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.Group; 
import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.transform.Rotate; 
import javafx.scene.media.AudioClip;

/**
 * Write a description of class Juego here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Paparazzi extends Application
{
    private int velocidadX = 2;
    private int velocidadY=2;
    private Timeline timeline;   
    private int velocidadLadrillo = -1;
    private int subirPaparazzi = 50;

    public static void main(String args[]) { 
        launch(args) ; 
    }

    @Override
    public void start(Stage escenario)
    {
        //objeto paparazzi
        Image image = new Image("camara.gif");
        ImageView im = new ImageView();
        im.setTranslateX(50);
        im.setTranslateY(200);
        im.setX(0);
        im.setY(200);
        im.setFitHeight(100);
        im.setFitWidth(90);
        im.setImage(image);
        
        
        //gif game over
         Image gameOver = new Image("gameover.gif");
        ImageView gameO = new ImageView();
        gameO.setTranslateX(00);
        gameO.setTranslateY(0);
        gameO.setX(0);
        gameO.setY(50);
        gameO.setFitHeight(500);
        gameO.setFitWidth(500);
        gameO.setImage(gameOver);
        gameO.setVisible(false);

        //etiqueta parte superior derecha 
        Label ladrillosSaltados = new Label("Ladrillos saltados: ");
        ladrillosSaltados.setTranslateX(250);

        //grupo y escena
        Group root = new Group(im,gameO, ladrillosSaltados);
        Scene scene = new Scene(root, 500, 500); // definimos escena 

        //creamos arraylist
        ArrayList <ImageView> ladrillo = new ArrayList<ImageView>();

        //objeto ladrillo
        Image image2 = new Image("ladrillo.png");
        ImageView im2 = new ImageView();
        im2.setTranslateX(400);
        im2.setTranslateY(200);
        im2.setX(450);
        im2.setY(250);
        im2.setFitHeight(50);
        im2.setFitWidth(50);
        im2.setImage(image2);
        ladrillo.add(im2);
        root.getChildren().add(im2);

        //sonido
        AudioClip plonkSound = new AudioClip("file:///home/valerie/Escritorio/DAM/Programacion/JuegoValerie/Sonido%20Mario%20Game%20Over..mp3");


        for(int cont=0;cont<100;cont++){  
            if(im2.getTranslateX() == 20){
                Image image3 = new Image("ladrillo.png");
                ImageView im3 = new ImageView();
                im3.setTranslateX(400);
                im3.setTranslateY(200);
                im3.setX(450);
                im3.setY(250);
                im3.setFitHeight(50);
                im3.setFitWidth(50);
                im3.setImage(image3);
                im3.setVisible(true);

                ladrillo.add(im3);
                root.getChildren().add(im3);
            }
        }

        timeline = new Timeline(new KeyFrame(Duration.millis(5), 
                new EventHandler<ActionEvent>() {
                    @Override public void handle(ActionEvent t) {

                        im.setTranslateX(im.getTranslateX() +velocidadX);
                        im.setTranslateY(im.getTranslateY() +velocidadY);

                        double xMin = im.getBoundsInParent().getMinX();
                        double yMin = im.getBoundsInParent().getMinY();
                        double xMax = im.getBoundsInParent().getMaxX();
                        double yMax = im.getBoundsInParent().getMaxY();

                        im2.setTranslateX(im2.getTranslateX() + velocidadLadrillo);

                        if (xMin < 0 || xMax > scene.getWidth()) {
                            velocidadX = 0;
                        }

                        if (yMin < 0) {
                            velocidadY = 0;
                        }

                        //paparazzi choca con foto 

                        if( im.getBoundsInParent().intersects(im2.getBoundsInParent())){
                            velocidadX = 0;
                            plonkSound.play();
                            timeline.stop();
                                    gameO.setVisible(true);
                                    im2.setVisible(false);

                        }

                        if( yMax > scene.getWidth()){
                            velocidadX = 0;
                            velocidadY = 0;

                        }

                        double xMinR = im.getBoundsInParent().getMinX();
                        double xMaxR = im.getBoundsInParent().getMaxX();

                        if(xMinR <= 0 || xMaxR  >= scene.getWidth()){
                            velocidadX = 0;                                

                        }

                    }
                }));

        timeline.setCycleCount(Timeline.INDEFINITE);

        scene.setOnKeyPressed(event -> {         

                if(event.getCode() == KeyCode.UP)
                {
                    im.setTranslateY(80);

                }

                if(event.getCode() == KeyCode.DOWN)
                {
                    im.setTranslateY(200);

                }
                event.consume();
            });
        escenario.setScene(scene);
        escenario.show();
        timeline.play();
    }                    

}
