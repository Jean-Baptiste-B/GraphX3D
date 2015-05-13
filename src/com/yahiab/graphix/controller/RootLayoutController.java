package com.yahiab.graphix.controller;

import com.yahiab.animations.AnimationsController;
import com.yahiab.app2d.Main;
import com.yahiab.coursquiz.CoursQuizController;
import com.yahiab.graphix.GraphiXX;
import com.yahiab.graphix.controller.videos.VideosController;
import com.yahiab.graphix.view.SVGPathIcons;
import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.Timeline;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Rectangle2D;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import javafx.scene.shape.Circle;
import javafx.scene.shape.SVGPath;
import javafx.stage.Screen;
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
    Rectangle2D bounds = Screen.getPrimary().getVisualBounds();
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
    private Button btnVideos;
    @FXML
    private Button btnPDFs;
    @FXML
    private StackPane stackPane;
    @FXML
    private Pane pane;

    @FXML
    public void handleAbout() {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("A propos");
        alert.setHeaderText("GraphX, un \u00e9diteur grahique 3D pour enfants.");
        alert.setContentText("Projet de la 2\u00e8me ann\u00e9e CPI, cr\u00e9\u00e9 par l'\u00e9quipe 21: \n\n" +
                "Yahia BELHAMRA\n" +
                "Moussaab AMRINE\n" +
                "Abdelkrim OUKHENNICHE\n" +
                "Mohammed Amine OUBERKOUK\n" +
                "Abdennour BENATALLAH\n" +
                "Maria AISSANI\n\n" +
                "Encadr\u00e9 par: Mme. BELATTAR et Mme. BOUZAR");
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
            stageApp3D.setTitle("GraphX, un \u00e9diteur graphique 3D pour enfants, avec impression 3D, les op\u00e9ration bool\u00e9ennes, et pleins d'autres fonctionnalit\u00e9s");
            stageApp3D.setHeight(bounds.getHeight());
            stageApp3D.setWidth(bounds.getWidth());
            stageApp3D.setResizable(false);
            stageApp3D.getIcons().addAll(new Image("/res/GraphX.png"));
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
            app2dStage.setTitle("Editeur graphique 2D pour enfants");
            app2dStage.setHeight(bounds.getHeight());
            app2dStage.setWidth(bounds.getWidth());
            app2dStage.setResizable(false);
            app2dStage.getIcons().addAll(new Image("/res/GraphX.png"));
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
            animationsStage.setTitle("Animations des diff\u00e9rentes formes g\u00e9om\u00e9triques 3D");
            animationsStage.setHeight(bounds.getHeight());
            animationsStage.setWidth(bounds.getWidth());
            animationsStage.setResizable(false);
            animationsStage.getIcons().addAll(new Image("/res/GraphX.png"));
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
            coursQuizStage.setTitle("Cours et quiz sur les formes g\u00e9om\u00e9trique 3D et 2D");
            coursQuizStage.setHeight(bounds.getHeight());
            coursQuizStage.setWidth(bounds.getWidth());
            coursQuizStage.setResizable(false);
            coursQuizStage.getIcons().addAll(new Image("/res/GraphX.png"));
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
            videoStage.setTitle("Des vid\u00e9os \u00e9ducatifs pour bien comprendre les diff\u00e9rentes formes g\u00e9om\u00e9triques 3D et 2D");
            videoStage.setHeight(bounds.getHeight());
            videoStage.setWidth(bounds.getWidth());
            videoStage.setResizable(false);
            videoStage.setOnCloseRequest(event -> {
                if (videosController.getMediaPlayer() != null)
                    videosController.getMediaPlayer().dispose();
                videosController.getVideoPane().getChildren().clear();
            });
            videoStage.getIcons().addAll(new Image("/res/GraphX.png"));
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

            Stage pdfStage = new Stage();
            Scene pdfScene = new Scene(node);
            pdfStage.setScene(pdfScene);
            pdfStage.setTitle("Les patrons des diff\u00e9rentes formes g\u00e9om\u00e9triques 3D");
            pdfStage.setHeight(bounds.getHeight());
            pdfStage.setWidth(bounds.getWidth());
            pdfStage.setResizable(false);
            pdfStage.getIcons().addAll(new Image("/res/GraphX.png"));
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
        SVGPath3D.setScaleX(5);
        SVGPath3D.setScaleY(5);
        SVGPath3D.setScaleZ(5);
        btn3DApp.setGraphic(SVGPath3D);

        SVGPath SVGPath2D = new SVGPath();
        SVGPath2D.setContent(SVGPathIcons.SVG2DApp);
        SVGPath2D.setFill(Paint.valueOf("#ee007f"));
        SVGPath2D.setScaleX(1.5);
        SVGPath2D.setScaleY(1.5);
        SVGPath2D.setScaleZ(1.5);
        btn2DApp.setGraphic(SVGPath2D);

        SVGPath SVGPathDemos = new SVGPath();
        SVGPathDemos.setContent(SVGPathIcons.map);
        SVGPathDemos.setFill(Paint.valueOf("#ff0000"));
        SVGPathDemos.setScaleX(1.8);
        SVGPathDemos.setScaleY(1.8);
        SVGPathDemos.setScaleZ(1.8);
        btnDemos.setGraphic(SVGPathDemos);

        SVGPath SVGPathQuiz = new SVGPath();
        SVGPathQuiz.setContent(SVGPathIcons.SVGQuiz);
        SVGPathQuiz.setFill(Paint.valueOf("#2ea436"));
        SVGPathQuiz.setScaleX(1.5);
        SVGPathQuiz.setScaleY(1.5);
        SVGPathQuiz.setScaleZ(1.5);
        btnQuiz.setGraphic(SVGPathQuiz);

        SVGPath videos = new SVGPath();
        videos.setContent(SVGPathIcons.SVGVideo);
        videos.setFill(Paint.valueOf("#ccdb00"));
        videos.setScaleX(2.4);
        videos.setScaleY(2.4);
        videos.setScaleZ(2.4);
        btnVideos.setGraphic(videos);

        SVGPath scissors = new SVGPath();
        scissors.setContent(SVGPathIcons.scissors);
        scissors.setFill(Paint.valueOf("#965000"));
        scissors.setScaleX(1.7);
        scissors.setScaleY(1.7);
        scissors.setScaleZ(1.7);
        btnPDFs.setGraphic(scissors);


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
            spawnRectangles(container);
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