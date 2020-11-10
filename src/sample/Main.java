package sample;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception{
        System.out.println("Main thread started...");
        Parent root = FXMLLoader.load(getClass().getResource("sample.fxml"));
        primaryStage.setTitle("Application | LAB6");
        primaryStage.setScene(new Scene(root, 640, 480));
        primaryStage.show();
    }


    public static void main(String[] args) {
        Application.launch(args);
    }
}
