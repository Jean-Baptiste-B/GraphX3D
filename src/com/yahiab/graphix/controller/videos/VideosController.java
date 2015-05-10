package com.yahiab.graphix.controller.videos;

import com.yahiab.graphix.GraphiXX;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.layout.Pane;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;

import java.net.URL;
import java.util.ResourceBundle;

/**
 * Created by Yahia on 01/05/2015.
 */
public class VideosController implements Initializable {

    @FXML
    private Pane videoPane;

    private MediaPlayer mediaPlayer;
    private MediaControl mediaControl;

    public MediaControl createContent(String mediaURL) {
        if (mediaPlayer != null)
            mediaPlayer.dispose();
        mediaPlayer = new MediaPlayer(new Media(mediaURL));
        mediaPlayer.setAutoPlay(true);
        mediaControl = new MediaControl(mediaPlayer);
        mediaControl.setMinSize(600, 400);
        mediaControl.setPrefSize(600, 400);
        mediaControl.setMaxSize(600, 400);
        return mediaControl;
    }

    @FXML
    public void playVideo1() {
        videoPane.getChildren().clear();
        videoPane.getChildren().addAll(createContent(GraphiXX.class.getResource("../../../res/videos/vid1_3d_solids.mp4").toString()));
    }

    @FXML
    public void playVideo2() {
        videoPane.getChildren().clear();
        videoPane.getChildren().addAll(createContent(GraphiXX.class.getResource("../../../res/videos/vid1_3d.mp4").toString()));
    }

    @FXML
    public void playVideo3() {
        videoPane.getChildren().clear();
        videoPane.getChildren().addAll(createContent(GraphiXX.class.getResource("../../../res/videos/vid2_2d.mp4").toString()));
    }

    @FXML
    public void playVideo4() {
        videoPane.getChildren().clear();
        videoPane.getChildren().addAll(createContent(GraphiXX.class.getResource("../../../res/videos/vid2_2d_train.mp4").toString()));
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }
}
