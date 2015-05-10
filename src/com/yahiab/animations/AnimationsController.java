package com.yahiab.animations;

import com.sun.javafx.scene.DirtyBits;
import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.TimelineBuilder;
import javafx.beans.property.DoubleProperty;
import javafx.beans.property.DoublePropertyBase;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Point3D;
import javafx.scene.Group;
import javafx.scene.PerspectiveCamera;
import javafx.scene.SubScene;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import javafx.scene.shape.Polygon;
import javafx.scene.shape.Rectangle;
import javafx.scene.transform.Rotate;
import javafx.scene.transform.RotateBuilder;
import javafx.scene.transform.Translate;
import javafx.util.Duration;

import java.net.URL;
import java.util.ResourceBundle;

/**
 * @author Benatallah
 */
public class AnimationsController implements Initializable {
    AnchorPane home = new AnchorPane();
    TimelineBuilder time1;
    TimelineBuilder time2;
    TimelineBuilder time3;
    TimelineBuilder time4;
    TimelineBuilder time5;
    TimelineBuilder time6;
    private DoubleProperty sideAngle = new SimpleDoubleProperty();
    private DoubleProperty side1Angle = new SimpleDoubleProperty();
    private DoubleProperty side2Angle = new SimpleDoubleProperty();
    private DoubleProperty side3Angle = new SimpleDoubleProperty();
    private DoubleProperty side4Angle = new SimpleDoubleProperty();
    private DoubleProperty side5Angle = new SimpleDoubleProperty();
    private DoubleProperty side6Angle = new SimpleDoubleProperty();
    private DoubleProperty side7Angle = new SimpleDoubleProperty();
    private DoubleProperty side8Angle = new SimpleDoubleProperty();
    private DoubleProperty side9Angle = new SimpleDoubleProperty();
    private DoubleProperty side6TranslateZ = new SimpleDoubleProperty();
    private DoubleProperty side6Translatex = new SimpleDoubleProperty();
    private DoubleProperty side5TranslateZ = new SimpleDoubleProperty();
    private DoubleProperty side5Translatex = new SimpleDoubleProperty();
    private DoubleProperty side4TranslateZ = new SimpleDoubleProperty();
    private DoubleProperty side4Translatex = new SimpleDoubleProperty();
    private DoubleProperty rootAngle = new SimpleDoubleProperty();
    @FXML
    private AnchorPane css;
    @FXML
    private SubScene scene;
    @FXML
    private Button b1;
    private Button b2;
    private Button b3;
    private Button b4;
    private Button b5;
    private Button b6;
    private String abdou = null;

    @FXML
    public void animate1() {


        abdou = "";
        abdou = b1.getId();

        //Scene scene1 = new Scene();
        scene.setRoot(new Group());
        //css.getStylesheets().add("/resources/shell.css");
         /*css.getStylesheets()
                 .add("res/animations/sky.css");*/


        PerspectiveCamera cam = new PerspectiveCamera();
        TriangleEquilateral xxa = new TriangleEquilateral(100.0);
        Polygon tri1a = xxa.createTriangleEquilateral();
        tri1a.setTranslateX(300);
        tri1a.setTranslateY(400);
        tri1a.setFill(Color.CYAN);
        tri1a.setStroke(Color.BLACK);


        Rotate rotatea0 = new Rotate();
        // rotate0.setPivotZ(0);
        rotatea0.setPivotX(200);
        rotatea0.setPivotY(0);
        rotatea0.setAxis(Rotate.X_AXIS);
        tri1a.getTransforms().add(rotatea0);
        rotatea0.angleProperty().bind(sideAngle);
        Rotate why = new Rotate();
        why.setAngle(-60);
        tri1a.getTransforms().add(why);
        /************************************/


        TriangleEquilateral xxa1 = new TriangleEquilateral(100.0);
        Polygon tria2 = xxa1.createTriangleEquilateral();
        tria2.setTranslateX(300);
        tria2.setTranslateY(213.2974);
        Translate what = new Translate();
        what.setX(50);
        tria2.getTransforms().add(what);
        tria2.setFill(Color.CYAN);
        tria2.setStroke(Color.BLACK);

        Rotate rotatea = new Rotate();
        //rotate.setPivotX(300);
        rotatea.setPivotY(86.7);
        //rotate.setPivotZ(0);
        rotatea.setAxis(Rotate.X_AXIS);
        tria2.getTransforms().add(rotatea);
        rotatea.angleProperty().bind(side1Angle);
        /***********************************/


        Rectangle rectanglea1 = new Rectangle(100, 100);
        rectanglea1.setX(200);
        rectanglea1.setY(300);
        rectanglea1.setFill(Color.web("f33882"));
        rectanglea1.setStroke(Color.BLACK);


        Rotate rotatea1 = new Rotate();
        rotatea1.setAxis(Rotate.Y_AXIS);
        rotatea1.setPivotX(300);
        rotatea1.setPivotY(300);
        rotatea1.setPivotZ(0);
        rectanglea1.getTransforms().add(rotatea1);
        rotatea1.angleProperty().bind(side2Angle);


        Rectangle rectanglea2 = new Rectangle(100, 100);
        rectanglea2.setX(300);
        rectanglea2.setY(300);
        rectanglea2.setStroke(Color.BLACK);
        rectanglea2.setFill(Color.PINK);


        Rectangle rectanglea3 = new Rectangle(100, 100);
        rectanglea3.setX(400);
        rectanglea3.setY(300);
        rectanglea3.setFill(Color.web("f33882"));
        rectanglea3.setStroke(Color.BLACK);


        Rotate rotatea3 = new Rotate();
        rotatea3.setPivotX(400);
        //rotate3.setPivotY(200);
        rotatea3.setPivotZ(0);
        rotatea3.setAxis(Rotate.Y_AXIS);
        rectanglea3.getTransforms().add(rotatea3);
        rotatea3.angleProperty().bind(side3Angle);


        final Rotate rootRotatea = RotateBuilder.create()
                .pivotX(0)
                .pivotY(0)
                .pivotZ(0)
                .axis(Rotate.X_AXIS)
                .build();
        rootRotatea.angleProperty().bind(rootAngle);

        Group root1 = new Group();
        root1.setLayoutX(370);
        root1.setLayoutY(200);
        root1.setStyle("-fx-background-color: white;");
        root1.getChildren().setAll(rectanglea2, tri1a, tria2, rectanglea3, rectanglea1);


        scene.setCamera(cam);
        root1.getTransforms().add(rootRotatea);


        scene.setRoot(root1);


    }

    @FXML
    public void pause() {


    }


    @FXML
    public void play() {

        if (abdou.equals("b1")) animate();

        else if (abdou.equals("b2")) animate11();

        else if (abdou.equals("b3")) animate22();

        else if (abdou.equals("b4")) animate33();

        else if (abdou.equals("b5")) animate44();


    }

    private void animate() {

        TimelineBuilder.create()
                .keyFrames(
                        new KeyFrame(
                                Duration.seconds(0),
                                new KeyValue(sideAngle, 00),
                                new KeyValue(side1Angle, 00),
                                new KeyValue(side2Angle, 0),
                                new KeyValue(side3Angle, 0),
                                new KeyValue(side4Angle, 0),
                                new KeyValue(side5Angle, 0),
                                new KeyValue(side6Angle, 0),
                                new KeyValue(side7Angle, 0),

                                new KeyValue(rootAngle, 0)
                        ),
                        new KeyFrame(
                                Duration.seconds(2),
                                new KeyValue(sideAngle, -90),
                                new KeyValue(side1Angle, 90),
                                new KeyValue(side2Angle, 0),
                                new KeyValue(side3Angle, 0),
                                new KeyValue(side4Angle, 0),
                                new KeyValue(side5Angle, 0),
                                new KeyValue(side6Angle, 0),
                                new KeyValue(side7Angle, 0),

                                new KeyValue(rootAngle, 0)
                        ),
                        new KeyFrame(
                                Duration.seconds(4),
                                new KeyValue(sideAngle, -90),
                                new KeyValue(side1Angle, 90),
                                new KeyValue(side2Angle, -120),
                                new KeyValue(side3Angle, 120),
                                new KeyValue(side4Angle, 00),
                                new KeyValue(side5Angle, 0),
                                new KeyValue(side6Angle, 0),
                                new KeyValue(side7Angle, 0),

                                new KeyValue(rootAngle, -20)
                        ),

                        new KeyFrame(
                                Duration.seconds(6),
                                new KeyValue(sideAngle, -90),
                                new KeyValue(side1Angle, 90),
                                new KeyValue(side2Angle, -120),
                                new KeyValue(side3Angle, 120),
                                new KeyValue(side4Angle, 00),
                                new KeyValue(side5Angle, 00),
                                new KeyValue(side6Angle, 0),
                                new KeyValue(side7Angle, 0),

                                new KeyValue(rootAngle, -30)
                        ),
                        new KeyFrame(
                                Duration.seconds(8),
                                new KeyValue(sideAngle, -90),
                                new KeyValue(side1Angle, 90),
                                new KeyValue(side2Angle, -120),
                                new KeyValue(side3Angle, 120),
                                new KeyValue(side4Angle, 00),
                                new KeyValue(side5Angle, 00),
                                new KeyValue(side6Angle, 0),
                                new KeyValue(side7Angle, 0),

                                new KeyValue(rootAngle, -40)
                        ),
                        new KeyFrame(
                                Duration.seconds(10),
                                new KeyValue(sideAngle, -90),
                                new KeyValue(side1Angle, 90),
                                new KeyValue(side2Angle, -120),
                                new KeyValue(side3Angle, 120),
                                new KeyValue(side4Angle, 00),
                                new KeyValue(side5Angle, 00),
                                new KeyValue(side6Angle, 0),
                                new KeyValue(side7Angle, 0),

                                new KeyValue(rootAngle, -40)
                        ),
                        new KeyFrame(
                                Duration.seconds(12),
                                new KeyValue(sideAngle, 0),
                                new KeyValue(side1Angle, 0),
                                new KeyValue(side2Angle, 0),
                                new KeyValue(side3Angle, 0),
                                new KeyValue(side4Angle, 00),
                                new KeyValue(side5Angle, 00),
                                new KeyValue(side6Angle, 0),
                                new KeyValue(side7Angle, 0),

                                new KeyValue(rootAngle, 00)
                        )
                )
                .build().play();
    }


    /***********************************************/
    /**
     * *******************************************
     */
    @FXML
    public void animate2() {

        //abdou="";
        abdou = "b2";

        scene.setRoot(new Group());


        PerspectiveCamera cam = new PerspectiveCamera();


        Hexagon xx = new Hexagon(100.0);
        Polygon hexa = xx.createHexagon();
        hexa.setTranslateX(300);
        hexa.setTranslateY(400);
        hexa.setFill(Color.CYAN);
        hexa.setStroke(Color.BLACK);

        Rotate rotate0 = new Rotate();
        // rotate0.setPivotX(300);
        //rotate0.setPivotY(400);
        rotate0.setPivotZ(0);
        rotate0.setAxis(Rotate.X_AXIS);
        hexa.getTransforms().add(rotate0);
        rotate0.angleProperty().bind(sideAngle);
        /************************************/


        Hexagon xx1 = new Hexagon(100.0);
        Polygon hexa1 = xx1.createHexagon();
        hexa1.setTranslateX(300);
        hexa1.setTranslateY(136.7);
        hexa1.setFill(Color.CYAN);
        hexa1.setStroke(Color.BLACK);

        Rotate rotate = new Rotate();
        //rotate.setPivotX(300);
        rotate.setPivotY(163.3);
        rotate.setPivotZ(0);
        rotate.setAxis(Rotate.X_AXIS);
        hexa1.getTransforms().add(rotate);
        rotate.angleProperty().bind(side1Angle);
        /***********************************/


        Rectangle rectanglea1 = new Rectangle(100, 100);
        rectanglea1.setX(200);
        rectanglea1.setY(300);
        rectanglea1.setFill(Color.web("f33882"));
        rectanglea1.setStroke(Color.BLACK);


        Rotate rotatea1 = new Rotate();
        rotatea1.setAxis(Rotate.Y_AXIS);
        rotatea1.setPivotX(300);
        rotatea1.setPivotY(300);
        rotatea1.setPivotZ(0);
        rectanglea1.getTransforms().add(rotatea1);
        rotatea1.angleProperty().bind(side2Angle);
        rectanglea1.translateZProperty().bind(side6TranslateZ);
        rectanglea1.translateXProperty().bind(side6Translatex);


        Rectangle rectanglea2 = new Rectangle(100, 100);
        rectanglea2.setX(200);
        rectanglea2.setY(300);
        rectanglea2.translateZProperty().bind(side4TranslateZ);
        rectanglea2.translateXProperty().bind(side4Translatex);
        rectanglea2.setStroke(Color.BLACK);
        rectanglea2.setFill(Color.PINK);

        Rectangle rectangle3 = new Rectangle(100, 100);
        rectangle3.setX(300);
        rectangle3.setY(300);
        rectangle3.setFill(Color.web("f33882"));
        rectangle3.setStroke(Color.BLACK);

        Rotate rotate3 = new Rotate();
        rotate3.setPivotX(400);
        //rotate3.setPivotY(200);
        rotate3.setPivotZ(0);
        rotate3.setAxis(Rotate.Y_AXIS);
        rectangle3.getTransforms().add(rotate3);
        rotate3.angleProperty().bind(side3Angle);

        Rectangle rectangle4 = new Rectangle(100, 100);
        rectangle4.setX(400);
        rectangle4.setY(300);
        rectangle4.setFill(Color.PINK);
        rectangle4.setStroke(Color.BLACK);

        Rotate rotate41 = new Rotate();
        rotate41.setPivotX(500);
        rotate41.setPivotY(200);
        rotate41.setPivotZ(0);
        rotate41.setAxis(Rotate.Y_AXIS);

        rectangle4.getTransforms().add(rotate41);
        rotate41.angleProperty().bind(side4Angle);

        Rotate rotate42 = new Rotate();
        rotate42.setPivotX(500);
        rotate42.setPivotY(200);
        rotate42.setPivotZ(-150);
        rotate42.setAxis(Rotate.Y_AXIS);

        rectangle4.getTransforms().add(rotate42);
        rotate42.angleProperty().bind(side5Angle);

        Rectangle rectangle5 = new Rectangle(100, 100);
        rectangle5.setX(500);
        rectangle5.setY(300);
        rectangle5.setFill(Color.web("f33882"));
        rectangle5.setStroke(Color.BLACK);

        Rotate rotate5 = new Rotate();
        rotate5.setPivotX(500);
        //rotate5.setPivotY(200);
        rotate5.setPivotZ(0);
        rotate5.setAxis(Rotate.Y_AXIS);
        rectangle5.getTransforms().add(rotate5);
        rotate5.angleProperty().bind(side6Angle);

        Rectangle rectangle6 = new Rectangle(100, 100);
        rectangle6.setX(600);
        rectangle6.setY(300);
        rectangle6.setFill(Color.web("f33882"));
        rectangle6.setStroke(Color.BLACK);


        Rotate rotate6 = new Rotate();
        rotate6.setPivotX(600);
        rotate6.setPivotY(300);
        rotate6.setPivotZ(0);
        rotate6.setAxis(Rotate.Y_AXIS);
        rectangle6.getTransforms().add(rotate6);
        rotate6.angleProperty().bind(side7Angle);
        rectangle6.translateZProperty().bind(side5TranslateZ);
        rectangle6.translateXProperty().bind(side5Translatex);


        final Rotate rootRotate = RotateBuilder.create()
                .pivotX(0)
                .pivotY(0)
                .pivotZ(0)
                .axis(Rotate.X_AXIS)
                .build();
        rootRotate.angleProperty().bind(rootAngle);

        Group root2 = new Group();
        root2.setStyle("-fx-background-color: white;");
        root2.setLayoutY(180);
        root2.setLayoutX(270);
        root2.getChildren().setAll(rectangle4, rectangle5, rectangle3, hexa, hexa1, rectangle6, rectanglea1, rectanglea2);


        scene.setCamera(cam);
        root2.getTransforms().add(rootRotate);


        scene.setRoot(root2);


    }

    private void animate11() {

        TimelineBuilder.create().keyFrames();
        TimelineBuilder.create()
                .keyFrames(
                        new KeyFrame(
                                Duration.seconds(0),
                                new KeyValue(sideAngle, 00),
                                new KeyValue(side1Angle, 00),
                                new KeyValue(side2Angle, 0),
                                new KeyValue(side3Angle, 0),
                                new KeyValue(side4Angle, 0),
                                new KeyValue(side5Angle, 0),
                                new KeyValue(side6Angle, 0),
                                new KeyValue(side7Angle, 0),
                                new KeyValue(side6TranslateZ, 0),
                                new KeyValue(side6TranslateZ, 0),
                                new KeyValue(rootAngle, 0)
                        ),
                        new KeyFrame(
                                Duration.seconds(2),
                                new KeyValue(sideAngle, -90),
                                new KeyValue(side1Angle, 90),
                                new KeyValue(side2Angle, -125),
                                new KeyValue(side3Angle, -55),
                                new KeyValue(side4Angle, 0),
                                new KeyValue(side5Angle, 0),
                                new KeyValue(side6Angle, 0),
                                new KeyValue(side7Angle, 0),
                                new KeyValue(side6TranslateZ, 0),
                                new KeyValue(side6TranslateZ, 0),
                                new KeyValue(side5TranslateZ, 0),
                                new KeyValue(rootAngle, -10)
                        ),
                        new KeyFrame(
                                Duration.seconds(4),
                                new KeyValue(sideAngle, -90),
                                new KeyValue(side1Angle, 90),
                                new KeyValue(side2Angle, -125),
                                new KeyValue(side3Angle, -55),
                                new KeyValue(side4Angle, 00),
                                new KeyValue(side5Angle, 0),
                                new KeyValue(side6Angle, 55),
                                new KeyValue(side7Angle, 125),
                                new KeyValue(side6TranslateZ, -81.65),
                                new KeyValue(side6Translatex, 42.65),

                                new KeyValue(side5TranslateZ, -81.65),
                                new KeyValue(side5Translatex, -42.65),

                                new KeyValue(rootAngle, -20)
                        ),

                        new KeyFrame(
                                Duration.seconds(6),
                                new KeyValue(sideAngle, -90),
                                new KeyValue(side1Angle, 90),
                                new KeyValue(side2Angle, -125),
                                new KeyValue(side3Angle, -55),
                                new KeyValue(side4Angle, 00),
                                new KeyValue(side5Angle, 00),
                                new KeyValue(side6Angle, 55),
                                new KeyValue(side7Angle, 125),
                                new KeyValue(side6TranslateZ, -81.65),
                                new KeyValue(side6Translatex, 42.65),

                                new KeyValue(side5TranslateZ, -81.65),
                                new KeyValue(side5Translatex, -42.65),
                                new KeyValue(side4TranslateZ, -163.3),
                                new KeyValue(side4Translatex, 200),
                                new KeyValue(rootAngle, -30)
                        ),
                        new KeyFrame(
                                Duration.seconds(8),
                                new KeyValue(sideAngle, -90),
                                new KeyValue(side1Angle, 90),
                                new KeyValue(side2Angle, -125),
                                new KeyValue(side3Angle, -55),
                                new KeyValue(side4Angle, 00),
                                new KeyValue(side5Angle, 00),
                                new KeyValue(side6Angle, 55),
                                new KeyValue(side7Angle, 125),
                                new KeyValue(side6TranslateZ, -81.65),
                                new KeyValue(side6Translatex, 42.65),

                                new KeyValue(side5TranslateZ, -81.65),
                                new KeyValue(side5Translatex, -42.65),
                                new KeyValue(side4TranslateZ, -163.3),
                                new KeyValue(side4Translatex, 200),
                                new KeyValue(rootAngle, -40)
                        ),
                        new KeyFrame(
                                Duration.seconds(10),
                                new KeyValue(sideAngle, -90),
                                new KeyValue(side1Angle, 90),
                                new KeyValue(side2Angle, -125),
                                new KeyValue(side3Angle, -55),
                                new KeyValue(side4Angle, 00),
                                new KeyValue(side5Angle, 00),
                                new KeyValue(side6Angle, 55),
                                new KeyValue(side7Angle, 125),
                                new KeyValue(side6TranslateZ, -81.65),
                                new KeyValue(side6Translatex, 42.65),

                                new KeyValue(side5TranslateZ, -81.65),
                                new KeyValue(side5Translatex, -42.65),
                                new KeyValue(side4TranslateZ, -163.3),
                                new KeyValue(side4Translatex, 200),
                                new KeyValue(rootAngle, -40)
                        ),
                        new KeyFrame(
                                Duration.seconds(12),
                                new KeyValue(sideAngle, 0),
                                new KeyValue(side1Angle, 0),
                                new KeyValue(side2Angle, 0),
                                new KeyValue(side3Angle, 0),
                                new KeyValue(side4Angle, 00),
                                new KeyValue(side5Angle, 00),
                                new KeyValue(side6Angle, 0),
                                new KeyValue(side7Angle, 0),
                                new KeyValue(side6TranslateZ, 0),
                                new KeyValue(side6Translatex, 0),

                                new KeyValue(side5TranslateZ, 0),
                                new KeyValue(side5Translatex, 0),
                                new KeyValue(side4TranslateZ, 0),
                                new KeyValue(side4Translatex, 0),
                                new KeyValue(rootAngle, 0)
                        )
                )
                .build().playFromStart();

    }

    /***********************************************/
    /**
     * *******************************************
     */
    @FXML
    public void animate3() {

        abdou = "b3";


        /** Création du cube **/
        Rectangle rectangle1 = new Rectangle(100, 100);
        rectangle1.setX(100);
        rectangle1.setY(200);
        rectangle1.setFill(Color.web("f33882"));
        rectangle1.setStroke(Color.BLACK);

        Rotate rotate1 = new Rotate();
        rotate1.setPivotX(200);
        rotate1.setPivotY(200);
        rotate1.setPivotZ(0);
        rotate1.setAxis(Rotate.Y_AXIS);
        rectangle1.getTransforms().add(rotate1);
        rotate1.angleProperty().bind(side2Angle);


        Rectangle rectangle2 = new Rectangle(100, 100);
        rectangle2.setX(200);
        rectangle2.setY(200);
        rectangle2.setStroke(Color.BLACK);
        rectangle2.setFill(Color.PINK);

        Rectangle rectangle3 = new Rectangle(100, 100);
        rectangle3.setX(300);
        rectangle3.setY(200);
        rectangle3.setFill(Color.web("f33882"));
        rectangle3.setStroke(Color.BLACK);

        Rotate rotate3 = new Rotate();
        rotate3.setPivotX(300);
        rotate3.setPivotY(200);
        rotate3.setPivotZ(0);
        rotate3.setAxis(Rotate.Y_AXIS);
        rectangle3.getTransforms().add(rotate3);
        rotate3.angleProperty().bind(side3Angle);

        Rectangle rectangle4 = new Rectangle(100, 100);
        rectangle4.setX(400);
        rectangle4.setY(200);
        rectangle4.setFill(Color.web("f33882"));
        rectangle4.setStroke(Color.BLACK);

        Rotate rotate41 = new Rotate();
        rotate41.setPivotX(400);
        rotate41.setPivotY(200);
        rotate41.setPivotZ(0);
        rotate41.setAxis(Rotate.Y_AXIS);

        rectangle4.getTransforms().add(rotate41);
        rotate41.angleProperty().bind(side4Angle);

        Rotate rotate42 = new Rotate();
        rotate42.setPivotX(400);
        rotate42.setPivotY(200);
        rotate42.setPivotZ(-100);
        rotate42.setAxis(Rotate.Y_AXIS);

        rectangle4.getTransforms().add(rotate42);
        rotate42.angleProperty().bind(side5Angle);

        Rectangle rectangle5 = new Rectangle(100, 100);
        rectangle5.setX(200);
        rectangle5.setY(100);
        rectangle5.setFill(Color.web("f33882"));
        rectangle5.setStroke(Color.BLACK);

        Rotate rotate5 = new Rotate();
        rotate5.setPivotX(200);
        rotate5.setPivotY(200);
        rotate5.setPivotZ(0);
        rotate5.setAxis(Rotate.X_AXIS);
        rectangle5.getTransforms().add(rotate5);
        rotate5.angleProperty().bind(side6Angle);

        Rectangle rectangle6 = new Rectangle(100, 100);
        rectangle6.setX(200);
        rectangle6.setY(300);
        rectangle6.setFill(Color.web("f33882"));
        rectangle6.setStroke(Color.BLACK);

        Rotate rotate6 = new Rotate();
        rotate6.setPivotX(200);
        rotate6.setPivotY(300);
        rotate6.setPivotZ(0);
        rotate6.setAxis(Rotate.X_AXIS);
        rectangle6.getTransforms().add(rotate6);
        rotate6.angleProperty().bind(side7Angle);

        final Rotate rootRotate = RotateBuilder.create()
                .pivotX(0)
                .pivotY(0)
                .pivotZ(-100)
                .axis(new Point3D(1, 1, 1))
                .build();
        rootRotate.angleProperty().bind(rootAngle);


        Group root3 = new Group();
        root3.setLayoutY(280);
        root3.setLayoutX(450);

        root3.setStyle("-fx-background-color: white;");
        root3.getChildren().setAll(rectangle2, rectangle3, rectangle6, rectangle5, rectangle1, rectangle4);


        scene.setCamera(new PerspectiveCamera());
        root3.getTransforms().add(rootRotate);
        scene.setRoot(root3);


    }

    private void animate22() {

        TimelineBuilder.create()
                .keyFrames(
                        new KeyFrame(
                                Duration.seconds(0),
                                new KeyValue(side2Angle, 0),
                                new KeyValue(side3Angle, 0),
                                new KeyValue(side4Angle, 0),
                                new KeyValue(side5Angle, 0),
                                new KeyValue(side6Angle, 0),
                                new KeyValue(side7Angle, 0),
                                new KeyValue(rootAngle, 0)
                        ),
                        new KeyFrame(
                                Duration.seconds(2),
                                new KeyValue(side2Angle, -90),
                                new KeyValue(side3Angle, 0),
                                new KeyValue(side4Angle, 0),
                                new KeyValue(side5Angle, 0),
                                new KeyValue(side6Angle, 0),
                                new KeyValue(side7Angle, 0),
                                new KeyValue(side6TranslateZ, 0),
                                new KeyValue(rootAngle, 0)
                        ),
                        new KeyFrame(
                                Duration.seconds(4),
                                new KeyValue(side2Angle, -90),
                                new KeyValue(side3Angle, 90),
                                new KeyValue(side4Angle, 90),
                                new KeyValue(side5Angle, 0),
                                new KeyValue(side6Angle, 0),
                                new KeyValue(side7Angle, 0),
                                new KeyValue(side6TranslateZ, 0),
                                new KeyValue(rootAngle, -5)
                        ),
                        new KeyFrame(
                                Duration.seconds(6),
                                new KeyValue(side2Angle, -90),
                                new KeyValue(side3Angle, 90),
                                new KeyValue(side4Angle, 90),
                                new KeyValue(side5Angle, 90),
                                new KeyValue(side6Angle, 0),
                                new KeyValue(side7Angle, 0),
                                new KeyValue(side6TranslateZ, 0),
                                new KeyValue(rootAngle, -10)
                        ),

                        new KeyFrame(
                                Duration.seconds(8),
                                new KeyValue(side2Angle, -90),
                                new KeyValue(side3Angle, 90),
                                new KeyValue(side4Angle, 90),
                                new KeyValue(side5Angle, 90),
                                new KeyValue(side6Angle, 90),
                                new KeyValue(side7Angle, -90),
                                new KeyValue(side6TranslateZ, -200),
                                new KeyValue(rootAngle, -20)
                        ),
                        new KeyFrame(
                                Duration.seconds(10),
                                new KeyValue(side2Angle, -90),
                                new KeyValue(side3Angle, 90),
                                new KeyValue(side4Angle, 90),
                                new KeyValue(side5Angle, 90),
                                new KeyValue(side6Angle, 90),
                                new KeyValue(side7Angle, -90),
                                new KeyValue(side6TranslateZ, -200),
                                new KeyValue(rootAngle, -30)
                        ),
                        new KeyFrame(
                                Duration.seconds(12),
                                new KeyValue(side2Angle, -90),
                                new KeyValue(side3Angle, 90),
                                new KeyValue(side4Angle, 90),
                                new KeyValue(side5Angle, 90),
                                new KeyValue(side6Angle, 90),
                                new KeyValue(side7Angle, -90),
                                new KeyValue(side6TranslateZ, -200),
                                new KeyValue(rootAngle, -30)
                        ),


                        new KeyFrame(
                                Duration.seconds(14),
                                new KeyValue(side2Angle, 0),
                                new KeyValue(side3Angle, 0),
                                new KeyValue(side4Angle, 0),
                                new KeyValue(side5Angle, 0),
                                new KeyValue(side6Angle, 0),
                                new KeyValue(side7Angle, 0),
                                new KeyValue(side6TranslateZ, 0),
                                new KeyValue(rootAngle, 0)
                        )
                )
                .build().playFromStart();
    }

    @FXML
    public void animate4() {


        abdou = "b4";

        /** Création du cube **/
        Rectangle rectangle1 = new Rectangle(100, 100);
        rectangle1.setX(100);
        rectangle1.setY(200);
        rectangle1.setFill(Color.web("f33882"));
        rectangle1.setStroke(Color.BLACK);

        Rotate rotate1 = new Rotate();
        rotate1.setPivotX(200);
        rotate1.setPivotY(200);
        rotate1.setPivotZ(0);
        rotate1.setAxis(Rotate.Y_AXIS);
        rectangle1.getTransforms().add(rotate1);
        rotate1.angleProperty().bind(side2Angle);


        Rectangle rectangle2 = new Rectangle(200, 100);
        rectangle2.setX(200);
        rectangle2.setY(200);
        rectangle2.setStroke(Color.BLACK);
        rectangle2.setFill(Color.PINK);

        Rectangle rectangle3 = new Rectangle(100, 100);
        rectangle3.setX(400);
        rectangle3.setY(200);
        rectangle3.setFill(Color.web("f33882"));
        rectangle3.setStroke(Color.BLACK);

        Rotate rotate3 = new Rotate();
        rotate3.setPivotX(400);
        rotate3.setPivotY(200);
        rotate3.setPivotZ(0);
        rotate3.setAxis(Rotate.Y_AXIS);
        rectangle3.getTransforms().add(rotate3);
        rotate3.angleProperty().bind(side3Angle);

        Rectangle rectangle4 = new Rectangle(200, 100);
        rectangle4.setX(500);
        rectangle4.setY(200);
        rectangle4.setFill(Color.web("f33882"));
        rectangle4.setStroke(Color.BLACK);

        Rotate rotate41 = new Rotate();
        rotate41.setPivotX(500);
        rotate41.setPivotY(200);
        rotate41.setPivotZ(0);
        rotate41.setAxis(Rotate.Y_AXIS);

        rectangle4.getTransforms().add(rotate41);
        rotate41.angleProperty().bind(side4Angle);

        Rotate rotate42 = new Rotate();
        rotate42.setPivotX(500);
        rotate42.setPivotY(200);
        rotate42.setPivotZ(-100);
        rotate42.setAxis(Rotate.Y_AXIS);

        rectangle4.getTransforms().add(rotate42);
        rotate42.angleProperty().bind(side5Angle);

        Rectangle rectangle5 = new Rectangle(200, 100);
        rectangle5.setX(200);
        rectangle5.setY(100);
        rectangle5.setFill(Color.web("f33882"));
        rectangle5.setStroke(Color.BLACK);

        Rotate rotate5 = new Rotate();
        rotate5.setPivotX(200);
        rotate5.setPivotY(200);
        rotate5.setPivotZ(0);
        rotate5.setAxis(Rotate.X_AXIS);
        rectangle5.getTransforms().add(rotate5);
        rotate5.angleProperty().bind(side6Angle);

        Rectangle rectangle6 = new Rectangle(200, 100);
        rectangle6.setX(200);
        rectangle6.setY(300);
        rectangle6.setFill(Color.web("f33882"));
        rectangle6.setStroke(Color.BLACK);

        Rotate rotate6 = new Rotate();
        rotate6.setPivotX(200);
        rotate6.setPivotY(300);
        rotate6.setPivotZ(0);
        rotate6.setAxis(Rotate.X_AXIS);
        rectangle6.getTransforms().add(rotate6);
        rotate6.angleProperty().bind(side7Angle);

        final Rotate rootRotate = RotateBuilder.create()
                .pivotX(0)
                .pivotY(0)
                .pivotZ(-100)
                .axis(new Point3D(1, 1, 1))
                .build();
        rootRotate.angleProperty().bind(rootAngle);

        Group root4 = new Group();
        root4.setStyle("-fx-background-color: white;");
        root4.getChildren().setAll(rectangle2, rectangle3, rectangle6, rectangle5, rectangle1, rectangle4);
        root4.setLayoutY(280);
        root4.setLayoutX(420);

        scene.setCamera(new PerspectiveCamera());
        root4.getTransforms().add(rootRotate);

        scene.setRoot(root4);


    }

    private void animate33() {

        TimelineBuilder.create()
                .keyFrames(
                        new KeyFrame(
                                Duration.seconds(0),
                                new KeyValue(side2Angle, 0),
                                new KeyValue(side3Angle, 0),
                                new KeyValue(side4Angle, 0),
                                new KeyValue(side5Angle, 0),
                                new KeyValue(side6Angle, 0),
                                new KeyValue(side7Angle, 0),
                                new KeyValue(rootAngle, 0)
                        ),
                        new KeyFrame(
                                Duration.seconds(2),
                                new KeyValue(side2Angle, -90),
                                new KeyValue(side3Angle, 0),
                                new KeyValue(side4Angle, 0),
                                new KeyValue(side5Angle, 0),
                                new KeyValue(side6Angle, 0),
                                new KeyValue(side7Angle, 0),
                                new KeyValue(side6TranslateZ, 0),
                                new KeyValue(rootAngle, 0)
                        ),
                        new KeyFrame(
                                Duration.seconds(4),
                                new KeyValue(side2Angle, -90),
                                new KeyValue(side3Angle, 90),
                                new KeyValue(side4Angle, 90),
                                new KeyValue(side5Angle, 0),
                                new KeyValue(side6Angle, 0),
                                new KeyValue(side7Angle, 0),
                                new KeyValue(side6TranslateZ, 0),
                                new KeyValue(rootAngle, -5)
                        ),
                        new KeyFrame(
                                Duration.seconds(6),
                                new KeyValue(side2Angle, -90),
                                new KeyValue(side3Angle, 90),
                                new KeyValue(side4Angle, 90),
                                new KeyValue(side5Angle, 90),
                                new KeyValue(side6Angle, 0),
                                new KeyValue(side7Angle, 0),
                                new KeyValue(side6TranslateZ, 0),
                                new KeyValue(rootAngle, -10)
                        ),

                        new KeyFrame(
                                Duration.seconds(8),
                                new KeyValue(side2Angle, -90),
                                new KeyValue(side3Angle, 90),
                                new KeyValue(side4Angle, 90),
                                new KeyValue(side5Angle, 90),
                                new KeyValue(side6Angle, 90),
                                new KeyValue(side7Angle, -90),
                                new KeyValue(side6TranslateZ, -200),
                                new KeyValue(rootAngle, -20)
                        ),
                        new KeyFrame(
                                Duration.seconds(10),
                                new KeyValue(side2Angle, -90),
                                new KeyValue(side3Angle, 90),
                                new KeyValue(side4Angle, 90),
                                new KeyValue(side5Angle, 90),
                                new KeyValue(side6Angle, 90),
                                new KeyValue(side7Angle, -90),
                                new KeyValue(side6TranslateZ, -200),
                                new KeyValue(rootAngle, -30)
                        ),
                        new KeyFrame(
                                Duration.seconds(12),
                                new KeyValue(side2Angle, -90),
                                new KeyValue(side3Angle, 90),
                                new KeyValue(side4Angle, 90),
                                new KeyValue(side5Angle, 90),
                                new KeyValue(side6Angle, 90),
                                new KeyValue(side7Angle, -90),
                                new KeyValue(side6TranslateZ, -200),
                                new KeyValue(rootAngle, -30)
                        ),


                        new KeyFrame(
                                Duration.seconds(14),
                                new KeyValue(side2Angle, 0),
                                new KeyValue(side3Angle, 0),
                                new KeyValue(side4Angle, 0),
                                new KeyValue(side5Angle, 0),
                                new KeyValue(side6Angle, 0),
                                new KeyValue(side7Angle, 0),
                                new KeyValue(side6TranslateZ, 0),
                                new KeyValue(rootAngle, 0)
                        )
                )
                .build().playFromStart();
    }
    /*************************************************/
    /**
     * *********************************************
     */
    public void animate5() {

        abdou = "b5";

        PerspectiveCamera cam = new PerspectiveCamera();
        TriangleEquilateral xx = new TriangleEquilateral(100.0);
        Polygon tri1 = xx.createTriangleEquilateral();
        tri1.setTranslateX(300);
        tri1.setTranslateY(400);
        tri1.setFill(Color.CYAN);
        tri1.setStroke(Color.BLACK);


        Rotate rotate0 = new Rotate();
        // rotate0.setPivotZ(0);
        rotate0.setPivotX(200);
        //rotate0.setPivotY(0);
        rotate0.setAxis(Rotate.X_AXIS);
        tri1.getTransforms().add(rotate0);
        rotate0.angleProperty().bind(sideAngle);
        Rotate why = new Rotate();
        why.setAngle(-60);
        tri1.getTransforms().add(why);
        /************************************/


        TriangleEquilateral xx1 = new TriangleEquilateral(100.0);
        Polygon tri2 = xx1.createTriangleEquilateral();
        tri2.setTranslateX(300);
        tri2.setTranslateY(213.2974);
        Translate what = new Translate();
        what.setX(50);
        tri2.getTransforms().add(what);
        tri2.setFill(Color.CYAN);
        tri2.setStroke(Color.BLACK);

        Rotate rotate = new Rotate();
        //rotate.setPivotX(300);
        rotate.setPivotY(86.7);
        //rotate.setPivotZ(0);
        rotate.setAxis(Rotate.X_AXIS);
        tri2.getTransforms().add(rotate);
        rotate.angleProperty().bind(side1Angle);
        /***********************************/


        TriangleEquilateral xx3 = new TriangleEquilateral(100.0);
        Polygon tri3 = xx3.createTriangleEquilateral();
        tri3.setTranslateX(300);
        tri3.setTranslateY(300);
        tri3.setFill(Color.web("f33882"));
        tri3.setStroke(Color.BLACK);


        Rotate rotate1 = new Rotate();
        rotate1.setAxis(Rotate.Y_AXIS);
        //rotate1.setPivotX(0);
        //rotate1.setPivotY(300);
        rotate1.setPivotZ(0);
        tri3.getTransforms().add(rotate1);
        rotate1.angleProperty().bind(side2Angle);
        Rotate why1 = new Rotate();
        why1.setAngle(30);
        tri3.getTransforms().add(why1);


        TriangleEquilateral xx4 = new TriangleEquilateral(100.0);
        Polygon tri4 = xx4.createTriangleEquilateral();
        tri4.setTranslateX(400);
        tri4.setTranslateY(300);
        tri4.setFill(Color.web("f33882"));
        tri4.setStroke(Color.BLACK);

        Rotate rotate3 = new Rotate();
        // rotate3.setPivotX(0);
        //rotate3.setPivotY(200);
        rotate3.setPivotZ(0);
        rotate3.setAxis(Rotate.Y_AXIS);
        tri4.getTransforms().add(rotate3);
        rotate3.angleProperty().bind(side3Angle);
        Rotate why2 = new Rotate();
        why2.setAngle(-30);
        tri4.getTransforms().add(why2);


        Rectangle rectangle2 = new Rectangle(100, 100);
        rectangle2.setX(300);
        rectangle2.setY(300);
        rectangle2.setStroke(Color.BLACK);
        rectangle2.setFill(Color.PINK);


        final Rotate rootRotate = RotateBuilder.create()

                .axis(Rotate.X_AXIS)
                .build();
        rootRotate.angleProperty().bind(rootAngle);

        Group root5 = new Group();
        root5.setStyle("-fx-background-color: white;");
        root5.getChildren().setAll(rectangle2, tri1, tri2, tri4, tri3);
        root5.setLayoutY(210);
        root5.setLayoutX(370);


        scene.setCamera(cam);
        root5.getTransforms().add(rootRotate);
        scene.setRoot(root5);


    }

    private void animate44() {
        TimelineBuilder.create()
                .keyFrames(
                        new KeyFrame(
                                Duration.seconds(0),
                                new KeyValue(sideAngle, 00),
                                new KeyValue(side1Angle, 00),
                                new KeyValue(side2Angle, 0),
                                new KeyValue(side3Angle, 0),
                                new KeyValue(side4Angle, 0),
                                new KeyValue(side5Angle, 0),
                                new KeyValue(side6Angle, 0),
                                new KeyValue(side7Angle, 0),

                                new KeyValue(rootAngle, 0)
                        ),

                        new KeyFrame(
                                Duration.seconds(2),
                                new KeyValue(sideAngle, -126.30),
                                new KeyValue(side1Angle, 126.30),
                                new KeyValue(side2Angle, 0),
                                new KeyValue(side3Angle, 0),
                                new KeyValue(side4Angle, 00),
                                new KeyValue(side5Angle, 0),
                                new KeyValue(side6Angle, 0),
                                new KeyValue(side7Angle, 0),

                                new KeyValue(rootAngle, -10)
                        ),

                        new KeyFrame(
                                Duration.seconds(4),
                                new KeyValue(sideAngle, -126.3),
                                new KeyValue(side1Angle, 126.3),
                                new KeyValue(side2Angle, -126),
                                new KeyValue(side3Angle, 124.5),
                                new KeyValue(side4Angle, 00),
                                new KeyValue(side5Angle, 00),
                                new KeyValue(side6Angle, 0),
                                new KeyValue(side7Angle, 0),

                                new KeyValue(rootAngle, -20)
                        ),

                        new KeyFrame(
                                Duration.seconds(6),
                                new KeyValue(sideAngle, -126.8),
                                new KeyValue(side1Angle, 126.8),
                                new KeyValue(side2Angle, -124.2),
                                new KeyValue(side3Angle, 124.5),
                                new KeyValue(side4Angle, 00),
                                new KeyValue(side5Angle, 00),
                                new KeyValue(side6Angle, 0),
                                new KeyValue(side7Angle, 0),

                                new KeyValue(rootAngle, -40)
                        ),
                        new KeyFrame(
                                Duration.seconds(8),
                                new KeyValue(sideAngle, -126.8),
                                new KeyValue(side1Angle, 126.8),
                                new KeyValue(side2Angle, -124.2),
                                new KeyValue(side3Angle, 124.5),
                                new KeyValue(side4Angle, 00),
                                new KeyValue(side5Angle, 00),
                                new KeyValue(side6Angle, 0),
                                new KeyValue(side7Angle, 0),

                                new KeyValue(rootAngle, -40)
                        ),
                        new KeyFrame(
                                Duration.seconds(10),
                                new KeyValue(sideAngle, 0),
                                new KeyValue(side1Angle, 0),
                                new KeyValue(side2Angle, 0),
                                new KeyValue(side3Angle, 0),
                                new KeyValue(side4Angle, 0),
                                new KeyValue(side5Angle, 0),
                                new KeyValue(side6Angle, 0),
                                new KeyValue(side7Angle, 0),

                                new KeyValue(rootAngle, 0)
                        )
                )
                .build().playFromStart();
    }
    /*************************************************/
    /*************************************************/

    /*************************************************/
    /**
     * *********************************************
     */

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }
}

class TriangleEquilateral extends Polygon {


    private final DoubleProperty width = new DoublePropertyBase() {

        @Override
        public void invalidated() {
            impl_markDirty(DirtyBits.NODE_GEOMETRY);
            impl_geomChanged();
        }

        @Override
        public Object getBean() {
            return TriangleEquilateral.this;
        }

        @Override
        public String getName() {
            return "width";
        }
    };
    private final DoubleProperty height = new DoublePropertyBase() {

        @Override
        public void invalidated() {
            impl_markDirty(DirtyBits.NODE_GEOMETRY);
            impl_geomChanged();
        }

        @Override
        public Object getBean() {
            return TriangleEquilateral.this;
        }

        @Override
        public String getName() {
            return "height";
        }
    };
    private Polygon shape = new Polygon();
    private DoubleProperty x;
    private DoubleProperty y;


    public TriangleEquilateral() {


    }


    public TriangleEquilateral(double height) {
        setHeight(height);
    }

    public TriangleEquilateral(double height, Paint fill) {
        setHeight(height);
        setFill(fill);
    }

    public TriangleEquilateral(double x, double y, double height) {
        this(height);
        setX(x);
        setY(y);
    }

    public final double getX() {
        return x == null ? 0.0 : x.get();
    }

    public final void setX(double value) {
        if (x != null || value != 0.0) {
            xProperty().set(value);
        }
    }

    public final DoubleProperty xProperty() {
        if (x == null) {
            x = new DoublePropertyBase() {

                @Override
                public void invalidated() {
                    impl_markDirty(DirtyBits.NODE_GEOMETRY);
                    impl_geomChanged();
                }

                @Override
                public Object getBean() {
                    return TriangleEquilateral.this;
                }

                @Override
                public String getName() {
                    return "x";
                }
            };
        }
        return x;
    }

    public Polygon createTriangleEquilateral() {

        shape.getPoints().addAll(
                0.0, 0.0,
                getHeight() / 2.0d, getHeight() * (Math.sqrt(3) / (double) 2),
                -getHeight() / 2.0d, getHeight() * (Math.sqrt(3) / (double) 2)
        );


        return (getShape());

    }

    public void setTranslationX(double x) {
        shape.setTranslateX(x);
    }

    public void setTranslationY(double y) {
        shape.setTranslateY(y);
    }

    public final double getY() {
        return y == null ? 0.0 : y.get();
    }

    public final void setY(double value) {
        if (y != null || value != 0.0) {
            yProperty().set(value);
        }
    }

    public final DoubleProperty yProperty() {
        if (y == null) {
            y = new DoublePropertyBase() {

                @Override
                public void invalidated() {
                    impl_markDirty(DirtyBits.NODE_GEOMETRY);
                    impl_geomChanged();
                }

                @Override
                public Object getBean() {
                    return TriangleEquilateral.this;
                }

                @Override
                public String getName() {
                    return "y";
                }
            };
        }
        return y;
    }

    public final double getWidth() {
        return width.get();
    }

    public final void setWidth(double value) {
        width.set(value);
    }

    public final DoubleProperty widthProperty() {
        return width;
    }

    public final double getHeight() {
        return height.get();
    }

    public final void setHeight(double value) {
        height.set(value);
    }

    public final DoubleProperty heightProperty() {
        return height;
    }


    /**
     * @return the shape
     */
    public Polygon getShape() {
        return shape;
    }


    /**
     * @param shape the shape to set
     */
    public void setShape(Polygon shape) {
        this.shape = shape;
    }


}

class Hexagon extends Polygon {

    private final DoubleProperty height = new DoublePropertyBase() {

        @Override
        public void invalidated() {
            impl_markDirty(DirtyBits.NODE_GEOMETRY);
            impl_geomChanged();
        }

        @Override
        public Object getBean() {
            return Hexagon.this;
        }

        @Override
        public String getName() {
            return "height";
        }
    };
    Polygon shape = new Polygon();
    private DoubleProperty x;
    private DoubleProperty y;

    public Hexagon() {

    }

    public Hexagon(double height) {
        setHeight(height);
    }


    public Hexagon(double height, Paint fill) {
        setHeight(height);
        setFill(fill);
    }

    public Hexagon(double x, double y, double height) {
        setHeight(height);
        setX(x);
        setY(y);
    }

    public final double getX() {
        return x == null ? 0.0 : x.get();
    }

    public final void setX(double value) {
        if (x != null || value != 0.0) {
            xProperty().set(value);
        }
    }

    public final DoubleProperty xProperty() {
        if (x == null) {
            x = new DoublePropertyBase() {

                @Override
                public void invalidated() {
                    impl_markDirty(DirtyBits.NODE_GEOMETRY);
                    impl_geomChanged();
                }

                @Override
                public Object getBean() {
                    return Hexagon.this;
                }

                @Override
                public String getName() {
                    return "x";
                }
            };
        }
        return x;
    }

    public Polygon createHexagon() {

        shape.getPoints().addAll(
                100.0, 0.0,
                getHeight() + 100.0, 0.0,
                100.0 + getHeight() + (getHeight() / Math.sqrt(3)), getHeight() * Math.sqrt(((double) 2) / ((double) 3)),
                getHeight() + 100.0, getHeight() * Math.sqrt(((double) 2) / ((double) 3)) * 2,
                100.0, getHeight() * Math.sqrt(((double) 2) / ((double) 3)) * 2,
                100.0 - getHeight() / Math.sqrt(3), getHeight() * Math.sqrt(((double) 2) / ((double) 3))
        );

        return (getShape());
    }

    public void setTranslationX(double x) {
        shape.setTranslateX(x);
    }

    public void setTranslationY(double y) {
        shape.setTranslateY(y);
    }

    public final double getY() {
        return y == null ? 0.0 : y.get();
    }

    public final void setY(double value) {
        if (y != null || value != 0.0) {
            yProperty().set(value);
        }
    }

    public final DoubleProperty yProperty() {
        if (y == null) {
            y = new DoublePropertyBase() {

                @Override
                public void invalidated() {
                    impl_markDirty(DirtyBits.NODE_GEOMETRY);
                    impl_geomChanged();
                }

                @Override
                public Object getBean() {
                    return Hexagon.this;
                }

                @Override
                public String getName() {
                    return "y";
                }
            };
        }
        return y;
    }

    public final double getHeight() {
        return height.get();
    }

    public final void setHeight(double value) {
        height.set(value);
    }

    public final DoubleProperty heightProperty() {
        return height;
    }

    /**
     * @return the shape
     */
    public Polygon getShape() {
        return shape;
    }

    /**
     * @param shape the shape to set
     */
    public void setShape(Polygon shape) {
        this.shape = shape;
    }

}