package sample;
import javafx.animation.PathTransition;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.shape.Line;
import javafx.util.Duration;

public class Controller {
    @FXML
    public ImageView cloud_2;
    @FXML
    public ImageView cloud_1;
    @FXML
    public ImageView no_lights;
    @FXML
    public ImageView background;
    @FXML
    public Button buttonStart;
    @FXML
    public Button buttonStop;

    @FXML
    private void bStartClick() {
        no_lights.setImage(new Image("./h_lights.png",300,300,false,false));
        startClouds();
    }

    public void bStopClick() {
        no_lights.setImage(new Image("./h_no_lights.png",300,300,false,false));
        stopClouds();
    }

    public void startClouds() {
        cloud_1.setImage(new Image("./cloud_1.png",25,25,false,false));
        cloud_2.setImage(new Image("./cloud_2.png",20,20,false,false));

        Line line_1 = new Line();
        line_1.setStartX(0);
        line_1.setStartY(25);
        line_1.setEndX(0);
        line_1.setEndY(-100);
        PathTransition transition_1 = new PathTransition();
        transition_1.setNode(cloud_1);
        transition_1.setDuration(Duration.seconds(1));
        transition_1.setPath(line_1);
        transition_1.setCycleCount(PathTransition.INDEFINITE);

        Line line_2 = new Line();
        line_2.setStartX(20);
        line_2.setStartY(50);
        line_2.setEndX(20);
        line_2.setEndY(-100);
        PathTransition transition_2 = new PathTransition();
        transition_2.setNode(cloud_2);
        transition_2.setDuration(Duration.seconds(1.5));
        transition_2.setPath(line_2);
        transition_2.setCycleCount(PathTransition.INDEFINITE);

        transition_1.play();
        transition_2.play();

        CloudThread cloudThread = new CloudThread();
        Thread thread = new Thread(cloudThread);
        thread.setDaemon(true);
        thread.start();
    }

    public void stopClouds() {
        cloud_1.setImage(null);
        cloud_2.setImage(null);

        Alert alert = new Alert(Alert.AlertType.WARNING);
        alert.setTitle("House light went off!");
        alert.setHeaderText(null);
        alert.setContentText("Lights were on for " + CloudThread.count + " seconds!");
        alert.showAndWait();

        CloudThread.stop();
    }
}

class CloudThread implements Runnable {
    public static int count;
    public static boolean running;
    @Override
    public void run() {
        System.out.println("Cloud thread started...");
        running = true;
        count = 0;
        while(running) {
            try {
                Thread.sleep(1000);
                count += 1;
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
    public static void stop() {
        System.out.println("Cloud thread stopped...");
        running = false;
    }
}