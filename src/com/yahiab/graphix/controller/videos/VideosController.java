package com.yahiab.graphix.controller.videos;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.layout.Pane;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;

import java.io.File;
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
        String adnane = (new File(mediaURL)).toURI().toASCIIString();
        System.out.println(adnane);
        mediaPlayer = new MediaPlayer(new Media(adnane));
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
        videoPane.getChildren().addAll(createContent((new File("res/videos/vid1.mp4")).getAbsolutePath()));
    }

    @FXML
    public void playVideo2() {
        videoPane.getChildren().clear();
        videoPane.getChildren().addAll(createContent((new File("res/videos/vid2.mp4")).getAbsolutePath()));
    }

    @FXML
    public void playVideo3() {
        videoPane.getChildren().clear();
        videoPane.getChildren().addAll(createContent((new File("res/videos/vid3.mp4")).getAbsolutePath()));
    }

    @FXML
    public void playVideo4() {
        videoPane.getChildren().clear();
        videoPane.getChildren().addAll(createContent((new File("res/videos/vid4.mp4")).getAbsolutePath()));
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }
}
