package com.yahiab.graphix;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

import java.io.IOException;

public class GraphiXX extends Application {

    private Stage primaryStage;
    private BorderPane rootLayout;

    public static void main(String[] args) {
        launch(args);
    }

    public Stage getPrimaryStage() {
        return this.primaryStage;
    }

    @Override
    public void start(Stage primaryStage) throws Exception{
        this.primaryStage = primaryStage;
        this.primaryStage.setTitle("GraphiXX, un \u00e9diteur graphique 3D pour enfants, avec impression 3D et pleins de fonctionnalit\u00e9s");
        try {
            FXMLLoader loader = new FXMLLoader(GraphiXX.class.getResource("view/RootLayout.fxml"));
            this.rootLayout = loader.load();
            Scene scene = new Scene(rootLayout);
            this.primaryStage.setScene(scene);
            this.primaryStage.setMaximized(true);
            this.primaryStage.setOnCloseRequest(event -> {
                System.exit(0);
            });
            this.primaryStage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
