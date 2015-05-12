package com.yahiab.graphix.controller._3d;

import com.yahiab.graphix.view.SVGPathIcons;
import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.Timeline;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Group;
import javafx.scene.PerspectiveCamera;
import javafx.scene.SceneAntialiasing;
import javafx.scene.SubScene;
import javafx.scene.control.Button;
import javafx.scene.control.Slider;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.MeshView;
import javafx.scene.shape.SVGPath;
import javafx.scene.transform.Rotate;
import javafx.scene.transform.Transform;
import javafx.scene.transform.Translate;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.net.URL;
import java.util.ResourceBundle;

/**
 * Created by Yahia on 03/05/2015.
 */
public class BooleanOperationController implements Initializable {

    private final PerspectiveCamera camera = new PerspectiveCamera(true);
    SubScene booleanOperationSubScene;
    private Main3DController parentController;
    private Stage booleanOperationStage;
    private ObjectProperty<Group> content = new SimpleObjectProperty<>();
    private MeshView meshViewResult;
    private Timeline animation;
    @FXML
    private AnchorPane resultSubSceneAnchorPane;
    @FXML
    private Button btnPlay;
    @FXML
    private Button btnPause;
    @FXML
    private Slider booleanOperationZoomSlider;

    {
        camera.setNearClip(1.0);
        camera.setFarClip(10000.0);
        camera.getTransforms().addAll(
                new Rotate(-20, Rotate.Y_AXIS),
                new Rotate(-20, Rotate.X_AXIS),
                new Rotate(-20, Rotate.Z_AXIS),
                new Translate(0, 0, -500)
        );
        content.set(new Group());
    }

    public void setParentController(Main3DController parentController) {
        this.parentController = parentController;
    }

    public void setBooleanOperationStage(Stage stage) {
        this.booleanOperationStage = stage;
    }

    public void clearSubScene() {
        this.content.get().getChildren().clear();
    }

    public void playAnimation() {
        animation.play();
    }

    public void pauseAnimation() {
        animation.pause();
    }

    public void setMeshViewResult(MeshView result) {
        this.meshViewResult = result;
        camera.setTranslateX(camera.getTranslateX() + result.getTranslateX());
        camera.setTranslateY(camera.getTranslateY() + result.getTranslateY());
        camera.setTranslateZ(camera.getTranslateZ() + result.getTranslateZ());

        this.content.get().getChildren().clear();
        this.content.get().getChildren().addAll(result);

        meshViewResult.setRotationAxis(Rotate.Y_AXIS);
        animation = new Timeline();
        animation.getKeyFrames().addAll(
                new KeyFrame(Duration.ZERO,
                        new KeyValue(meshViewResult.rotateProperty(), 0d)),
                new KeyFrame(Duration.seconds(5),
                        new KeyValue(meshViewResult.rotateProperty(), 360d)));
        animation.setCycleCount(Timeline.INDEFINITE);
        this.playAnimation();
    }

    public void btnOKBooleanOperationResult() {
        this.animation.stop();

        this.parentController.addBooleanOperationResult(this.meshViewResult);
        this.booleanOperationStage.close();
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {

        booleanOperationSubScene = new SubScene(this.content.get(), 620, 290, true, SceneAntialiasing.BALANCED);
        booleanOperationSubScene.setFill(Color.ALICEBLUE);
        booleanOperationSubScene.setCamera(camera);

        this.resultSubSceneAnchorPane.getChildren().clear();
        this.resultSubSceneAnchorPane.getChildren().addAll(booleanOperationSubScene);

        booleanOperationZoomSlider.valueProperty().addListener((observable, oldValue, newValue) -> {
            for (Transform transform : camera.getTransforms())
                if (transform instanceof Translate)
                    ((Translate) transform).setZ(newValue.doubleValue() * (-1));
        });

        SVGPath play = new SVGPath();
        play.setContent(SVGPathIcons.play);
        play.setScaleX(0.7);
        play.setScaleY(0.7);
        play.setScaleZ(0.7);
        play.setFill(Color.WHITE);
        btnPlay.setGraphic(play);

        SVGPath pause = new SVGPath();
        pause.setContent(SVGPathIcons.pause);
        pause.setScaleX(0.7);
        pause.setScaleY(0.7);
        pause.setScaleZ(0.7);
        pause.setFill(Color.WHITE);
        btnPause.setGraphic(pause);
    }
}
