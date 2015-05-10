package com.yahiab.graphix.controller.videos;

import javafx.application.Platform;
import javafx.beans.Observable;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Control;
import javafx.scene.control.Label;
import javafx.scene.control.Slider;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.scene.media.MediaPlayer;
import javafx.scene.media.MediaView;
import javafx.stage.Stage;
import javafx.util.Duration;

public class MediaControl extends BorderPane {

    private final boolean repeat = false;
    private final Image PlayButtonImage = new Image("res/videos/playbutton.png");
    private final Image PauseButtonImage = new Image("res/videos/pausebutton.png");
    ImageView imageViewPlay = new ImageView(PlayButtonImage);
    ImageView imageViewPause = new ImageView(PauseButtonImage);
    private MediaPlayer mediaPlayer;
    private MediaView mediaView;
    private boolean stopRequested = false;
    private boolean atEndOfMedia = false;
    private Duration duration;
    private Slider timeSlider;
    private Label playTime;
    private Slider volumeSlider;
    private HBox mediaBar;
    private Pane mvPane;
    private Stage newStage;
    private boolean fullScreen = false;

    public MediaControl(final MediaPlayer mediaPlayer) {
        this.mediaPlayer = mediaPlayer;
        setStyle("-fx-background-color: #bfc2c7;"); // TODO: Use css file
        mediaView = new MediaView(mediaPlayer);
        mvPane = new Pane();
        mvPane.getChildren().add(mediaView);
        mvPane.setStyle("-fx-background-color: black;"); // TODO: Use css file
        setCenter(mvPane);
        mediaBar = new HBox(5.0);
        mediaBar.setPadding(new Insets(5, 10, 5, 10));
        mediaBar.setAlignment(Pos.CENTER_LEFT);
        BorderPane.setAlignment(mediaBar, Pos.CENTER);

        final Button playButton = new Button();
        playButton.setMinWidth(Control.USE_PREF_SIZE);

        playButton.setGraphic(imageViewPlay);
        playButton.setOnAction((ActionEvent e) -> {
            updateValues();
            MediaPlayer.Status status = mediaPlayer.getStatus();
            if (status == MediaPlayer.Status.UNKNOWN
                    || status == MediaPlayer.Status.HALTED) {
                // don't do anything in these states
                return;
            }

            if (status == MediaPlayer.Status.PAUSED
                    || status == MediaPlayer.Status.READY
                    || status == MediaPlayer.Status.STOPPED) {
                // rewind the movie if we're sitting at the end
                if (atEndOfMedia) {
                    mediaPlayer.seek(mediaPlayer.getStartTime());
                    atEndOfMedia = false;
                    playButton.setGraphic(imageViewPlay);
                    //playButton.setText(">");
                    updateValues();
                }
                mediaPlayer.play();
                playButton.setGraphic(imageViewPause);
                //playButton.setText("||");
            } else {
                mediaPlayer.pause();
            }
        });
        mediaPlayer.currentTimeProperty().addListener((ObservableValue<? extends Duration> observable, Duration oldValue, Duration newValue) -> {
            updateValues();
        });
        mediaPlayer.setOnPlaying(() -> {
            if (stopRequested) {
                mediaPlayer.pause();
                stopRequested = false;
            } else {
                playButton.setGraphic(imageViewPause);
                //playButton.setText("||");
            }
        });
        mediaPlayer.setOnPaused(() -> {
            playButton.setGraphic(imageViewPlay);
            //playButton.setText("||");
        });
        mediaPlayer.setOnReady(() -> {
            duration = mediaPlayer.getMedia().getDuration();
            updateValues();
        });

        mediaPlayer.setCycleCount(repeat ? MediaPlayer.INDEFINITE : 1);
        mediaPlayer.setOnEndOfMedia(() -> {
            if (!repeat) {
                playButton.setGraphic(imageViewPlay);
                //playButton.setText(">");
                stopRequested = true;
                atEndOfMedia = true;
            }
        });
        mediaBar.getChildren().add(playButton);

        // Time label
        Label timeLabel = new Label("Time");
        timeLabel.setMinWidth(Control.USE_PREF_SIZE);
        mediaBar.getChildren().add(timeLabel);


        // Time slider
        timeSlider = new Slider();
        timeSlider.setMinWidth(30);
        timeSlider.setMaxWidth(Double.MAX_VALUE);

        HBox.setHgrow(timeSlider, Priority.ALWAYS);
        timeSlider.valueProperty().addListener((Observable ov) -> {
            if (timeSlider.isValueChanging()) {
                // multiply duration by percentage calculated by slider position
                if (duration != null) {
                    mediaPlayer.seek(duration.multiply(timeSlider.getValue() / 100.0));
                }
                updateValues();

            }
        });
        mediaBar.getChildren().add(timeSlider);

        // Play label
        playTime = new Label();
        playTime.setMinWidth(Control.USE_PREF_SIZE);

        mediaBar.getChildren().add(playTime);


        //Fullscreen button
        Button buttonFullScreen = new Button("Full Screen");
        buttonFullScreen.setMinWidth(Control.USE_PREF_SIZE);

        buttonFullScreen.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                if (!fullScreen) {
                    newStage = new Stage();
                    newStage.fullScreenProperty().addListener((ObservableValue<? extends Boolean> ov, Boolean t, Boolean t1) -> {
                        onFullScreen();
                    });
                    final BorderPane borderPane = new BorderPane() {
                        @Override
                        protected void layoutChildren() {
                            if (mediaView != null && getBottom() != null) {
                                mediaView.setFitWidth(getWidth());
                                mediaView.setFitHeight(getHeight() - getBottom().prefHeight(-1));
                            }
                            super.layoutChildren();
                            if (mediaView != null) {
                                if (getCenter() != null) { //if smaller pane has content
                                    mediaView.setTranslateX((((Pane) getCenter()).getWidth() - mediaView.prefWidth(-1)) / 2);
                                    mediaView.setTranslateY((((Pane) getCenter()).getHeight() - mediaView.prefHeight(-1)) / 2);
                                }
                            }
                        }
                    };

                    setCenter(null);
                    setBottom(null);
                    borderPane.setCenter(mvPane);
                    borderPane.setBottom(mediaBar);

                    Scene newScene = new Scene(borderPane);
                    newStage.setScene(newScene);
                    //Workaround for disposing stage when exit fullscreen
                    newStage.setX(-100000);
                    newStage.setY(-100000);

                    newStage.setFullScreen(true);
                    fullScreen = true;
                    newStage.show();

                } else {
                    //toggle FullScreen
                    fullScreen = false;
                    newStage.setFullScreen(false);

                }
            }
        });
        mediaBar.getChildren().add(buttonFullScreen);

        // Volume label
        Label volumeLabel = new Label("Vol");
        volumeLabel.setMinWidth(Control.USE_PREF_SIZE);
        mediaBar.getChildren().add(volumeLabel);

        // Volume slider
        volumeSlider = new Slider();
        volumeSlider.setPrefWidth(70);
        volumeSlider.setMinWidth(30);
        volumeSlider.setMaxWidth(Region.USE_PREF_SIZE);
        volumeSlider.valueProperty().addListener((Observable ov) -> {
        });
        volumeSlider.valueProperty().addListener((ObservableValue<? extends Number> observable, Number oldValue, Number newValue) -> {
            if (volumeSlider.isValueChanging()) {
                mediaPlayer.setVolume(volumeSlider.getValue() / 100.0);
            }
        });
        mediaBar.getChildren().add(volumeSlider);

        setBottom(mediaBar);

    }

    @Override
    protected void layoutChildren() {
        if (mediaView != null && getBottom() != null) {
            mediaView.setFitWidth(getWidth());
            mediaView.setFitHeight(getHeight() - getBottom().prefHeight(-1));
        }
        super.layoutChildren();
        if (mediaView != null && getCenter() != null) {
            mediaView.setTranslateX((((Pane) getCenter()).getWidth() - mediaView.prefWidth(-1)) / 2);
            mediaView.setTranslateY((((Pane) getCenter()).getHeight() - mediaView.prefHeight(-1)) / 2);
        }
    }

    @Override
    protected double computeMinWidth(double height) {
        return mediaBar.prefWidth(-1);
    }

    @Override
    protected double computeMinHeight(double width) {
        return 200;
    }

    @Override
    protected double computePrefWidth(double height) {
        return Math.max(mediaPlayer.getMedia().getWidth(), mediaBar.prefWidth(height));
    }

    @Override
    protected double computePrefHeight(double width) {
        return mediaPlayer.getMedia().getHeight() + mediaBar.prefHeight(width);
    }

    @Override
    protected double computeMaxWidth(double height) {
        return Double.MAX_VALUE;
    }

    @Override
    protected double computeMaxHeight(double width) {
        return Double.MAX_VALUE;
    }

    protected void onFullScreen() {
        if (!newStage.isFullScreen()) {

            fullScreen = false;
            BorderPane smallBP = (BorderPane) newStage.getScene().getRoot();
            smallBP.setCenter(null);
            setCenter(mvPane);

            smallBP.setBottom(null);
            setBottom(mediaBar);
            Platform.runLater(() -> {
                newStage.close();
            });

        }
    }

    protected void updateValues() {
        if (playTime != null && timeSlider != null && volumeSlider != null && duration != null) {
            Platform.runLater(() -> {
                Duration currentTime = mediaPlayer.getCurrentTime();
                playTime.setText(formatTime(currentTime, duration));
                timeSlider.setDisable(duration.isUnknown());
                if (!timeSlider.isDisabled() && duration.greaterThan(Duration.ZERO) && !timeSlider.isValueChanging()) {
                    timeSlider.setValue(currentTime.divide(duration).toMillis() * 100.0);
                }
                if (!volumeSlider.isValueChanging()) {
                    volumeSlider.setValue((int) Math.round(mediaPlayer.getVolume() * 100));
                }
            });
        }
    }

    private String formatTime(Duration elapsed, Duration duration) {
        int intElapsed = (int) Math.floor(elapsed.toSeconds());
        int elapsedHours = intElapsed / (60 * 60);
        if (elapsedHours > 0) {
            intElapsed -= elapsedHours * 60 * 60;
        }
        int elapsedMinutes = intElapsed / 60;
        int elapsedSeconds = intElapsed - elapsedHours * 60 * 60 - elapsedMinutes * 60;

        if (duration.greaterThan(Duration.ZERO)) {
            int intDuration = (int) Math.floor(duration.toSeconds());
            int durationHours = intDuration / (60 * 60);
            if (durationHours > 0) {
                intDuration -= durationHours * 60 * 60;
            }
            int durationMinutes = intDuration / 60;
            int durationSeconds = intDuration - durationHours * 60 * 60 - durationMinutes * 60;

            if (durationHours > 0) {
                return String.format("%d:%02d:%02d/%d:%02d:%02d",
                        elapsedHours, elapsedMinutes, elapsedSeconds,
                        durationHours, durationMinutes, durationSeconds);
            } else {
                return String.format("%02d:%02d/%02d:%02d",
                        elapsedMinutes, elapsedSeconds,
                        durationMinutes, durationSeconds);
            }
        } else {
            if (elapsedHours > 0) {
                return String.format("%d:%02d:%02d",
                        elapsedHours, elapsedMinutes, elapsedSeconds);
            } else {
                return String.format("%02d:%02d",
                        elapsedMinutes, elapsedSeconds);
            }
        }
    }

    public MediaPlayer getMediaPlayer() {
        return mediaPlayer;
    }

    public void setMediaPlayer(MediaPlayer mediaPlayer) {
        this.mediaPlayer = mediaPlayer;
    }
}