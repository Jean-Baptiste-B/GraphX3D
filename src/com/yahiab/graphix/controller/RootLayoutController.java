package com.yahiab.graphix.controller;

import com.yahiab.animations.AnimationsController;
import com.yahiab.app2d.Main;
import com.yahiab.coursquiz.CoursQuizController;
import com.yahiab.graphix.GraphiXX;
import com.yahiab.graphix.controller.pdf.PDFController;
import com.yahiab.graphix.controller.videos.VideosController;
import com.yahiab.graphix.view.SVGPathIcons;
import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.Timeline;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import javafx.scene.shape.Circle;
import javafx.scene.shape.SVGPath;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

/**
 * Created by Yahia on 24/02/2015.
 */
public class RootLayoutController implements Initializable{

    // Circle colors
    Color[] colors = {
            new Color(0.2, 0.5, 0.8, 1.0).saturate().brighter().brighter(),
            new Color(0.3, 0.2, 0.7, 1.0).saturate().brighter().brighter(),
            new Color(0.8, 0.3, 0.9, 1.0).saturate().brighter().brighter(),
            new Color(0.4, 0.3, 0.9, 1.0).saturate().brighter().brighter(),
            new Color(0.2, 0.5, 0.7, 1.0).saturate().brighter().brighter()
    };

    @FXML
    AnchorPane rootAnchorLayout;
    @FXML
    private BorderPane rootBorderPane;
    @FXML
    private Button btn3DApp;
    @FXML
    private Button btn2DApp;
    @FXML
    private Button btnDemos;
    @FXML
    private Button btnQuiz;
    @FXML
    private StackPane stackPane;
    @FXML
    private Pane pane;

    @FXML
    public void handleAbout() {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("About");
        alert.setHeaderText("Look, an Information Dialog");
        alert.setContentText("Team 21\n\n3D Graphix Editor for Kids\n\nCreated by : Yahia BELHAMRA\n\nEmail : dy_belhamra@esi.dz");
        alert.showAndWait();
    }

    @FXML
    public void handleHelp() {
        File helpFile = new File("res/help.pdf");
        try {
            Runtime.getRuntime().exec("cmd /c start \"\" \"" + helpFile.getAbsolutePath());
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    @FXML
    public void handleExit() {
        System.exit(0);
    }

    @FXML
    public void actionButton3D() {
        try {
            FXMLLoader loader = new FXMLLoader(GraphiXX.class.getResource("view/_3d/main.fxml"));
            AnchorPane node = loader.load();
            Stage stageApp3D = new Stage();
            Scene sceneApp3D = new Scene(node);

            stageApp3D.setScene(sceneApp3D);
            stageApp3D.setTitle("Editeur graphique 3D pour enfants, avec impression 3D, les op\u00e9ration bool\u00e9ennes, et pleins d'autres fonctionnalit\u00e9s");
            stageApp3D.setResizable(false);
            stageApp3D.setMaximized(true);
            stageApp3D.show();

        } catch (IOException e) {
            e.printStackTrace();
        }

    }
    @FXML
    public void actionButton2D() {
        try {
            FXMLLoader loader = new FXMLLoader(Main.class.getResource("kikim_2d.fxml"));
            AnchorPane node = loader.load();

            Stage app2dStage = new Stage();
            Scene app2dScene = new Scene(node);

            app2dStage.setScene(app2dScene);
            app2dStage.setMaximized(true);
            app2dStage.setTitle("Editeur graphique 2D pour enfants");
            app2dStage.setResizable(false);
            app2dStage.show();

        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
    @FXML
    public void actionButtonDemos() {
        try {
            FXMLLoader loader = new FXMLLoader(AnimationsController.class.getResource("AnimationsView.fxml"));
            AnchorPane node = loader.load();

            Stage animationsStage = new Stage();
            Scene animationsScene = new Scene(node);

            animationsScene.getStylesheets().add("com/yahiab/animations/res/animations/sky.css");

            animationsStage.setScene(animationsScene);
            animationsStage.setMaximized(true);
            animationsStage.setTitle("Animations des diff\u00e9rentes formes g\u00e9om\u00e9triques 3D");
            animationsStage.setResizable(false);
            animationsStage.show();

        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
    @FXML
    public void actionButtonQuiz() {
        try {
            FXMLLoader loader = new FXMLLoader(CoursQuizController.class.getResource("CoursQuiz.fxml"));
            AnchorPane node = loader.load();

            Stage coursQuizStage = new Stage();
            Scene coursQuizScene = new Scene(node);
            coursQuizStage.setScene(coursQuizScene);
            coursQuizStage.setMaximized(true);
            coursQuizStage.setTitle("Cours et quiz sur les formes g\u00e9om\u00e9trique 3D et 2D");
            coursQuizStage.setResizable(false);
            coursQuizStage.show();

        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    @FXML
    public void actionButtonVideos() {
        try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(GraphiXX.class.getResource("view/videos/VideosView.fxml"));

            AnchorPane node = loader.load();

            VideosController videosController = loader.getController();

            Stage videoStage = new Stage();
            Scene videoScene = new Scene(node);
            videoStage.setScene(videoScene);
            videoStage.setTitle("Des videos educatifs pour bien comprendre les diff\u00e9rentes formes geometriques 3D et 2D");
            videoStage.setResizable(false);
            videoStage.setOnCloseRequest(event -> {
                videosController.getMediaPlayer().dispose();
                videosController.getVideoPane().getChildren().clear();
            });
            videoStage.show();

        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    @FXML
    public void actionButtonPDF() {
        try {
            FXMLLoader loader = new FXMLLoader(GraphiXX.class.getResource("view/pdf/PDFView.fxml"));
            AnchorPane node = loader.load();

            PDFController pdfController = loader.getController();

            Stage pdfStage = new Stage();
            Scene pdfScene = new Scene(node);
            pdfStage.setScene(pdfScene);
            pdfStage.setTitle("Les patrons des diff\u00e9rentes formes g\u00e9om\u00e9triques 3D");
            pdfStage.setResizable(false);
            pdfStage.setMaximized(true);
            pdfStage.show();

        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {

        /// SVGdata
        SVGPath SVGPath3D = new SVGPath();
        SVGPath3D.setContent(SVGPathIcons.SVG3DApp);
        SVGPath3D.setFill(Paint.valueOf("#1e04c9"));
        SVGPath3D.setScaleX(3);
        SVGPath3D.setScaleY(3);
        SVGPath3D.setScaleZ(3);
        btn3DApp.setGraphic(SVGPath3D);

        SVGPath SVGPath2D = new SVGPath();
        SVGPath2D.setContent(SVGPathIcons.SVG2DApp);
        SVGPath2D.setFill(Paint.valueOf("#ee007f"));
        SVGPath2D.setScaleX(2.3);
        SVGPath2D.setScaleY(2.3);
        SVGPath2D.setScaleZ(2.3);
        btn2DApp.setGraphic(SVGPath2D);

        SVGPath SVGPathDemos = new SVGPath();
        SVGPathDemos.setContent(SVGPathIcons.SVGDemos);
        SVGPathDemos.setFill(Paint.valueOf("#ff6300"));
        SVGPathDemos.setScaleX(3);
        SVGPathDemos.setScaleY(3);
        SVGPathDemos.setScaleZ(3);
        btnDemos.setGraphic(SVGPathDemos);

        SVGPath SVGPathQuiz = new SVGPath();
        SVGPathQuiz.setContent(SVGPathIcons.SVGQuiz);
        SVGPathQuiz.setFill(Paint.valueOf("#2ea436"));
        SVGPathQuiz.setScaleX(2.2);
        SVGPathQuiz.setScaleY(2.2);
        SVGPathQuiz.setScaleZ(2.2);
        btnQuiz.setGraphic(SVGPathQuiz);

        pane.setStyle("-fx-background-color: rgba(1, 0, 255, 0.24)");
        int spawnNodes = 500;
        for (int i = 0; i < spawnNodes; i++) {
            spawnCircles(pane);
            spawnRectangles(pane);
        }
    }


    private void spawnRectangles(Pane container) {
        javafx.scene.shape.Rectangle node = new javafx.scene.shape.Rectangle(0, 0);
        node.setManaged(false);
        node.setFill(colors[(int) (Math.random() * colors.length)]);
        node.setX(1000 * Math.random());
        node.setY(700 * Math.random());
        container.getChildren().add(node);
        // create a timeline that fades the circle in and and out and also moves it across the screen
        Timeline timeline = new Timeline(
                new KeyFrame(
                        Duration.ZERO,
                        new KeyValue(node.widthProperty(), 0),
                        new KeyValue(node.heightProperty(), 0),
                        new KeyValue(node.xProperty(), node.getX() * (1 + Math.random())),
                        new KeyValue(node.yProperty(), node.getY() * (1 + Math.random())),
                        new KeyValue(node.opacityProperty(), 0)),
                new KeyFrame(
                        Duration.seconds(5 + Math.random() * 5),
                        new KeyValue(node.opacityProperty(), Math.random()),
                        new KeyValue(node.widthProperty(), Math.random() * 50),
                        new KeyValue(node.heightProperty(), Math.random() * 50)),
                new KeyFrame(
                        Duration.seconds(10 + Math.random() * 20),
                        new KeyValue(node.widthProperty(), 0),
                        new KeyValue(node.heightProperty(), 0),
                        new KeyValue(node.xProperty(), Math.random() * 1000),
                        new KeyValue(node.yProperty(), Math.random() * 700),
                        new KeyValue(node.opacityProperty(), 0))
        );
        // timeline shall be executed once
        timeline.setCycleCount(1);
        // when we are done we spawn another node
        timeline.setOnFinished(evt -> {
            container.getChildren().remove(node);
            spawnCircles(container);
        });
        // finally, we play the timeline
        timeline.play();
    }

    private void spawnCircles(Pane container) {
        Circle node = new Circle(0);
        node.setManaged(false);
        node.setFill(colors[(int) (Math.random() * colors.length)]);
        node.setCenterX(1000 * Math.random());
        node.setCenterY(700 * Math.random());
        container.getChildren().add(node);
        // create a timeline that fades the circle in and and out and also moves it across the screen
        Timeline timeline = new Timeline(
                new KeyFrame(
                        Duration.ZERO,
                        new KeyValue(node.radiusProperty(), 0),
                        new KeyValue(node.centerXProperty(), node.getCenterX() * (1 + Math.random())),
                        new KeyValue(node.centerYProperty(), node.getCenterY() * (1 + Math.random())),
                        new KeyValue(node.opacityProperty(), 0)),
                new KeyFrame(
                        Duration.seconds(5 + Math.random() * 5),
                        new KeyValue(node.opacityProperty(), Math.random()),
                        new KeyValue(node.radiusProperty(), Math.random() * 20)),
                new KeyFrame(
                        Duration.seconds(10 + Math.random() * 20),
                        new KeyValue(node.radiusProperty(), 0),
                        new KeyValue(node.centerXProperty(), Math.random() * 1000),
                        new KeyValue(node.centerYProperty(), Math.random() * 700),
                        new KeyValue(node.opacityProperty(), 0))
        );
        // timeline shall be executed once
        timeline.setCycleCount(1);
        // when we are done we spawn another node
        timeline.setOnFinished(evt -> {
            container.getChildren().remove(node);
            spawnCircles(container);
        });
        // finally, we play the timeline
        timeline.play();
    }

}