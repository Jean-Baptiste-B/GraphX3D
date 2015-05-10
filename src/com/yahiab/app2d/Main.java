package com.yahiab.app2d;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.io.IOException;

/**
 * @author magic
 */
public class Main extends Application {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws IOException {

        VBox root = FXMLLoader.load(getClass().getResource("kikim_2d.fxml"));
        primaryStage.setTitle("APP");
        primaryStage.setScene(new Scene(root, 1000, 605));
        primaryStage.setMinWidth(600);
        primaryStage.setMinHeight(600);


        primaryStage.show();
        primaryStage.setFullScreen(true);

    }

}
